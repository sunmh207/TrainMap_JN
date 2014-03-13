package com.jitong.sms.domain;

import com.jitong.JitongConstants;
import com.jitong.common.form.JTField;
import com.jitong.common.form.JTFieldType;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;

public class SMS {
	private String SMSID;
	private String phoneNumber;
	private String requestTime;
	private String SMSContent;
	private String businessType;
	private String businessId;
	private String status = JitongConstants.SMS_STATUS_INIT; 
	private String needVoice;
	private String verify;//呼叫确认，needVoice==1时采用
	private String operatorId;
	private String mgrIds;
	
	/**
	 * 接受者的ID(User.id)
	 */
	private String recipientId;
	
	private String sendTime;
	private String senderInfo;
	private String spID = JitongConstants.SPID;
	private String failureTimes;
	
	
	
	
	public String getSMSID() {
		return SMSID;
	}
	public void setSMSID(String sMSID) {
		SMSID = sMSID;
	}
	@JTField(chineseName="手机号",order=5)
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@JTField(chineseName="发送时间",order=20)
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	@JTField(chineseName="短信内容",order=10)
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
	@JTField(fieldType=JTFieldType.SMSStatus,chineseName="发送状态",order=15)
	public String getStatus() {
		return StringUtil.trim(status);
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNeedVoice() {
		return needVoice;
	}
	public void setNeedVoice(String needVoice) {
		this.needVoice = needVoice;
	}
	@JTField(fieldType=JTFieldType.UserID,chineseName="姓名",order=1)
	public String getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
		//set MgrIds
		/*String mgrIds = SysCache.getManagerIds(recipientId);
		this.setMgrIds(mgrIds);*/
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	@JTField(chineseName="发送者信息",order=30)
	public String getSenderInfo() {
		return senderInfo;
	}
	public void setSenderInfo(String senderInfo) {
		this.senderInfo = senderInfo;
	}
	@JTField(chineseName="SP_ID",order=25)
	public String getSpID() {
		return spID;
	}
	public void setSpID(String spID) {
		this.spID = spID;
	}
	public String getFailureTimes() {
		return failureTimes;
	}
	public void setFailureTimes(String failureTimes) {
		this.failureTimes = failureTimes;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getVerify() {
		return verify;
	}
	public void setVerify(String verify) {
		this.verify = verify;
	}
	
	public String getMgrIds() {
		return mgrIds;
	}
	public void setMgrIds(String mgrIds) {
		this.mgrIds = mgrIds;
	}
	@Override
	public String toString() {
		return "SMS [SMSID=" + SMSID + ", phoneNumber=" + phoneNumber
				+ ", requestTime=" + requestTime + ", SMSContent=" + SMSContent
				+ ", businessType=" + businessType + ", businessId="
				+ businessId + ", status=" + status + ", needVoice="
				+ needVoice + ", verify=" + verify + ", operatorId="
				+ operatorId + ", recipientId=" + recipientId + ", sendTime="
				+ sendTime + ", senderInfo=" + senderInfo + ", spID=" + spID
				+ ", failureTimes=" + failureTimes + "]";
	}
	
	
}
