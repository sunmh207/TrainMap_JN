package com.jitong.anquanjiaoyu.domain;

import com.jitong.common.domain.SMSProducer;
import com.jitong.common.form.JTField;

public class Dati extends SMSProducer{
	private String createTime;
	private String officialAnswer;
	private String startDate;
	private String endDate;
	private String result;
	private String creatorId;
	private String unitName;
	
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
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	@JTField(chineseName="标题",order=1)
	public String getTitle() {
		return super.getTitle();
	}
	
	@Override
	@JTField(chineseName="内容",order=2)
	public String getContent() {
		return super.getContent();
	}
	@Override
	@JTField(chineseName="答题对象",order=3)
	public String getSendToNames() {
		return super.getSendToNames();
	}
	@Override
	public String getBusinessType() {
		return "WAPDATI";
	}
	@JTField(chineseName="答案",order=4)
	public String getOfficialAnswer() {
		return officialAnswer;
	}

	public void setOfficialAnswer(String officialAnswer) {
		this.officialAnswer = officialAnswer;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
