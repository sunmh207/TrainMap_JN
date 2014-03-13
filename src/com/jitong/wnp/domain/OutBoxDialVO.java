package com.jitong.wnp.domain;

import java.util.Date;

import com.jitong.common.form.JTField;
import com.jitong.common.form.JTFieldType;
import com.jitong.common.util.StringUtil;


public class OutBoxDialVO {
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
	private String verify="0"; 
	
	private String senderInfo;
	
	private String businessType;
	private String businessId;
	
	private String operatorId="";

	/**
	 * 接受者的ID(User.id)
	 */
	private String recipientId;
	private String mgrIds;
	
	
	
	/**
	 * 接收者姓名
	 */
	private String recipientName;

	/**
	 * 性别
	 */
	private String gender;

	/**
	 * 工号
	 */
	private String GH;
	/**
	 * 单位-部门
	 */
	// private String unitDept;
	private String unit;
	private String dept;
	/**
	 * 出生日期
	 * 
	 * @return
	 */
	private String birthday;

	
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
	@JTField(chineseName="手机号码",order=5)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@JTField(chineseName="内容",order=10)
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
	@JTField(chineseName="建立时间",order=15)
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
	@JTField(chineseName="发送状态",order=30)
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
			return "未知状态";
	}
	public String getFingerHTML() {
		if (finger == null)
			return null;
		finger = finger.trim();
		if (finger.equals("0"))
			return "新";
		else if (finger.equals("1"))
			return "电话成功";
		else if (finger.equals("2"))
			return "<font color=red>电话失败</font>";
		else if (finger.equals("3"))
			return "<font color=red>电话失败</font>";
		else if (finger.equals("4"))
			return "<font color=red>语音失败,短信成功</font>";
		else if (finger.equals("5"))
			return "<font color=red>语音失败,短信失败</font>";
		else if (finger.equals("6"))
			return "<font color=red>语音失败,短信失败</font>";
		else if (finger.equals("7"))
			return "<font color=red>语音失败,短信失败</font>";
		else if (finger.equals("8"))
			return "<font color=red>语音失败,短信失败</font>";
		else if (finger.equals("9"))
			return "<font color=red>语音失败,短信失败</font>";
		else if (finger.equals("a"))
			return "限制号码";
		else
			return "未知状态";
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
	@JTField(chineseName="通讯方式",order=20)
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
	@JTField(fieldType=JTFieldType.StringBool,chineseName="是否呼叫确认",order=35)
	public String getVerify() {
		return verify;
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

	@JTField(chineseName="姓名",order=1)
	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGH() {
		return GH;
	}

	public void setGH(String gH) {
		GH = gH;
	}

	public String getUnitDept() {
		return unit + "-" + dept;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	@JTField(chineseName="发送人信息",order=25)
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
