package com.jitong.meeting.domain;

import com.jitong.JitongConstants;
import com.jitong.common.form.JTField;
import com.jitong.common.form.JTFieldType;

public class Huiyijingshen {
	private String huiyijingshenId;
	private String time;
	private String deptName;
	private String content;
	private String sender;
	private String needVoice;
	private String creatorId;
	private String unitName;

	

	public String getHuiyijingshenId() {
		return huiyijingshenId;
	}

	public void setHuiyijingshenId(String huiyijingshenId) {
		this.huiyijingshenId = huiyijingshenId;
	}
	@JTField(chineseName="时间",order=1)
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	@JTField(chineseName="部门",order=5)
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@JTField(chineseName="内容",order=15)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@JTField(chineseName="发送人",order=10)
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	@JTField(fieldType=JTFieldType.StringBool,chineseName="是否语音",order=20)
	public String getNeedVoice() {
		return needVoice;
	}

	public void setNeedVoice(String needVoice) {
		if (needVoice == null||JitongConstants.STRING_FALSE.equals(needVoice)) {
			this.needVoice = JitongConstants.STRING_FALSE;
			return;
		}
		if(JitongConstants.STRING_TRUE.equals(needVoice)){
			this.needVoice = JitongConstants.STRING_TRUE;
			return;
		}
		
		if ("true".equals(needVoice.toLowerCase())) {
			this.needVoice = JitongConstants.STRING_TRUE;
		} else {
			this.needVoice = JitongConstants.STRING_FALSE;
		}
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
