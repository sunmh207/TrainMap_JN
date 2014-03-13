package com.jitong.qiyezixun.domain;

import com.jitong.common.form.JTField;

public class Festival {
	private String id;
	private String festivalName;
	private String festivalDate;
	private String SMSContent;
	private String creatorId;
	private String unitName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@JTField(chineseName="节日名称",order=0)
	public String getFestivalName() {
		return festivalName;
	}

	public void setFestivalName(String festivalName) {
		this.festivalName = festivalName;
	}
	@JTField(chineseName="日期",order=5)
	public String getFestivalDate() {
		return festivalDate;
	}

	public void setFestivalDate(String festivalDate) {
		this.festivalDate = festivalDate;
	}
	@JTField(chineseName="短信内容",order=10)
	public String getSMSContent() {
		return SMSContent;
	}

	public void setSMSContent(String sMSContent) {
		SMSContent = sMSContent;
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
