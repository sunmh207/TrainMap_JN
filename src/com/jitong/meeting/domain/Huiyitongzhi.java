package com.jitong.meeting.domain;

import com.jitong.common.form.JTField;
import com.jitong.common.form.JTFieldType;
import com.jitong.common.util.StringUtil;

public class Huiyitongzhi {
	private String huiyitongzhiId;
	private String time;
	private String deptName;
	private String content;
	private String sender;
	private String needVoice;
	private String verify;
	private String reuseFlag;
	private String creatorId;
	private String unitName;

	public String getHuiyitongzhiId() {
		return huiyitongzhiId;
	}

	public void setHuiyitongzhiId(String huiyitongzhiId) {
		this.huiyitongzhiId = huiyitongzhiId;
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
		this.needVoice = StringUtil.boolString2IntString(needVoice);
	}

	public String getReuseFlag() {
		return reuseFlag;
	}

	public void setReuseFlag(String reuseFlag) {
		this.reuseFlag = StringUtil.boolString2IntString(reuseFlag);
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = StringUtil.boolString2IntString(verify);
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
