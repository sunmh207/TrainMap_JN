package com.stanley;

public class JicheYunxingInfo {
	private String id;
	private String CH;//完整的车号
	
	private String driver;
	private String driver2;
	private String startTime;
	private String startStation;
	private String endTime;
	private String endStation;
	private String note;
	private String loconumberCode;//简化的车号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCH() {
		return CH;
	}
	public void setCH(String cH) {
		CH = cH;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getDriver2() {
		return driver2;
	}
	public void setDriver2(String driver2) {
		this.driver2 = driver2;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getStartStation() {
		return startStation;
	}
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getEndStation() {
		return endStation;
	}
	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getLoconumberCode() {
		return loconumberCode;
	}
	public void setLoconumberCode(String loconumberCode) {
		this.loconumberCode = loconumberCode;
	}
	
}
