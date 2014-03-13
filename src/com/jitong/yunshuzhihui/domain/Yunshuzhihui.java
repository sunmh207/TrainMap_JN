package com.jitong.yunshuzhihui.domain;

import java.util.Date;

import com.jitong.common.form.JTField;

public class Yunshuzhihui {
	
	private String id;
	private String name;
	private String phone;
	private String workNo;
	private Date sendDate;
	private String content;
	private String status;
	
	private String sendDriver;
	private String checi;
	private String jiche;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JTField(chineseName="姓名",order=1)
	public String getName() {
		return name;
	}
	public void setName(String hName) {
		this.name = hName;
	}
	@JTField(chineseName="手机",order=2)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@JTField(chineseName="工号",order=3)
	public String getWorkNo() {
		return workNo;
	}
	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}
	@JTField(chineseName="发送时间",order=4)
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	@JTField(chineseName="内容",order=5)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@JTField(chineseName="发送状态",order=6)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSendDriver() {
		return sendDriver;
	}
	public void setSendDriver(String sendDriver) {
		this.sendDriver = sendDriver;
	}
	public String getCheci() {
		return checi;
	}
	public void setCheci(String checi) {
		this.checi = checi;
	}
	public String getJiche() {
		return jiche;
	}
	public void setJiche(String jiche) {
		this.jiche = jiche;
	}
	@Override
	public String toString() {
		return "Yunshuzhihui [id=" + id + ", hName=" + name + ", phone="
				+ phone + ", workNo=" + workNo + ", sendDate=" + sendDate
				+ ", content=" + content + ", status=" + status
				+ ", sendDriver=" + sendDriver + ", checi=" + checi
				+ ", jiche=" + jiche + "]";
	}
	

	
}
