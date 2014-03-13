package com.jitong.shigongxunjian.domain;

import com.jitong.common.domain.MMSProducer;
import com.jitong.common.form.JTField;



public class Shigongtu extends MMSProducer{
	private String time;
	private String zone;
	private String description;
	
	@JTField(chineseName="日期",order=1)
	public String getTime() {
		return time;
	}
	public void setTime(String date) {
		this.time = date;
	}
	@JTField(chineseName="地点",order=2)
	public String getZone() {
		return zone;
	}
	public void setZone(String place) {
		this.zone = place;
	}
	@JTField(chineseName="描述",order=3)
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
		return "SHIGONGTU";
	}
	
	
}
