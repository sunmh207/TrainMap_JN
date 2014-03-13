package com.jitong.sms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DBtools;
import com.jitong.sms.dao.InBoxDAO;
import com.jitong.sms.domain.InBox;
import com.jitong.sms.domain.InBoxVO;

public class InBoxService extends BaseService {
	InBoxDAO dao;

	public InBoxService(Session s) {
		super(s);
		dao = new InBoxDAO(s);
	}

	public InBoxService() throws JTException {
		super();
		dao = new InBoxDAO(s);
	}

	public List<InBox> queryInBoxByBusinessInfo(String businessType, String businessId) throws JTException {
		try {
			return dao.queryInBoxByBusinessInfo(businessType, businessId);
		} catch (HibernateException e) {
			throw new JTException("Error occured when query InBox.", e, this.getClass());
		}
	}

	public List<InBox> queryInBoxByBusinessInfo(String businessType, String businessId, String fromtime, String endtime) throws JTException {
		try {
			return dao.queryInBoxByBusinessInfo(businessType, businessId, fromtime, endtime);
		} catch (HibernateException e) {
			throw new JTException("Error occured when query InBox.", e, this.getClass());
		}
	}

	public List<InBox> queryNotReplyInBox(String businessType, String businessId, String fromtime, String endtime) throws JTException {
		try {
			return dao.queryNotReplyInBox(businessType, businessId, fromtime, endtime);
		} catch (HibernateException e) {
			throw new JTException("Error occured when query InBox.", e, this.getClass());
		}
	}

	public List<InBoxVO> queryInBoxVOByBusinessInfo(String businessType, String businessId) throws JTException {
		try {
			return dao.queryInBoxVOByBusinessInfo(businessType, businessId);
		} catch (HibernateException e) {
			throw new JTException("Error occured when query InBox.", e, this.getClass());
		}
	}

	public void deleteInBox(String inBoxId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deleteBo(InBox.class, inBoxId);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("修改失败", e, this.getClass());
		}
	}

	public void delete(String phoneNumber, String reachTime) throws JTException{
		try {
			Connection conn = DBtools.getSession().connection();
			PreparedStatement ps = conn.prepareStatement("delete from JTMOBILE_INBOX where PHONE_NUMBER=? and REACH_TIME=?");
			ps.setString(1, phoneNumber);
			ps.setString(2, reachTime);
			ps.execute();
			conn.commit();
			ps.close();
		} catch (Exception e) {
			throw new JTException("新增失败", e, this.getClass());
		}
	}	
	/*
	 * public void insertInBox(InBox inBox) throws JTException { Transaction tx
	 * = null; try { tx = this.beginTransaction(); dao.createBo(inBox);
	 * this.commitTransaction(tx); } catch (JTException e) {
	 * this.rollbackTransaction(tx); throw new JTException("新增失败", e,
	 * this.getClass()); } }
	 */
	public void insertInBox(InBox inBox) throws JTException {
		if (inBox == null)
			throw new JTException("InBox is null", this.getClass());
		try {
			Connection conn = DBtools.getSession().connection();
			PreparedStatement ps = conn
					.prepareStatement("insert into JTMOBILE_INBOX(BUSINESS_TYPE,BUSINESS_ID,PHONE_NUMBER,REACH_TIME,CONTENT,REPLY_FLAG) values(?,?,?,?,?,?)");
			ps.setString(1, inBox.getBusinessType());
			ps.setString(2, inBox.getBusinessId());
			ps.setString(3, inBox.getPhoneNumber());
			ps.setString(4, inBox.getReachTime());
			ps.setString(5, inBox.getContent());
			ps.setString(6, inBox.getReply());
			ps.executeUpdate();
			conn.commit();
			ps.close();
		} catch (Exception e) {
			throw new JTException("新增失败", e, this.getClass());
		}
	}

	
	
	/*
	 * public void updateInBox(InBox inBox) throws JTException { Transaction tx
	 * = null; try { tx = this.beginTransaction(); dao.updateBo(inBox);
	 * this.commitTransaction(tx); } catch (JTException e) {
	 * this.rollbackTransaction(tx); throw new JTException("更新失败", e,
	 * this.getClass()); } }
	 */

	public static void main(String[] args) throws Exception {

		InBoxService s = new InBoxService();
		InBox in = new InBox();
		in.setBusinessId("bid000");
		in.setBusinessType("TP");
		in.setPhoneNumber("1301111111");
		in.setContent("xxx");
		System.out.println("=====");
		s.insertInBox(in);
	}
}
