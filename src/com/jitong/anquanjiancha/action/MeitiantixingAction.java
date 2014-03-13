package com.jitong.anquanjiancha.action;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.anquanjiancha.domain.Meitiantixing;
import com.jitong.anquanjiancha.form.MeitiantixingSearchForm;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.console.domain.User;
import com.jitong.sms.service.SMSService;

public class MeitiantixingAction extends JITActionBase {

	private Meitiantixing meitiantixing;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private MeitiantixingSearchForm searchForm;

	public Meitiantixing getMeitiantixing() {
		return meitiantixing;
	}

	public void setMeitiantixing(Meitiantixing meitiantixing) {
		this.meitiantixing = meitiantixing;
	}

	public MeitiantixingSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(MeitiantixingSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	@Override
	public void validate() {
		return;
	}

	public String save() throws JTException {
		if (id != null && id.trim().length() > 0) {
			SMSService service = new SMSService();
			BaseDAO dao = new BaseDAO(service.getS());
			meitiantixing = (Meitiantixing) dao.findBoById(
					Meitiantixing.class, id);
			service.enhanceSMSProducer(meitiantixing);

		}
		if (meitiantixing == null) {
			meitiantixing = new Meitiantixing();
			meitiantixing.setSendTime(DateUtil.getCurrentTime("yyyy-MM-dd"));
			User loginUser = getLoginUser();
			meitiantixing.setSendByID(loginUser.getId());
			meitiantixing.setSendByName(loginUser.getName());
			meitiantixing.setDepartment(loginUser.getDept());
			meitiantixing.setCreatorId(loginUser.getId());
			meitiantixing.setUnitName(loginUser.getUnit());
		}
		return SUCCESS;
	}

	public String doSave() throws JTException {
		if (meitiantixing != null) {
			SMSService service = new SMSService();
			meitiantixing.setCreatorId(this.getLoginUser().getId());
			meitiantixing.setUnitName(this.getLoginUser().getUnit());
			service.saveTimedSMSProducer(meitiantixing);
			
		}
		return "thankYou";
	}

	public String details() throws JTException {
		Session s = DBtools.getSession();
		BaseDAO dao = new BaseDAO(s);
		meitiantixing = (Meitiantixing) dao.findBoById(
				Meitiantixing.class, id);
		return SUCCESS;
	}
	
	public String delete() throws JTException {
		SMSService service = new SMSService();
		Transaction t = service.beginTransaction();
		service.deleteTimedSMSProducer(Meitiantixing.class, id);
		service.commitTransaction(t);
		return "thankYou";
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		if(searchForm==null){
			searchForm = new MeitiantixingSearchForm();
		}
		searchForm.setUnitName(this.getLoginUser().getUnit());
		Class<?> domainClass = Meitiantixing.class;
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName() + " me " + hqlsufix +" order by me.sendTime desc";
		return hql;
	}

}
