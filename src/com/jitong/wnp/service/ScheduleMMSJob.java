package com.jitong.wnp.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jitong.common.exception.JTException;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.DateUtil;
import com.jitong.sms.domain.MMS;
import com.jitong.sms.domain.ScheduleMMS;

public class ScheduleMMSJob implements Job {
	static Logger logger = LoggerFactory.getLogger(ScheduleMMSJob.class);
	private static Session session;

	public ScheduleMMSJob() throws JTException {
		if (session == null) {
			session = DBtools.getExclusiveSession();
		}
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail jobDetail = context.getJobDetail();
		ScheduleMMSJob.sendMMS(jobDetail);
	}

	private static synchronized void sendMMS(JobDetail jobDetail) {
		String jobDetailName = jobDetail.getName();
		ScheduleMMS schedMMS = (ScheduleMMS) jobDetail.getJobDataMap().get("schedMMSID_" + jobDetailName);
		MMS mms = new MMS();
		mms.setBusinessId(schedMMS.getBusinessId());
		mms.setBusinessType(schedMMS.getBusinessType());
		mms.setOperatorId(schedMMS.getOperatorId());
		mms.setPhoneNumber(schedMMS.getPhoneNumber());
		mms.setRecipientId(schedMMS.getRecipientId());
		mms.setRequestTime(DateUtil.getCurrentTime());
		mms.setSenderInfo(schedMMS.getSenderInfo());
		mms.setTitle(schedMMS.getTitle());
		mms.setRealMmsId(schedMMS.getRealMMSID());
		
		Transaction transaction = null;
		try {
			transaction = ScheduleMMSJob.session.beginTransaction();
			ScheduleMMSJob.session.save(mms);
			// ScheduleSMSJob.session.flush();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

}
