package com.stanley;

import java.io.Serializable;

import com.jitong.common.util.StringUtil;

public class TaiWei implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String gdName;
	private String x;
	private String y;
	private String currentCH;
	//private String loconumberCode;//车号缩位编码
	private String currentDriver;
	private String currentDriver2;
	private String startTime;
	private String startStation;
	private String endTime;
	private String endStation;
	private String lastUpdate;
	private String note;
	private String hasIssue;//有货预报里有记录，并且没有过期
	private String issueContent;//有货预报内容
	private String didian;//地点
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getCurrentCH() {
		return currentCH;
	}
	public void setCurrentCH(String currentCH) {
		this.currentCH = currentCH;
	}
	public String getCurrentDriver() {
		return currentDriver;
	}
	public void setCurrentDriver(String currentDriver) {
		this.currentDriver = currentDriver;
	}
	public String getCurrentDriver2() {
		return currentDriver2;
	}
	public void setCurrentDriver2(String currentDriver2) {
		this.currentDriver2 = currentDriver2;
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
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getGdName() {
		return gdName;
	}
	public void setGdName(String gdName) {
		this.gdName = gdName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getHasIssue() {
		return hasIssue;
	}
	public void setHasIssue(String hasIssue) {
		this.hasIssue = hasIssue;
	}
	
	public String getIssueContent() {
		return StringUtil.trim(issueContent);
	}
	public void setIssueContent(String issueContent) {
		this.issueContent = issueContent;
	}
	
	/*public String getLoconumberCode() {
		return loconumberCode;
	}
	public void setLoconumberCode(String loconumberCode) {
		this.loconumberCode = loconumberCode;
	}*/
	
	public String getDidian() {
		return didian;
	}
	public void setDidian(String didian) {
		this.didian = didian;
	}
	/*public String toString(){
		return this.getGdName()+"|"+this.getX()+"|"+this.getY()+"|"+this.getLoconumberCode()+"|"+this.getCurrentDriver()+"|"+this.getCurrentDriver2()+"|"+this.getStartTime()+"|"+this.getStartStation()+"|"+this.getEndTime()+"|"+this.getEndStation()+"|"+this.getNote()+"|"+this.getHasIssue()+"|"+this.getIssueContent();
	}*/
	public String toString(){
		return this.getGdName()+"|"+this.getX()+"|"+this.getY()+"|"+this.getCurrentCH()+"|"+this.getCurrentDriver()+"|"+this.getCurrentDriver2()+"|"+this.getStartTime()+"|"+this.getStartStation()+"|"+this.getEndTime()+"|"+this.getEndStation()+"|"+this.getNote()+"|"+this.getHasIssue()+"|"+this.getIssueContent();
	}
}
