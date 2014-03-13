package com.jitong.yunshuzhihui.action;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.yunshuzhihui.domain.Yunshuzhihui;
import com.jitong.yunshuzhihui.form.YunshuzhihuiSearchForm;

public class YunshuzhihuiAction extends JITActionBase{
	YunshuzhihuiSearchForm searchForm;
	String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public YunshuzhihuiSearchForm getSearchForm() {
		return searchForm;
	}
	public void setSearchForm(YunshuzhihuiSearchForm searchForm) {
		this.searchForm = searchForm;
	}
	@Override
	public String getListHQL(ArrayList<Object> params) throws JTException {
		Class<?> domainClass = Yunshuzhihui.class;
		if(searchForm==null)searchForm = new YunshuzhihuiSearchForm();
		if("chucheng".equals(type)){
			searchForm.setContentType("【叫班】");
		}
		if("daicheng".equals(type)){
			searchForm.setContentType("【待乘】");
		}
		if("biangeng".equals(type)){
			searchForm.setContentType("【变更】");
		}
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName() + " me " + hqlsufix + " order by sendDate desc";
		System.out.println(params);
		return hql;
	}
	@Override
	public String getCategoryId() {
		System.out.println(type);
		if(StringUtil.isEmpty(type)){
			return "yunshuzhihui.yunshuzhihui";
		}
		return "yunshuzhihui."+type;
	}
	
}
