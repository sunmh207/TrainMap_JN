package com.jitong.equipment.action;

import java.util.ArrayList;
import java.util.List;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.console.domain.User;
import com.jitong.equipment.domain.LH;
import com.jitong.equipment.form.LHSearchForm;
import com.jitong.equipment.service.RepairPlanService;
import com.opensymphony.xwork2.Preparable;

public class LHAction extends JITActionBase implements Preparable {
	private LHSearchForm searchForm;
	private LH lh;
	private List cxlist;
	private static RepairPlanService service;


	public void prepare() throws JTException {
		if (service == null) {
			service = new RepairPlanService();
		}
		if (lh != null && lh.getId() != null) {
			lh = (LH)service.findBoById(LH.class, lh.getId());
		}
		//ss1,ss3,ss4,ss4b,nd5,ss6,ss7,ss8,df5,df7,ss7d 

		cxlist = new ArrayList<String>();
		cxlist.add("SS1");
		cxlist.add("SS3");
		cxlist.add("SS4");
		cxlist.add("SS4B");
		cxlist.add("ND5");
		cxlist.add("SS6");
		cxlist.add("SS7");
		cxlist.add("SS8");
		cxlist.add("DF5");
		cxlist.add("DF7");
		cxlist.add("SS7D");
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		return INPUT;
	}

	public String delete() throws JTException {
		service.deleteBo(LH.class,lh.getId());
		list();
		return SUCCESS;
	}

	public String save() throws Exception {
		if (lh.getId() == null || "".equals(lh.getId())) {
			lh.setCreateTime(DateUtil.getCurrentTime());
			User u = this.getLoginUser();
			lh.setCreatorId(u.getId());
			lh.setCreatorName(u.getName());
			service.createBo(lh);
			addActionError("灵活提交成功");
		} else {
			service.updateBo(lh);
		}
		return list();
	}
	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from LH me " + hqlsufix + " order by me.createTime desc";
		return hql;
	}


	public String getCategoryId() {
		return "equipment.lh";
	}

	public LHSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(LHSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public LH getLH() {
		return lh;
	}

	public void setLH(LH lh) {
		this.lh = lh;
	}

	public LH getLh() {
		return lh;
	}

	public void setLh(LH lh) {
		this.lh = lh;
	}

	public List getCxlist() {
		return cxlist;
	}

	public void setCxlist(List cxlist) {
		this.cxlist = cxlist;
	}


}
