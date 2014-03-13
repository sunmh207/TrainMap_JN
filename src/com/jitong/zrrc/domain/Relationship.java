package com.jitong.zrrc.domain;

import com.jitong.common.form.JTField;

public class Relationship {
	private String id;
	private String personId;
	private String personName;
	private String managerIds;
	private String managerNames;
	private String familyR;
	private String familyName;
	private String familyPhoneNumber;
	private String kqUserId;
	private String createTime;
	
	private String lastEventDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getManagerIds() {
		return managerIds;
	}
	public void setManagerIds(String managerIds) {
		this.managerIds = managerIds;
	}
	public String getFamilyR() {
		return familyR;
	}
	public void setFamilyR(String familyR) {
		this.familyR = familyR;
	}
	@JTField(chineseName="家属姓名",order=10)
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	@JTField(chineseName="家属电话",order=15)
	public String getFamilyPhoneNumber() {
		return familyPhoneNumber;
	}
	public void setFamilyPhoneNumber(String familyPhoneNumber) {
		this.familyPhoneNumber = familyPhoneNumber;
	}
	@JTField(chineseName="考勤系统用户ID",order=20)
	public String getKqUserId() {
		return kqUserId;
	}
	public void setKqUserId(String kqUserId) {
		this.kqUserId = kqUserId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@JTField(chineseName="责任人",order=0)
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	@JTField(chineseName="包保人",order=5)
	public String getManagerNames() {
		return managerNames;
	}
	public void setManagerNames(String managerNames) {
		this.managerNames = managerNames;
	}
	@JTField(chineseName="最后事故日期",order=25)
	public String getLastEventDate() {
		return lastEventDate;
	}
	public void setLastEventDate(String lastEventDate) {
		this.lastEventDate = lastEventDate;
	}
	
	
}
