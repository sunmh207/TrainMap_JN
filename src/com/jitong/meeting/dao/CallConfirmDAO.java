package com.jitong.meeting.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.meeting.domain.CallConfirm;

public class CallConfirmDAO extends BaseDAO {
	public CallConfirmDAO(Session session) {
		super(session);
	}

	/* HuiyitongzhiDetail */

	public CallConfirm findCallConfirm(String callConfirmId) throws JTException {
		try {
			return (CallConfirm) super.findBoById(CallConfirm.class, callConfirmId);
		} catch (HibernateException e) {
			throw new JTException("Error occured when find CallConfirm. callConfirmId=" + callConfirmId, e, this.getClass());
		}
	}

	public List<CallConfirm> queryCallConfirms(String businessType) throws JTException {
		try {
			return (List<CallConfirm>) super.find("from CallConfirm u where u.businessType='" + businessType + "'");
		} catch (HibernateException e) {
			throw new JTException("Error occured when query CallConfirms.", e, this.getClass());
		}
	}

	public List<CallConfirm> queryHuiyitongzhisDetailByMeetingTime(String meetingTime) throws JTException {
		try {
			return (List<CallConfirm>) super.find("from CallConfirm u where u.meetingTime='" + meetingTime + "'");
		} catch (HibernateException e) {
			throw new JTException("Error occured when query CallConfirms.", e, this.getClass());
		}
	}
	public List<CallConfirm> queryHuiyitongzhisDetailByMeetingTime(String meetingTime, String businessType) throws JTException {
		try {
			return (List<CallConfirm>) super.find("from CallConfirm u where u.meetingTime='" + meetingTime + "' and u.businessType='" + businessType + "'");
		} catch (HibernateException e) {
			throw new JTException("Error occured when query CallConfirms.", e, this.getClass());
		}
	}

}
