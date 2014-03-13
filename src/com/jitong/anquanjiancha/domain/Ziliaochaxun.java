package com.jitong.anquanjiancha.domain;

import com.jitong.common.domain.SMSProducer;
import com.jitong.common.form.JTField;


public class Ziliaochaxun extends SMSProducer{
	private String reason;
	private String keyword;
	
	@JTField(chineseName="原因",order=4)
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@JTField(chineseName="关键字",order=3)
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	@JTField(chineseName="发送时间",order=2)
	public String getSendTime() {
		return super.getSendTime();
	}
	
	@Override
	@JTField(chineseName="发送人",order=1)
	public String getSendByName() {
		return super.getSendByName();
	}
	
	@JTField(chineseName="标题",order=5)
	public String getTitle() {
		return super.getTitle();
	}
	@Override
	@JTField(chineseName="内容",order=6)
	public String getContent() {
		// TODO Auto-generated method stub
		return super.getContent();
	}
	
	@Override
	public String getBusinessType() {
		// TODO Auto-generated method stub
		return "ZILIAOCHAXUN";
	}
	
}
