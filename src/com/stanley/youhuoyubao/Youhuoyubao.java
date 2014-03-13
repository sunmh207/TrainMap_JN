package com.stanley.youhuoyubao;

import com.jitong.common.form.JTField;

public class Youhuoyubao {
	private String id;
	private String ch;
	private String reportTime;
	private String needShow;
	private String content;
	private String reporter;
	private String expireTime;
	private String sysTime;
	private String processType;
	private String infoType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JTField(chineseName="机车号",order=5)
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch; 
	}
	@JTField(chineseName="提报时间",order=10)
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	@JTField(chineseName="是否显示",order=15)
	public String getNeedShowTXT() {
		return "1".equals(needShow)?"是":"否";
	}
	public String getNeedShow() {
		return needShow;
	}
	public void setNeedShow(String needShow) {
		this.needShow = needShow;
	}
	@JTField(chineseName="故障内容",order=20)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@JTField(chineseName="提报人",order=25)
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	@JTField(chineseName="过期时间",order=30)
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	@JTField(chineseName="数据提交时间",order=35)
	public String getSysTime() {
		return sysTime;
	}
	public void setSysTime(String sysTime) {
		this.sysTime = sysTime;
	}
	@JTField(chineseName="处理方式",order=40)
	public String getProcessType() {
		return processType;
	}
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	@JTField(chineseName="信息类型",order=45)
	public String getInfoType() {
		return infoType;
	}
	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
}
