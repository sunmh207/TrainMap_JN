package com.jitong.wnp.domain;

import com.jitong.common.form.JTField;
import com.jitong.common.form.JTFieldType;

public class ReceivedMMS {
	private String mmsId;//彩信的唯一id
	private String toPhoneNumber;
	private String ccPhoneNumber;
	private String receiveTime;
	private String status;//0:初始，1：已处理
	private String title;
	private String mgrIds;
	
	public String getMmsId() {
		return mmsId;
	}

	public void setMmsId(String mmsId) {
		this.mmsId = mmsId;
	}
	
	public String getToPhoneNumber() {
		return toPhoneNumber;
	}

	public void setToPhoneNumber(String toPhoneNumber) {
		this.toPhoneNumber = toPhoneNumber;
	}
	
	@JTField(fieldType=JTFieldType.PhoneNumber2UserName,chineseName="姓名",order=0)
	public String getPhoneNumber2UserName(){
		return ccPhoneNumber;
	}
	@JTField(chineseName="电话号码",order=5)
	public String getCcPhoneNumber() {
		return ccPhoneNumber;
	}

	public void setCcPhoneNumber(String ccPhoneNumber) {
		this.ccPhoneNumber = ccPhoneNumber;
	}
	@JTField(chineseName="到达时间",order=10)
	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@JTField(chineseName="标题",order=15)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@JTField(chineseName="状态",order=20)
	public String getStatusTXT() {
		if("0".equals(status)){
			return "初始";
		}
		if("1".equals(status)){
			return "已处理";
		}
		return "未知";
	}

	public String getMgrIds() {
		return mgrIds;
	}

	public void setMgrIds(String mgrIds) {
		this.mgrIds = mgrIds;
	}
	
}
