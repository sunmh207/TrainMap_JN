package com.jitong.sms.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.jitong.common.form.JTField;
import com.jitong.common.form.JTFieldType;


public class InBoxVO implements Serializable {
	private String businessType;
	private String businessId;
	private String phoneNumber;
	private String reachTime;
	private String content;
	private String reply;
	/**
	 * 发送者的ID(User.id)
	 */
	private String senderId;
	/**
	 * 发送者姓名
	 */
	private String senderName;
	
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
	//private String unitDept;
	private String unit;
	private String dept;
	/**
	 * 出生日期
	 * @return
	 */
	private String birthday;
	
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
	@JTField(fieldType=JTFieldType.PhoneNumber2UserName,chineseName="姓名",order=0)
	public String getPhone4Name(){
		return this.phoneNumber;
	}
	
	@JTField(chineseName="手机号",order=25)
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@JTField(chineseName="时间",order=15)
	public String getReachTime() {
		return reachTime;
	}
	public void setReachTime(String reachTime) {
		this.reachTime = reachTime;
	}
	@JTField(chineseName="内容",order=35)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	@JTField(chineseName="性别",order=20)
	//@JTField(fieldType=JTFieldType.StringBool,chineseName="是否呼叫确认",order=35)
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@JTField(chineseName="工号",order=10)
	public String getGH() {
		return GH;
	}
	public void setGH(String gH) {
		GH = gH;
	}
	@JTField(chineseName="单位-部门",order=15)
	public String getUnitDept() {
		return unit+"-"+dept;
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
	
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public boolean equals(Object obj) {
		if (!(obj instanceof InBoxVO)) {
			return false;
		} else {
			InBoxVO inBox = (InBoxVO) obj;
			return new EqualsBuilder().appendSuper(super.equals(obj)).append(this.businessType, inBox.businessType).append(this.businessId, inBox.businessId)
					.append(this.phoneNumber, inBox.phoneNumber).isEquals();
		}
	}

	public int hashCode() {
		return new HashCodeBuilder(-528253723, -475504089).appendSuper(super.hashCode()).append(this.businessType).append(this.businessId).append(
				this.phoneNumber).toHashCode();
	}
	
	
}
