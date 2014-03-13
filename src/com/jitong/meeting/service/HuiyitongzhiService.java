package com.jitong.meeting.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.JitongConstants;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.meeting.dao.HuiyitongzhiDAO;
import com.jitong.meeting.domain.Huiyitongzhi;

public class HuiyitongzhiService extends BaseService {
	HuiyitongzhiDAO dao;

	public HuiyitongzhiService(Session s) {
		super(s);
		dao = new HuiyitongzhiDAO(s);
	}

	public HuiyitongzhiService() throws JTException {
		super();
		dao = new HuiyitongzhiDAO(s);
	}

	public Huiyitongzhi findHuiyitongzhi(String huiyitongzhiId) throws JTException {
		return dao.findHuiyitongzhi(huiyitongzhiId);
	}

	public List<Huiyitongzhi> queryHuiyitongzhis() throws JTException {
		return dao.queryHuiyitongzhis();
	}

	public List<Huiyitongzhi> queryReuseHuiyitongzhis() throws JTException {
		try {
			return (List<Huiyitongzhi>) dao.find("from Huiyitongzhi u where u.reuseFlag='"+JitongConstants.STRING_TRUE+"'");
		} catch (HibernateException e) {
			throw new JTException("Error occured when query Huiyitongzhis.", e, this.getClass());
		}
	}
	public void deleteHuiyitongzhi(String huiyitongzhiId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deleteBo(Huiyitongzhi.class, huiyitongzhiId);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("修改失败", e, this.getClass());
		}
	}
	
	

	public String insertHuiyitongzhi(Huiyitongzhi huiyitongzhi) throws JTException {
		Transaction tx = null;
		String id;
		try {
			tx = this.beginTransaction();
			id  = dao.createBo(huiyitongzhi);
			this.commitTransaction(tx);
			return id;
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("新增失败", e, this.getClass());
		}
	}

	public void updateHuiyitongzhi(Huiyitongzhi huiyitongzhi) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.updateBo(huiyitongzhi);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("更新失败", e, this.getClass());
		}
	}
}
