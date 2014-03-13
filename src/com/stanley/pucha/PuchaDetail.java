package com.stanley.pucha;

public class PuchaDetail {
	private String id;
	private String puchadanId;
	private String ch;
	private String item;
	private String isDone;
	private String puchaPerson;
	private String puchaTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPuchadanId() {
		return puchadanId;
	}

	public void setPuchadanId(String puchadanId) {
		this.puchadanId = puchadanId;
	}

	public String getCh() {
		return ch;
	}

	public void setCh(String ch) {
		this.ch = ch;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getIsDone() {
		return isDone;
	}
	public String getIsDoneTXT() {
		return "1".equals(isDone)?"是":"否";
	}

	public void setIsDone(String isDone) {
		this.isDone = isDone;
	}

	public String getPuchaPerson() {
		return puchaPerson;
	}

	public void setPuchaPerson(String puchaPerson) {
		this.puchaPerson = puchaPerson;
	}

	public String getPuchaTime() {
		return puchaTime;
	}

	public void setPuchaTime(String puchaTime) {
		this.puchaTime = puchaTime;
	}
}
