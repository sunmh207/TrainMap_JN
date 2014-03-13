package com.stanley.locomotivedict;

import com.jitong.common.form.JTField;

public class Locomotive { 
	private String id;
	private String locomodel;
	private String loconumber;
	private String loconumberCode;//loconumber_code，缩位编码
	
	private String other;
	//private String ismanager;
	private String didian;//青岛，聊城
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JTField(chineseName="机车型号",order=10)
	public String getLocomodel() {
		return locomodel;
	}
	public void setLocomodel(String locomodel) {
		this.locomodel = locomodel;
	}
	@JTField(chineseName="机车号",order=20)
	public String getLoconumber() {
		return loconumber;
	}
	public void setLoconumber(String loconumber) {
		this.loconumber = loconumber;
	}
	@JTField(chineseName="备注",order=40)
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	/*public String getIsmanager() {
		return ismanager;
	}
	@JTField(chineseName="是否本段",order=50)
	public String getIsmanagerTXT() {
		return "1".equals(ismanager)?"是":"否";
	}
	public void setIsmanager(String ismanager) {
		this.ismanager = ismanager;
	}*/
	@JTField(chineseName="缩位编号",order=30)
	public String getLoconumberCode() {
		return loconumberCode;
	}
	public void setLoconumberCode(String loconumberCode) {
		this.loconumberCode = loconumberCode;
	}
	public String getDidian() {
		return didian;
	}
	public void setDidian(String didian) {
		this.didian = didian;
	}

}
