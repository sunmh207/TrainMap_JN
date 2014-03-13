package com.jitong.anquanjiancha.form;

import com.jitong.common.form.FieldCondition;
import com.jitong.common.form.Operation;
import com.jitong.common.form.SameUnitSearchForm;

public class RenwutongzhiSearchForm extends SameUnitSearchForm{
	private String sendTime_start;
	private String sendTime_end;
	private String title;

	public String getSendTime_start() {
		return sendTime_start;
	}
	public void setSendTime_start(String sendTime_start) {
		this.sendTime_start = sendTime_start;
	}
	
	public String getSendTime_end() {
		return sendTime_end;
	}
	public void setSendTime_end(String sendTime_end) {
		this.sendTime_end = sendTime_end;
	}
	
	@FieldCondition(op=Operation.like)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "KaohetongzhiSearchForm [sendTime_start=" + sendTime_start
				+ ", sendTime_end=" + sendTime_end + "]";
	}
	
	
}
