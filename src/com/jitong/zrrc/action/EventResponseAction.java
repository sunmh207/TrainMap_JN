package com.jitong.zrrc.action;

import java.util.ArrayList;
import java.util.List;

import com.jitong.JitongConstants;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.console.domain.User;
import com.jitong.sms.domain.SMS;
import com.jitong.sms.service.SMSService;
import com.jitong.zrrc.domain.EventResponse;
import com.jitong.zrrc.domain.Relationship;
import com.jitong.zrrc.service.ZRRCService;
import com.opensymphony.xwork2.Preparable;

public class EventResponseAction extends JITActionBase implements Preparable {
	private EventResponse eventresponse;

	private static ZRRCService service;
	private static SMSService smsService;
	private List<String> typeList;

	public void prepare() throws JTException {
		if (service == null) {
			service = new ZRRCService();
		}
		if (smsService == null) {
			smsService = new SMSService();
		}
		if (eventresponse != null && eventresponse.getId() != null) {
			eventresponse = (EventResponse) service.findBoById(EventResponse.class, eventresponse.getId());
		}
		typeList = new ArrayList<String>();
		typeList.add("安全状态");
		typeList.add("违章情况");
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		return INPUT;
	}

	public String delete() throws JTException {
		service.deleteBo(EventResponse.class, eventresponse.getId());
		return list();
	}

	public String save() throws Exception {
		if (eventresponse.getId() == null || "".equals(eventresponse.getId())) {
			String perId = eventresponse.getPersonId();
			Relationship r = service.findRelationshipByPerId(perId);
			if(r!=null){
				r.setLastEventDate(eventresponse.getEventDate());
				service.updateBo(r);
			}
			service.createBo(eventresponse);
			List<User> mgrList = service.queryManagers(eventresponse.getPersonId());
			// send SMS
			for (User mgr : mgrList) {
				if(mgr==null){
					continue;
				}
				SMS sms = new SMS();
				sms.setBusinessType("ZRRC");
				sms.setBusinessId(eventresponse.getId());
				sms.setPhoneNumber(mgr.getPhoneNumber());
				sms.setRequestTime(DateUtil.getCurrentTime(JitongConstants.JT_DATETIME_FORMAT));
				sms.setSMSContent(eventresponse.getEventDesc());
				sms.setRecipientId(mgr.getId());
				User loginUser = this.getLoginUser();
				sms.setOperatorId(loginUser.getId());
				sms.setSenderInfo(this.getLoginUserInfo());
				smsService.insertSMS(sms);
			}

		} else {
			service.updateBo(eventresponse);
		}
		return list();
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "from EventResponse me order by me.eventDate desc";
		return hql;
	}

	public String getCategoryId() {
		return "zrrc.eventresponse";
	}

	public EventResponse getEventresponse() {
		return eventresponse;
	}

	public void setEventresponse(EventResponse eventresponse) {
		this.eventresponse = eventresponse;
	}

	public List<String> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<String> typeList) {
		this.typeList = typeList;
	}

}
