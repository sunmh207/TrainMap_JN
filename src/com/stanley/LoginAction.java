package com.stanley;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.jitong.JitongConstants;
import com.jitong.common.util.ActionUtil;
import com.jitong.common.util.SysCache;
import com.opensymphony.xwork2.ActionSupport;
import com.stanley.pms.User;

public class LoginAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static Logger logger = Logger.getLogger(LoginAction.class);
	private String username;
	private String password;
	private String securitycode;
	private String redirect;

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

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	/**
	 * external login
	 * 
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		User user  = new User();
		user.setUsername(username);
		user.setPassword(password);
		try {
			if (!User.isAdmin(username, password)) {
				addActionError("用户名或密码错误。");
				return INPUT;
			}
			logger.debug(getUsername());
			session.put(JitongConstants.USER, user);
		} catch (Exception e) {
			String errorMsg = e.getMessage();
			addActionError(errorMsg);
			return INPUT;
		}

		return SUCCESS;
	}

	public String execute() throws Exception {
		return INPUT;
	}
	
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	
}