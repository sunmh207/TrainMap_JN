package com.jitong.anquanjiancha.action;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Transaction;

import com.jitong.anquanjiancha.domain.Weizhangdengji;
import com.jitong.anquanjiancha.form.WeizhangdengjiSearchForm;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.ActionUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.sms.service.SMSService;

public class WeizhangdengjiAction extends JITActionBase {
	
	private WeizhangdengjiSearchForm searchForm;
	
	
	public WeizhangdengjiSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(WeizhangdengjiSearchForm searchFrom) {
		this.searchForm = searchFrom;
	}
	private Weizhangdengji weizhangdengji;

	public Weizhangdengji getWeizhangdengji() {
		return weizhangdengji;
	}

	public void setWeizhangdengji(Weizhangdengji weizhangdengji) {
		this.weizhangdengji = weizhangdengji;
	}

	public String save(){
		weizhangdengji = new Weizhangdengji();
		weizhangdengji.setSendTime(new Date());
		return SUCCESS;
	}
	public String doSave() throws JTException{
		if(weizhangdengji!=null){
			SMSService service = new SMSService();
			Transaction t = service.beginTransaction();
			BaseDAO dao = new BaseDAO(service.getS());
			dao.createBo(weizhangdengji);
			service.commitTransaction(t);
		}
		return "thankYou";
	}
	
	public String getListHQL(ArrayList<Object> params) throws JTException {
		if(searchForm==null){
			searchForm = new WeizhangdengjiSearchForm();
		}
		searchForm.setUnitName(this.getLoginUser().getUnit());

		Class<?> domainClass = ActionUtil.retireDomainClassNameFromAction(this);
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName()+" me "+hqlsufix + " order by sendTime desc";
		return hql;
	}
}	
