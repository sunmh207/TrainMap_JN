package com.jitong.anquanjiancha.action;

import java.util.ArrayList;

import org.hibernate.Transaction;

import com.jitong.anquanjiancha.domain.Ziliaochaxun;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.ActionUtil;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.SearchFormUtil;

public class ZiliaochaxunAction extends JITActionBase {
	private Ziliaochaxun ziliaochaxun;
	
	public Ziliaochaxun getZiliaochaxun() {
		return ziliaochaxun;
	}
	public void setZiliaochaxun(Ziliaochaxun ziliaochaxun) {
		this.ziliaochaxun = ziliaochaxun;
	}
	public String save(){
		return SUCCESS;
	}
	public String doSave() throws JTException{
		if(ziliaochaxun!=null){
			BaseDAO dao = new BaseDAO(DBtools.getSession());
			BaseService service = new BaseService();
			Transaction t = service.beginTransaction();
			dao.createBo(ziliaochaxun);
			service.commitTransaction(t);
			return "thankYou";
		}else {
			return INPUT;
		}	
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		Class<?> domainClass = ActionUtil.retireDomainClassNameFromAction(this);
		//String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		//String hql = "from " + domainClass.getName() + " me " + hqlsufix;
		String hql = "from Ziliaochaxun s order by s.sendTime desc";
		return hql;
	}
	 
}
