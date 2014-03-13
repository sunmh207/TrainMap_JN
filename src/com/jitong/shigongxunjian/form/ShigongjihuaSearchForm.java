package com.jitong.shigongxunjian.form;

import com.jitong.common.form.FieldCondition;
import com.jitong.common.form.Operation;
import com.jitong.shiguchuli.form.ZiliaochaxunSearchForm;


public class ShigongjihuaSearchForm extends ZiliaochaxunSearchForm{
	private String time_start;
	private String time_end;
	private String zone;
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


	@Override
	public String toString() {
		return "ShigongtuSearchForm [time_start=" + time_start + ", time_end=" + time_end + "]";
	}
	
	@FieldCondition(op = Operation.like)
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
}
