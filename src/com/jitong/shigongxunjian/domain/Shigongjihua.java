package com.jitong.shigongxunjian.domain;

import com.jitong.common.domain.SMSProducer;
import com.jitong.common.form.JTField;

public class Shigongjihua extends SMSProducer {

	private String time;
	private String zone;
	private String master;
	private String requirement;
	private String createTime;

	@JTField(chineseName="施工日期",order=1)
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	@JTField(chineseName="施工区段",order=5)
	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}
	@JTField(chineseName="负责人",order=10)
	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}
	@JTField(chineseName="施工要求",order=15)
	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@JTField(chineseName="施工人员",order=20)
	public String getSendToNames() {
		return super.getSendToNames();
	}

	@Override
	public String getSMSContent() {
		return "【施工计划通知】<施工时间>: " + getTime() + ";\n<区段>: " + getZone()
				+ ";\n<负责人>: " + getMaster() + ";\n<施工要求>: " + getRequirement();
	}

	@Override
	public String getBusinessType() {
		return "SHIGONGJIHUA";
	}

}
