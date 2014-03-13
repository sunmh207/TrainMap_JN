package com.jitong.equipment.domain;

import com.jitong.common.form.JTField;
import com.jitong.common.form.JTFieldType;
import com.jitong.wnp.domain.Task;

public class RepairTask extends Task{
	private String CH;//车号
	private String repairDate;//检修日期
	private String repairContent;//检修内容
	private String note;//注意事项
	private String taiwei;//台位
	
	@JTField(chineseName="车号",order=0)
	public String getCH() {
		return CH;
	}
	public void setCH(String cH) {
		CH = cH;
	}
	@JTField(chineseName="检修日期",order=5)
	public String getRepairDate() {
		return repairDate;
	}
	public void setRepairDate(String repairDate) {
		this.repairDate = repairDate;
	}
	@JTField(chineseName="检修内容内容",order=10)
	public String getRepairContent() {
		return repairContent;
	}
	public void setRepairContent(String repairContent) {
		this.repairContent = repairContent;
	}
	@JTField(chineseName="注意事项",order=15)
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@JTField(chineseName="台位",order=20)
	public String getTaiwei() {
		return taiwei;
	}
	public void setTaiwei(String taiwei) {
		this.taiwei = taiwei;
	}
	
	/////override Tasks
	
	@JTField(fieldType=JTFieldType.UserID,chineseName="提交人",order=25)
	public String getOperatorId() {
		return super.getOperatorId();
	}
	@JTField(chineseName="提醒开始时间",order=30)
	public String getStartTime() {
		return super.getStartTime();
	}
	@JTField(chineseName="提醒结束时间",order=35)
	public String getEndTime() {
		return super.getEndTime();
	}
	
	@JTField(chineseName="循环",order=40)
	public String getIsRepeatTXT() {
		return super.getIsRepeatTXT();
	}
	@JTField(chineseName="提醒内容",order=45)
	public String getContent() {
		return super.getContent();
	}

}
