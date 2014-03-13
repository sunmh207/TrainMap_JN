package com.jitong.meeting.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.meeting.dao.CallConfirmDAO;
import com.jitong.meeting.domain.CallConfirm;
import com.jitong.meeting.domain.Huiyitongzhi;

public class CallConfirmService extends BaseService {
	CallConfirmDAO dao;

	public CallConfirmService(Session s) {
		super(s);
		dao = new CallConfirmDAO(s);
	}

	public CallConfirmService() throws JTException {
		super();
		dao = new CallConfirmDAO(s);
	}

	public CallConfirm findCallConfirm(String callConfirmlId) throws JTException {
		return dao.findCallConfirm(callConfirmlId);
	}

	public List<CallConfirm> queryCallConfirms(String businessType) throws JTException {
		return dao.queryCallConfirms(businessType);
	}

	public List<CallConfirm> queryHuiyitongzhisDetailByMeetingTime(String meetingTime) throws JTException {
		return dao.queryHuiyitongzhisDetailByMeetingTime(meetingTime);
	}
	public List<CallConfirm> queryHuiyitongzhisDetailByMeetingTime(String meetingTime, String businessType) throws JTException {
		return dao.queryHuiyitongzhisDetailByMeetingTime(meetingTime, businessType);
	}

	public void deleteCallConfirm(String callConfirmId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deleteBo(Huiyitongzhi.class, callConfirmId);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("修改失败", e, this.getClass());
		}
	}

	public void insertCallConfirm(CallConfirm callConfirm) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.createBo(callConfirm);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("新增失败", e, this.getClass());
		}
	}

	public void updateCallConfirm(CallConfirm callConfirm) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.updateBo(callConfirm);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("更新失败", e, this.getClass());
		}
	}

}
