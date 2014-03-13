package com.jitong.wnp.form;

import com.jitong.common.form.FieldCondition;
import com.jitong.common.form.Operation;
import com.jitong.common.form.SMSPrivilegesSearchForm;

public class MMSVOSearchForm  extends SMSPrivilegesSearchForm {
	private String phoneNumber;
	private String requestTime_start;
	private String requestTime_end;
	private String recipientName;
	
	@FieldCondition(op = Operation.like)
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	public String getRequestTime_start() {
		return requestTime_start;
	}
	public void setRequestTime_start(String requestTimeStart) {
		requestTime_start = requestTimeStart;
	}
	public String getRequestTime_end() {
		return requestTime_end;
	}
	public void setRequestTime_end(String requestTimeEnd) {
		requestTime_end = requestTimeEnd;
	}
	@FieldCondition(op = Operation.like)
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	
	@Override
	public String toString() {
		return "HuiyitongzhiSearchForm [requestTime_start=" + requestTime_start + ", requestTime_end=" + requestTime_end + "]";
	}
}
