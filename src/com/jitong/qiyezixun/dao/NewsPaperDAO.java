package com.jitong.qiyezixun.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.qiyezixun.domain.NewsPaper;

public class NewsPaperDAO extends BaseDAO{
	public NewsPaperDAO(Session session) {
		super(session);
	}

	public NewsPaper findNewsPaper(String newsPaperId) throws JTException {
		try {
			return (NewsPaper) super.findBoById(NewsPaper.class, newsPaperId);
		} catch (HibernateException e) {
			throw new JTException("Error occured when find NewsPaper. newsPaperId=" + newsPaperId, e, this.getClass());
		}
	}

	public List<NewsPaper> queryNewsPapers() throws JTException {
		try {
			return (List<NewsPaper>) super.find("from NewsPaper u");
		} catch (HibernateException e) {
			throw new JTException("Error occured when query NewsPapers.", e, this.getClass());
		}
	}

	
}
