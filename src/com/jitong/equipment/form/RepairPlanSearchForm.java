package com.jitong.equipment.form;

import com.jitong.common.form.FieldCondition;
import com.jitong.common.form.Operation;
import com.jitong.common.form.SearchForm;

public class RepairPlanSearchForm extends SearchForm {
	private String part;
	private String cx;
	
	@FieldCondition(op = Operation.like)
	public String getCx() {
		return cx;
	}

	public void setCx(String cx) {
		this.cx = cx;
	}

	
	@FieldCondition(op = Operation.like)
	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}

}
