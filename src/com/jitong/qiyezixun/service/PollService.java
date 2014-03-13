package com.jitong.qiyezixun.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DateUtil;
import com.jitong.qiyezixun.domain.Poll;

public class PollService extends BaseService {
	BaseDAO dao;

	public PollService(Session s) {
		super(s);
		dao = new BaseDAO(s);
	}

	public PollService() throws JTException {
		super();
		dao = new BaseDAO(s);
	}

	public Poll findPoll(String pollId) throws JTException {
		return (Poll) dao.findBoById(Poll.class, pollId);
	}

	public List<Poll> queryPolls() throws JTException {
		return (List<Poll>) dao.find("from Poll u");
	}
	public List<Poll> queryRunningPolls() throws JTException {
		String currentDate = DateUtil.getCurrentDate();
		return (List<Poll>) dao.find("from Poll u where (u.startDate is null or u.startDate<='"+currentDate+"') and (u.endDate is null or u.endDate>='"+currentDate+"')");
	}
	public void deletePoll(String pollId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deleteBo(Poll.class, pollId);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("修改失败", e, this.getClass());
		}
	}

	public String insertPoll(Poll poll) throws JTException {
		Transaction tx = null;
		String id;
		try {
			tx = this.beginTransaction();
			id = dao.createBo(poll);
			this.commitTransaction(tx);
			return id;
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("新增失败", e, this.getClass());
		}
	}

	public void updatePoll(Poll poll) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.updateBo(poll);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("更新失败", e, this.getClass());
		}
	}
}
