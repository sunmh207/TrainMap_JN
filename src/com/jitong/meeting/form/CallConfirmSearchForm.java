package com.jitong.meeting.form;

import com.jitong.common.form.SearchForm;

public class CallConfirmSearchForm extends SearchForm{
	private String meetingTime_start;
	private String meetingTime_end;
	
	
	
	public String getMeetingTime_start() {
		return meetingTime_start;
	}



	public void setMeetingTime_start(String meetingTimeStart) {
		meetingTime_start = meetingTimeStart;
	}



	public String getMeetingTime_end() {
		return meetingTime_end;
	}



	public void setMeetingTime_end(String meetingTimeEnd) {
		meetingTime_end = meetingTimeEnd;
	}



	@Override
	public String toString() {
		return "CallConfirmSearchForm [meetingTime_start=" + meetingTime_start
				+ ", meetingTime_end=" + meetingTime_end + "]";
	}
}
