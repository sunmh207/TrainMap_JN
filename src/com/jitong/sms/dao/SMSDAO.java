package com.jitong.sms.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.sms.domain.MMSVO;
import com.jitong.sms.domain.SMS;
import com.jitong.sms.domain.SMSVO;
import com.jitong.sms.domain.ScheduleMMS;
import com.jitong.sms.domain.ScheduleSMS;

public class SMSDAO extends BaseDAO {

	public SMSDAO(Session session) {
		super(session);
	}

	public SMS findSMS(String smsId) throws JTException {
		try {
			return (SMS) super.findBoById(SMS.class, smsId);
		} catch (HibernateException e) {
			throw new JTException("Error occured when find smsId. smsId=" + smsId, e, this.getClass());
		}
	}

	public SMS querySMSByBusinessInfo(String businessType, String businessId) throws JTException {
		try {
			List<SMS> l = (List<SMS>) super.find("from SMS s where s.businessType='" + businessType + "' and s.businessId='" + businessId + "'");
			if (l != null && l.size() > 0) {
				return l.get(0);
			}
			return null;
		} catch (HibernateException e) {
			throw new JTException("Error occured when query SMS.", e, this.getClass());
		}
	}

	/**
	 * 根据smsid查询对应的发送记录
	 * 
	 * @param smsId
	 * @return
	 * @throws JTException
	 */
	public List<SMS> querySMSSent(String smsId) throws JTException {
		try {
			return (List<SMS>) super.find("from SMSSend s where SMSID='" + smsId + "'");
		} catch (HibernateException e) {
			throw new JTException("Error occured when query SMSSent.", e, this.getClass());
		}
	}

	public List<SMSVO> querySMSVOByOperatorId(String operatorId) throws JTException {
		try {
			return (List<SMSVO>) super.find("from SMSVO s where s.operatorId='" + operatorId + "'");
		} catch (HibernateException e) {
			throw new JTException("Error occured when query SMSVO.", e, this.getClass());
		}
	}

	public SMSVO findSMSVO(String smsId) throws JTException {
		try {
			return (SMSVO) super.findBoById(SMSVO.class, smsId);
		} catch (HibernateException e) {
			throw new JTException("Error occured when find smsId. smsId=" + smsId, e, this.getClass());
		}
	}

	public List<SMSVO> querySMSVOByBusinessInfo(String businessType, String businessId) throws JTException {
		try {

			List<SMSVO> l = (List<SMSVO>) super.find("from SMSVO s where s.businessType='" + businessType + "' and s.businessId='" + businessId + "'");
			return l;
			/*
			 * if (l != null && l.size() > 0) { return l.get(0); } return null;
			 */
		} catch (HibernateException e) {
			throw new JTException("Error occured when query SMS.", e, this.getClass());
		}
	}

	/****************** MMS ******************/
	public List<MMSVO> queryMMSVOByBusinessInfo(String businessType, String businessId) throws JTException {
		try {
			return (List<MMSVO>) super.find("from MMSVO s where s.businessType='" + businessType + "' and s.businessId='" + businessId + "'");

		} catch (HibernateException e) {
			throw new JTException("Error occured when query MMS.", e, this.getClass());
		}
	}

	@Override
	public void deletePo(Object po) throws JTException {
		// TODO Auto-generated method stub
		super.deletePo(po);
	}

	/******************** ScheduleSMS *************************/
	public List<ScheduleSMS> queryScheduleSMSByBusinessInfo(String businessType, String businessId) throws JTException {
		try {
			return (List<ScheduleSMS>) super.find("from ScheduleSMS s where s.businessType='" + businessType + "' and s.businessId='" + businessId + "'");
		} catch (HibernateException e) {
			throw new JTException("Error occured when query SMS.", e, this.getClass());
		}
	}

	public ScheduleSMS findScheduleSMSByBusinessInfo(String businessType, String businessId, String recipientId) throws JTException {
		try {
			List<ScheduleSMS> l = (List<ScheduleSMS>) super.find("from ScheduleSMS s where s.businessType='" + businessType + "' and s.businessId='"
					+ businessId + "' and s.recipientId='" + recipientId + "'");
			if (l != null && l.size() > 0) {
				return l.get(0);
			}
			return null;
		} catch (HibernateException e) {
			throw new JTException("Error occured when query SMS.", e, this.getClass());
		}
	}

	public List<ScheduleSMS> queryUnexpiredScheduleSMS() throws JTException {
		String currTime = DateUtil.getCurrentTime();
		try {
			return (List<ScheduleSMS>) super.find("from ScheduleSMS s where s.endTime>='" + currTime + "'");
		} catch (HibernateException e) {
			throw new JTException("Error occured when query SMS.", e, this.getClass());
		}
	}
	/******************** ScheduleMMS *************************/
	public List<ScheduleMMS> queryScheduleMMSByBusinessInfo(String businessType, String businessId) throws JTException {
		try {
			return (List<ScheduleMMS>) super.find("from ScheduleMMS s where s.businessType='" + businessType + "' and s.businessId='" + businessId + "'");
		} catch (HibernateException e) {
			throw new JTException("Error occured when query SMS.", e, this.getClass());
		}
	}
	
	public ScheduleMMS findScheduleMMSByBusinessInfo(String businessType, String businessId, String recipientId) throws JTException {
		try {
			List<ScheduleMMS> l = (List<ScheduleMMS>) super.find("from ScheduleMMS s where s.businessType='" + businessType + "' and s.businessId='"
					+ businessId + "' and s.recipientId='" + recipientId + "'");
			if (l != null && l.size() > 0) {
				return l.get(0);
			}
			return null;
		} catch (HibernateException e) {
			throw new JTException("Error occured when query SMS.", e, this.getClass());
		}
	}
	
	public List<ScheduleMMS> queryUnexpiredScheduleMMS() throws JTException {
		String currTime = DateUtil.getCurrentTime();
		try {
			return (List<ScheduleMMS>) super.find("from ScheduleMMS s where s.endTime>='" + currTime + "'");
		} catch (HibernateException e) {
			throw new JTException("Error occured when query MMS.", e, this.getClass());
		}
	}
}
