package com.jitong.anquanjiancha.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.anquanjiancha.domain.Anquantianshutixing;
import com.jitong.anquanjiancha.form.AnquantianshutixingSearchForm;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.console.domain.User;
import com.jitong.sms.service.SMSService;

public class AnquantianshutixingAction extends JITActionBase {
	private static Logger logger = Logger
			.getLogger(AnquantianshutixingAction.class);
	private static Map<String, String> schedules = new LinkedHashMap<String, String>();

	static {
		schedules.put("每天", "每天");
		schedules.put("每5天", "每5天");
		schedules.put("每10天", "每10天");
		schedules.put("每15天", "每15天");
		schedules.put("每20天", "每20天");
		schedules.put("每周", "每周");
		schedules.put("每2周", "每2周");
		schedules.put("每月", "每月");
		schedules.put("每2个月", "每2个月");
		schedules.put("每3个月", "每3个月");
		schedules.put("每4个月", "每4个月");
		schedules.put("每6个月", "每6个月");
		schedules.put("每9个月", "每9个月");
		schedules.put("每年", "每年");
	}

	public Map<String, String> getSchedules() {
		return schedules;
	}

	public void setSchedules(Map<String, String> schedules) {
		AnquantianshutixingAction.schedules = schedules;
	}

	private Anquantianshutixing anquantianshutixing;

	private AnquantianshutixingSearchForm searchForm;

	public Anquantianshutixing getAnquantianshutixing() {
		return anquantianshutixing;
	}

	public void setAnquantianshutixing(Anquantianshutixing anquantianshutixing) {
		this.anquantianshutixing = anquantianshutixing;
	}

	public AnquantianshutixingSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(AnquantianshutixingSearchForm searchForm) {
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
			anquantianshutixing = (Anquantianshutixing) dao.findBoById(
					Anquantianshutixing.class, id);
			service.enhanceSMSProducer(anquantianshutixing);
			
		}
		if (anquantianshutixing == null) {
			anquantianshutixing = new Anquantianshutixing();
			anquantianshutixing.setSendTime(DateUtil
					.getCurrentTime("yyyy-MM-dd"));
			User loginUser = getLoginUser();
			anquantianshutixing.setSendByID(loginUser.getId());
			anquantianshutixing.setSendByName(loginUser.getName());
			anquantianshutixing.setDepartment(loginUser.getDept());
			anquantianshutixing.setSchedule("每10天");
			anquantianshutixing.setCreatorId(this.getLoginUser().getId());
			anquantianshutixing.setUnitName(this.getLoginUser().getUnit());
		}
		return SUCCESS;
	}

	public String details() throws JTException {
		Session s = DBtools.getSession();
		BaseDAO dao = new BaseDAO(s);
		anquantianshutixing = (Anquantianshutixing) dao.findBoById(
				Anquantianshutixing.class, id);
		return SUCCESS;
	}

	public String doSave() throws JTException {
		if (anquantianshutixing != null) {
			System.out.println(anquantianshutixing);
			SMSService service = new SMSService();
			anquantianshutixing.setCreatorId(this.getLoginUser().getId());
			anquantianshutixing.setUnitName(this.getLoginUser().getUnit());
			service.saveTimedSMSProducer(anquantianshutixing);
			logger.debug(anquantianshutixing.getSendToIds());
		}
		return "thankYou";
	}

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String delete() throws JTException {
		SMSService service = new SMSService();
		Transaction t = service.beginTransaction();
		logger.debug("deleting Tixingxuexi id:" + id);
		service.deleteTimedSMSProducer(Anquantianshutixing.class, id);
		service.commitTransaction(t);
		return "list";
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		if(searchForm==null){
			searchForm = new AnquantianshutixingSearchForm();
		}
		searchForm.setUnitName(this.getLoginUser().getUnit());
		Class<?> domainClass = Anquantianshutixing.class;
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName() + " me " + hqlsufix +" order by me.sendTime desc";
		return hql;
	}
	
}
