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
import com.jitong.sms.domain.SMS;
import com.jitong.sms.domain.ScheduleSMS;
import com.jitong.sms.service.SMSService;

public class ScheduleSMSJob implements Job {
	static Logger logger = LoggerFactory.getLogger(ScheduleSMSJob.class);
	private static Session session;

	public ScheduleSMSJob() throws JTException {
		if (session == null) {
			session = DBtools.getExclusiveSession();
		}
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail jobDetail = context.getJobDetail();
		ScheduleSMSJob.sendSMS(jobDetail);
	}

	private static synchronized void sendSMS(JobDetail jobDetail) {
		String jobDetailName = jobDetail.getName();
		ScheduleSMS schedSMS = (ScheduleSMS) jobDetail.getJobDataMap().get("schedSMSID_" + jobDetailName);
		SMS sms = new SMS();
		sms.setBusinessId(schedSMS.getBusinessId());
		sms.setBusinessType(schedSMS.getBusinessType());
		sms.setOperatorId(schedSMS.getOperatorId());
		sms.setPhoneNumber(schedSMS.getPhoneNumber());
		sms.setRecipientId(schedSMS.getRecipientId());
		sms.setRequestTime(DateUtil.getCurrentTime());
		sms.setSenderInfo(schedSMS.getSenderInfo());
		sms.setSMSContent(schedSMS.getSMSContent());
		Transaction transaction = null;
		try {
			transaction = ScheduleSMSJob.session.beginTransaction();
			SMSService service = new SMSService(ScheduleSMSJob.session);
			service.insertSMS(sms);
			//ScheduleSMSJob.session.save(sms);
			// ScheduleSMSJob.session.flush();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}

}
