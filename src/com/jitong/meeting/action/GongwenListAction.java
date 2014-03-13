package com.jitong.meeting.action;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.util.StringUtil;

public class GongwenListAction extends JITActionBase {
	public String getCategoryId() {
		return "meeting.gongwenlist";
	}

	public String getListHQL(ArrayList<Object> params) {
		String qryPhoneNumber = request.getParameter("qryPhoneNumber");
		String qryContent = request.getParameter("qryContent");
		return "from SMS s where s.SMSContent like '%公文提醒%' and s.SMSContent like '%" + StringUtil.trim(qryContent) + "%' and s.phoneNumber like '%"
				+ StringUtil.trim(qryPhoneNumber) + "%' and s.mgrIds like '%"+this.getLoginUser().getId()+"%' order by s.requestTime desc";
	}
}
