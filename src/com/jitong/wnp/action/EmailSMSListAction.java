package com.jitong.wnp.action;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.util.StringUtil;

public class EmailSMSListAction extends JITActionBase {
	private static String businessType = "mysmslist";

	public String getListHQL(ArrayList<Object> params) {
		String qryPhoneNumber = request.getParameter("qryPhoneNumber");
		String qryContent = request.getParameter("qryContent");
		return "from SMS s where s.SMSContent like '%邮件提醒%' and s.SMSContent like '%" + StringUtil.trim(qryContent) + "%' and s.phoneNumber like '%"
				+ StringUtil.trim(qryPhoneNumber) + "%' and s.mgrIds like '%"+this.getLoginUser().getId()+"%' order by s.requestTime desc";
	}
}
