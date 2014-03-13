package com.jitong.qiyezixun.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.ActionUtil;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.qiyezixun.domain.Festival;
import com.jitong.qiyezixun.form.FestivalSearchForm;
import com.jitong.sms.domain.TimedSMS;
import com.jitong.sms.service.SMSService;
import com.opensymphony.xwork2.Preparable;

public class FestivalAction extends JITActionBase implements Preparable {
	private FestivalSearchForm searchForm;
	private Festival festival;
	private static BaseService service;
	private static SMSService smsService;
	public String businessClass = "Festival";
	private static String businessType = "Festival";
	private String festivalId;
	
	public void prepare() throws JTException {
		if (service == null) {
			service = new BaseService();
		}
		if (smsService == null) {
			smsService = new SMSService();
		}

		if (festival != null && festival.getId() != null) {
			festival = (Festival) service.findBoById(Festival.class, festival.getId());
		}

	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		return INPUT;
	}
	public String delete() throws Exception{
		if (festivalId!=null){
			service.deleteBo(Festival.class,festivalId);
			List<TimedSMS> smslist = smsService.querySMSByBusiness(businessType, festivalId);
			smsService.deleteAll(smslist);
		}
		return list();
	}
	public String save() throws Exception {
		festival.setCreatorId(this.getLoginUser().getId());
		festival.setUnitName(this.getLoginUser().getUnit());
		String festivalId = service.createBo(festival);

		String idString = request.getParameter("useridStr");
		String[] ids = StringUtil.parseString2Array(idString, ",");
		for (int i = 0; i < ids.length; i++) {

			// send SMS
			/*SMS sms = new SMS();
			sms.setBusinessType(businessType);
			sms.setBusinessId(festivalId);
			User u = SysCache.interpertUser(ids[i]);
			sms.setPhoneNumber(u.getPhoneNumber());
			sms.setRequestTime(DateUtil.getCurrentTime(JitongConstants.JT_DATETIME_FORMAT));
			sms.setSMSContent(festival.getSMSContent());
			sms.setRecipientId(ids[i]);
			
			User loginUser = (User)session.get(JitongConstants.USER);
			sms.setSenderInfo(loginUser.getName()+"-"+loginUser.getUnitDept()+"-"+loginUser.getLastLoginIp());
			smsService.insertSMS(sms);*/
			
			
			User u = SysCache.interpertUser(ids[i]);
			
			
			TimedSMS timedSMS = new TimedSMS();
			timedSMS.setPhoneNumber(u.getPhoneNumber());
			timedSMS.setBusinessType(businessType);
			timedSMS.setBusinessId(festivalId);
			timedSMS.setRequestTime(DateUtil.getCurrentTime());
			timedSMS.setSMSContent(festival.getSMSContent());
			timedSMS.setRecipientId(u.getId());
			timedSMS.setSchedule("每年");
			
			String festivalDate = festival.getFestivalDate();
			timedSMS.setSendTime(festivalDate);
			timedSMS.setStartDate(festivalDate);
			String endDate = DateUtil.datePlus(festival.getFestivalDate(),"yyyy-MM-dd",Calendar.YEAR,100);
			timedSMS.setEndDate(endDate);
			
			User loginUser = this.getLoginUser();
			timedSMS.setOperatorId(loginUser.getId());
			
			timedSMS.setSenderInfo(this.getLoginUserInfo());
			smsService.createBo(timedSMS);
			
		}

		return SUCCESS;

	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		if (searchForm == null) {
			searchForm = new FestivalSearchForm();
		}
		searchForm.setUnitName(this.getLoginUser().getUnit());

		Class<?> domainClass = ActionUtil.retireDomainClassNameFromAction(this);
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName() + " me " + hqlsufix+" order by me.festivalDate desc";
		return hql;
	}

	public String getCategoryId() {
		return "qiyezixun.festival";
	}

	public FestivalSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(FestivalSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public Festival getFestival() {
		return festival;
	}

	public void setFestival(Festival festival) {
		this.festival = festival;
	}

	public String getBusinessClass() {
		return businessClass;
	}

	public void setBusinessClass(String businessClass) {
		this.businessClass = businessClass;
	}

	public String getFestivalId() {
		return festivalId;
	}

	public void setFestivalId(String festivalId) {
		this.festivalId = festivalId;
	}

}
