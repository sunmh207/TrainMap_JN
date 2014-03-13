package com.jitong.common.domain;


public abstract class TimedSMSProducer extends SMSProducer{
	private String schedule = "每年"; 
	private String startDate;
	private String endDate;
	
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String offsetDays) {
		this.schedule = offsetDays;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "TimedSMSProducer [schedule=" + schedule + ", startDate="
				+ startDate + ", endDate=" + endDate + ", super()="
				+ super.toString() + "]";
	}
	
	
	
}
