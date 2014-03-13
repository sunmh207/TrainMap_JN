package com.jitong.zrrc.domain;

import com.jitong.common.form.JTField;

public class EventResponse {
	private String id;
	private String personId;
	private String personName;
	private String eventType;
	private String eventDate;
	private String eventDesc;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	@JTField(chineseName="责任人",order=0)
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	@JTField(chineseName="事故类型",order=5)
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	@JTField(chineseName="事故日期",order=10)
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	@JTField(chineseName="事故描述",order=15)
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	
}
