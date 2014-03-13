package com.jitong.wap.action;

import java.util.ArrayList;

import com.jitong.anquanjiaoyu.domain.Shoujibao;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.form.SearchForm;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.SearchFormUtil;

public class WapShoujibaoAction  extends JITActionBase{
	private SearchForm searchForm;

	private String id;
	private Shoujibao shoujibao;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Shoujibao getShoujibao() {
		return shoujibao;
	}

	public void setShoujibao(Shoujibao Shoujibao) {
		this.shoujibao = Shoujibao;
	}

	public SearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(SearchForm searchForm) {
		this.searchForm = searchForm;
	}
	
	public String getListHQL(ArrayList<Object> params) throws JTException {
		Class<?> domainClass = Shoujibao.class;
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName()+" me "+hqlsufix +"order by me.sendTime desc";
		return hql;
	}
	public String query(){
		return SUCCESS;
	}
	public String detail() throws JTException{
		BaseDAO dao = new BaseDAO(DBtools.getSession());
		shoujibao = (Shoujibao) dao.findBoById(Shoujibao.class, id);
		return SUCCESS;
	}
	
}
