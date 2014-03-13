package com.jitong.sms.domain;

import com.jitong.JitongConstants;

public class ScheduleSMS {

	private String SMSID;
	private String phoneNumber;
	private String requestTime;
	private String SMSContent;
	private String businessType;
	private String businessId;
	private String status = JitongConstants.SMS_STATUS_INIT;
	private String recipientId;
	private String cronExp;
	private String startTime;
	private String endTime;

	private String senderInfo;
	private String operatorId;
	public String getSMSID() {
		return SMSID;
	}
	public void setSMSID(String sMSID) {
		SMSID = sMSID;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getSMSContent() {
		return SMSContent;
	}
	public void setSMSContent(String sMSContent) {
		SMSContent = sMSContent;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}
	public String getCronExp() {
		return cronExp;
	}
	public void setCronExp(String cronExp) {
		this.cronExp = cronExp;
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
	public String getSenderInfo() {
		return senderInfo;
	}
	public void setSenderInfo(String senderInfo) {
		this.senderInfo = senderInfo;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	

}
