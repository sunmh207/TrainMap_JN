package com.jitong.sms.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.sms.domain.InBox;
import com.jitong.sms.domain.InBoxVO;

public class InBoxDAO extends BaseDAO {

	public InBoxDAO(Session session) {
		super(session);
	}

	/**
	 * 
	 * @param businessType
	 *            比如TP,DT
	 * @param businessId
	 *            比如A,B,C....AB,...
	 * @return
	 * @throws JTException
	 */
	public List<InBox> queryInBoxByBusinessInfo(String businessType, String businessId) throws JTException {
		try {
			//return (List<InBox>) super.find("from InBox s where s.businessType='" + businessType + "' and s.businessId='" + businessId + "'");
			return (List<InBox>) super.find("from InBox s where s.businessId='" + businessId + "' and s.businessType='" + businessType + "'");
		} catch (HibernateException e) {
			throw new JTException("Error occured when query InBox.", e, this.getClass());
		}
	}
	public List<InBox> queryInBoxByBusinessInfo(String businessType, String businessId, String fromtime, String endtime) throws JTException {
		try {
			//return (List<InBox>) super.find("from InBox s where s.businessType='" + businessType + "' and s.businessId='" + businessId + "'");
			return (List<InBox>) super.find("from InBox s where s.businessId='" + businessId + "' and s.businessType='" + businessType + "' and s.reachTime>='"+fromtime+"' and s.reachTime<='"+endtime+"'");
		} catch (HibernateException e) {
			throw new JTException("Error occured when query InBox.", e, this.getClass());
		}
	}
	public List<InBox> queryNotReplyInBox(String businessType, String businessId, String fromtime, String endtime) throws JTException {
		try {
			//return (List<InBox>) super.find("from InBox s where s.businessType='" + businessType + "' and s.businessId='" + businessId + "'");
			return (List<InBox>) super.find("from InBox s where s.businessId='" + businessId + "' and s.businessType='" + businessType + "' and s.reachTime>='"+fromtime+"' and s.reachTime<='"+endtime+"' and (s.reply is null or s.reply <> '1')");
		} catch (HibernateException e) {
			throw new JTException("Error occured when query InBox.", e, this.getClass());
		}
	}
	
	
	

	public List<InBoxVO> queryInBoxVOByBusinessInfo(String businessType, String businessId) throws JTException {
		try {
			return (List<InBoxVO>) super.find("from InBoxVO s where s.businessType='" + businessType + "' and s.businessId='" + businessId + "'");
		} catch (HibernateException e) {
			throw new JTException("Error occured when query InBox.", e, this.getClass());
		}
	}
}
