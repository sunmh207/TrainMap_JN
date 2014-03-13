package com.stanley.jichezhuangtai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.opensymphony.xwork2.Preparable;

public class JichezhuangtaiAction extends JITActionBase implements Preparable {
	private String qryCh;
	private String qryType;
	private Map<String,String> isLastList;
	private static BaseService svr;
	private Jichezhuangtai jichezhuangtai;

	public void prepare() throws JTException {
		if (svr == null) {
			svr = new BaseService();
		}
		if (jichezhuangtai != null && jichezhuangtai.getId() != null) {
			jichezhuangtai = (Jichezhuangtai) svr.findBoById(Jichezhuangtai.class, jichezhuangtai.getId());
		}
		isLastList = new HashMap<String,String>();
		isLastList.put("1","是");
		isLastList.put("0","否");
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		return INPUT;

	}

	public String delete() throws JTException {
		svr.deleteBo(Jichezhuangtai.class, jichezhuangtai.getId());
		return list();
	}

	public String save() throws Exception {
		try {
			// new
			jichezhuangtai.setSubmitTime(DateUtil.getCurrentTime());
			if (jichezhuangtai.getId() == null || "".equals(jichezhuangtai.getId())) {
				svr.createBo(jichezhuangtai);
				// modify
			} else {
				// password is not empty
				svr.updateBo(jichezhuangtai);
			}
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = " from Jichezhuangtai me where 1=1 ";

		if (!StringUtil.isEmpty(qryCh) && !"null".equals(qryCh)) {
			hql += " and me.ch like '%" + qryCh + "%'";
		}
		if (!StringUtil.isEmpty(qryType) && !"null".equals(qryType)) {
			hql += " and me.type like '%" + qryType + "%'";
		}
		hql +=" order by me.submitTime desc ";
		return hql;
	}

	public String getQryCh() {
		return qryCh;
	}

	public void setQryCh(String qryCh) {
		this.qryCh = qryCh;
	}

	public String getQryType() {
		return qryType;
	}

	public void setQryType(String qryType) {
		this.qryType = qryType;
	}

	public Jichezhuangtai getJichezhuangtai() {
		return jichezhuangtai;
	}

	public void setJichezhuangtai(Jichezhuangtai jichezhuangtai) {
		this.jichezhuangtai = jichezhuangtai;
	}

	public Map<String, String> getIsLastList() {
		return isLastList;
	}

	public void setIsLastList(Map<String, String> isLastList) {
		this.isLastList = isLastList;
	}

}
