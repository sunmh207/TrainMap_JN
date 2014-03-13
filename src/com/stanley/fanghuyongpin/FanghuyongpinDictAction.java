package com.stanley.fanghuyongpin;

import java.util.ArrayList;
import java.util.List;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.StringUtil;
import com.opensymphony.xwork2.Preparable;
import com.stanley.locomotivedict.LocomotiveService;

public class FanghuyongpinDictAction extends JITActionBase implements Preparable {
	private String qryYpName;
	private String fanghuyongpindictId;
	private List<String> locomodelList;
	private static LocomotiveService svr;
	private FanghuyongpinDict fanghuyongpindict;

	public void prepare() throws JTException {
		if (svr == null) {
			svr = new LocomotiveService();
		}
		if (fanghuyongpindictId != null ) {
			fanghuyongpindict = (FanghuyongpinDict) svr.findBoById(FanghuyongpinDict.class, fanghuyongpindictId);
		}
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		try{
			locomodelList = svr.queryLocalLocoList();
		}catch(Exception e){
			this.logger.error(e.getClass());
		}
		return INPUT;

	}

	public String delete() throws JTException {
		svr.deleteBo(FanghuyongpinDict.class, fanghuyongpindictId);
		return list();
	}

	public String save() throws Exception {
		try {
			// new
			if (fanghuyongpindict.getId() == null || "".equals(fanghuyongpindict.getId())) {
				svr.createBo(fanghuyongpindict);
				// modify
			} else {
				svr.updateBo(fanghuyongpindict);
			}
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = " from FanghuyongpinDict me where 1=1 ";
		if (!StringUtil.isEmpty(qryYpName) && !"null".equals(qryYpName)) {
			hql += " and me.ypName like '%" + qryYpName + "%'";
		}
		return hql;
	}

	public String getQryYpName() {
		return qryYpName;
	}

	public void setQryYpName(String qryYpName) {
		this.qryYpName = qryYpName;
	}

	public String getFanghuyongpindictId() {
		return fanghuyongpindictId;
	}

	public void setFanghuyongpindictId(String fanghuyongpindictId) {
		this.fanghuyongpindictId = fanghuyongpindictId;
	}

	public List<String> getLocomodelList() {
		return locomodelList;
	}

	public void setLocomodelList(List<String> locomodelList) {
		this.locomodelList = locomodelList;
	}

	public FanghuyongpinDict getFanghuyongpindict() {
		return fanghuyongpindict;
	}

	public void setFanghuyongpindict(FanghuyongpinDict fanghuyongpindict) {
		this.fanghuyongpindict = fanghuyongpindict;
	}

	

}
