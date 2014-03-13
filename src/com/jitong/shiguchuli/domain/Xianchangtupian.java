package com.jitong.shiguchuli.domain;

import com.jitong.common.domain.MMSProducer;
import com.jitong.common.form.JTField;



public class Xianchangtupian extends MMSProducer{
	private String accidentDate;
	private String accidentPlace;
	private String description;
	private String creatorId;
	private String unitName;
	
	@JTField(chineseName="事故日期",order=1)
	public String getAccidentDate() {
		return accidentDate;
	}
	public void setAccidentDate(String date) {
		this.accidentDate = date;
	}
	@JTField(chineseName="事故地点",order=2)
	public String getAccidentPlace() {
		return accidentPlace;
	}
	public void setAccidentPlace(String place) {
		this.accidentPlace = place;
	}
	@JTField(chineseName="事故描述",order=3)
	public String getDescription() {
		return description;
	}
	public void setDescription(String descriptio) {
		this.description = descriptio;
	}

	@Override
	@JTField(chineseName="发送日期",order=4)
	public String getSendTime() {
		return super.getSendTime();
	}
	
	@Override
	@JTField(chineseName="接收人",order=5)
	public String getSendToNames() {
		return super.getSendToNames();
	}
	@Override
	public String getContent() {
		return getDescription();
	}
	
	@Override
	public String getBusinessType() {
		return "XIANCHANGTUPIAN";
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
