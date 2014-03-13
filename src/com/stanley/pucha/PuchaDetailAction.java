package com.stanley.pucha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.StringUtil;
import com.opensymphony.xwork2.Preparable;

public class PuchaDetailAction extends JITActionBase implements Preparable {
	private Puchadan puchadan;
	private String puchadanId;
	private PuchaDetail puchadetail;
	private static PuchaService service;
	private Map isdoneList;
	
	public void prepare() throws JTException {
		if (service == null) {
			service = new PuchaService();
		}
		if (puchadanId != null) {
			puchadan = (Puchadan) service.findBoById(Puchadan.class, puchadanId);
		}
		if (puchadetail != null && puchadetail.getId() != null) {
			puchadetail = (PuchaDetail) service.findBoById(PuchaDetail.class, puchadetail.getId());
		}
		isdoneList = new HashMap<String,String>();
		isdoneList.put("1","是");
		isdoneList.put("0","否");
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		return INPUT;
	}

	public String delete() throws JTException {
		service.deleteBo(PuchaDetail.class, puchadetail.getId());
		service.updatePuchadanIsDoneByDetails(puchadetail.getPuchadanId());
		return list();
	}

	public String save() throws Exception {
		if(StringUtil.isEmpty(puchadetail.getCh())){
			addFieldError("puchadetail.ch","车号不能为空");
			return INPUT;
		}
		if(StringUtil.isEmpty(puchadetail.getItem())){
			addFieldError("puchadetail.item","普查项目不能为空");
			return INPUT;
		}
		
		if (puchadetail.getId() == null || "".equals(puchadetail.getId())) {
			puchadetail.setPuchadanId(puchadanId);
			service.createBo(puchadetail);
			service.updatePuchadanIsDoneByDetails(puchadetail.getPuchadanId());
		} else {
			service.updateBo(puchadetail);
			service.updatePuchadanIsDoneByDetails(puchadetail.getPuchadanId());
		}
		return list();
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "from PuchaDetail me where me.puchadanId='" + puchadanId + "' ";
		return hql;
	}

	public Puchadan getPuchadan() {
		return puchadan;
	}

	public void setPuchadan(Puchadan puchadan) {
		this.puchadan = puchadan;
	}

	public String getPuchadanId() {
		return puchadanId;
	}

	public void setPuchadanId(String puchadanId) {
		this.puchadanId = puchadanId;
	}

	public PuchaDetail getPuchadetail() {
		return puchadetail;
	}

	public void setPuchadetail(PuchaDetail puchadetail) {
		this.puchadetail = puchadetail;
	}

	public Map getIsdoneList() {
		return isdoneList;
	}

	public void setIsdoneList(Map isdoneList) {
		this.isdoneList = isdoneList;
	}

}
