package com.jitong.sms.domain;

import com.jitong.JitongConstants;
import com.jitong.common.form.JTField;
import com.jitong.common.form.JTFieldType;
import com.jitong.common.util.SysCache;

public class MMSVO {
	private String mmsId;
	private String realMmsId;
	private String title;
	private String phoneNumber;
	private String requestTime;
	private String businessType;
	private String businessId;
	private String status = JitongConstants.SMS_STATUS_INIT;
	private String senderInfo;
	private String mgrIds;
	

	public String getRealMmsId() {
		return realMmsId;
	}

	public void setRealMmsId(String realMmsId) {
		this.realMmsId = realMmsId;
	}

	private String operatorId;

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	/**
	 * 接收者的ID(User.id)
	 */
	private String recipientId;
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

	public String getMmsId() {
		return mmsId;
	}

	public void setMmsId(String mmsId) {
		this.mmsId = mmsId;
	}

	@JTField(chineseName = "手机号码", order = 5)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JTField(chineseName = "发送时间", order = 10)
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

	@JTField(fieldType = JTFieldType.SMSStatus, chineseName = "发送状态", order = 15)
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
		//set MgrIds
		/*String mgrIds = SysCache.getManagerIds(recipientId);
		this.setMgrIds(mgrIds);*/
	}

	@JTField(chineseName = "接收人", order = 1)
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

	@JTField(chineseName = "发送者信息", order = 20)
	public String getSenderInfo() {
		return senderInfo;
	}

	public void setSenderInfo(String senderInfo) {
		this.senderInfo = senderInfo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMgrIds() {
		return mgrIds;
	}

	public void setMgrIds(String mgrIds) {
		this.mgrIds = mgrIds;
	}

}
