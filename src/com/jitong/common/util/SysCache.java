package com.jitong.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.hibernate.Session;

import com.jitong.JitongConstants;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.console.domain.LimitIP;
import com.jitong.console.domain.Menu;
import com.jitong.console.domain.Role;
import com.jitong.console.domain.RolePerson;
import com.jitong.console.domain.User;
import com.jitong.console.domain.UserRole;
import com.jitong.console.service.RoleMenuService;
import com.jitong.console.service.RoleService;
import com.jitong.console.service.UserRoleService;
import com.jitong.console.service.UserService;
import com.jitong.sms.domain.SMSSecurityCode;
import com.jitong.sms.service.SMSService;

public class SysCache {
	public static final int OPER_ADD = 0;
	public static final int OPER_UPDATE = 1;
	public static final int OPER_DELETE = 2;

	private static Session session;
	private static boolean closeSession = false;
	public static final Map<String, User> userMap = new HashMap<String, User>();// userId->User
	public static final Map<String, User> phoneUserMap = new HashMap<String, User>();// phoneNumber->User
	public static final Map<String, Role> roleMap = new HashMap<String, Role>();// roleId->Role
	public static final Map<String, List<Menu>> moduleMenuMap = new HashMap<String, List<Menu>>();// moduleId->List<Menu>
	//public static final Map<String, List<Role>> userRolesMap = new HashMap<String, List<Role>>();// userId->List<Role>(The
																									// roles
																									// which
																									// this
																									// user
																									// belongs
																									// to)
	public static final Map<String, Set<User>> personMgrMap = new HashMap<String, Set<User>>();// person->
																								// Manager
																								// set

	public static final Vector<String> G = new Vector<String>();

	/**
	 * <unitName,List<deptName>>
	 */
	public static final Map<String, Set<String>> unitDeptMap = new HashMap<String, Set<String>>();

	private static final Map<String, LimitIP> IPMap = new HashMap<String, LimitIP>();

	private static final Map<String, SMSSecurityCode> SMSSecurityCodMap = new HashMap<String, SMSSecurityCode>();// User.Id->SMSSecurityCode

	public synchronized static void loadSysCache() throws JTException {
		try {
			closeSession = false;
			SysCache.loadUserMap();
			SysCache.loadPhoneUserMap();
			SysCache.loadRoleMap();
			SysCache.loadModuleMenuMap();
			SysCache.loadUnitDept(SysCache.userMap);
			// SysCache.loadUserRolesMap(SysCache.userMap);
			SysCache.loadLimitIP();
			SysCache.loadScheduleSMSReminder();
			SysCache.loadScheduleMMSReminder();
			SysCache.loadPersonMgrMap();
			SysCache.loadADMIN();
		} catch (Exception e) {
			e.printStackTrace();
			throw new JTException(e.getMessage(), e, SysCache.class);
		} finally {
			session.close();
			session = null;
			closeSession = true;
		}
	}

	private synchronized static void checkSession() throws JTException {
		if (session == null || !session.isConnected()) {
			try {
				session = DBtools.getExclusiveSession();
			} catch (JTException e) {
				throw e;
			}
		}
	}

	public static void loadADMIN() throws JTException{
		checkSession();
		try {
			UserService us = new UserService(session);
			JitongConstants.ADMIN_USER=us.findUserByLoginName(JitongConstants.ADMIN); 
		} catch (Exception e) {
			e.printStackTrace();
			throw new JTException("Load role failed.", e, SysCache.class);
		}
	}
	
