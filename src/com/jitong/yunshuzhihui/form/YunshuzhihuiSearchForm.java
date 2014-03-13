package com.jitong.yunshuzhihui.form;

import com.jitong.common.form.FieldCondition;
import com.jitong.common.form.Operation;
import com.jitong.common.form.SearchForm;


public class YunshuzhihuiSearchForm extends SearchForm{
	private String name;
	private String checi;
	private String jiche;
	private String workNo;
	private String content;
	private String contentType;
	
	public String getName() {
		return name;
	}
	public void setName(String hName) {
		this.name = hName;
	}
	public String getCheci() {
		return checi;
	}
	public void setCheci(String checi) {
		this.checi = checi;
	}
	public String getJiche() {
		return jiche;
	}
	public void setJiche(String jiche) {
		this.jiche = jiche;
	}
	public String getWorkNo() {
		return workNo;
	}
	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@FieldCondition(op=Operation.startWiths, field="content")
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	@Override
	public String toString() {
		return "YunshuzhihuiSearchForm [name=" + name + ", checi=" + checi
				+ ", jiche=" + jiche + ", workNo=" + workNo + ", content="
				+ content + ", contentType=" + contentType + "]";
	}

}
