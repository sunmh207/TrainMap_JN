package com.stanley.pucha;

import com.jitong.common.form.JTField;

public class PuchaDetailV {
	//puchadan
	private String puchadanId;
	private String puchadan_puchaTime;
	private String faqiDept;
	private String faqiPerson;
	private String puchaName;
	private String chs;
	private String items;
	private String puchadan_isDone;
	private String puchadan_doneTime;
	
	//puchadetail
	private String puchadetailId;
	private String ch;
	private String item;
	private String puchadetail_isDone;
	private String puchaPerson;
	private String puchadetail_puchaTime;
	public String getPuchadanId() {
		return puchadanId;
	}
	public void setPuchadanId(String puchadanId) {
		this.puchadanId = puchadanId;
	}
	@JTField(chineseName="时间",order=5)
	public String getPuchadan_puchaTime() {
		return puchadan_puchaTime;
	}
	public void setPuchadan_puchaTime(String puchadan_puchaTime) {
		this.puchadan_puchaTime = puchadan_puchaTime;
	}
	@JTField(chineseName="发起部门",order=10)
	public String getFaqiDept() {
		return faqiDept;
	}
	public void setFaqiDept(String faqiDept) {
		this.faqiDept = faqiDept;
	}
	@JTField(chineseName="发起人",order=15)
	public String getFaqiPerson() {
		return faqiPerson;
	}
	public void setFaqiPerson(String faqiPerson) {
		this.faqiPerson = faqiPerson;
	}
	@JTField(chineseName="普查名称",order=20)
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
	@JTField(chineseName="普查项目",order=25)
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public String getPuchadan_isDone() {
		return puchadan_isDone;
	}
	public void setPuchadan_isDone(String puchadan_isDone) {
		this.puchadan_isDone = puchadan_isDone;
	}
	
	
	public String getPuchadan_doneTime() {
		return puchadan_doneTime;
	}
	public void setPuchadan_doneTime(String puchadan_doneTime) {
		this.puchadan_doneTime = puchadan_doneTime;
	}
	public String getPuchadetailId() {
		return puchadetailId;
	}
	public void setPuchadetailId(String puchadetailId) {
		this.puchadetailId = puchadetailId;
	}
	@JTField(chineseName="车号",order=30)
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	@JTField(chineseName="检查项目",order=35)
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getPuchadetail_isDone() {
		return puchadetail_isDone;
	}
	@JTField(chineseName="是否完成",order=40)
	public String getPuchadetail_isDoneTXT() {
		return "1".equals(puchadetail_isDone)?"是":"否";
	}
	public void setPuchadetail_isDone(String puchadetail_isDone) {
		this.puchadetail_isDone = puchadetail_isDone;
	}
	@JTField(chineseName="普查人",order=45)
	public String getPuchaPerson() {
		return puchaPerson;
	}
	public void setPuchaPerson(String puchaPerson) {
		this.puchaPerson = puchaPerson;
	}
	@JTField(chineseName="普查时间",order=50)
	public String getPuchadetail_puchaTime() {
		return puchadetail_puchaTime;
	}
	public void setPuchadetail_puchaTime(String puchadetail_puchaTime) {
		this.puchadetail_puchaTime = puchadetail_puchaTime;
	}

	
}
