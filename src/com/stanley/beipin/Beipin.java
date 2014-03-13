package com.stanley.beipin;

import com.jitong.common.form.JTField;

public class Beipin {
	private String id;
	private String beipinName;
	private String issueType;
	private String locomodel;
	private String note;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JTField(chineseName="备品名称",order=10)
	public String getBeipinName() {
		return beipinName;
	}
	public void setBeipinName(String beipinName) {
		this.beipinName = beipinName;
	}
	@JTField(chineseName="发放类型",order=20)
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	@JTField(chineseName="适用机型",order=30)
	public String getLocomodel() {
		return locomodel;
	}
	public void setLocomodel(String locomodel) {
		this.locomodel = locomodel;
	}
	@JTField(chineseName="备注",order=40)
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	

}
