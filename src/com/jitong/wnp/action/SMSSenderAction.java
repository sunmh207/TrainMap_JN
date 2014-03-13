package com.jitong.wnp.action;

import java.util.ArrayList;

import com.jitong.JitongConstants;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.sms.domain.SMS;
import com.jitong.sms.service.SMSService;

public class SMSSenderAction extends JITActionBase {
	private static String businessType = "smssender";

	public String save() throws Exception {
		SMSService smsService = new SMSService();
		String idString = request.getParameter("useridStr");
		String smsContent = request.getParameter("smsContent");
		String[] ids = StringUtil.parseString2Array(idString, ",");
		
		if(smsContent==null||smsContent.length()<=0){
			addActionError("短信不能为空");
			return INPUT;
		}
		
		if(ids.length<=0){
			addActionError("人员不能为空");
			return INPUT;
		}
		for (int i = 0; i < ids.length; i++) {

			// send SMS
			SMS sms = new SMS();
			sms.setBusinessType(businessType);
			// sms.setBusinessId();
			User u = SysCache.interpertUser(ids[i]);
			sms.setPhoneNumber(u.getPhoneNumber());
			sms.setRequestTime(DateUtil.getCurrentTime(JitongConstants.JT_DATETIME_FORMAT));
			sms.setSMSContent(smsContent);
			sms.setRecipientId(ids[i]);

			User loginUser = (User) session.get(JitongConstants.USER);
			//sms.setSenderInfo(loginUser.getName() + "-" + loginUser.getUnitDept() + "-" + loginUser.getLastLoginIp());
			sms.setSenderInfo(this.getLoginUserInfo());
			sms.setOperatorId(loginUser.getId());
			smsService.insertSMS(sms);
		}
		addActionError("发送成功！");
		return INPUT;
	}

	public String getCategoryId() {
		return "wnp.smssender";
	}
}
