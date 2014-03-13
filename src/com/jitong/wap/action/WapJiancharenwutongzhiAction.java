package com.jitong.wap.action;

import java.util.ArrayList;

import com.jitong.anquanjiancha.domain.Renwutongzhi;
import com.jitong.anquanjiancha.form.RenwutongzhiSearchForm;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.SearchFormUtil;

public class WapJiancharenwutongzhiAction  extends JITActionBase{
	private RenwutongzhiSearchForm searchForm;

	private String id;
	private Renwutongzhi Renwutongzhi;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Renwutongzhi getRenwutongzhi() {
		return Renwutongzhi;
	}

	public void setRenwutongzhi(Renwutongzhi Renwutongzhi) {
		this.Renwutongzhi = Renwutongzhi;
	}

	public RenwutongzhiSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(RenwutongzhiSearchForm searchForm) {
		this.searchForm = searchForm;
	}
	
	public String getListHQL(ArrayList<Object> params) throws JTException {
		Class<?> domainClass = Renwutongzhi.class;
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName()+" me "+hqlsufix +"order by me.sendTime desc";
		return hql;
	}
	public String query(){
		return SUCCESS;
	}
	public String detail() throws JTException{
		BaseDAO dao = new BaseDAO(DBtools.getSession());
		Renwutongzhi = (Renwutongzhi) dao.findBoById(Renwutongzhi.class, id);
		return SUCCESS;
	}
	
}
