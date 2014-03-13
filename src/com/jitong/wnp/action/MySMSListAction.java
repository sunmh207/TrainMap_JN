package com.jitong.wnp.action;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.wnp.form.SMSVOSearchForm;

public class MySMSListAction extends JITActionBase {
	private SMSVOSearchForm searchForm;
	public SMSVOSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(SMSVOSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		//String where = generateWhereHQLBySearchForm(params);
		if(searchForm==null){
			searchForm = new SMSVOSearchForm();
		}
		searchForm.setMgrIds(this.getLoginUser().getId());
		String hqlsufix= SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql;
		hql = "from SMSVO me " + hqlsufix + " order by me.requestTime desc";
		return hql;
	}
	
	public String getCategoryId() {
		return "wnp.mysmslist";
	}
}
