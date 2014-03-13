package com.jitong.meeting.form;

import com.jitong.common.form.SameUnitSearchForm;

public class HuiyijingshenSearchForm extends SameUnitSearchForm{
	private String time_start;
	private String time_end;
	
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
		return "HuiyijingshenSearchForm [time_start=" + time_start
				+ ", time_end=" + time_end + "]";
	}
}
