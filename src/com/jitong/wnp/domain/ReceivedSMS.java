package com.jitong.wnp.domain;

import com.jitong.common.form.JTField;
import com.jitong.common.form.JTFieldType;

public class ReceivedSMS {
	private String smsId;//短信的唯一id
	private String phoneNumber;
	private String reachTime;
	private String content;
	private String flag;//0:初始，1：已处理
	private String mgrIds;//管理者ids
	
	public String getSmsId() {
		return smsId;
	}
	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}
	@JTField(fieldType=JTFieldType.PhoneNumber2UserName,chineseName="姓名",order=0)
	public String getPhoneNumber2UserName(){
		return phoneNumber;
	}
	
	@JTField(chineseName="电话号码",order=1)
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@JTField(chineseName="到达时间",order=5)
	public String getReachTime() {
		return reachTime;
	}
	public void setReachTime(String reachTime) {
		this.reachTime = reachTime;
	}
	@JTField(chineseName="短信内容",order=10)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@JTField(chineseName="状态",order=15)
	public String getFlagTXT() {
		if("0".equals(flag)){
			return "初始";
		}
		if("1".equals(flag)){
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
