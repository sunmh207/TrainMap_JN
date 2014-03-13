package com.jitong.qiyezixun.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.qiyezixun.dao.NewsPaperDAO;
import com.jitong.qiyezixun.domain.BirthdaySetting;
import com.jitong.qiyezixun.domain.NewsPaper;

public class BirthdayService extends BaseService {
	NewsPaperDAO dao;

	public BirthdayService(Session s) {
		super(s);
		dao = new NewsPaperDAO(s);
	}

	public BirthdayService() throws JTException {
		super();
		dao = new NewsPaperDAO(s);
	}

	public BirthdaySetting findBirthdaySetting() throws JTException {
		try {
			List<BirthdaySetting> l = (List<BirthdaySetting>) new BaseDAO(s).find("from BirthdaySetting u");
			if (l != null && l.size() > 0) {
				return l.get(0);
			}
			return null;
		} catch (HibernateException e) {
			throw new JTException("Error occured when query NewsPapers.", e, this.getClass());
		}
	}

}