	public synchronized static void loadUserMap() throws JTException {
		checkSession();
		try {
			userMap.clear();
			UserService userService = new UserService(session);
			List<User> list = userService.queryUsers();
			int count = list.size();
			if (count > 0) {
				for (int i = 0; i < count; i++) {
					User user = list.get(i);
					userMap.put(user.getId(), user);
				}
			}

			if (closeSession) {
				session.close();
				session = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new JTException("Load user failed.", e, SysCache.class);
		}
	}

	public synchronized static void loadPhoneUserMap() throws JTException {
		checkSession();
		try {
			phoneUserMap.clear();
			UserService userService = new UserService(session);
			List<User> list = userService.queryUsers();
			int count = list.size();
			if (count > 0) {
				for (int i = 0; i < count; i++) {
					User user = list.get(i);
					phoneUserMap.put(user.getPhoneNumber(), user);
					phoneUserMap.put("86" + user.getPhoneNumber(), user);
					phoneUserMap.put("+86" + user.getPhoneNumber(), user);
				}
			}

			if (closeSession) {
				session.close();
				session = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new JTException("Load user failed.", e, SysCache.class);
		}
	}

	public synchronized static void loadRoleMap() throws JTException {
		checkSession();
		try {
			roleMap.clear();
			RoleService roleService = new RoleService(session);
			List<Role> list = roleService.queryRoles();
			int count = list.size();
			if (count > 0) {
				for (int i = 0; i < count; i++) {
					Role role = list.get(i);
					roleMap.put(role.getRoleId(), role);
				}
			}

			if (closeSession) {
				session.close();
				session = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new JTException("Load role failed.", e, SysCache.class);
		}
	}

	// *********userRoles Map*************
	/*public synchronized static void loadUserRolesMap(Map<String, User> usrMap) throws JTException {
		checkSession();
		try {
			userRolesMap.clear();
			UserRoleService urService = new UserRoleService(session);
			Iterator it = usrMap.keySet().iterator();
			while (it.hasNext()) {
				String userId = (String) it.next();
				userRolesMap.put(userId, urService.queryRoleByUserId(userId));
			}
			if (closeSession) {
				session.close();
				session = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new JTException("Load role failed.", e, SysCache.class);
		}
	}*/

	/**
	 * Update(add/delete) userRolesMap
	 * 
	 * @param userId
	 * @param roleId
	 * @param action
	 * @throws JTException
	 */
	/*public synchronized static void updateUserRolesMap(String userId, String roleId, String action) throws JTException {
		List<Role> rList = userRolesMap.get(userId);
		if ("add".equals(action)) {
			if (rList == null) {
				rList = new ArrayList<Role>();
			}
			Role r = roleMap.get(roleId);
			rList.add(r);
			//userRolesMap.put(userId, rList);
		} else if ("delete".equals(action)) {
			if (rList != null) {
				Role currentR = roleMap.get(roleId);
				if (currentR == null)
					return;

				for (Role r : rList) {
					r.getRoleId().equals(currentR.getRoleId());
					rList.remove(r);
					return;
				}
			}
		}

		// TODO
	}*/

	// *********personMgrMap**********
	private synchronized static void loadPersonMgrMap() throws JTException {
		try {
			checkSession();
			personMgrMap.clear();
			BaseService service = new BaseService(session);
			// select * from jtmobile_user_role_r ur, jtmobile_role_person_r rp
			// where ur.role_id=rp.role_id and
			// ur.user_id='9DD8-AB84-4762-9C9F-C785802CAA43'
			List list = service.queryByHql("select ur,rp from  UserRole ur, RolePerson rp where ur.roleId=rp.roleId order by rp.personId");
			if (list == null || list.isEmpty())
				return;
			for (Object o : list) {
				Object[] ary = (Object[]) o;
				UserRole ur = (UserRole) ary[0];
				RolePerson rp = (RolePerson) ary[1];

				String mgrId = ur.getUserId();
				String personId = rp.getPersonId();

				Set<User> set = personMgrMap.get(personId);
				if (set == null) {
					set = new HashSet<User>();
					personMgrMap.put(personId, set);
				}
				User manager = SysCache.interpertUser(mgrId);
				set.add(manager);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new JTException("Load personMgrMap failed.", e, SysCache.class);
		}
	}
	/**
	 * Reload a person's managers;
	 * when add/remove user from role,add/remove person from role, call this method.
	 * @param personId
	 * @throws JTException
	 */
	public synchronized static void reloadPersonMgrMap(String personId,Session s) throws JTException {
		try {
			BaseService service = new BaseService(s);
			List list = service.queryByHql("select ur from  UserRole ur, RolePerson rp where ur.roleId=rp.roleId and rp.personId='"+personId+"'");
			Set<User> set = new HashSet<User>();
			personMgrMap.put(personId, set);
			
			if (list == null || list.isEmpty())
				return;
				
			for (Object o : list) {
				UserRole ur = (UserRole) o;
				String mgrId = ur.getUserId();
				User manager = SysCache.interpertUser(mgrId);
				set.add(manager);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new JTException("Load personMgrMap failed.", e, SysCache.class);
		}
	}
	
	public static Set<User> getManagers(String personId){
		return personMgrMap.get(personId);
	}
	/**
	 * 
	 * @param personId
	 * @return  mgrid1,mgrid2,...
	 */
	public static String getManagerIds(String personId){
		String managerIds=JitongConstants.ADMIN_USER.getId();
		//get person's managers
		Set<User> managers = SysCache.getManagers(personId);
		if(managers!=null){
			Iterator<User> it =managers.iterator();
			while(it.hasNext()){
				User u = it.next();
				if(u!=null){
					managerIds +=","+u.getId();
				}
			}
		}
		return managerIds;
	}
	
	// *********userRoles Map end*************
	public synchronized static void loadModuleMenuMap() throws JTException {
		checkSession();
		try {
			moduleMenuMap.clear();
			RoleMenuService service = new RoleMenuService(session);
			List<Menu> list = service.queryAllMenus();
			for (Menu menu : list) {
				String moduleId = menu.getModuleId();
				List<Menu> menuList = moduleMenuMap.get(moduleId);
				if (menuList == null) {
					menuList = new ArrayList<Menu>();
					menuList.add(menu);
					moduleMenuMap.put(moduleId, menuList);
				} else {
					menuList.add(menu);
				}
			}
			if (closeSession) {
				session.close();
				session = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new JTException("Load menu failed.", e, SysCache.class);
		}
	}

	/**
	 * 
	 * @param usrMap
	 */
	private synchronized static void loadUnitDept(Map<String, User> usrMap) throws JTException {
		checkSession();
		if (usrMap == null)
			return;
		Iterator it = usrMap.keySet().iterator();
		while (it.hasNext()) {
			String userId = (String) it.next();
			User user = usrMap.get(userId);
			String unitName = user.getUnit();
			if (StringUtil.isEmpty(unitName)) {
				continue;
			}
			String deptName = user.getDept();
			Set<String> deptSet = unitDeptMap.get(unitName);
			if (deptSet == null) {
				Set<String> s = new HashSet<String>();
				if (!StringUtil.isEmpty(deptName)) {
					s.add(deptName);
				}
				unitDeptMap.put(unitName, s);
			} else {
				if (!StringUtil.isEmpty(deptName)) {
					deptSet.add(deptName);
				}
			}
		}
	}

	public synchronized static void loadLimitIP() throws JTException {
		checkSession();
		try {
			IPMap.clear();
			BaseService service = new BaseService(session);
			List<Object> list = service.queryAll(LimitIP.class);
			int count = list.size();
			if (count > 0) {
				for (int i = 0; i < count; i++) {
					LimitIP limitIP = (LimitIP) list.get(i);
					IPMap.put(limitIP.getIp(), limitIP);
				}
			}

			if (closeSession) {
				session.close();
				session = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new JTException("Load LimitIP failed.", e, SysCache.class);
		}
	}

	public synchronized static void loadScheduleSMSReminder() throws JTException {
		checkSession();
		try {

			SMSService service = new SMSService(session);
			service.loadAndRunScheduleSMS();
			if (closeSession) {
				session.close();
				session = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new JTException("Load ScheduleSMS failed.", e, SysCache.class);
		}
	}

	public synchronized static void loadScheduleMMSReminder() throws JTException {
		checkSession();
		try {

			SMSService service = new SMSService(session);
			service.loadAndRunScheduleMMS();
			if (closeSession) {
				session.close();
				session = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new JTException("Load ScheduleMMS failed.", e, SysCache.class);
		}
	}

	public static User interpertUser(String userId) {
		return SysCache.userMap.get(userId);
	}

	public static User interpertUserByPhone(String phoneNumber) {
		return SysCache.phoneUserMap.get(phoneNumber);
	}

	public static String interpertUserName(String userId) {
		User user = interpertUser(userId);
		if (user != null) {
			return user.getName();
		} else {
			return null;
		}
	}

	public static String interpertUserNameByPhone(String phoneNumber) {
		User user = interpertUserByPhone(phoneNumber);
		if (user != null) {
			return user.getName();
		} else {
			return null;
		}
	}

	public static Role interpertRole(String roleId) {
		return SysCache.roleMap.get(roleId);
	}

	public static String interpertRoleName(String roleId) {
		Role role = interpertRole(roleId);
		if (role != null) {
			return role.getRoleName();
		} else {
			return null;
		}
	}

	public synchronized static void reloadUserById(String userId, int operType) throws JTException {
		try {
			switch (operType) {
			case OPER_ADD:
			case OPER_UPDATE:
				checkSession();
				UserService ps = new UserService(session);
				User u = ps.findUser(userId);
				if (u != null) {
					userMap.put(u.getId(), u);
					phoneUserMap.put(u.getPhoneNumber(), u);
					phoneUserMap.put("86" + u.getPhoneNumber(), u);
				}
				session.close();
				session = null;
				break;
			case OPER_DELETE:
				User usr = userMap.get(userId);
				if (usr != null) {
					phoneUserMap.remove(usr.getPhoneNumber());
					phoneUserMap.remove("86" + usr.getPhoneNumber());
				}
				userMap.remove(userId);
				break;
			}
		} catch (JTException e) {
			throw new JTException("Update userMap failed", e, SysCache.class);
		}
	}

	/**
	 * check if the ip is in the permitted ip list.
	 * 
	 * @param ip
	 * @return
	 */
	public static boolean isPermittedIP(String ip) {
		if (ip == null)
			return false;
		LimitIP limitIP = SysCache.IPMap.get(ip);
		if (limitIP == null)
			return false;

		return true;
	}

	public synchronized static void updatePermittedIP(LimitIP limitIp) {
		SysCache.IPMap.put(limitIp.getIp(), limitIp);
	}

	public synchronized static void removePermittedIP(String ip) {
		SysCache.IPMap.remove(ip);
	}

	public synchronized static void updateSMSSecurityCode(String userId, SMSSecurityCode code) {
		SysCache.SMSSecurityCodMap.put(userId, code);
	}

	public static SMSSecurityCode getSMSSecurityCode(String userId) {
		return SysCache.SMSSecurityCodMap.get(userId);
	}

	/*
	 * public static void main(String[] args) throws JTException {
	 * 
	 * SysCache.loadSysCache(); Iterator it = unitDeptMap.keySet().iterator();
	 * while (it.hasNext()) { String unitName = (String) it.next();
	 * System.out.println("===" + unitName); Set deptSet =
	 * unitDeptMap.get(unitName); Iterator dit = deptSet.iterator(); if (dit !=
	 * null) { while (dit.hasNext()) { System.out.println(dit.next() + ","); } }
	 * } }
	 */

	/*
	 * public static void main1(String[] args) { PrintStream out = System.out;
	 * out.print("var depts = ["); boolean fisrtUnit = true; for
	 * (Map.Entry<String, Set<String>> entry : unitDeptMap.entrySet()) {
	 * out.print(fisrtUnit ? "\n" : ",\n"); String unit = entry.getKey();
	 * out.print("\t\"" + unit + "\":["); boolean first = true; for (String dept
	 * : entry.getValue()) { if (!first) out.print(","); out.print("\"" + dept +
	 * "\""); first = false; } out.print("]"); } out.print("\n];"); }
	 */

	public static void main(String[] args) throws Exception {
		/*
		 * SysCache.loadModuleMenuMap(); for (String[] module :
		 * com.jitong.console.domain.Menu.MODULES) { if
		 * (moduleMenuMap.containsKey(module[0])) {
		 * System.out.println(module[1]);
		 * java.util.List<com.jitong.console.domain.Menu> menuList =
		 * moduleMenuMap.get(module[0]); for (com.jitong.console.domain.Menu
		 * menu : menuList) { System.out.println("-"+menu.getName()); } } }
		 */
		SysCache.loadPersonMgrMap();
	}
}
