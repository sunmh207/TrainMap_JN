package com.jitong.equipment.domain;

import com.jitong.common.form.JTField;
import com.jitong.common.form.JTFieldType;

/**
 * 零活
 * @author stanley.sun
 *
 */
public class LH {
	private String id;
	private String ch;
	private String cx;
	private String creatorId;
	private String creatorName;
	private String createTime;
	private String mainPart;
	private String partName;
	private String symptom;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JTField(chineseName="车号",order=0)
	public String getCh() {
		return ch;
	}
	public void setCh(String ch) {
		this.ch = ch;
	}
	@JTField(chineseName="车型",order=5)
	public String getCx() {
		return cx;
	}
	public void setCx(String cx) {
		this.cx = cx;
	}
	@JTField(fieldType=JTFieldType.UserID,chineseName="提票人",order=10)
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	@JTField(chineseName="提票时间",order=15)
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@JTField(chineseName="主要部件",order=20)
	public String getMainPart() {
		return mainPart;
	}
	public void setMainPart(String mainPart) {
		this.mainPart = mainPart;
	}
	@JTField(chineseName="部件名称",order=25)
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	@JTField(chineseName="故障现象",order=30)
	public String getSymptom() {
		return symptom;
	}
	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}
	
	
}
