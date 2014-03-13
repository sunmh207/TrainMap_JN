package com.jitong.qiyezixun.domain;

import com.jitong.common.form.JTField;
import com.jitong.common.form.JTFieldType;

public class DeptBirthday {
	private String id;
	private String unitDept;
	private String creatorId;
	private String format;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnitDept() {
		return unitDept;
	}
	public void setUnitDept(String unitDept) {
		this.unitDept = unitDept;
	}
	@JTField(fieldType=JTFieldType.UserID,chineseName="创建者",order=5)
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	
	@JTField(chineseName="慰问内容",order=1)
	public String getFormat() {
		return format;
	}
	
	public void setFormat(String format) {
		this.format = format;
	}
	
	
}
