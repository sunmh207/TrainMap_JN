package com.jitong.anquanjiaoyu.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.anquanjiaoyu.domain.Wenda;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DateUtil;
import com.jitong.qiyezixun.domain.Poll;

public class WendaService extends BaseService {
	BaseDAO dao;

	public WendaService(Session s) {
		super(s);
		dao = new BaseDAO(s);
	}

	public WendaService() throws JTException {
		super();
		dao = new BaseDAO(s);
	}

	public List<Wenda> queryRunningWendas() throws JTException {
		String currentDate = DateUtil.getCurrentDate();
		return (List<Wenda>) dao
				.find("from Wenda u");
	}
	
	public void updateWenda(Wenda wenda) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.updateBo(wenda);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("更新失败", e, this.getClass());
		}
	}
	public static void main(String[] args) throws JTException {
		new WendaService().queryRunningWendas();
	}
	
	
}
