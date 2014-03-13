package com.jitong.zrrc.action;

import java.util.ArrayList;
import java.util.List;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.console.service.UserService;
import com.opensymphony.xwork2.Preparable;

public class ZRRCPopUserQueryAction extends JITActionBase implements Preparable {
	private String qryName;
	private static UserService userService;
	private List<User> users;
	private User loginUser;
	
	public void prepare() throws JTException {
		if (userService == null) {
			userService = new UserService();
		}
	}

	public String list() throws JTException {
		String pageSizeStr = request.getParameter("pageSize");
		if(pageSizeStr==null||pageSizeStr.trim().length()==0){
			setPageSize(500);
		}
		this.setBusinessClass("com.jitong.console.domain.User");

		return super.list();
	}

	public String select() throws JTException {
		String[] ids = request.getParameterValues("chk");
		if (ids != null && ids.length > 0) {
			String idStr = "";
			String nameStr = "";
			for (int i = 0; i < ids.length; i++) {
				if (i > 0) {
					idStr += ",";
					nameStr += ",";
				}
				idStr += ids[i];
				nameStr += SysCache.interpertUserName(ids[i]);
			}
			request.setAttribute("idStr", idStr);
			request.setAttribute("nameStr", nameStr);
		}
		return SUCCESS;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "from User person where person.id in (select r.personId from Relationship r) ";
		if (!StringUtil.isEmpty(qryName) && !"null".equals(qryName)) {
			hql += " and person.name like '%" + qryName + "%'";
		}
		return hql;
	}

	public String getQryName() {
		return qryName;
	}

	public void setQryName(String qryName) {
		this.qryName = qryName;
	}
	
}
