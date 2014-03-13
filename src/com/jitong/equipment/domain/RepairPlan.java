package com.jitong.equipment.domain;

import com.jitong.common.form.JTField;

public class RepairPlan {
	private String id;
	/**
	 * 车型
	 */
	private String cx;
	/**
	 * 部件
	 */
	private String part;
	/**
	 * 注意事项
	 */
	private String note;
	private String createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JTField(chineseName="车型",order=0)
	public String getCx() {
		return cx;
	}

	public void setCx(String cx) {
		this.cx = cx;
	}
	@JTField(chineseName="部件",order=5)
	public String getPart() {
		return part;
	}

	public void setPart(String part) {
		this.part = part;
	}
	@JTField(chineseName="注意事项",order=10)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
