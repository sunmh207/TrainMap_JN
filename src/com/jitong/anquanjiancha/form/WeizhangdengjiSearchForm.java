package com.jitong.anquanjiancha.form;

import com.jitong.common.form.FieldCondition;
import com.jitong.common.form.Operation;
import com.jitong.common.form.SameUnitSearchForm;

public class WeizhangdengjiSearchForm extends SameUnitSearchForm {
	private String code;

	@FieldCondition(op=Operation.like_nocase)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
