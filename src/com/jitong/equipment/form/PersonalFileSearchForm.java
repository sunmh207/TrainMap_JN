package com.jitong.equipment.form;

import com.jitong.common.form.FieldCondition;
import com.jitong.common.form.Operation;
import com.jitong.common.form.SearchForm;

public class PersonalFileSearchForm extends SearchForm {
	private String name;
	private String desc;

	private String createTime_start;
	private String createTime_end;

	@FieldCondition(op = Operation.like)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@FieldCondition(op = Operation.like)
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCreateTime_start() {
		return createTime_start;
	}

	public void setCreateTime_start(String createTimeStart) {
		createTime_start = createTimeStart;
	}

	public String getCreateTime_end() {
		return createTime_end;
	}

	public void setCreateTime_end(String createTimeEnd) {
		createTime_end = createTimeEnd;
	}

	@Override
	public String toString() {
		return "PersonalFileSearchForm [createTime_start=" + createTime_start + ", createTime_end=" + createTime_end + "]";
	}

}
