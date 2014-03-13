package com.stanley.beipin;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.StringUtil;
import com.opensymphony.xwork2.Preparable;
import com.stanley.locomotivedict.LocomotiveService;

public class BeipinReleaseAction extends JITActionBase implements Preparable {
	private String qryBeipinName;
	private String qryCh;
	private String qryGetPerson;
	private String qryGetTime_start;
	private String qryGetTime_end;
	private static BaseService svr;

	public void prepare() throws JTException {
		if (svr == null) {
			svr = new LocomotiveService();
		}
	}

	public String list() throws JTException {
		return super.list();
	}



	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = " from BeipinRelease me where 1=1 ";

		if (!StringUtil.isEmpty(qryBeipinName) && !"null".equals(qryBeipinName)) {
			hql += " and me.BeipinName like '%" + qryBeipinName + "%'";
		}
		if (!StringUtil.isEmpty(qryCh) && !"null".equals(qryCh)) {
			hql += " and me.ch like '%" + qryCh + "%'";
		}
		if (!StringUtil.isEmpty(qryGetPerson) && !"null".equals(qryGetPerson)) {
			hql += " and me.getPerson like '%" + qryGetPerson + "%'";
		}
		if (!StringUtil.isEmpty(qryGetTime_start) && !"null".equals(qryGetTime_start)) {
			hql += " and me.getTime>=  '" + qryGetTime_start + "'";
		}
		if (!StringUtil.isEmpty(qryGetTime_end) && !"null".equals(qryGetTime_end)) {
			hql += " and me.getTime<=  '" + qryGetTime_end + "'";
		}
		hql +=" order by me.getTime desc ";
		return hql;
	}

	public String getQryBeipinName() {
		return qryBeipinName;
	}

	public void setQryBeipinName(String qryBeipinName) {
		this.qryBeipinName = qryBeipinName;
	}

	public String getQryCh() {
		return qryCh;
	}

	public void setQryCh(String qryCh) {
		this.qryCh = qryCh;
	}

	public String getQryGetPerson() {
		return qryGetPerson;
	}

	public void setQryGetPerson(String qryGetPerson) {
		this.qryGetPerson = qryGetPerson;
	}

	public String getQryGetTime_start() {
		return qryGetTime_start;
	}

	public void setQryGetTime_start(String qryGetTime_start) {
		this.qryGetTime_start = qryGetTime_start;
	}

	public String getQryGetTime_end() {
		return qryGetTime_end;
	}

	public void setQryGetTime_end(String qryGetTime_end) {
		this.qryGetTime_end = qryGetTime_end;
	}

	
}
