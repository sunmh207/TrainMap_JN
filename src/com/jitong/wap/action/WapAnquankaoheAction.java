package com.jitong.wap.action;

import java.util.ArrayList;

import com.jitong.anquanjiancha.domain.Weizhangdengji;
import com.jitong.anquanjiancha.form.WeizhangdengjiSearchForm;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.SearchFormUtil;

public class WapAnquankaoheAction  extends JITActionBase{
	private WeizhangdengjiSearchForm searchForm;

	private String id;
	private Weizhangdengji weizhangdengji;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Weizhangdengji getWeizhangdengji() {
		return weizhangdengji;
	}

	public void setWeizhangdengji(Weizhangdengji weizhangdengji) {
		this.weizhangdengji = weizhangdengji;
	}

	public WeizhangdengjiSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(WeizhangdengjiSearchForm searchForm) {
		this.searchForm = searchForm;
	}
	
	public String getListHQL(ArrayList<Object> params) throws JTException {
		Class<?> domainClass = Weizhangdengji.class;
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName()+" me "+hqlsufix +"order by me.sendTime desc";
		return hql;
	}
	public String query(){
		return SUCCESS;
	}
	public String detail() throws JTException{
		BaseDAO dao = new BaseDAO(DBtools.getSession());
		weizhangdengji = (Weizhangdengji) dao.findBoById(Weizhangdengji.class, id);
		return SUCCESS;
	}
	
}
