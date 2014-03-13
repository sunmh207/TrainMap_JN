package com.stanley.locomotivedict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.StringUtil;
import com.opensymphony.xwork2.Preparable;

public class LocomotiveDictAction extends JITActionBase implements Preparable {
	private String qryCh;
	private String locomotiveId;
	private Map<String,String> didianList;
	private static BaseService svr;
	private Locomotive locomotive;

	public void prepare() throws JTException {
		if (svr == null) {
			svr = new BaseService();
		}
		if (locomotiveId != null ) {
			locomotive = (Locomotive) svr.findBoById(Locomotive.class, locomotiveId);
		}
		didianList = new HashMap<String,String>();
		didianList.put("青岛","青岛");
		didianList.put("聊城","聊城");
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		return INPUT;

	}

	public String delete() throws JTException {
		svr.deleteBo(Locomotive.class, locomotiveId);
		return list();
	}

	public String save() throws Exception {
		try {
			// new
			if (locomotive.getId() == null || "".equals(locomotive.getId())) {
				svr.createBo(locomotive);
				// modify
			} else {
				svr.updateBo(locomotive);
			}
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = " from Locomotive me where 1=1 ";

		if (!StringUtil.isEmpty(qryCh) && !"null".equals(qryCh)) {
			hql += " and me.loconumber like '%" + qryCh + "%'";
		}
		
		hql+=" order by me.loconumber";
		return hql;
	}

	public String getQryCh() {
		return qryCh;
	}

	public void setQryCh(String qryCh) {
		this.qryCh = qryCh;
	}


	public Locomotive getLocomotive() {
		return locomotive;
	}

	public void setLocomotive(Locomotive locomotive) {
		this.locomotive = locomotive;
	}

	public String getLocomotiveId() {
		return locomotiveId;
	}

	public void setLocomotiveId(String locomotiveId) {
		this.locomotiveId = locomotiveId;
	}

	public Map<String, String> getDidianList() {
		return didianList;
	}

	public void setDidianList(Map<String, String> didianList) {
		this.didianList = didianList;
	}

}
