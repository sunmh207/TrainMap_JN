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
import com.jitong.meeting.domain.Huiyitongzhi;
import com.jitong.meeting.form.HuiyitongzhiSearchForm;
import com.jitong.meeting.service.CallConfirmService;
import com.jitong.meeting.service.HuiyitongzhiService;
import com.jitong.sms.domain.SMS;
import com.jitong.sms.service.SMSService;
import com.opensymphony.xwork2.Preparable;

public class HuiyitongzhiAction extends JITActionBase implements Preparable {
	private Huiyitongzhi huiyitongzhi;
	private HuiyitongzhiSearchForm searchForm;
	private static String businessType="huiyitongzhi";
	private static HuiyitongzhiService service;
	private static CallConfirmService callConfirmService;
	private static SMSService smsService;
	
	private List<Huiyitongzhi> huiyitongzhis;

	private String huiyitongzhiId;
	
	public void prepare() throws JTException {
		if (service == null) {
			service = new HuiyitongzhiService();
		}
		if (callConfirmService == null) {
			callConfirmService = new CallConfirmService();
		}
		if (smsService == null) {
			smsService = new SMSService();
		}

		if (huiyitongzhi != null && huiyitongzhi.getHuiyitongzhiId() != null) {
			huiyitongzhi = service.findHuiyitongzhi(huiyitongzhi.getHuiyitongzhiId());
		}
		huiyitongzhis = service.queryHuiyitongzhis();
	}
	
	public String list() throws JTException {
		return super.list();
	}

	public String input() throws JTException {
		//huiyitongzhis = service.queryHuiyitongzhis();
		return INPUT;
	}
	public String delete() throws Exception{
		if (huiyitongzhiId!=null){
			service.deleteHuiyitongzhi(huiyitongzhiId);
		}
		return list();
	}
	public String save() throws Exception {
		// insert huiyitongzhi
		huiyitongzhi.setCreatorId(this.getLoginUser().getId());
		huiyitongzhi.setUnitName(this.getLoginUser().getUnit());
		String huiyitongzhiId = service.insertHuiyitongzhi(huiyitongzhi); 

		// insert huiyitongzhiDetail
		String idString = request.getParameter("useridStr");
		String[] ids = StringUtil.parseString2Array(idString, ",");
		for (int i = 0; i < ids.length; i++) {
			CallConfirm callConfirm = new CallConfirm();
			callConfirm.setBusinessType(businessType);
			callConfirm.setContent(huiyitongzhi.getContent());

			User user = SysCache.interpertUser(ids[i]);

			callConfirm.setDeptName(user.getDept());
			callConfirm.setMeetingTime(huiyitongzhi.getTime());
			callConfirm.setNeedVoice(huiyitongzhi.getNeedVoice());
			callConfirm.setPhoneNumber(user.getPhoneNumber());
			callConfirm.setRecipientId(ids[i]);
			callConfirm.setRecipientName(user.getName());
			callConfirm.setUnitName(user.getUnit());

			callConfirmService.insertCallConfirm(callConfirm);

			// send SMS
			SMS sms = new SMS();
			sms.setBusinessType(businessType);
			//sms.setBusinessId(callConfirm.getCallConfirmId());
			sms.setBusinessId(huiyitongzhiId);
			//User u = SysCache.interpertUser(ids[i]);
			sms.setPhoneNumber(user.getPhoneNumber());
			sms.setRequestTime(DateUtil.getCurrentTime(JitongConstants.JT_DATETIME_FORMAT));
			sms.setSMSContent(huiyitongzhi.getContent());
			sms.setNeedVoice(huiyitongzhi.getNeedVoice());
			sms.setRecipientId(ids[i]);
			User loginUser = (User)session.get(JitongConstants.USER);
			sms.setOperatorId(loginUser.getId());
			//sms.setSenderInfo(loginUser.getName()+"-"+loginUser.getUnitDept()+"-"+loginUser.getLastLoginIp());
			sms.setSenderInfo(this.getLoginUserInfo());
			sms.setVerify(huiyitongzhi.getVerify());
			smsService.insertSMS(sms);
		}

		//huiyitongzhis = service.queryHuiyitongzhis();
		return SUCCESS;
	}

	public Huiyitongzhi getHuiyitongzhi() {
		return huiyitongzhi;
	}

	public void setHuiyitongzhi(Huiyitongzhi huiyitongzhi) {
		this.huiyitongzhi = huiyitongzhi;
	}

	public List<Huiyitongzhi> getHuiyitongzhis() {
		return huiyitongzhis;
	}

	public void setHuiyitongzhis(List<Huiyitongzhi> huiyitongzhis) {
		this.huiyitongzhis = huiyitongzhis;
	}
	
	public HuiyitongzhiSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(HuiyitongzhiSearchForm searchForm) {
		this.searchForm = searchForm;
	}


	public static String getBusinessType() {
		return businessType;
	}

	public static void setBusinessType(String businessType) {
		HuiyitongzhiAction.businessType = businessType;
	}

	public String getHuiyitongzhiId() {
		return huiyitongzhiId;
	}

	public void setHuiyitongzhiId(String huiyitongzhiId) {
		this.huiyitongzhiId = huiyitongzhiId;
	}

	public String getCategoryId() {
		return "meeting.huiyitongzhi";
	}
	public String getListHQL(ArrayList<Object> params) throws JTException {
		if(searchForm==null){
			searchForm = new HuiyitongzhiSearchForm();
		}
		searchForm.setUnitName(this.getLoginUser().getUnit());

		Class<?> domainClass = ActionUtil.retireDomainClassNameFromAction(this);
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName()+" me "+hqlsufix +" order by me.time desc";
		return hql;
	}
}
