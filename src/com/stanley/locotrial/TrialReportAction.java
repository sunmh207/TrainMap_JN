package com.stanley.locotrial;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.StringUtil;
import com.opensymphony.xwork2.Preparable;

public class TrialReportAction extends JITActionBase implements Preparable {
	private String qryTrialName;
	private String qryLocomodel;
	private String trialreportId;

	private static BaseService svr;
	private TrialReport trialreport;

	public void prepare() throws JTException {
		if (svr == null) {
			svr = new BaseService();
		}
		if (trialreportId != null ) {
			trialreport = (TrialReport) svr.findBoById(TrialReport.class, trialreportId);
		}
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		return INPUT;

	}

	public String delete() throws JTException {
		svr.deleteBo(TrialReport.class, trialreportId);
		return list();
	}

	public String save() throws Exception {
		try {
			// new
			if (trialreport.getId() == null || "".equals(trialreport.getId())) {
				svr.createBo(trialreport);
				// modify
			} else {
				svr.updateBo(trialreport);
			}
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = " from TrialReport me where 1=1 ";

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
		return trialreportId;
	}

	public void setTrialitemId(String trialreportId) {
		this.trialreportId = trialreportId;
	}

	public TrialReport getTrialitem() {
		return trialreport;
	}

	public void setTrialitem(TrialReport trialreport) {
		this.trialreport = trialreport;
	}

}
