package com.jitong.wnp.action;

import java.util.ArrayList;

import com.jitong.JitongConstants;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.console.domain.User;
import com.jitong.wnp.form.OutBoxDialVOSearchForm;
import com.jitong.wnp.form.SMSVOSearchForm;

public class OutBoxDialActiion extends JITActionBase {
	private OutBoxDialVOSearchForm searchForm;
	
	
	public OutBoxDialVOSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(OutBoxDialVOSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public String getCategoryId() {
		return "wnp.queryoutbox";
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		if(searchForm==null){
			searchForm = new OutBoxDialVOSearchForm();
		}
		searchForm.setMgrIds(this.getLoginUser().getId());
		String hqlsufix= SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from OutBoxDialVO me "+hqlsufix+" order by me.dtCreate desc";
		return hql;

	}

}
