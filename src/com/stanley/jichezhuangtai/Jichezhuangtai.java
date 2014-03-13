package com.stanley.jichezhuangtai;

public class Jichezhuangtai {
	private String id;
	private String ch;
	private String type;
	private String content;
	private String submitPerson;
	private String submitTime;
	private String isLast;
	private String submitDept;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubmitPerson() {
		return submitPerson;
	}
	public void setSubmitPerson(String submitPerson) {
		this.submitPerson = submitPerson;
	}
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public String getIsLast() {
		return isLast;
	}
	public String getIsLastTxt() {
		return "1".equals(isLast)?"是":"否";
	}
	public void setIsLast(String isLast) {
		this.isLast = isLast;
	}
	public String getSubmitDept() {
		return submitDept;
	}
	public void setSubmitDept(String submitDept) {
		this.submitDept = submitDept;
	}
	
}
