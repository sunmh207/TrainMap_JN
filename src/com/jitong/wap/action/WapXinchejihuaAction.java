package com.jitong.wap.action;

import java.util.ArrayList;
import java.util.List;

import com.jitong.JitongConstants;
import com.jitong.common.Pager;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.console.domain.User;
import com.jitong.yunshuzhihui.domain.Yunshuzhihui;
import com.jitong.yunshuzhihui.form.YunshuzhihuiSearchForm;

public class WapXinchejihuaAction  extends JITActionBase{
	private YunshuzhihuiSearchForm searchForm;
	private String id;
	private Yunshuzhihui Yunshuzhihui;
	private String submit;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Yunshuzhihui getYunshuzhihui() {
		return Yunshuzhihui;
	}

	public void setYunshuzhihui(Yunshuzhihui Yunshuzhihui) {
		this.Yunshuzhihui = Yunshuzhihui;
	}

	public YunshuzhihuiSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(YunshuzhihuiSearchForm searchForm) {
		this.searchForm = searchForm;
	}
	
	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}
	@Override
	public String list() throws JTException {
		if("下一个".equals(submit)){
			//currentPage++;
			super.nextPage();
		}
		if("上一个".equals(submit)){
			//currentPage--;
			super.previousPage();
		}
		setPageSize(1);
		return super.list();
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		User loginUser = this.getLoginUser();
		setPageSize(1);
		Class<?> domainClass = Yunshuzhihui.class;
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql;
		if(StringUtil.isEmpty(hqlsufix)){
			//hql = "from " + domainClass.getName()+" me where me.sendDriver ='"+loginUser.getUnit()+"' order by me.sendDate desc";
			if(JitongConstants.ADMIN.equals(loginUser.getLoginName())){
				hql = "from " + domainClass.getName()+" me order by me.sendDate desc";
			}else{
				hql = "from " + domainClass.getName()+" me where me.phone ='"+loginUser.getPhoneNumber()+"' order by me.sendDate desc";
			}
		}else{
			//hql = "from " + domainClass.getName()+" me "+hqlsufix +" and me.sendDriver = '"+loginUser.getUnit()+"' order by me.sendDate desc";
			if(JitongConstants.ADMIN.equals(loginUser.getLoginName())){
				hql = "from " + domainClass.getName()+" me "+hqlsufix+"' order by me.sendDate desc";
			}else{
				hql = "from " + domainClass.getName()+" me "+hqlsufix +" and me.phone = '"+loginUser.getPhoneNumber()+"' order by me.sendDate desc";
			}
		}
		return hql;
	}
	public String query(){
		return SUCCESS;
	}
	public String detail() throws JTException{
		BaseDAO dao = new BaseDAO(DBtools.getSession());
		Yunshuzhihui = (Yunshuzhihui) dao.findBoById(Yunshuzhihui.class, id);
		return SUCCESS;
	}
	
	public String getCategoryId(){
		return "xinchejihua";
	}
	
}
