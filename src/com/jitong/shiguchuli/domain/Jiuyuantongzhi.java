package com.jitong.shiguchuli.domain;

import com.jitong.common.domain.SMSProducer;
import com.jitong.common.form.JTField;

public class Jiuyuantongzhi extends SMSProducer{
	private String needConfirm="0";
	
	private String startVoiceFile;
	private String creatorId;
	private String unitName;
	public String getNeedConfirm() {
		return needConfirm;
	}

	public void setNeedConfirm(String needConfirm) {
		this.needConfirm = needConfirm;
	}

	public String getStartVoiceFile() {
		return startVoiceFile;
	}

	public void setStartVoiceFile(String startVoiceFile) {
		this.startVoiceFile = startVoiceFile;
	}

	@Override
	public String getBusinessType() {
		return "JIUYUANTONGZHI";
	}
	
	@Override
	@JTField(chineseName="发送时间",order=1)
	public String getSendTime() {
		return super.getSendTime();
	}
	
	@Override
	@JTField(chineseName="发送对象",order=2)
	public String getSendToNames() {
		return super.getSendToNames();
	}

	@Override
	@JTField(chineseName="内容",order=5)
	public String getContent() {
		return super.getContent();
	}

	@JTField(chineseName="发送人",order=6)
	public String getSendByName() {
		return super.getSendByName();
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
