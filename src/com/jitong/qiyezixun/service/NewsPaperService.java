package com.jitong.qiyezixun.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.qiyezixun.dao.NewsPaperDAO;
import com.jitong.qiyezixun.domain.NewsPaper;
import com.jitong.sms.domain.MMS;
import com.jitong.sms.service.SMSService;

public class NewsPaperService extends BaseService {
	NewsPaperDAO dao;

	public NewsPaperService(Session s) {
		super(s);
		dao = new NewsPaperDAO(s);
	}

	public NewsPaperService() throws JTException {
		super();
		dao = new NewsPaperDAO(s);
	}

	public NewsPaper findNewsPaper(String newsPaperId) throws JTException {
		return dao.findNewsPaper(newsPaperId);
	}

	public List<NewsPaper> queryNewsPapers() throws JTException {
		return dao.queryNewsPapers();
	}

	public void deleteNewsPaper(String newsPaperId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deleteBo(NewsPaper.class, newsPaperId);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("修改失败", e, this.getClass());
		}
	}

	public void insertNewsPaper(NewsPaper newsPaper) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.createBo(newsPaper);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("新增失败", e, this.getClass());
		}
	}

	public void updateNewsPaper(NewsPaper newsPaper) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.updateBo(newsPaper);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("更新失败", e, this.getClass());
		}
	}
	
}
