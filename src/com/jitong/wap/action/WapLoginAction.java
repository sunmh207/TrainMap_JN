package com.jitong.wap.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.jitong.JitongConstants;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.StringUtil;
import com.jitong.console.domain.User;
import com.jitong.console.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class WapLoginAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static Logger logger = Logger.getLogger(WapLoginAction.class);
	private String username;
	private String password;

	protected HttpServletRequest request;
	protected Map<String, Object> session;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() throws Exception {
		if (StringUtil.isEmpty(getUsername())) {
			addActionError("用户名不能为空。");
			return INPUT;
		}
		try {
			UserService userService = new UserService();
			User user = userService.verifyLogon(username, password, session);
			logger.debug(getUsername());
			session.put(JitongConstants.USER, user);
		} catch (JTException e) {
			String errorMsg = e.getMessage();
			addActionError(errorMsg);
			return INPUT;
		}
		return SUCCESS;

	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
}
