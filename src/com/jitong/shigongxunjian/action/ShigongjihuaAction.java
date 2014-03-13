package com.jitong.shigongxunjian.action;

import java.util.ArrayList;

import org.hibernate.Transaction;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.form.SearchForm;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.console.domain.User;
import com.jitong.shigongxunjian.domain.Shigongjihua;
import com.jitong.shigongxunjian.form.ShigongjihuaSearchForm;
import com.jitong.sms.service.SMSService;

public class ShigongjihuaAction extends JITActionBase {
	
	private Shigongjihua shigongjihua;

	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	private ShigongjihuaSearchForm searchForm;
	
	public Shigongjihua getShigongjihua() {
		return shigongjihua;
	}

	public void setShigongjihua(Shigongjihua shigongjihua) {
		this.shigongjihua = shigongjihua;
	}
	
	
	public ShigongjihuaSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(ShigongjihuaSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public String save(){
		shigongjihua = new Shigongjihua();
		shigongjihua.setSendTime(DateUtil.getCurrentTime("yyyy-MM-dd"));
		User loginUser = getLoginUser();
		shigongjihua.setSendByID(loginUser.getId());
		shigongjihua.setSendByName(loginUser.getName());
		shigongjihua.setDepartment(loginUser.getUnitDept());
		return SUCCESS;
	}
	public String doSave() throws JTException{
		if(shigongjihua!=null){
			SMSService service = new SMSService();
			shigongjihua.setContent(DateUtil.getCurrentTime());
			service.saveSMSProducer(shigongjihua);
		}
		return "thankYou";
	}
	public String delete() throws JTException {
		BaseService service = new BaseService();
		BaseDAO dao = new BaseDAO(service.getS());
		Transaction t = service.beginTransaction();
		Object o = dao.findBoById(Shigongjihua.class, id);
		dao.deletePo(o);
		service.commitTransaction(t);
		return "thankYou";
	}
	public String getListHQL(ArrayList<Object> params) throws JTException {
		Class<?> domainClass = Shigongjihua.class;
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName()+" me "+hqlsufix +" order by time desc";
		return hql;
	}

}
