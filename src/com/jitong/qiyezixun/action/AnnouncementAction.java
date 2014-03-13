package com.jitong.qiyezixun.action;

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
import com.jitong.qiyezixun.domain.Announcement;
import com.jitong.qiyezixun.domain.Festival;
import com.jitong.qiyezixun.form.AnnouncementSearchForm;
import com.jitong.qiyezixun.form.FestivalSearchForm;
import com.jitong.qiyezixun.service.AnnouncementService;
import com.jitong.sms.domain.SMS;
import com.jitong.sms.service.SMSService;
import com.opensymphony.xwork2.Preparable;

public class AnnouncementAction extends JITActionBase implements Preparable {
	private Announcement announcement;
	private AnnouncementSearchForm searchForm;
	
	private static String businessType="announcement";
	private AnnouncementService service;
	private SMSService smsService;
	
	private List<Announcement> announcements;
	private String announcementId;
	public void prepare() throws JTException {
		if (service == null) {
			service = new AnnouncementService();
		}
		if (smsService == null) {
			smsService = new SMSService();
		}

		if (announcement != null && announcement.getAnnouncementId() != null) {
			announcement = service.findAnnouncement(announcement.getAnnouncementId());
		}
	}

	public String input() throws JTException {
		return INPUT;
	}
	public String delete() throws Exception{
		if (announcementId!=null){
			service.deleteAnnouncement(announcementId);
		}
		return list();
	}
	public String save() throws JTException {
		announcement.setCreatorId(this.getLoginUser().getId());
		announcement.setUnitName(this.getLoginUser().getUnit());
		String announcementId = service.insertAnnouncement(announcement);

		// insert announcementDetail
		String idString = request.getParameter("useridStr");
		String[] ids = StringUtil.parseString2Array(idString, ",");
		for (int i = 0; i < ids.length; i++) {
			// send SMS
			SMS sms = new SMS();
			sms.setBusinessType(businessType);
			sms.setBusinessId(announcementId);
			User u = SysCache.interpertUser(ids[i]);
			sms.setPhoneNumber(u.getPhoneNumber());
			sms.setRequestTime(DateUtil.getCurrentTime(JitongConstants.JT_DATETIME_FORMAT));
			sms.setSMSContent(announcement.getContent());
			sms.setRecipientId(ids[i]);
			User loginUser = (User)session.get(JitongConstants.USER);
			sms.setSenderInfo(loginUser.getName()+"-"+loginUser.getUnitDept()+"-"+loginUser.getLastLoginIp());
			smsService.insertSMS(sms);
		}

		return SUCCESS;
	}

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	public List<Announcement> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(List<Announcement> announcements) {
		this.announcements = announcements;
	}
	public String getCategoryId() {
		return "qiyezixun.announcement";
	}

	public AnnouncementSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(AnnouncementSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public static String getBusinessType() {
		return businessType;
	}

	public static void setBusinessType(String businessType) {
		AnnouncementAction.businessType = businessType;
	}
	
	public String getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(String announcementId) {
		this.announcementId = announcementId;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		if (searchForm == null) {
			searchForm = new AnnouncementSearchForm();
		}
		searchForm.setUnitName(this.getLoginUser().getUnit());

		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from Announcement me "+hqlsufix +" order by me.announcementDate desc";
		return hql;
	}
}
