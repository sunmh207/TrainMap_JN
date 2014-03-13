package com.jitong.anquanjiancha.domain;

import java.util.Date;

import com.jitong.common.form.JTField;

public class Weizhangdengji{
	private String id;
	private String content;
	private String title;
	private String code;
	private String status;
	private Date sendTime;
	private String creatorId;
	private String unitName;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JTField(chineseName="事故等级",order=4)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@JTField(chineseName="上板类型",order=5)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@JTField(chineseName="事故内容",order=3)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@JTField(chineseName="主题",order=2)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@JTField(chineseName="上传时间",order=1)
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	
}
