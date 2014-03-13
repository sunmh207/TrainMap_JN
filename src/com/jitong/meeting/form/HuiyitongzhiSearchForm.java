package com.jitong.meeting.form;

import com.jitong.common.form.FieldCondition;
import com.jitong.common.form.Operation;
import com.jitong.common.form.SameUnitSearchForm;

public class HuiyitongzhiSearchForm extends SameUnitSearchForm {
	private String time_start;
	private String time_end;
	private String deptName;

	
	public String getTime_start() {
		return time_start;
	}
	@FieldCondition(op = Operation.like)
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
		return "HuiyitongzhiSearchForm [time_start=" + time_start + ", time_end=" + time_end + "]";
	}
}
