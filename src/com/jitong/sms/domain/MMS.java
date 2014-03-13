package com.jitong.sms.domain;

import java.sql.Blob;

import com.jitong.JitongConstants;
import com.jitong.common.util.SysCache;

public class MMS {

	private String SMSID;
	private String realMmsId;
	private String title;
	private String phoneNumber;
	private String requestTime;
	private String businessType;
	private String businessId;
	private String status = JitongConstants.SMS_STATUS_INIT;
	private String operatorId;
	private String recipientId;
	private String mgrIds;

	private Blob mmsContent;

	private String senderInfo;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Blob getMmsContent() {
		return mmsContent;
	}

	public void setMmsContent(Blob mmsContent) {
		this.mmsContent = mmsContent;
	}

	public String getSMSID() {
		return SMSID;
	}

	public void setSMSID(String sMSID) {
		SMSID = sMSID;
	}

	public String getRealMmsId() {
		return realMmsId;
	}

	public void setRealMmsId(String realMmsId) {
		this.realMmsId = realMmsId;
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

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
		//set MgrIds
		/*String mgrIds = SysCache.getManagerIds(recipientId);
		this.setMgrIds(mgrIds);*/
	}

	public String getSenderInfo() {
		return senderInfo;
	}

	public void setSenderInfo(String senderInfo) {
		this.senderInfo = senderInfo;
	}

	public String getMgrIds() {
		return mgrIds;
	}

	public void setMgrIds(String mgrIds) {
		this.mgrIds = mgrIds;
	}

}
