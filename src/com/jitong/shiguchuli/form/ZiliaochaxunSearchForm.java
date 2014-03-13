package com.jitong.shiguchuli.form;

import com.jitong.common.form.FieldCondition;
import com.jitong.common.form.Operation;
import com.jitong.common.form.SameUnitSearchForm;

public class ZiliaochaxunSearchForm extends SameUnitSearchForm{
	private String kind;
	private String title;
	private String content;
	
	@FieldCondition(op = Operation.eq)
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
