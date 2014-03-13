package com.jitong.meeting.action;

import java.util.ArrayList;
import java.util.List;

import com.jitong.JitongConstants;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.ActionUtil;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.meeting.domain.CallConfirm;
import com.jitong.meeting.domain.Huiyijingshen;
import com.jitong.meeting.form.HuiyijingshenSearchForm;
import com.jitong.meeting.service.CallConfirmService;
import com.jitong.meeting.service.HuiyijingshenService;
import com.jitong.sms.domain.SMS;
import com.jitong.sms.service.SMSService;
import com.opensymphony.xwork2.Preparable;

public class HuiyijingshenAction extends JITActionBase implements Preparable {
	private Huiyijingshen huiyijingshen;
	private HuiyijingshenSearchForm searchForm;
	
	private static String businessType="huiyijingshen";
	private HuiyijingshenService service;
	private CallConfirmService callConfirmService;
	private SMSService smsService;
	
	private List<Huiyijingshen> huiyijingshens;

	private String huiyijingshenId;
	
	public void prepare() throws JTException {
		if (service == null) {
			service = new HuiyijingshenService();
		}
		if (callConfirmService == null) {
			callConfirmService = new CallConfirmService();
		}
		if (smsService == null) {
			smsService = new SMSService();
		}

		if (huiyijingshen != null && huiyijingshen.getHuiyijingshenId() != null) {
			huiyijingshen = service.findHuiyijingshen(huiyijingshen.getHuiyijingshenId());
		}
	}

	public String input() throws JTException {
		return INPUT;
	}
	public String delete() throws Exception{
		if (huiyijingshenId!=null){
			service.deleteHuiyijingshen(huiyijingshenId);
		}
		return list();
	}
	public String save() throws JTException {
		huiyijingshen.setCreatorId(this.getLoginUser().getId());
		huiyijingshen.setUnitName(this.getLoginUser().getUnit());
		String huiyijingshenId = service.insertHuiyijingshen(huiyijingshen);

		// insert huiyijingshenDetail
		String idString = request.getParameter("useridStr");
		String[] ids = StringUtil.parseString2Array(idString, ",");
		for (int i = 0; i < ids.length; i++) {
			CallConfirm callConfirm = new CallConfirm();
			callConfirm.setBusinessType(businessType);
			callConfirm.setContent(huiyijingshen.getContent());

			User user = SysCache.interpertUser(ids[i]);

			callConfirm.setDeptName(user.getDept());
			callConfirm.setMeetingTime(huiyijingshen.getTime());
			callConfirm.setNeedVoice(huiyijingshen.getNeedVoice());
			callConfirm.setPhoneNumber(user.getPhoneNumber());
			callConfirm.setRecipientId(ids[i]);
			callConfirm.setRecipientName(user.getName());
			callConfirm.setUnitName(user.getUnit());

			callConfirmService.insertCallConfirm(callConfirm);

			// send SMS
			SMS sms = new SMS();
			sms.setBusinessType(businessType);
			//sms.setBusinessId(callConfirm.getCallConfirmId());
			sms.setBusinessId(huiyijingshenId);
			//User u = SysCache.interpertUser(ids[i]);
			sms.setPhoneNumber(user.getPhoneNumber());
			sms.setRequestTime(DateUtil.getCurrentTime(JitongConstants.JT_DATETIME_FORMAT));
			sms.setSMSContent(huiyijingshen.getContent());
			sms.setNeedVoice(huiyijingshen.getNeedVoice());
			sms.setRecipientId(ids[i]);
			User loginUser = (User)session.get(JitongConstants.USER);
			//sms.setSenderInfo(loginUser.getName()+"-"+loginUser.getUnitDept()+"-"+loginUser.getLastLoginIp());
			sms.setSenderInfo(this.getLoginUserInfo());
			sms.setOperatorId(loginUser.getId());
			smsService.insertSMS(sms);
		}

		return SUCCESS;
	}

	public Huiyijingshen getHuiyijingshen() {
		return huiyijingshen;
	}

	public void setHuiyijingshen(Huiyijingshen huiyijingshen) {
		this.huiyijingshen = huiyijingshen;
	}

	public List<Huiyijingshen> getHuiyijingshens() {
		return huiyijingshens;
	}

	public void setHuiyijingshens(List<Huiyijingshen> huiyijingshens) {
		this.huiyijingshens = huiyijingshens;
	}
	public String getCategoryId() {
		return "meeting.huiyijingshen";
	}

	public HuiyijingshenSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(HuiyijingshenSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public static String getBusinessType() {
		return businessType;
	}

	public static void setBusinessType(String businessType) {
		HuiyijingshenAction.businessType = businessType;
	}
	
	public String getHuiyijingshenId() {
		return huiyijingshenId;
	}

	public void setHuiyijingshenId(String huiyijingshenId) {
		this.huiyijingshenId = huiyijingshenId;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		if(searchForm==null){
			searchForm = new HuiyijingshenSearchForm();
		}
		searchForm.setUnitName(this.getLoginUser().getUnit());
		Class<?> domainClass = ActionUtil.retireDomainClassNameFromAction(this);
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName()+" me "+hqlsufix+" order by me.time desc";
		return hql;
	}
}
