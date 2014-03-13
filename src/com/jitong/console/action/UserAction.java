package com.jitong.console.action;

import java.util.ArrayList;
import java.util.List;

import com.jitong.JitongConstants;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.console.service.UserService;
import com.opensymphony.xwork2.Preparable;

public class UserAction extends JITActionBase implements Preparable {
	// private UserSearchForm searchForm;
	private String qryName;
	private String qryNumber;
	private String qryUnitName;
	private String qryDeptName;
	private String qryZhiwu;

	public String getQryNumber() {
		return qryNumber;
	}

	public void setQryNumber(String qryNumber) {
		this.qryNumber = qryNumber;
	}

	public String getQryUnitName() {
		return qryUnitName;
	}

	public void setQryUnitName(String qryUnitName) {
		this.qryUnitName = qryUnitName;
	}

	public String getQryDeptName() {
		return qryDeptName;
	}

	public void setQryDeptName(String qryDeptName) {
		this.qryDeptName = qryDeptName;
	}

	public String getQryZhiwu() {
		return qryZhiwu;
	}

	public void setQryZhiwu(String qryZhiwu) {
		this.qryZhiwu = qryZhiwu;
	}

	private static UserService userService;
	private User user;
	private List<User> users;
	private List<String> genderList;
	private User loginUser;

	private String confirmpassword;

	public void prepare() throws JTException {
		if (userService == null) {
			userService = new UserService();
		}
		if (user != null && user.getId() != null) {
			user = userService.findUser(user.getId());
		}
		genderList = new ArrayList<String>();
		genderList.add("男");
		genderList.add("女");
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		return INPUT;

	}

	public String delete() throws JTException {
		userService.deleteUser(user.getId());
		SysCache.reloadUserById(user.getId(), SysCache.OPER_DELETE);
		return list();
	}

	public String emptyPassword() throws Exception {
		User u = userService.findUser(user.getId());
		u.setPassword(StringUtil.md5(""));
		userService.updateUser(u);
		SysCache.reloadUserById(user.getId(), SysCache.OPER_UPDATE);
		addActionError("清除密码成功。");
		return list();
	}

	public String save() throws Exception {
		try {
			// new
			if (user.getId() == null || "".equals(user.getId())) {
				if (!StringUtil.isEmpty(user.getPassword()) || !StringUtil.isEmpty(confirmpassword)) {
					if (!user.getPassword().equals(confirmpassword)) {
						addFieldError("confirmpassword", "两次输入的密码不匹配！");
						return INPUT;
					}
				}
				user.setPassword(StringUtil.md5(user.getPassword()));
				userService.insertUser((User) session.get(JitongConstants.USER), user);
				SysCache.reloadUserById(user.getId(), SysCache.OPER_ADD);
				// modify
			} else {
				// password is not empty
				if (!StringUtil.isEmpty(user.getPassword())) {
					if (!user.getPassword().equals(confirmpassword)) {
						addFieldError("confirmpassword", "两次输入的密码不匹配！");
						return INPUT;
					}

					user.setPassword(StringUtil.md5(user.getPassword()));

					// password is empty, no change
				} else {
					String oldpassword = userService.findUser(user.getId()).getPassword();
					user.setPassword(oldpassword);
				}
				userService.updateUser(user);
				SysCache.reloadUserById(user.getId(), SysCache.OPER_UPDATE);
			}
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getCategoryId() {
		return "console.user";
	}

	public List<String> getGenderList() {
		return genderList;
	}

	public void setGenderList(List<String> genderList) {
		this.genderList = genderList;
	}

	/*
	 * public UserSearchForm getSearchForm() { return searchForm; }
	 * 
	 * public void setSearchForm(UserSearchForm searchForm) { this.searchForm =
	 * searchForm; }
	 */

	/*
	 * public String getListHQL(ArrayList<Object> params) throws JTException {
	 * Class<?> domainClass = ActionUtil.retireDomainClassNameFromAction(this);
	 * String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params); String
	 * hql = "from " + domainClass.getName() + " me " + hqlsufix; return hql; }
	 */

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "";
		loginUser = (User) session.get(JitongConstants.USER);
		// if login user is not admin, check person previlige
		if (!JitongConstants.ADMIN.equals(loginUser.getLoginName())) {
			hql = "select distinct person from User u, UserRole ur, RolePerson rp, User person where u.id = ur.userId and ur.roleId =  rp.roleId and rp.personId=person.id and u.loginName ='"
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
		if (!StringUtil.isEmpty(qryUnitName) && !"null".equals(qryUnitName)) {
			hql += " and person.unit = '" + qryUnitName + "'";
		}
		if (!StringUtil.isEmpty(qryDeptName) && !"null".equals(qryDeptName)) {
			hql += " and person.dept = '" + qryDeptName + "'";
		}
		if (!StringUtil.isEmpty(qryZhiwu) && !"null".equals(qryZhiwu)) {
			hql += " and person.zhiwu like '%" + qryZhiwu + "%'";
		}
		return hql;
	}

	public String getQryName() {
		return qryName;
	}

	public void setQryName(String qryName) {
		this.qryName = qryName;
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

}
