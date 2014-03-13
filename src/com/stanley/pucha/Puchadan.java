package com.stanley.pucha;

public class Puchadan {
	private String id;
	private String puchaTime;
	private String faqiDept;
	private String faqiPerson;
	private String puchaName;
	private String chs;
	private String items;
	private String isDone;
	private String doneTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPuchaTime() {
		return puchaTime;
	}

	public void setPuchaTime(String puchaTime) {
		this.puchaTime = puchaTime;
	}

	public String getFaqiDept() {
		return faqiDept;
	}

	public void setFaqiDept(String faqiDept) {
		this.faqiDept = faqiDept;
	}

	public String getFaqiPerson() {
		return faqiPerson;
	}

	public void setFaqiPerson(String faqiPerson) {
		this.faqiPerson = faqiPerson;
	}

	public String getPuchaName() {
		return puchaName;
	}

	public void setPuchaName(String puchaName) {
		this.puchaName = puchaName;
	}

	public String getChs() {
		return chs;
	}

	public void setChs(String chs) {
		this.chs = chs;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getIsDone() {
		return isDone;
	}
	public String getIsDoneTxt() {
		return "1".equals(isDone)?"是":"否";
	}

	public void setIsDone(String isDone) {
		this.isDone = isDone;
	}

	public String getDoneTime() {
		return doneTime;
	}

	public void setDoneTime(String doneTime) {
		this.doneTime = doneTime;
	}

}
