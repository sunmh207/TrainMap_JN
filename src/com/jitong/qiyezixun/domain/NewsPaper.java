package com.jitong.qiyezixun.domain;

import com.jitong.JitongConstants;
import com.jitong.common.form.JTField;
import com.jitong.common.util.StringUtil;

public class NewsPaper {
	private String id;
	private String name;
	private String date;
	private String content;
	private String enable;
	private String creatorId;
	private String unitName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@JTField(chineseName="刊名",order=0)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JTField(chineseName="日期",order=5)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	@JTField(chineseName="内容",order=10)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if (content != null && content.length() > 600) {
			this.content = content.substring(0, 600);
		} else {
			this.content = content;
		}
	}

	public String getEnable() {
		return enable;
	}
	@JTField(chineseName="是否显示",order=15)
	public String getEnableTXT() {
		if ("false".equals(enable)) {
			return "否";
		} else {
			return "是";
		}
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getDateMM_DD() {
		String value = null;
		if (!StringUtil.isEmpty(date) && date.length() > 5) {
			value = date.substring(5, 10);
		}
		return value;
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

	public static void main(String[] args) {
		System.out.println("2011-02-10".substring(5, 10));
	}
	
}