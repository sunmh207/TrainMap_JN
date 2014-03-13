package com.stanley.pucha;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.StringUtil;
import com.opensymphony.xwork2.Preparable;

public class PuchaDetailVAction extends JITActionBase implements Preparable {
	private String qryPuchaName;
	private String qryCh;
	private String qryIsDone;
	private static PuchaService svr;
	private PuchaDetail puchadetail;
	private Map<String,String> isDoneList;
	
	public void prepare() throws JTException {
		if (svr == null) {
			svr = new PuchaService();
		}
		if (puchadetail != null && puchadetail.getId() != null) {
			puchadetail = (PuchaDetail) svr.findBoById(PuchaDetail.class, puchadetail.getId());
		}
		isDoneList = new TreeMap<String,String>();
		isDoneList.put("", "全部");
		isDoneList.put("0", "否");
		isDoneList.put("1", "是");
	}

	public String list() throws JTException {
		return super.list();
	}


	public String delete() throws JTException {
		svr.deletePuchaDetail(puchadetail.getId());
		return list();
	}
	public String done() throws JTException {
		puchadetail.setIsDone("1");
		svr.updatePuchaDetail(puchadetail);
		svr.updatePuchadanIsDoneByDetails(puchadetail.getPuchadanId());
		return list();
	}


	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = " from PuchaDetailV me where 1=1 ";
		if (!StringUtil.isEmpty(qryPuchaName) && !"null".equals(qryPuchaName)) {
			hql += " and me.puchaName like '%" + qryPuchaName + "%'";
		}
		if (!StringUtil.isEmpty(qryCh) && !"null".equals(qryCh)) {
			hql += " and me.ch like '%" + qryCh + "%'";
		}
		if (!StringUtil.isEmpty(qryIsDone) && !"null".equals(qryIsDone)) {
			hql += " and me.puchadetail_isDone = '" + qryIsDone + "'";
		}
		hql +=" order by me.puchadan_puchaTime desc ";
		return hql;
	}

	public String getQryCh() {
		return qryCh;
	}

	public void setQryCh(String qryCh) {
		this.qryCh = qryCh;
	}

	public String getQryPuchaName() {
		return qryPuchaName;
	}

	public void setQryPuchaName(String qryPuchaName) {
		this.qryPuchaName = qryPuchaName;
	}

	public PuchaDetail getPuchadetail() {
		return puchadetail;
	}

	public void setPuchadetail(PuchaDetail puchadetail) {
		this.puchadetail = puchadetail;
	}

	public String getQryIsDone() {
		return qryIsDone;
	}

	public void setQryIsDone(String qryIsDone) {
		this.qryIsDone = qryIsDone;
	}

	public Map<String, String> getIsDoneList() {
		return isDoneList;
	}

	public void setIsDoneList(Map<String, String> isDoneList) {
		this.isDoneList = isDoneList;
	}


}
