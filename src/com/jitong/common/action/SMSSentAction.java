package com.jitong.common.action;

import java.util.ArrayList;

import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.opensymphony.xwork2.Preparable;

public class SMSSentAction extends JITActionBase implements Preparable {
	private static BaseService service;
	private String smsid = "";
	private String categoryId;

	public void prepare() throws JTException {
		if (service == null) {
			service = new BaseService();
		}
	}

	public String list() throws JTException {
		return super.list();
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "from SMSSent s where s.SMSID='"+smsid+"'";
		return hql;
	}

	public String getSmsid() {
		return smsid;
	}

	public void setSmsid(String smsid) {
		this.smsid = smsid;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}


}
