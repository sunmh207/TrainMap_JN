package com.stanley.beipin;

import com.jitong.common.form.JTField;

public class BeipinRelease {
	private String id;
	private String getTime;
	private String getPerson;
	private String ch;
	private String beipinName;
	private String recipient;
	private String receiveTime;
	private String isOK;
	private String returnPerson;
	private String returnTime;
	private String issueType;
	private String hasReceipts;
	private String isDone;
	private String getMode;
	private String batch;
	private String getPersoncode;
	private String returnPersoncode;
	private String note;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JTField(chineseName="领取时间",order=10)
	public String getGetTime() {
		return getTime;
	}
	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}
	@JTField(chineseName="领取人",order=20)
	public String getGetPerson() {
		return getPerson;
	}
	public void setGetPerson(String getPerson) {
		this.getPerson = getPerson;
	}
	@JTField(chineseName="机车号",order=30)
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	@JTField(chineseName="备品名称",order=40)
	public String getBeipinName() {
		return beipinName;
	}
	public void setBeipinName(String beipinName) {
		this.beipinName = beipinName;
	}
	@JTField(chineseName="接收人",order=50)
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	@JTField(chineseName="接收时间",order=60)
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getIsOK() {
		return isOK;
	}
	@JTField(chineseName="是否良好",order=70)
	public String getIsOKTXT() {
		return "1".equals(isOK)?"是":"否";
	}
	public void setIsOK(String isOK) {
		this.isOK = isOK;
	}
	@JTField(chineseName="交还人",order=80)
	public String getReturnPerson() {
		return returnPerson;
	}
	public void setReturnPerson(String returnPerson) {
		this.returnPerson = returnPerson;
	}
	@JTField(chineseName="交换时间",order=90)
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	@JTField(chineseName="发放类型",order=100)
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public String getHasReceipts() {
		return hasReceipts;
	}
	@JTField(chineseName="单据填写",order=110)
	public String getHasReceiptsTXT() {
		return "1".equals(hasReceipts)?"是":"否";
	}
	public void setHasReceipts(String hasReceipts) {
		this.hasReceipts = hasReceipts;
	}
	public String getIsDone() {
		return isDone;
	}
	/*public String getIsDoneTXT() {
		return "1".equals(isDone)?"是":"否";
	}*/
	public void setIsDone(String isDone) {
		this.isDone = isDone;
	}
	@JTField(chineseName="领取方式",order=120)
	public String getGetMode() {
		return getMode;
	}
	public void setGetMode(String getMode) {
		this.getMode = getMode;
	}
	@JTField(chineseName="批次号",order=130)
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	@JTField(chineseName="领取人编号",order=140)
	public String getGetPersoncode() {
		return getPersoncode;
	}
	public void setGetPersoncode(String getPersoncode) {
		this.getPersoncode = getPersoncode;
	}
	@JTField(chineseName="交还人编号",order=150)
	public String getReturnPersoncode() {
		return returnPersoncode;
	}
	public void setReturnPersoncode(String returnPersoncode) {
		this.returnPersoncode = returnPersoncode;
	}
	@JTField(chineseName="备注",order=160)
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

}
