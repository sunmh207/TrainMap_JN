package com.jitong.common.action;

import org.apache.log4j.Logger;

import com.jitong.common.util.CategoryUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CategoryItemAutowiringInterceptor extends AbstractInterceptor {
	private static Logger logger= Logger.getLogger(CategoryItemAutowiringInterceptor.class);
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		/*Object action = actionInvocation.getAction();
		logger.debug("action class: "+action.getClass());
		if (action instanceof JITActionBase) {
			JITActionBase jtAction = (JITActionBase) action;
			CategoryUtil.injectCategoryInfo(jtAction);
			logger.debug(jtAction.getSession());
		}*/
		return actionInvocation.invoke();
	}

}
