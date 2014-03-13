package com.jitong.qiyezixun.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.qiyezixun.domain.Announcement;

public class AnnouncementDAO extends BaseDAO{
	public AnnouncementDAO(Session session) {
		super(session);
	}

	public Announcement findAnnouncement(String announcementId) throws JTException {
		try {
			return (Announcement) super.findBoById(Announcement.class, announcementId);
		} catch (HibernateException e) {
			throw new JTException("Error occured when find Announcement. announcementId=" + announcementId, e, this.getClass());
		}
	}

	public List<Announcement> queryAnnouncements() throws JTException {
        try {
           return (List<Announcement>)super.find("from Announcement u");
        } catch (HibernateException e) {
            throw new JTException("Error occured when query Announcement.", e, this.getClass());
        }
    }
	}
