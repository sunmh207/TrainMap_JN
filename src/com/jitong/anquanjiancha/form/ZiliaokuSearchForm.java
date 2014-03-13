package com.jitong.anquanjiancha.form;

import com.jitong.common.form.SearchForm;

public class ZiliaokuSearchForm extends SearchForm{
	
	private String kind;
	private String title;
	private String content;
	
	
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "ZiliaokuSearchForm [kind=" + kind + ", title=" + title
				+ ", content=" + content + "]";
	}
	
	
	 
	
}
