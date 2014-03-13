package com.jitong.anquanjiancha.action;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.anquanjiancha.domain.Kaohetongzhi;
import com.jitong.anquanjiancha.domain.Renwutongzhi;
import com.jitong.anquanjiancha.form.RenwutongzhiSearchForm;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.ActionUtil;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.console.domain.User;
import com.jitong.sms.service.SMSService;

public class RenwutongzhiAction extends JITActionBase {

	private Renwutongzhi renwutongzhi;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private RenwutongzhiSearchForm searchForm;

	public Renwutongzhi getRenwutongzhi() {
		return renwutongzhi;
	}

	public void setRenwutongzhi(Renwutongzhi renwutongzhi) {
		this.renwutongzhi = renwutongzhi;
	}

	public RenwutongzhiSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(RenwutongzhiSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	@Override
	public void validate() {
		return;
	}

	public String save() {
		renwutongzhi = new Renwutongzhi();
		renwutongzhi.setSendTime(DateUtil.getCurrentTime("yyyy-MM-dd"));
		User loginUser = getLoginUser();
		renwutongzhi.setSendByID(loginUser.getId());
		renwutongzhi.setSendByName(loginUser.getName());
		renwutongzhi.setDepartment(loginUser.getDept());
		renwutongzhi.setDept(loginUser.getDept());
		renwutongzhi.setUnit(loginUser.getUnit());
		renwutongzhi.setCreatorId(loginUser.getId());
		renwutongzhi.setUnitName(loginUser.getUnit());
		return SUCCESS;
	}

	public String doSave() throws JTException {
		if (renwutongzhi != null) {
			SMSService service = new SMSService();
			renwutongzhi.setSendByName(getLoginUser().getName());
			String department = StringUtil.trim(renwutongzhi.getUnit()) + "-" + StringUtil.trim(renwutongzhi.getDept());
			renwutongzhi.setDepartment(department );
			renwutongzhi.setCreatorId(this.getLoginUser().getId());
			renwutongzhi.setUnitName(this.getLoginUser().getUnit());
			service.saveSMSProducer(renwutongzhi);
		}
		return "thankYou";
	}

	public String delete() throws JTException {
		BaseService service = new BaseService();
		BaseDAO dao = new BaseDAO(service.getS());
		Transaction t = service.beginTransaction();
		Object o = dao.findBoById(Renwutongzhi.class, id);
		dao.deletePo(o);
		service.commitTransaction(t);
		return "thankYou";
	}
	public String details() throws JTException {
		Session s = DBtools.getSession();
		BaseDAO dao = new BaseDAO(s);
		renwutongzhi = (Renwutongzhi) dao.findBoById(Renwutongzhi.class, id);
		return SUCCESS;
	}
	public String getListHQL(ArrayList<Object> params) throws JTException {
		if(searchForm==null){
			searchForm = new RenwutongzhiSearchForm();
		}
		searchForm.setUnitName(this.getLoginUser().getUnit());

		Class<?> domainClass = ActionUtil.retireDomainClassNameFromAction(this);
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName() + " me " + hqlsufix+" order by me.sendTime desc";
		return hql;
	}

}
