package com.jitong.qiyezixun.form;

import com.jitong.common.form.SameUnitSearchForm;

public class AnnouncementSearchForm extends SameUnitSearchForm{
	private String announcementDate_start;
	private String announcementDate_end;
	public String getAnnouncementDate_start() {
		return announcementDate_start;
	}

	public void setAnnouncementDate_start(String announcementDateStart) {
		announcementDate_start = announcementDateStart;
	}

	public String getAnnouncementDate_end() {
		return announcementDate_end;
	}

	public void setAnnouncementDate_end(String announcementDateEnd) {
		announcementDate_end = announcementDateEnd;
	}


	@Override
	public String toString() {
		return "AnnouncementSearchForm [announcementDate_start=" + announcementDate_start
				+ ", announcementDate_end=" + announcementDate_end + "]";
	}
}
