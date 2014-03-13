package com.jitong.wap.action;

import java.util.Map;

import org.apache.log4j.Logger;

import com.jitong.JitongConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class WapAuthInterceptor extends AbstractInterceptor {
	private static Logger logger = Logger.getLogger(WapAuthInterceptor.class);
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		logger.debug(session);
		if(session.get(JitongConstants.USER)==null){
			return "waplogin";
		}
		return actionInvocation.invoke();
	}

}
