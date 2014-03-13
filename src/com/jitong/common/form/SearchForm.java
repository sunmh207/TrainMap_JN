package com.jitong.common.form;


public class SearchForm {
	private String sendTime_start;
	private String sendTime_end;
	private String sendByName;
	
	private String sendToNames;
	private String content;
	private String title;
	private String department;
	
	
	public String getSendTime_start() {
		return sendTime_start;
	}

	public void setSendTime_start(String sendTimeStart) {
		sendTime_start = sendTimeStart;
	}

	public String getSendTime_end() {
		return sendTime_end;
	}

	public void setSendTime_end(String sendTimeEnd) {
		sendTime_end = sendTimeEnd;
	}

	public String getSendByName() {
		return sendByName;
	}

	public void setSendByName(String sendByName) {
		this.sendByName = sendByName;
	}

	public String getSendToNames() {
		return sendToNames;
	}

	public void setSendToNames(String sendToNames) {
		this.sendToNames = sendToNames;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	private String orderBy;

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
}
