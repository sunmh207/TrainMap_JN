package com.jitong.anquanjiancha.domain;

import com.jitong.common.domain.SMSProducer;
import com.jitong.common.form.JTField;

public class Shigutongbao extends SMSProducer{
	private String creatorId;
	private String unitName;
	
	@Override
	@JTField(chineseName="通报日期",order=1)
	public String getSendTime() {
		return super.getSendTime();
	}
	
	@Override
	@JTField(chineseName="通报对象",order=3)
	public String getSendToNames() {
		return super.getSendToNames();
	}
	@JTField(chineseName="标题",order=4)
	public String getTitle() {
		return  super.getTitle();
	}
	@Override
	@JTField(chineseName="内容",order=5)
	public String getContent() {
		return super.getContent();
	}

	@JTField(chineseName="通报部门",order=2)
	public String getDepartment() {
		return super.getDepartment();
	}
	
	
	@Override
	public String getBusinessType() {
		return "SHIGUTONGBAO";
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}	
	
}
