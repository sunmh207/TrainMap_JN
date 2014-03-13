package com.jitong.anquanjiancha.domain;

import com.jitong.common.domain.TimedSMSProducer;
import com.jitong.common.form.JTField;


public class Anquantianshutixing extends TimedSMSProducer{
	private String creatorId;
	private String unitName;
	
	@Override
	@JTField(chineseName="提醒日期",order=1)
	public String getSendTime() {
		return super.getSendTime();
	}
	
	@Override
	@JTField(chineseName="开始时间",order=2)
	public String getStartDate() {
		return super.getStartDate();
	}
	
	@Override
	@JTField(chineseName="结束时间",order=3)
	public String getEndDate() {
		return super.getEndDate();
	}
	@Override
	@JTField(chineseName="提醒内容",order=4)
	public String getContent() {
		return super.getContent();
	}
	@Override
	@JTField(chineseName="时间间隔",order=5)
	public String getSchedule() {
		return super.getSchedule();
	}
	
	@Override
	@JTField(chineseName="提醒人员",order=6)
	public String getSendToNames() {
		return super.getSendToNames();
	}

	@Override
	public String getBusinessType() {
		return "ANQUANTIANSHU";
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
