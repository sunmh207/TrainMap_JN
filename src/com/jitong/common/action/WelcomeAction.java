package com.jitong.common.action;

import org.apache.log4j.Logger;

import com.jitong.JitongConstants;
import com.opensymphony.xwork2.Action;



public class WelcomeAction extends JITActionBase {
	private static Logger logger = Logger.getLogger(WelcomeAction.class);
	@Override
	public String execute() throws Exception {
		logger.debug("in welcome action.");
		logger.debug(this.session);
		if(session.get(JitongConstants.USER)==null){
			return Action.LOGIN;
		}
		return SUCCESS;
	}

}
