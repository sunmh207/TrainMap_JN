package com.jitong.wap.action;

import java.util.ArrayList;

import com.jitong.anquanjiancha.domain.Ziliaoku;
import com.jitong.anquanjiancha.form.ZiliaokuSearchForm;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.SearchFormUtil;

public class WapZiliaokuAction  extends JITActionBase{
	private ZiliaokuSearchForm searchForm;

	private String id;
	private Ziliaoku ziliaoku;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Ziliaoku getZiliaoku() {
		return ziliaoku;
	}

	public void setZiliaoku(Ziliaoku Ziliaoku) {
		this.ziliaoku = Ziliaoku;
	}

	public ZiliaokuSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(ZiliaokuSearchForm searchForm) {
		this.searchForm = searchForm;
	}
	
	public String getListHQL(ArrayList<Object> params) throws JTException {
		Class<?> domainClass = Ziliaoku.class;
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName() + " me " + hqlsufix;
		hql = hql.replaceAll("me.content", "concat(me.content,me.title)");
		return hql;
	}
	public String query(){
		return SUCCESS;
	}
	public String detail() throws JTException{
		BaseDAO dao = new BaseDAO(DBtools.getSession());
		ziliaoku = (Ziliaoku) dao.findBoById(Ziliaoku.class, id);
		return SUCCESS;
	}
	
}
