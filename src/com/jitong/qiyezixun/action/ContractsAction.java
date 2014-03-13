package com.jitong.qiyezixun.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.jitong.JitongConstants;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.console.service.UserService;
import com.opensymphony.xwork2.Preparable;

public class ContractsAction extends JITActionBase implements Preparable {

	private static UserService userService;
	private List<User> users;
	private String qryName = "";
	private String qryNumber = "";
	public String businessClass = "User";

	private List<String> unitList;
	private String qryUnitName;
	private List<String> deptList;
	private String qryDeptName;

	public void prepare() throws JTException {
		if (userService == null) {
			userService = new UserService();
		}

		unitList = new ArrayList<String>();
		Iterator unitIt = SysCache.unitDeptMap.keySet().iterator();
		while (unitIt.hasNext()) {
			String unitName = (String) unitIt.next();
			unitList.add(unitName);
		}

		deptList = new ArrayList<String>();
		if (!StringUtil.isEmpty(qryUnitName)) {
			Set s = SysCache.unitDeptMap.get(qryUnitName);
			if (s != null) {
				Iterator deptIt = s.iterator();
				while (deptIt.hasNext()) {
					String deptName = (String) deptIt.next();
					deptList.add(deptName);
				}
			}
		}
	}

	public String list() throws JTException {
		return super.list();
	}

	public String getListHQL(ArrayList<Object> params) {
		// String hql= "from User person where person.name like '%" + qryName +
		// "%'";
		String hql = "";
		User loginUser = (User) session.get(JitongConstants.USER);
		// if login user is not admin, check person previlige
		if (!JitongConstants.ADMIN.equals(loginUser.getLoginName())) {
			hql = " select distinct person from User u, UserRole ur, RolePerson rp, User person where u.id = ur.userId and ur.roleId =  rp.roleId and rp.personId=person.id and u.loginName ='"
					+ loginUser.getLoginName() + "'";
		} else {
			hql = " from User person where 1=1 ";
		}
		if (!StringUtil.isEmpty(qryName) && !"null".equals(qryName)) {
			hql += " and person.name like '%" + qryName + "%'";
		}
		if (!StringUtil.isEmpty(qryNumber) && !"null".equals(qryNumber)) {
			hql += " and person.phoneNumber like '%" + qryNumber + "%'";
		}

		String unitDept = composeUnitDept(qryUnitName, qryDeptName);
		if (!StringUtil.isEmpty(unitDept) && !"null".equals(unitDept)) {
			hql += " and person.unitDept like '" + unitDept + "%'";
		}
		return hql;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getQryName() {
		return qryName;
	}

	public void setQryName(String qryName) {
		this.qryName = qryName;
	}

	public String getQryNumber() {
		return qryNumber;
	}

	public void setQryNumber(String qryNumber) {
		this.qryNumber = qryNumber;
	}

	public String getCategoryId() {
		return "qiyezixun.contracts";
	}

	public List<String> getUnitList() {
		return unitList;
	}

	public void setUnitList(List<String> unitList) {
		this.unitList = unitList;
	}

	public String getQryUnitName() {
		return qryUnitName;
	}

	public void setQryUnitName(String qryUnitName) {
		this.qryUnitName = qryUnitName;
	}

	public List<String> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<String> deptList) {
		this.deptList = deptList;
	}

	public String getQryDeptName() {
		return qryDeptName;
	}

	public void setQryDeptName(String qryDeptName) {
		this.qryDeptName = qryDeptName;
	}

	private String composeUnitDept(String unitName, String deptName) {
		String unitDept = null;
		if (!StringUtil.isEmpty(unitName) && !StringUtil.isEmpty(deptName)) {
			unitDept = unitName + "-" + deptName;
		} else if (!StringUtil.isEmpty(unitName)) {
			unitDept = unitName;
		} else if (!StringUtil.isEmpty(deptName)) {
			unitDept = deptName;
		}
		return unitDept;
	}
}
