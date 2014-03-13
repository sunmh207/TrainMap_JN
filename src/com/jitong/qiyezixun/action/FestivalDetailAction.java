package com.jitong.qiyezixun.action;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.opensymphony.xwork2.Preparable;

public class FestivalDetailAction extends JITActionBase implements Preparable {
	private static BaseService service;
	private String qryName = "";
	private String businessClass = "User";
	private String businessId;

	public void prepare() throws JTException {
		if (service == null) {
			service = new BaseService();
		}
	}

	public String list() throws JTException {
		return super.list();
	}

	public String getListHQL(ArrayList<Object> params) {
		return "from SMSVO s where s.recipientName like '%" + qryName + "%' and s.businessType='festival' and s.businessId='" + businessId
				+ "' order by s.GH desc";
	}

	public String getQryName() {
		return qryName;
	}

	public void setQryName(String qryName) {
		this.qryName = qryName;
	}

	public String getCategoryId() {
		return "qiyezixun.festival";
	}

	public String getBusinessClass() {
		return businessClass;
	}

	public void setBusinessClass(String businessClass) {
		this.businessClass = businessClass;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

}
