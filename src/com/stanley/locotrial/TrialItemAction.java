package com.stanley.locotrial;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.StringUtil;
import com.opensymphony.xwork2.Preparable;

public class TrialItemAction extends JITActionBase implements Preparable {
	private String qryTrialName;
	private String qryLocomodel;
	private String trialitemId;

	private static BaseService svr;
	private TrialItem trialitem;

	public void prepare() throws JTException {
		if (svr == null) {
			svr = new BaseService();
		}
		if (trialitemId != null ) {
			trialitem = (TrialItem) svr.findBoById(TrialItem.class, trialitemId);
		}
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		return INPUT;

	}

	public String delete() throws JTException {
		svr.deleteBo(TrialItem.class, trialitemId);
		return list();
	}

	public String save() throws Exception {
		try {
			// new
			if (trialitem.getId() == null || "".equals(trialitem.getId())) {
				svr.createBo(trialitem);
				// modify
			} else {
				svr.updateBo(trialitem);
			}
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = " from TrialItem me where 1=1 ";

		if (!StringUtil.isEmpty(qryTrialName) && !"null".equals(qryTrialName)) {
			hql += " and me.trialName like '%" + qryTrialName + "%'";
		}
		if (!StringUtil.isEmpty(qryLocomodel) && !"null".equals(qryLocomodel)) {
			hql += " and me.locomodel like '%" + qryLocomodel + "%'";
		}
		return hql;
	}

	public String getQryTrialName() {
		return qryTrialName;
	}

	public void setQryTrialName(String qryTrialName) {
		this.qryTrialName = qryTrialName;
	}

	public String getQryLocomodel() {
		return qryLocomodel;
	}

	public void setQryLocomodel(String qryLocomodel) {
		this.qryLocomodel = qryLocomodel;
	}

	public String getTrialitemId() {
		return trialitemId;
	}

	public void setTrialitemId(String trialitemId) {
		this.trialitemId = trialitemId;
	}

	public TrialItem getTrialitem() {
		return trialitem;
	}

	public void setTrialitem(TrialItem trialitem) {
		this.trialitem = trialitem;
	}

	
	

}
