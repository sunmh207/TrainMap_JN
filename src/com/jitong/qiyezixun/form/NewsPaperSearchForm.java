package com.jitong.qiyezixun.form;

import com.jitong.common.form.FieldCondition;
import com.jitong.common.form.Operation;
import com.jitong.common.form.SameUnitSearchForm;

public class NewsPaperSearchForm extends SameUnitSearchForm{
	private String date_start;
	private String date_end;
	private String name;

	public String getDate_start() {
		return date_start;
	}

	public void setDate_start(String dateStart) {
		date_start = dateStart;
	}

	public String getDate_end() {
		return date_end;
	}

	public void setDate_end(String dateEnd) {
		date_end = dateEnd;
	}
	
	@FieldCondition(op=Operation.like)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "NewsPaperSearchForm [date_start=" + date_start
				+ ", date_end=" + date_end + "]";
	}
}
