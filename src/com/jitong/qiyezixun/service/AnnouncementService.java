package com.jitong.qiyezixun.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.qiyezixun.dao.AnnouncementDAO;
import com.jitong.qiyezixun.domain.Announcement;

public class AnnouncementService extends BaseService {
	AnnouncementDAO dao;

	public AnnouncementService(Session s) {
		super(s);
		dao = new AnnouncementDAO(s);
	}

	public AnnouncementService() throws JTException {
		super();
		dao = new AnnouncementDAO(s);
	}

	public Announcement findAnnouncement(String announcementId) throws JTException {
		return dao.findAnnouncement(announcementId);
	}

	public List<Announcement> queryAnnouncements() throws JTException {
		return dao.queryAnnouncements();
	}

	public void deleteAnnouncement(String announcementId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deleteBo(Announcement.class, announcementId);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("修改失败", e, this.getClass());
		}
	}

	public String insertAnnouncement(Announcement announcement) throws JTException {
		Transaction tx = null;
		String id;
		try {
			tx = this.beginTransaction();
			id = dao.createBo(announcement);
			this.commitTransaction(tx);
			return id;
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("新增失败", e, this.getClass());
		}
	}

	public void updateAnnouncement(Announcement announcement) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.updateBo(announcement);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("更新失败", e, this.getClass());
		}
	}
}
