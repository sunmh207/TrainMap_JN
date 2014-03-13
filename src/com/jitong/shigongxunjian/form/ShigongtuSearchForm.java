package com.jitong.shigongxunjian.form;

import com.jitong.common.form.FieldCondition;
import com.jitong.common.form.Operation;
import com.jitong.shiguchuli.form.ZiliaochaxunSearchForm;


public class ShigongtuSearchForm extends ZiliaochaxunSearchForm{
	private String time_start;
	private String time_end;
	private String description;
	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String timeStart) {
		time_start = timeStart;
	}
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end(String timeEnd) {
		time_end = timeEnd;
	}
	
	@FieldCondition(op = Operation.like)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "ShigongtuSearchForm [time_start=" + time_start + ", time_end=" + time_end + "]";
	}
}
