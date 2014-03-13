package com.stanley.fanghuyongpin;

import com.jitong.common.form.JTField;

public class FanghuyongpinDict {
	private String id;
	private String ypName;
	private String duration;
	private String locomodel;
	private String note;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JTField(chineseName="用品名称",order=10)
	public String getYpName() {
		return ypName;
	}
	public void setYpName(String ypName) {
		this.ypName = ypName;
	}
	@JTField(chineseName="期限",order=20)
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	@JTField(chineseName="适用车型",order=30)
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
