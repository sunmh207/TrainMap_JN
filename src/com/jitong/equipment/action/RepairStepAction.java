package com.jitong.equipment.action;

import java.util.ArrayList;

import com.jitong.JitongConstants;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.equipment.domain.RepairPlan;
import com.jitong.equipment.domain.RepairStep;
import com.jitong.equipment.service.RepairPlanService;
import com.jitong.sms.domain.SMS;
import com.jitong.sms.service.SMSService;
import com.opensymphony.xwork2.Preparable;

public class RepairStepAction extends JITActionBase implements Preparable {
	private RepairStep repairStep;
	private String planId;
	private RepairPlan repairPlan;
	private static RepairPlanService service;
	private static SMSService smsService;

	public void prepare() throws JTException {
		if (service == null) {
			service = new RepairPlanService();
		}
		if (planId != null) {
			repairPlan = (RepairPlan) service.findBoById(RepairPlan.class, planId);
		}
		if (repairStep != null && repairStep.getId() != null) {
			repairStep = (RepairStep) service.findBoById(RepairStep.class, repairStep.getId());
		}
		if (smsService == null) {
			smsService = new SMSService();
		}
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		return INPUT;
	}

	public String delete() throws JTException {
		service.deleteBo(RepairStep.class, repairStep.getId());
		return list();
	}

	public String save() throws Exception {
		if(StringUtil.isEmpty(repairStep.getNumber())){
			addFieldError("repairStep.number","编号不能为空");
			return INPUT;
		}
		if(StringUtil.isEmpty(repairStep.getName())){
			addFieldError("repairStep.name","名称不能为空");
			return INPUT;
		}
		
		if(StringUtil.isEmpty(repairStep.getSmsContent())){
			addFieldError("repairStep.smsContent","短信内容不能为空");
			return INPUT;
		}
		if (repairStep.getId() == null || "".equals(repairStep.getId())) {
			repairStep.setPlanId(planId);
			service.createBo(repairStep);
			String idString = request.getParameter("useridStr");
			String[] ids = StringUtil.parseString2Array(idString, ",");
			for (int i = 0; i < ids.length; i++) {
				User user = SysCache.interpertUser(ids[i]);
				// send SMS
				SMS sms = new SMS();
				sms.setBusinessType("RepairStep");
				// sms.setBusinessId(callConfirm.getCallConfirmId());
				sms.setBusinessId(repairStep.getId());
				sms.setPhoneNumber(user.getPhoneNumber());
				sms.setRequestTime(DateUtil.getCurrentTime());
				sms.setSMSContent(repairStep.getSmsContent());
				sms.setRecipientId(ids[i]);
				User loginUser = (User) session.get(JitongConstants.USER);
				sms.setOperatorId(loginUser.getId());
				sms.setSenderInfo(this.getLoginUserInfo());
				smsService.insertSMS(sms);
			}
		} else {
			service.updateBo(repairStep);
		}
		return list();
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "from RepairStep me where me.planId='" + planId + "' order by me.number+0";
		return hql;
	}

	public String getCategoryId() {
		return "equipment.repairplan";
	}

	public RepairStep getRepairStep() {
		return repairStep;
	}

	public void setRepairStep(RepairStep repairStep) {
		this.repairStep = repairStep;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public RepairPlan getRepairPlan() {
		return repairPlan;
	}

	public void setRepairPlan(RepairPlan repairPlan) {
		this.repairPlan = repairPlan;
	}

}
