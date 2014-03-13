package com.stanley.pucha;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.StringUtil;
import com.opensymphony.xwork2.Preparable;
import com.stanley.locomotivedict.LocomotiveService;

public class PuchadanAction extends JITActionBase implements Preparable {
	private String qryPuchaName;
	private String qryCh;
	private Map<String,String> isdoneList;
	private List<String> locoList;
	private static PuchaService svr;
	private Puchadan puchadan;

	public void prepare() throws JTException {
		if (svr == null) {
			svr = new PuchaService();
		}
		if (puchadan != null && puchadan.getId() != null) {
			puchadan = (Puchadan) svr.findBoById(Puchadan.class, puchadan.getId());
		}
		isdoneList = new TreeMap<String,String>();
		isdoneList.put("0","否");
		isdoneList.put("1","是");
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		try{
		LocomotiveService ls = new LocomotiveService(); 
		locoList= ls.queryLocalLocoList();
		}catch(Exception e){
			logger.error(e.getCause());
			e.printStackTrace();
		}
		return INPUT;

	}

	public String delete() throws JTException {
		svr.deletePuchadan(puchadan.getId());
		return list();
	}

	public String save() throws Exception {
		try {
			// new
			if (puchadan.getId() == null || "".equals(puchadan.getId())) {
				svr.createBo(puchadan);
				
				String chs = puchadan.getChs();
				if(chs!=null){
					String[] chsArry = chs.split(",");
					for(String ch:chsArry){
						PuchaDetail puchaDetail = new PuchaDetail();
						puchaDetail.setCh(ch);
						puchaDetail.setPuchadanId(puchadan.getId());
						puchaDetail.setIsDone("0");
						svr.createBo(puchaDetail);
					}
				}
				// modify
			} else {
				// password is not empty
				svr.updateBo(puchadan);
			}
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = " from Puchadan me where 1=1 ";
		if (!StringUtil.isEmpty(qryPuchaName) && !"null".equals(qryPuchaName)) {
			hql += " and me.puchaName like '%" + qryPuchaName + "%'";
		}
		if (!StringUtil.isEmpty(qryCh) && !"null".equals(qryCh)) {
			hql += " and me.chs like '%" + qryCh + "%'";
		}
		hql +=" order by me.puchaTime desc ";
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

	public Puchadan getPuchadan() {
		return puchadan;
	}

	public void setPuchadan(Puchadan puchadan) {
		this.puchadan = puchadan;
	}

	public Map<String, String> getIsdoneList() {
		return isdoneList;
	}

	public void setIsdoneList(Map<String, String> isdoneList) {
		this.isdoneList = isdoneList;
	}

	public List<String> getLocoList() {
		return locoList;
	}

	public void setLocoList(List<String> locoList) {
		this.locoList = locoList;
	}

}
