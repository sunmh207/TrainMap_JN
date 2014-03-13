package com.jitong.wnp.form;

import com.jitong.common.form.FieldCondition;
import com.jitong.common.form.Operation;
import com.jitong.common.form.SMSPrivilegesSearchForm;

public class ReceivedMMSSearchForm extends SMSPrivilegesSearchForm {
	private String toPhoneNumber;
	private String ccPhoneNumber;

	@FieldCondition(op = Operation.like)
	public String getToPhoneNumber() {
		return toPhoneNumber;
	}

	public void setToPhoneNumber(String toPhoneNumber) {
		this.toPhoneNumber = toPhoneNumber;
	}
	@FieldCondition(op = Operation.like)
	public String getCcPhoneNumber() {
		return ccPhoneNumber;
	}

	public void setCcPhoneNumber(String ccPhoneNumber) {
		this.ccPhoneNumber = ccPhoneNumber;
	}
}
