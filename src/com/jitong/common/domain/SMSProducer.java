package com.jitong.common.domain;

import com.jitong.JitongConstants;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;

public abstract class SMSProducer {
	private String id;
	private String sendByID;
	private String sendTime = DateUtil.getCurrentTime();
	private String sendByName;
	private String sendToNames;
	private String sendToIds;
	private String sendToPhones;
	private String sendToUnit;
	private String sendToDept;
	private String sendToSelectBy = JitongConstants.SELECT_BY_PERSON;
	private String content;
	protected String title;
	private String department;
	private String needVoice = "0";
	private String operatorId;
	
	private String senderInfo;
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSendByID() {
		return sendByID;
	}

	public void setSendByID(String sendByID) {
		this.sendByID = sendByID;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getSendByName() {
		return sendByName;
	}

	public void setSendByName(String sendByName) {
		this.sendByName = sendByName;
	}

	public String getSendToNames() {
		return sendToNames;
	}

	public void setSendToNames(String sendToNames) {
		this.sendToNames = sendToNames;
	}

	public String getSendToIds() {
		return sendToIds;
	}

	public String getSendToPhones() {
		return sendToPhones;
	}

	public void setSendToPhones(String sendToPhones) {
		this.sendToPhones = sendToPhones;
	}

	public void setSendToIds(String sendToIds) {
		this.sendToIds = sendToIds;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String isNeedVoice() {
		return needVoice;
	}

	public void setNeedVoice(String needVoice) {
		this.needVoice = needVoice;
	}

	public String getSMSContent() {
		StringBuffer sbBuffer = new StringBuffer();
		if(!StringUtil.isEmpty(getTitle())){
			sbBuffer.append("[").append(getTitle()).append("]\n");	
		}
		sbBuffer.append(getContent());
		if(!StringUtil.isEmpty(getDepartment())){
			sbBuffer.append("(").append(getDepartment()).append(")");	
		}
		return sbBuffer.toString();
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public abstract String getBusinessType();

	public String getSendToUnit() {
		return sendToUnit;
	}

	public void setSendToUnit(String sendToUnits) {
		this.sendToUnit = sendToUnits;
	}

	public String getSendToDept() {
		return sendToDept;
	}

	public void setSendToDept(String sendToDepts) {
		this.sendToDept = sendToDepts;
	}

	public String getSendToSelectBy() {
		return sendToSelectBy;
	}

	public void setSendToSelectBy(String sendToSelectBy) {
		this.sendToSelectBy = sendToSelectBy;
	}

	public String getNeedVoice() {
		return needVoice;
	}

	public String getSenderInfo() {
		return senderInfo;
	}

	public void setSenderInfo(String senderInfo) {
		this.senderInfo = senderInfo;
	}

	@Override
	public String toString() {
		return "SMSProducer [id=" + id + ", sendByID=" + sendByID
				+ ", sendTime=" + sendTime + ", sendByName=" + sendByName
				+ ", sendToNames=" + sendToNames + ", sendToIds=" + sendToIds
				+ ", sendToUnit=" + sendToUnit + ", sendToDept=" + sendToDept
				+ ", sendToSelectBy=" + sendToSelectBy + ", content=" + content
				+ ", title=" + title + ", department=" + department
				+ ", needVoice=" + needVoice + "]";
	}



	
}
