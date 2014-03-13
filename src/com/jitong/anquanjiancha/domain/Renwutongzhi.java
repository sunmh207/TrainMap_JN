package com.jitong.anquanjiancha.domain;

import com.jitong.common.domain.SMSProducer;
import com.jitong.common.form.JTField;

public class Renwutongzhi extends SMSProducer{
	private String title;
	private String department;
	private String unit;
	private String dept;
	private String creatorId;
	private String unitName;
	
	@JTField(chineseName="标题",order=4)
	public String getTitle() {
		return title;
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

	public void setTitle(String title) {
		this.title = title;
	}

	@JTField(chineseName="考核部门",order=6)
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String getBusinessType() {
		return "RENWUTONGZHI";
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
