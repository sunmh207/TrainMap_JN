package com.jitong.wnp.action;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.wnp.domain.ReceivedSMS;
import com.jitong.wnp.form.ReceivedSMSSearchForm;
import com.jitong.wnp.form.SMSVOSearchForm;

public class ReceivedSMSAction extends JITActionBase {
	private ReceivedSMSSearchForm searchForm;
	private String smsId;

	public ReceivedSMSSearchForm getSearchForm() {
		return searchForm;
	}
	
	public void setSearchForm(ReceivedSMSSearchForm searchForm) { 
		this.searchForm=searchForm;
	}

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		//String where = generateWhereHQLBySearchForm(params);
		if(searchForm==null){
			searchForm = new ReceivedSMSSearchForm();
		}
		searchForm.setMgrIds(this.getLoginUser().getId());
		String hqlsufix= SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from ReceivedSMS me " + hqlsufix + " order by me.reachTime desc";
		return hql;
	}

	public String delete() throws JTException {
		BaseService service = new BaseService();
		service.deleteBo(ReceivedSMS.class, smsId);
		return list();
	}

	public String getCategoryId() {
		return "wnp.receivedsmslist";
	}

	
}
