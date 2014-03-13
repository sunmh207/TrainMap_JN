package com.jitong.anquanjiancha.action;

import java.util.ArrayList;

import org.hibernate.Transaction;

import com.jitong.anquanjiancha.domain.Kaohetongzhi;
import com.jitong.anquanjiancha.form.KaohetongzhiSearchForm;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.ActionUtil;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.console.domain.User;
import com.jitong.sms.service.SMSService;

public class KaohetongzhiAction extends JITActionBase {
	
	private Kaohetongzhi kaohetongzhi;

	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	private KaohetongzhiSearchForm searchForm;
	
	public Kaohetongzhi getKaohetongzhi() {
		return kaohetongzhi;
	}

	public void setKaohetongzhi(Kaohetongzhi kaohetongzhi) {
		this.kaohetongzhi = kaohetongzhi;
	}
	
	
	public KaohetongzhiSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(KaohetongzhiSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public String save(){
		kaohetongzhi = new Kaohetongzhi();
		kaohetongzhi.setSendTime(DateUtil.getCurrentTime("yyyy-MM-dd"));
		User loginUser = getLoginUser();
		kaohetongzhi.setSendByID(loginUser.getId());
		kaohetongzhi.setSendByName(loginUser.getName());
		kaohetongzhi.setDepartment(loginUser.getUnitDept());
		kaohetongzhi.setDept(loginUser.getDept());
		kaohetongzhi.setUnit(loginUser.getUnit());
		kaohetongzhi.setUnitName(loginUser.getUnit());
		kaohetongzhi.setCreatorId(loginUser.getId());
		return SUCCESS;
	}
	public String doSave() throws JTException{
		if(kaohetongzhi!=null){
			SMSService service = new SMSService();
			String department = StringUtil.trim(kaohetongzhi.getUnit()) + "-" + StringUtil.trim(kaohetongzhi.getDept());
			kaohetongzhi.setDepartment(department );
			kaohetongzhi.setCreatorId(this.getLoginUser().getId());
			kaohetongzhi.setUnitName(this.getLoginUser().getUnit());
			service.saveSMSProducer(kaohetongzhi);
		}
		return "thankYou";
	}
	public String delete() throws JTException {
		BaseService service = new BaseService();
		BaseDAO dao = new BaseDAO(service.getS());
		Transaction t = service.beginTransaction();
		Object o = dao.findBoById(Kaohetongzhi.class, id);
		dao.deletePo(o);
		service.commitTransaction(t);
		return "thankYou";
	}
	public String getListHQL(ArrayList<Object> params) throws JTException {
		if(searchForm==null){
			searchForm = new KaohetongzhiSearchForm();
		}
		searchForm.setUnitName(this.getLoginUser().getUnit());

		Class<?> domainClass = ActionUtil.retireDomainClassNameFromAction(this);
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params); 
		String hql = "from " + domainClass.getName()+" me "+hqlsufix +" order by sendTime desc";
		return hql;
	}

}
