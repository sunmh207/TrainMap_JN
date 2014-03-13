package com.jitong.common.action;

import java.util.ArrayList;
import java.util.Date;

import com.jitong.common.exception.JTException;
import com.jitong.common.form.UserSMSDialSearchForm;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.wnp.domain.OutBoxDial;
import com.jitong.wnp.domain.OutBoxDialVO;
import com.opensymphony.xwork2.Preparable;

public class UserSMSDialAction extends JITActionBase implements Preparable {
	private static BaseService service;
	private String qryName = "";
	
	private String businessId;
	private String businessType;
	private String outboxdialId;
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
		
		UserSMSDialSearchForm searchForm = new UserSMSDialSearchForm();
		searchForm.setBusinessId(businessId);
		searchForm.setBusinessType(businessType);
		searchForm.setRecipientName(qryName);
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm , params);
		String hql = "from " + OutBoxDialVO.class.getName()+" me "+hqlsufix;
		return hql;
	}

	public String resend()throws JTException {	
		OutBoxDial dial= (OutBoxDial)service.findBoById(OutBoxDial.class, outboxdialId);
		if(dial!=null){
			dial.setFinger("0");
			dial.setDtCreate(new Date());
			service.updateBo(dial);
		}
		this.addActionMessage("语音已经重新发送!");
		return list();
	}
	
	public String getQryName() {
		return qryName;
	}

	public void setQryName(String qryName) {
		this.qryName = qryName;
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

	public String getOutboxdialId() {
		return outboxdialId;
	}

	public void setOutboxdialId(String outboxdialId) {
		this.outboxdialId = outboxdialId;
	}
	
}
