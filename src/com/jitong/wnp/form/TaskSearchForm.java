package com.jitong.wnp.form;

import com.jitong.common.form.SameUnitSearchForm;

public class TaskSearchForm extends SameUnitSearchForm{
	private String isRepeat;
	private String content;
	private String businessType="Task";
	public String getIsRepeat() {
		return isRepeat;
	}
	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
}
