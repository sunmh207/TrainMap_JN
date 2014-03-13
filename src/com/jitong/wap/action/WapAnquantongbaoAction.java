package com.jitong.wap.action;

import java.util.ArrayList;

import com.jitong.anquanjiancha.domain.Shigutongbao;
import com.jitong.anquanjiancha.form.ShigutongbaoSearchForm;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.SearchFormUtil;

public class WapAnquantongbaoAction  extends JITActionBase{
	private ShigutongbaoSearchForm searchForm;

	private String id;
	private Shigutongbao shigutongbao;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Shigutongbao getShigutongbao() {
		return shigutongbao;
	}

	public void setShigutongbao(Shigutongbao Shigutongbao) {
		this.shigutongbao = Shigutongbao;
	}

	public ShigutongbaoSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(ShigutongbaoSearchForm searchForm) {
		this.searchForm = searchForm;
	}
	
	public String getListHQL(ArrayList<Object> params) throws JTException {
		Class<?> domainClass = Shigutongbao.class;
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName()+" me "+hqlsufix +"order by me.sendTime desc";
		return hql;
	}
	public String query(){
		return SUCCESS;
	}
	public String detail() throws JTException{
		BaseDAO dao = new BaseDAO(DBtools.getSession());
		shigutongbao = (Shigutongbao) dao.findBoById(Shigutongbao.class, id);
		return SUCCESS;
	}
	
}
