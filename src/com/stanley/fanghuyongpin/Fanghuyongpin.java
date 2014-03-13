package com.stanley.fanghuyongpin;

import com.jitong.common.form.JTField;

public class Fanghuyongpin {
	private String id;
	private String ch;
	private String ypName;
	private String setupTime;
	private String changeTime;
	private String changePerson;
	private String issueDate;
	private String note;
	private String quantity;
	private String isChanged;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JTField(chineseName="机车号",order=10)
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	@JTField(chineseName="用品名称",order=15)
	public String getYpName() {
		return ypName;
	}
	public void setYpName(String ypName) {
		this.ypName = ypName;
	}
	@JTField(chineseName="上车时间",order=25)
	public String getSetupTime() {
		return setupTime;
	}
	public void setSetupTime(String setupTime) {
		this.setupTime = setupTime;
	}
	@JTField(chineseName="到期日期",order=30)
	public String getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}
	@JTField(chineseName="更换人",order=40)
	public String getChangePerson() {
		return changePerson;
	}
	public void setChangePerson(String changePerson) {
		this.changePerson = changePerson;
	}
	
	@JTField(chineseName="检验日期",order=5)
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@JTField(chineseName="数量",order=20)
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getIsChanged() {
		return isChanged;
	}
	@JTField(chineseName="是否更换",order=35)
	public String getIsChangedTXT() {
		return "1".equals(isChanged)?"是":"否";
	}
	public void setIsChanged(String isChanged) {
		this.isChanged = isChanged;
	}

}
