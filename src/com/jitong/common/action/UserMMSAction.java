package com.jitong.common.action;

import java.util.ArrayList;

import com.jitong.common.exception.JTException;
import com.jitong.common.form.UserSMSSearchForm;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.sms.domain.MMSVO;
import com.opensymphony.xwork2.Preparable;

public class UserMMSAction extends JITActionBase implements Preparable {
	private static BaseService service;
	private String qryName = "";
	private String businessClass = "User";
	private String businessId;
	private String businessType;
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
		
		UserSMSSearchForm searchForm = new UserSMSSearchForm();
		searchForm.setBusinessId(businessId);
		searchForm.setBusinessType(businessType);
		searchForm.setRecipientName(qryName);
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm , params);
		System.out.println(params);
		String hql = "from " + MMSVO.class.getName()+" me "+hqlsufix;
		return hql;
	}

	public String getQryName() {
		return qryName;
	}

	public void setQryName(String qryName) {
		this.qryName = qryName;
	}

	public String getBusinessClass() {
		return businessClass;
	}

	public void setBusinessClass(String businessClass) {
		this.businessClass = businessClass;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	

}
