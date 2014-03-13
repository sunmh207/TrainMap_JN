package com.stanley.beipin;

import java.util.ArrayList;
import java.util.List;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.StringUtil;
import com.opensymphony.xwork2.Preparable;

public class BeipinAction extends JITActionBase implements Preparable {
	private String beipinId;
	private String qryBeipinName ;
	private String qryIssueType;
	private String qryLocomodel;
	
	private static BaseService svr;
	private Beipin beipin;
	private List<String> issueTypeList;

	public void prepare() throws JTException {
		if (svr == null) {
			svr = new BaseService();
		}
		if (beipinId != null ) {
			beipin = (Beipin) svr.findBoById(Beipin.class, beipinId);
		}
			issueTypeList = new ArrayList<String>();
			issueTypeList.add("");
			issueTypeList.add("按机车发放");
			issueTypeList.add("按人员发放");
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		return INPUT;
	}

	public String delete() throws JTException {
		svr.deleteBo(Beipin.class, beipinId);
		return list();
	}

	public String save() throws Exception {
		try {
			// new
			if (beipin.getId() == null || "".equals(beipin.getId())) {
				svr.createBo(beipin);
				// modify
			} else {
				svr.updateBo(beipin);
			}
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = " from Beipin me where 1=1 ";
		if (!StringUtil.isEmpty(qryBeipinName) && !"null".equals(qryBeipinName)) {
			hql += " and me.beipinName like '%" + qryBeipinName + "%'";
		}
		if (!StringUtil.isEmpty(qryIssueType) && !"null".equals(qryIssueType)) {
			hql += " and me.issueType = '" + qryIssueType + "'";
		}
		if (!StringUtil.isEmpty(qryLocomodel) && !"null".equals(qryLocomodel)) {
			hql += " and me.locomodel like '%" + qryLocomodel + "%'";
		}
		//hql +=" order by me.changeTime desc ";
		return hql;
	}

	
	public Beipin getBeipin() {
		return beipin;
	}

	public void setBeipin(Beipin beipin) {
		this.beipin = beipin;
	}

	public String getBeipinId() {
		return beipinId;
	}

	public void setBeipinId(String beipinId) {
		this.beipinId = beipinId;
	}

	public String getQryBeipinName() {
		return qryBeipinName;
	}

	public void setQryBeipinName(String qryBeipinName) {
		this.qryBeipinName = qryBeipinName;
	}

	public String getQryIssueType() {
		return qryIssueType;
	}

	public void setQryIssueType(String qryIssueType) {
		this.qryIssueType = qryIssueType;
	}

	public String getQryLocomodel() {
		return qryLocomodel;
	}

	public void setQryLocomodel(String qryLocomodel) {
		this.qryLocomodel = qryLocomodel;
	}

	public List<String> getIssueTypeList() {
		return issueTypeList;
	}

	public void setIssueTypeList(List<String> issueTypeList) {
		this.issueTypeList = issueTypeList;
	}
	

}
