package com.jitong.anquanjiaoyu.domain;

import com.jitong.common.form.JTField;

public class DatiAnswer {

	private String id;
	private String title;
	private String datiId;
	private String securityCode;
	private String answer;
	private String name;
	private String dept;
	private String phone;
	private String replyTime;
	private String userId;
	private String createDate;
	private String correctFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDatiId() {
		return datiId;
	}

	public void setDatiId(String datiId) {
		this.datiId = datiId;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@JTField(chineseName = "答题人", order = 1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JTField(chineseName = "单位", order = 2)
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@JTField(chineseName = "手机", order = 3)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@JTField(chineseName = "答题时间", order = 4)
	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCorrectFlag() {
		return correctFlag;
	}

	public void setCorrectFlag(String correctFlag) {
		this.correctFlag = correctFlag;
	}

	public String getCorrectFlagHTML() {
		if ("1".equals(correctFlag)) {
			return "<font color='green'>正确</font>";
		} else if ("0".equals(correctFlag)) {
			return "<font color='green'>错误</font>";
		} else {
			return "";
		}
	}
}
