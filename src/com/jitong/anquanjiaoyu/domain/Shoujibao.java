package com.jitong.anquanjiaoyu.domain;

import com.jitong.JitongConstants;
import com.jitong.common.domain.TimedSMSProducer;
import com.jitong.common.form.JTField;

public class Shoujibao extends TimedSMSProducer {
	private String createTime;
	private String creatorId;
	private String unitName;
	
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	
	@Override
	public String getSendToSelectBy() {
		return JitongConstants.SELECT_BY_ALL;
	}
	
	@Override
	public String getSchedule() {
		return "每100年";
	}
	@Override
	public String getBusinessType() {
		return "YEWUSHOUJIBAO";
	}
	
	@Override
	@JTField(chineseName="日期",order=1)
	public String getSendTime() {
		return super.getSendTime();
	}
	
	@Override
	@JTField(chineseName="标题",order=2)
	public String getTitle() {
		return super.getTitle();
	}
	@Override
	@JTField(chineseName="内容",order=3)
	public String getContent() {
		return super.getContent();
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
