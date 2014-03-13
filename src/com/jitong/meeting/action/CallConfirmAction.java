package com.jitong.meeting.action;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.StringUtil;
import com.jitong.console.domain.User;
import com.jitong.meeting.domain.CallConfirm;

public class CallConfirmAction extends JITActionBase {

	private CallConfirm callConfirm;
	private String startTime;
	private String endTime;
	private String searchContent;


	public CallConfirm getCallConfirm() {
		return callConfirm;
	}

	public void setCallConfirm(CallConfirm callConfirm) {
		this.callConfirm = callConfirm;
	}


	public String getCategoryId() {
		return "meeting.callconfirm";
	}

	@Override
	public void validate() {
		return;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {

		//String hql = "from SMSVO sms where sms.businessType in ('huiyitongzhi','huiyijingshen')";
		//String hql = "from SMSVO sms where sms.businessType ='huiyitongzhi' ";//只显示会议通知内容
		User loginUser = this.getLoginUser();
		//String hql = "select sms from SMSVO sms, User u where sms.operatorId=u.id and u.unit='"+loginUser.getUnit()+"' and sms.businessType ='huiyitongzhi' ";//只显示会议通知内容
		String hql = "select sms from SMSVO sms where sms.mgrIds like '%"+loginUser.getId()+"%' and sms.businessType ='huiyitongzhi' ";//只显示会议通知内容
		if (!StringUtil.isEmpty(startTime)) {
			hql += "and sms.requestTime>='" + startTime + "'";
		}
		if (!StringUtil.isEmpty(endTime)) {
			hql += "and sms.requestTime<='" + endTime + "'";
		}
		if (!StringUtil.isEmpty(searchContent)) {
			hql += "and sms.SMSContent like '%" + searchContent + "%'";
		}

		hql += "order by sms.requestTime desc ";
		return hql;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
}
