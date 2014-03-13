package com.jitong.sms.domain;

import com.jitong.JitongConstants;
import com.jitong.common.util.StringUtil;
/** 
 * 短信发送记录
 * （一条短信可能发送失败多次，每次都有一条记录）
 * 对应数据库表JTMOBILE_SMS_SENDER
 * @author stanley.sun
 *
 */
public class SMSSent {
	private String SMSSentId;
	private String SMSID;
	private String phoneNumber;
	private String SMSContent;
	private String status = JitongConstants.SMS_STATUS_INIT; 
    private String msgSendTime;
    private String failureReason;
    private String spID;
    private String spSenderInfo;
	public String getSMSSentId() {
		return SMSSentId;
	}
	public void setSMSSentId(String sMSSentId) {
		SMSSentId = sMSSentId;
	}
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
	public String getSMSContent() {
		return SMSContent;
	}
	public void setSMSContent(String sMSContent) {
		SMSContent = sMSContent;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsgSendTime() {
		return msgSendTime;
	}
	public void setMsgSendTime(String msgSendTime) {
		this.msgSendTime = msgSendTime;
	}
	public String getFailureReason() {
		return failureReason;
	}
	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}
	public String getSpID() {
		return spID;
	}
	public void setSpID(String spID) {
		this.spID = spID;
	}
	public String getSpSenderInfo() {
		return spSenderInfo;
	}
	public void setSpSenderInfo(String spSenderInfo) {
		this.spSenderInfo = spSenderInfo;
	}
    
	
	
	
}
