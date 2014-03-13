package com.jitong.wap.action;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.StringUtil;
import com.jitong.qiyezixun.domain.Announcement;
import com.jitong.qiyezixun.service.AnnouncementService;

public class WapAnnouncementAction extends JITActionBase {

	private String qryName = "";
	private String announcementId;
	private Announcement announcement;

	public String getQryName() {
		return qryName;
	}

	public void setQryName(String qryName) {
		this.qryName = qryName;
	}

	public String getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(String announcementId) {
		this.announcementId = announcementId;
	}

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	public String execute() throws JTException {
		return super.list();
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		if (StringUtil.isEmpty(qryName)) {
			return "from Announcement q order by q.announcementDate desc";
		} else {
			return "from Announcement q where q.topic like '%" + qryName + "%' order by q.announcementDate desc";
		}
	}

	public String detail() throws JTException {
		if (announcementId != null) {
			AnnouncementService service = new AnnouncementService();
			announcement = service.findAnnouncement(announcementId);
		}
		return "detail";
	}
}
