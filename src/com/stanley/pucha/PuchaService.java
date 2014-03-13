package com.stanley.pucha;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.console.dao.UserDAO;

public class PuchaService extends BaseService {
	UserDAO dao;

	public PuchaService(Session s) {
		super(s);
		dao = new UserDAO(s);
	}

	public PuchaService() throws JTException {
		super();
		dao = new UserDAO(s);
	}

	public Puchadan findPuchadan(String id) throws JTException {
		return (Puchadan) dao.findBoById(Puchadan.class, id);
	}

	public List<Puchadan> queryPuchadans() throws JTException {
		return (List<Puchadan>) dao.find("from Puchadan u");
	}
	
	public void deletePuchadan(String id) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deleteBo(Puchadan.class, id);
			dao.deleteAll(queryPuchaDetails(id));
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("修改失败", e, this.getClass());
		}
	}
	public void insertPuchadan(Puchadan puchadan) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.createBo(puchadan);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("插入失败", e, this.getClass());
		}
	}
	public void updatePuchadan(Puchadan puchadan) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.updateBo(puchadan);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("更新失败", e, this.getClass());
		}
	}
	
	public boolean updatePuchadanIsDoneByDetails(String puchadanId) throws JTException {
		Puchadan puchadan =findPuchadan(puchadanId);
		List<PuchaDetail> detailList= queryPuchaDetails(puchadanId);
		for(PuchaDetail d:detailList){
			if(!"1".equals(d.getIsDone())){
				puchadan.setIsDone("0");
				this.updatePuchadan(puchadan);
				return false;
			}
		}
		//all detail's isdone =1
		puchadan.setIsDone("1");
		this.updatePuchadan(puchadan);
		return true;
	}
	
////////////////PuchaDetail//////////////////////////
	public List<PuchaDetail> queryPuchaDetails(String puchadanId) throws JTException {
		return (List<PuchaDetail>) dao.find("from PuchaDetail u where u.puchadanId='"+puchadanId+"'");
	}
	
	public void deletePuchaDetail(String id) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deleteBo(PuchaDetail.class, id);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("修改失败", e, this.getClass());
		}
	}
	public void insertPuchaDetail(PuchaDetail puchaDetail) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.createBo(puchaDetail);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("插入失败", e, this.getClass());
		}
	}
	public void updatePuchaDetail(PuchaDetail puchaDetail) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.updateBo(puchaDetail);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("更新失败", e, this.getClass());
		}
	}
}
