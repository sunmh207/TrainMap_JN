package com.jitong.anquanjiancha.domain;

import com.jitong.common.domain.SMSProducer;
import com.jitong.common.form.JTField;


public class Kaohetongzhi extends  SMSProducer{
	//private String title;
	private String department;
	private String unit;
	private String dept;
	private String creatorId;
	private String unitName;
	

	public void setDepartment(String department) {
		this.department = department;
	}
	
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	@Override
	public String toString() {
		return "Kaohetongzhi [title=" + title + ", department=" + department
				+ ", super=" + super.toString() + "]";
	}
	
	@Override
	public String getBusinessType() {
		return "KAOHETONGZHI";
	}
	@Override
	@JTField(chineseName="发送时间",order=1)
	public String getSendTime() {
		return super.getSendTime();
	}
	
	@Override
	@JTField(chineseName="发送人",order=2)
	public String getSendByName() {
		return super.getSendByName();
	}
	
	@Override
	@JTField(chineseName="发送对象",order=3)
	public String getSendToNames() {
		return super.getSendToNames();
	}
	@Override
	@JTField(chineseName="内容",order=5)
	public String getContent() {
		return super.getContent();
	}
	@JTField(chineseName="标题",order=4)
	public String getTitle() {
		return title;
	}

	@JTField(chineseName="考核部门",order=6)
	public String getDepartment() {
		return department;
	}


	public String getCreatorId() {
		return creatorId;
	}


	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}


	public String getUnitName() {
		return unitName;
	}


	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
}
