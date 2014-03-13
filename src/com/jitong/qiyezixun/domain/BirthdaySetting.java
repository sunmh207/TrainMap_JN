package com.jitong.qiyezixun.domain;

import com.jitong.common.util.StringUtil;

public class BirthdaySetting {
	private String id;
	/**
	 * 生日慰问内容。替换符有可以被替换成相应的值
	 */
	private String format="{姓名}：集通铁路祝您生日快乐";
	
	public static final String NAME="\\{姓名\\}";
	public static final String DEPT="\\{部门\\}";
	public static final String UNIT="\\{单位\\}";
	public static final String BIRTHDAY="\\{生日\\}";
	
	public String enable="true";
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = StringUtil.boolString2IntString(enable);
	}
	
}
