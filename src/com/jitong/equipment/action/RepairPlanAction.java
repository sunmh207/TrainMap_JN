package com.jitong.equipment.action;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.ActionUtil;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.equipment.domain.RepairPlan;
import com.jitong.equipment.form.RepairPlanSearchForm;
import com.jitong.equipment.service.RepairPlanService;
import com.opensymphony.xwork2.Preparable;

public class RepairPlanAction extends JITActionBase implements Preparable {
	private RepairPlanSearchForm searchForm;
	private RepairPlan repairPlan;

	private static RepairPlanService service;

	public String businessClass = "RepairPlan";

	public void prepare() throws JTException {
		if (service == null) {
			service = new RepairPlanService();
		}
		if (repairPlan != null && repairPlan.getId() != null) {
			repairPlan = (RepairPlan)service.findBoById(RepairPlan.class, repairPlan.getId());
		}
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		return INPUT;
	}

	public String delete() throws JTException {
		service.deleteBo(RepairPlan.class,repairPlan.getId());
		list();
		return SUCCESS;
	}

	public String save() throws Exception {
		if(StringUtil.isEmpty(repairPlan.getCx())){
			addFieldError("repairPlan.cx","车型不能为空");
			return INPUT;
		}
		if(StringUtil.isEmpty(repairPlan.getNote())){
			addFieldError ("repairPlan.note","注意事项不能为空");
			return INPUT;
		}
		if (repairPlan.getId() == null || "".equals(repairPlan.getId())) {
			repairPlan.setCreateTime(DateUtil.getCurrentTime());
			service.createBo(repairPlan);
		} else {
			service.updateBo(repairPlan);
		}
		return list();
	}
	public String getListHQL(ArrayList<Object> params) throws JTException {
		Class<?> domainClass = ActionUtil.retireDomainClassNameFromAction(this);
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from RepairPlan me " + hqlsufix + " order by me.createTime desc";
		return hql;
	}


	public String getCategoryId() {
		return "equipment.repairplan";
	}

	public String getBusinessClass() {
		return businessClass;
	}

	public void setBusinessClass(String businessClass) {
		this.businessClass = businessClass;
	}

	public RepairPlanSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(RepairPlanSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public RepairPlan getRepairPlan() {
		return repairPlan;
	}

	public void setRepairPlan(RepairPlan repairPlan) {
		this.repairPlan = repairPlan;
	}


}
