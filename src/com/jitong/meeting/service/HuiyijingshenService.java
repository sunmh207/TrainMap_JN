package com.jitong.meeting.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.meeting.dao.HuiyijingshenDAO;
import com.jitong.meeting.domain.Huiyijingshen;

public class HuiyijingshenService extends BaseService {
	HuiyijingshenDAO dao;

	public HuiyijingshenService(Session s) {
		super(s);
		dao = new HuiyijingshenDAO(s);
	}

	public HuiyijingshenService() throws JTException {
		super();
		dao = new HuiyijingshenDAO(s);
	}

	public Huiyijingshen findHuiyijingshen(String huiyijingshenId) throws JTException {
		return dao.findHuiyijingshen(huiyijingshenId);
	}

	public List<Huiyijingshen> queryHuiyijingshens() throws JTException {
		return dao.queryHuiyijingshens();
	}

	public void deleteHuiyijingshen(String huiyijingshenId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deleteBo(Huiyijingshen.class, huiyijingshenId);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("修改失败", e, this.getClass());
		}
	}

	public String insertHuiyijingshen(Huiyijingshen huiyijingshen) throws JTException {
		Transaction tx = null;
		String id;
		try {
			tx = this.beginTransaction();
			id = dao.createBo(huiyijingshen);
			this.commitTransaction(tx);
			return id;
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("新增失败", e, this.getClass());
		}
	}

	public void updateHuiyijingshen(Huiyijingshen huiyijingshen) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.updateBo(huiyijingshen);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("更新失败", e, this.getClass());
		}
	}	
}
