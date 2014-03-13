package com.jitong.common.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.jitong.JitongConstants;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.ActionUtil;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.console.service.UserService;
import com.jitong.sms.domain.SMSSecurityCode;
import com.jitong.sms.service.SMSService;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements ServletRequestAware, SessionAware {

	private static Logger logger = Logger.getLogger(LoginAction.class);
	private String username;
	private String password;
	private String securitycode;
	private String redirect;

	private String isInIpRange;

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

	public String generateSMSSecurityCode() throws Exception {
		UserService userService = new UserService();
		//User u = userService.findUserByLoginName(username);
		User user = userService.verifyLogon(username, password, session);
		if (user == null) {
			addActionError("请输入正确的用户名、密码后再获取验证码。");
			return INPUT;
		}
		SMSService smsservice = new SMSService();
		smsservice.generateSMSSecurityCode(user, 30);
		addActionError("短信验证码已经发送至您手机，请查收。");
		return INPUT;
	}

	@Override
	public String execute() throws Exception {
		String ip = ActionUtil.getRemoteAddr(request);
		if (SysCache.isPermittedIP(ip)) {
			isInIpRange = "1";
		} else {
			isInIpRange = "0";
		}
		return INPUT;
	}
/**
 * external login
 * @return
 * @throws Exception
 */
	public String elogin() throws Exception {
		String ip = ActionUtil.getRemoteAddr(request);
		UserService userService = new UserService();
		if (!JitongConstants.ADMIN.equals(username)) {
			if ("1".equals(JitongConstants.SMS_SECURITY_CODE_ENABLED)) {
				User u = userService.findUserByLoginName(username);
				if (u == null) {
					addActionError("用户不存在");
					return INPUT;
				}
				SMSSecurityCode code = SysCache.getSMSSecurityCode(u.getId());
				if (StringUtil.isEmpty(securitycode) || code == null || !code.isValid() || !securitycode.equals(code.getCode())) {
					addActionError("短信验证码错误");
					return INPUT;
				}
			}
		}
		try {
			User user = userService.verifyLogon(username, password, session);
			if (user == null) {
				addActionError("用户名或密码错误。");
				return INPUT;
			}
			user.setLastLoginIp(ip);
			user.setLastLoginTime(DateUtil.getCurrentTime());
			logger.debug(getUsername());
			session.put(JitongConstants.USER, user);
		} catch (JTException e) {
			String errorMsg = e.getMessage();
			addActionError(errorMsg);
			return INPUT;
		}
		String categoryId = (String) session.get("categoryId");
		logger.debug(categoryId);
		if (categoryId != null && categoryId.indexOf('.') > 0) {
			redirect = "/" + (categoryId.replace('.', '/')) + "/index.do";
			logger.debug(redirect);
			return "redirect";
		}
		return SUCCESS;
	}
/**
 * internal login
 * @return
 * @throws Exception
 */
	public String ilogin() throws Exception {
		if (StringUtil.isEmpty(getUsername())) {
			addActionError("用户名不能为空。");
			return INPUT;
		}
		String ip = ActionUtil.getRemoteAddr(request);
		UserService userService = new UserService();
		try {
			User user = userService.verifyLogon(username, password, session);
			if (user == null) {
				addActionError("用户名或密码错误。");
				return INPUT;
			}
			user.setLastLoginIp(ip);
			user.setLastLoginTime(DateUtil.getCurrentTime());
			logger.debug(getUsername());
			session.put(JitongConstants.USER, user);
		} catch (JTException e) {
			String errorMsg = e.getMessage();
			addActionError(errorMsg);
			return INPUT;
		}
		String categoryId = (String) session.get("categoryId");
		logger.debug(categoryId);
		if (categoryId != null && categoryId.indexOf('.') > 0) {
			redirect = "/" + (categoryId.replace('.', '/')) + "/index.do";
			logger.debug(redirect);
			return "redirect";
		}
		return SUCCESS;

	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public String getSecuritycode() {
		return securitycode;
	}

	public void setSecuritycode(String securitycode) {
		this.securitycode = securitycode;
	}

	public String getIsInIpRange() {
		return isInIpRange;
	}

	public void setIsInIpRange(String isInIpRange) {
		this.isInIpRange = isInIpRange;
	}

}
