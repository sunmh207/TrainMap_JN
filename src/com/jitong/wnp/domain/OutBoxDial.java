package com.jitong.wnp.domain;

import java.util.Date;

import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;

public class OutBoxDial {
	public static final String MSG_TYPE_TELL="1";
	public static final String MSG_TYPE_MSG="2";
	public static final String MSG_TYPE_TELLMSG="3";
	
	public static final String VERIFY_DEFAULT="0";
	public static final String VERIFY_YES="1";
	
	private String outboxdialId;
	private int id=0;
	private String mobile="";
	private String msg="";
	private String startVoiceFile="";
	private String endVoiceFile="";
	private Date dtCreate;
	private Date dtCalled;
	private String finger="0";
	private String msgType="";
	private String purpose="1";
	//private String ip="";
	private String senderInfo;
	private String verify="0"; 
	
	private String businessType;
	private String businessId;
	
	private String operatorId="";

	/**
	 * 接受者的ID(User.id)
	 */
	private String recipientId;
	private String mgrIds;
	
	
	public String getOutboxdialId() {
		return outboxdialId;
	}

	public void setOutboxdialId(String outboxdialId) {
		this.outboxdialId = outboxdialId;
	}
    
	@Deprecated 
	public int getId() {
		return id;
	}
	@Deprecated 
	public void setId(int id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStartVoiceFile() {
		return startVoiceFile;
	}

	public void setStartVoiceFile(String startVoiceFile) {
		this.startVoiceFile = startVoiceFile;
	}

	public String getEndVoiceFile() {
		return endVoiceFile;
	}

	public void setEndVoiceFile(String endVoiceFile) {
		this.endVoiceFile = endVoiceFile;
	}

	public Date getDtCreate() {
		return dtCreate;
	}

	public void setDtCreate(Date dtCreate) {
		this.dtCreate = dtCreate;
	}

	public Date getDtCalled() {
		return dtCalled;
	}

	public void setDtCalled(Date dtCalled) {
		this.dtCalled = dtCalled;
	}

	public String getFinger() {
		return StringUtil.trim(finger);
	}

	public String getFingerTXT() {
		if (finger == null)
			return null;
		finger = finger.trim();
		if (finger.equals("0"))
			return "新";
		else if (finger.equals("1"))
			return "电话成功";
		else if (finger.equals("2"))
			return "电话失败";
		else if (finger.equals("3"))
			return "电话失败";
		else if (finger.equals("4"))
			return "短信成功";
		else if (finger.equals("5"))
			return "短信失败";
		else if (finger.equals("6"))
			return "短信失败";
		else if (finger.equals("7"))
			return "短信失败";
		else if (finger.equals("8"))
			return "短信失败";
		else if (finger.equals("9"))
			return "短信失败";
		else if (finger.equals("a"))
			return "限制号码";
		else
			return "";
	}

	public void setFinger(String finger) {
		this.finger = finger;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getPurpose() {
		return purpose;
	}

	public String getPurposeTXT() {
		if (purpose == null)
			return null;
		purpose=purpose.trim();
		if (purpose.equals("1"))
			return "电话";
		else if (purpose.equals("2"))
			return "短信";
		else if (purpose.equals("3"))
			return "电话+短信";
		else return "未知";
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	/*public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}*/

	
	public String getVerify() {
		return verify;
	}

	public String getSenderInfo() {
		return senderInfo;
	}

	public void setSenderInfo(String senderInfo) {
		this.senderInfo = senderInfo;
	}

	public void setVerify(String verify) {
		this.verify = StringUtil.boolString2IntString(verify);
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

	public static void main(String[] args){
		
		System.out.println(new OutBoxDial().getId());
	}

	public String getMgrIds() {
		return mgrIds;
	}

	public void setMgrIds(String mgrIds) {
		this.mgrIds = mgrIds;
	}
	
}
