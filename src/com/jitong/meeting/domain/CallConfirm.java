package com.jitong.meeting.domain;

import com.jitong.JitongConstants;
import com.jitong.common.form.JTField;
import com.jitong.common.form.JTFieldType;

public class CallConfirm {
	private String callConfirmId;  //pk
	private String businessType;  //
	
	private String recipientId;
	private String recipientName;
	private String phoneNumber;
	private String unitName;
	private String deptName;
	private String status = JitongConstants.SMS_STATUS_INIT; 
	private String meetingTime;
	private String content;
	private String needVoice;
	public String getCallConfirmId() {
		return callConfirmId;
	}
	public void setCallConfirmId(String callConfirmId) {
		this.callConfirmId = callConfirmId;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	@JTField(fieldType=JTFieldType.UserID,chineseName="姓名",order=0)
	public String getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMeetingTime() {
		return meetingTime;
	}
	public void setMeetingTime(String meetingTime) {
		this.meetingTime = meetingTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNeedVoice() {
		return needVoice;
	}
	public void setNeedVoice(String needVoice) {
		this.needVoice = needVoice;
	}
	
	
	
	
	
}
