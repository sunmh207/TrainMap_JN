package com.jitong.common.timer;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.sms.domain.TimedSMS;
import com.jitong.sms.service.SMSService;

public class TimedSMSDailyChecker {
	public static final Logger logger = Logger.getLogger(TimedSMSDailyChecker.class);
	public static void checkTodaysSMS() throws JTException{
		logger.debug("TimedSMSDailyChecker is checking ....");
		SMSService service = new SMSService();
		Transaction t = service.beginTransaction();
		List<TimedSMS> timedSMSInRange = service.getAllTimedSMSInRange();
		for (TimedSMS timedSMS : timedSMSInRange) {
			String sendTime = timedSMS.getSendTime();
			String schedule = timedSMS.getSchedule();
			logger.debug(timedSMS.getSMSContent()+":"+sendTime+"-"+schedule);
			String timegap = DateUtil.shallSendToday(sendTime,schedule);
			System.out.println(schedule+"-"+timegap);
			if(timegap!=null){
				service.sendSMSBySchedule(timedSMS,timegap);
			}
		}
		t.commit();
		logger.debug("TimedSMSDailyChecker finished checking ....");
	}
	
	public static void main(String[] args) throws JTException {
		checkTodaysSMS();
	}
}
