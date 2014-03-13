package com.jitong.anquanjiancha.domain;


public class KaohetongzhiSendTo {
	private String id;
	private String kaohetongzhiId;
	private String sendToId;
	
	private String department;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKaohetongzhiId() {
		return kaohetongzhiId;
	}
	public void setKaohetongzhiId(String kaohetongzhiId) {
		this.kaohetongzhiId = kaohetongzhiId;
	}
	public String getSendToId() {
		return sendToId;
	}
	public void setSendToId(String sendToId) {
		this.sendToId = sendToId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "KaohetongzhiSendTo [id=" + id + ", kaohetongzhiId="
				+ kaohetongzhiId + ", sendToId=" + sendToId + ", department="
				+ department + "]";
	}
	
	
	
}
