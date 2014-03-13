package com.jitong.wnp.form;

import com.jitong.common.form.FieldCondition;
import com.jitong.common.form.Operation;
import com.jitong.common.form.SMSPrivilegesSearchForm;

public class ReceivedSMSSearchForm extends SMSPrivilegesSearchForm {
	private String phoneNumber;
	private String reachTime_start;
	private String reachTime_end;
	
	@FieldCondition(op = Operation.like)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getReachTime_start() {
		return reachTime_start;
	}

	public void setReachTime_start(String reachTimeStart) {
		reachTime_start = reachTimeStart;
	}

	public String getReachTime_end() {
		return reachTime_end;
	}

	public void setReachTime_end(String reachTimeEnd) {
		reachTime_end = reachTimeEnd;
	}
	@Override
	public String toString() {
		return "ReceivedSMSSearchForm [reachTime_start=" + reachTime_start
				+ ", reachTime_end=" + reachTime_end + "]";
	}
}
