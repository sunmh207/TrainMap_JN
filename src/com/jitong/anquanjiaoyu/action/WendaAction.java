package com.jitong.anquanjiaoyu.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;

import com.jitong.JitongConstants;
import com.jitong.anquanjiaoyu.domain.Wenda;
import com.jitong.anquanjiaoyu.form.WendaSearchForm;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.common.util.SysUtil;
import com.jitong.console.domain.User;
import com.jitong.sms.domain.InBoxVO;
import com.jitong.sms.service.InBoxService;
import com.jitong.sms.service.SMSService;

public class WendaAction extends JITActionBase{
	private Wenda wenda;
	
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private List<InBoxVO> details;

	private WendaSearchForm searchForm;
	
	public Wenda getWenda() {
		return wenda;
	}

	public void setWenda(Wenda wenda) {
		this.wenda = wenda;
	}
	
	
	public List getDetails() {
		return details;
	}

	public void setDetails(List details) {
		this.details = details;
	}

	public WendaSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(WendaSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public String save(){
		wenda = new Wenda(); 
		wenda.setNumber(SysUtil.generateBusinessCode(JitongConstants.BUSINESS_TYPE_DATI));
		return SUCCESS;
	}
	public String doSave() throws JTException{
		if(wenda!=null){
			SMSService service = new SMSService();
			User loginUser = this.getLoginUser();
			wenda.setCreatorId(loginUser.getId());
			wenda.setUnitName(loginUser.getUnit());
			wenda.setCreateTime(DateUtil.getCurrentTime());
			service.saveSMSProducer(wenda);
		}
		return "thankYou";
	}
	public String delete() throws JTException {
		BaseService service = new BaseService();
		BaseDAO dao = new BaseDAO(service.getS());
		Transaction t = service.beginTransaction();
		Object o = dao.findBoById(Wenda.class, id);
		dao.deletePo(o);
		service.commitTransaction(t);
		return "thankYou";
	}
	public String getListHQL(ArrayList<Object> params) throws JTException {
		if(searchForm==null){
			searchForm = new WendaSearchForm();
		}
		searchForm.setUnitName(this.getLoginUser().getUnit());

		Class<?> domainClass = Wenda.class;
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName()+" me "+hqlsufix + " order by createTime desc";
		return hql;
	}
	public String details() throws JTException{
		InBoxService inboxService = new InBoxService();
		this.details = inboxService.queryInBoxVOByBusinessInfo(JitongConstants.BUSINESS_TYPE_DATI, wenda.getNumber());
		return "details";
	}
	
public static void main(String[] args) {
	String generateBusinessCode = SysUtil.generateBusinessCode(JitongConstants.BUSINESS_TYPE_DATI);
	System.out.println(generateBusinessCode);
}
}
