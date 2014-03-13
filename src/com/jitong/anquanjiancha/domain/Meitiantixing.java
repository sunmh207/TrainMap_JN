package com.jitong.anquanjiancha.domain;

import com.jitong.common.domain.TimedSMSProducer;
import com.jitong.common.form.JTField;

public class Meitiantixing extends TimedSMSProducer{
	private String creatorId;
	private String unitName;
	@Override
	@JTField(chineseName="日期",order=1)
	public String getSendTime() {
		return super.getSendTime();
	}
	@Override
	@JTField(chineseName="提醒人员",order=4)
	public String getSendToNames() {
		return super.getSendToNames();
	}
	@Override
	@JTField(chineseName="提醒内容",order=3)
	public String getContent() {
		return super.getContent();
	}
	@JTField(chineseName="主题",order=2)
	public String getTitle() {
		return super.getTitle();
	}
	
	@Override
	public String getSchedule() {
		return "每年";
	}
	@Override
	public String getBusinessType() {
		return "MEITIANTIXING";
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
