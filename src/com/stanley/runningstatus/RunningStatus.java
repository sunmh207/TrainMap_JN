package com.stanley.runningstatus;

import com.jitong.common.form.JTField;

public class RunningStatus {
	private String ID;
	private String Info_ID; //INFO_ID	
	private String train;//TRAIN 车次	 
	private String locomotive;//LOCOMOTIVE 机车号	
	private String drivercode;//DRIVERCODE	
	private String drivername;//DRIVERNAME	
	private String vicedrivercode;//VICEDRIVERCODE	
	private String vicedrivername;//VICEDRIVERNAME	
	private String status;//STATUS	运行状态
	private String statustimer;//STATUSTIMER 运行状态时间	
	private String site;//SITE	
	private String sitecode;//SITECODE	
	private String other;//OTHER	
	private String processing_time;//PROCESSING_TIME监控文件处理时间	
	private String ljk_filename;//LJK_FILENAME	监控文件名
	private String linecode;//LINECODE 线路编号
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getInfo_ID() {
		return Info_ID;
	}
	public void setInfo_ID(String info_ID) {
		Info_ID = info_ID;
	}
	@JTField(chineseName="车次",order=5)
	public String getTrain() {
		return train;
	}
	public void setTrain(String train) {
		this.train = train;
	}
	@JTField(chineseName="机车号",order=10)
	public String getLocomotive() {
		return locomotive;
	}
	public void setLocomotive(String locomotive) {
		this.locomotive = locomotive;
	}
	
	@JTField(chineseName="司机编号",order=15)
	public String getDrivercode() {
		return drivercode;
	}
	public void setDrivercode(String drivercode) {
		this.drivercode = drivercode;
	}
	@JTField(chineseName="司机姓名",order=20)
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	
	@JTField(chineseName="副司机编号",order=25)
	public String getVicedrivercode() {
		return vicedrivercode;
	}
	public void setVicedrivercode(String vicedrivercode) {
		this.vicedrivercode = vicedrivercode;
	}
	@JTField(chineseName="副司机姓名",order=30)
	public String getVicedrivername() {
		return vicedrivername;
	}
	public void setVicedrivername(String vicedrivername) {
		this.vicedrivername = vicedrivername;
	}
	
	@JTField(chineseName="运行状态",order=35)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@JTField(chineseName="运行状态时间",order=40)
	public String getStatustimer() {
		return statustimer;
	}
	public void setStatustimer(String statustimer) {
		this.statustimer = statustimer;
	}
	
	@JTField(chineseName="地点",order=45)
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getSitecode() {
		return sitecode;
	}
	public void setSitecode(String sitecode) {
		this.sitecode = sitecode;
	}
	
	@JTField(chineseName="备注",order=50)
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getProcessing_time() {
		return processing_time;
	}
	public void setProcessing_time(String processing_time) {
		this.processing_time = processing_time;
	}
	public String getLjk_filename() {
		return ljk_filename;
	}
	public void setLjk_filename(String ljk_filename) {
		this.ljk_filename = ljk_filename;
	}
	public String getLinecode() {
		return linecode;
	}
	public void setLinecode(String linecode) {
		this.linecode = linecode;
	}

}
