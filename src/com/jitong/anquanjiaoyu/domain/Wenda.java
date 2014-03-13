package com.jitong.anquanjiaoyu.domain;

import com.jitong.common.domain.SMSProducer;
import com.jitong.common.form.JTField;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;

public class Wenda extends SMSProducer{
	
	private String number;
	private String answer;
	private String createTime = DateUtil.getCurrentTime();
	private  String correctCount;
	private String wrongCount;
	private String noreplyCount;
	
	private String startDate;
	private String endDate;
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

	public String getNumber() {
		return number;
	}

	@JTField(chineseName="答案",order=3)
	public String getAnswer() {
		return answer;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public void setAnswer(String answer) {
		this.answer = answer;
	}

	
	@JTField(chineseName="正确人数",order=4)
	public String getCorrectCount() {
		return correctCount;
	}


	public void setCorrectCount(String correctCount) {
		this.correctCount = correctCount;
	}

	@JTField(chineseName="错误人数",order=4)
	public String getWrongCount() {
		return wrongCount;
	}


	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setWrongCount(String wrongCount) {
		this.wrongCount = wrongCount;
	}


	@Override
	public String getBusinessType() {
		return "HUDONGWENDA";
	}
	@Override
	public String getSMSContent() {
		return super.getSMSContent()+"[回复"+getNumber()+":答案进行答题 ]";
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
	@JTField(chineseName="开始日期",order=5)
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	@JTField(chineseName="结束日期",order=10)
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getNoreplyCount() {
		return noreplyCount;
	}

	public void setNoreplyCount(String noreplyCount) {
		this.noreplyCount = noreplyCount;
	}
	
	
}
