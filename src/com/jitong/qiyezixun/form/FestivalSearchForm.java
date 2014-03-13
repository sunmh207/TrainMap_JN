package com.jitong.qiyezixun.form;

import com.jitong.common.form.SameUnitSearchForm;

public class FestivalSearchForm extends SameUnitSearchForm{
	private String festivalDate_start;
	private String festivalDate_end;

	public String getFestivalDate_start() {
		return festivalDate_start;
	}

	public void setFestivalDate_start(String festivalDateStart) {
		festivalDate_start = festivalDateStart;
	}

	public String getFestivalDate_end() {
		return festivalDate_end;
	}

	public void setFestivalDate_end(String festivalDateEnd) {
		festivalDate_end = festivalDateEnd;
	}


	@Override
	public String toString() {
		return "FestivalSearchForm [festivalDate_start=" + festivalDate_start
				+ ", festivalDate_end=" + festivalDate_end + "]";
	}
}
