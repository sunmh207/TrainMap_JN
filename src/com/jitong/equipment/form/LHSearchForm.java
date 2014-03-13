package com.jitong.equipment.form;

import com.jitong.common.form.FieldCondition;
import com.jitong.common.form.Operation;
import com.jitong.common.form.SearchForm;

public class LHSearchForm extends SearchForm {
	private String ch;
	private String cx;
	private String creatorName;
	private String createTime_start;
	private String createTime_end;
	private String mainPart;
	private String partName;
	private String symptom;

	@FieldCondition(op = Operation.like)
	public String getCx() {
		return cx;
	}

	public void setCx(String cx) {
		this.cx = cx;
	}

	@FieldCondition(op = Operation.like)
	public String getCh() {
		return ch;
	}

	public void setCh(String ch) {
		this.ch = ch;
	}

	@FieldCondition(op = Operation.like)
	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
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

	@FieldCondition(op = Operation.like)
	public String getMainPart() {
		return mainPart;
	}

	public void setMainPart(String mainPart) {
		this.mainPart = mainPart;
	}

	@FieldCondition(op = Operation.like)
	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	@FieldCondition(op = Operation.like)
	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	@Override
	public String toString() {
		return "LHSearchForm [createTime_start=" + createTime_start + ", createTime_end=" + createTime_end + "]";
	}

}
