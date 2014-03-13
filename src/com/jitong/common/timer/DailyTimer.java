package com.jitong.common.timer;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.jitong.JitongConstants;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.console.service.UserService;
import com.jitong.qiyezixun.domain.BirthdaySetting;
import com.jitong.qiyezixun.domain.DeptBirthday;
import com.jitong.qiyezixun.service.BirthdayService;
import com.jitong.qiyezixun.service.DeptBirthdayService;
import com.jitong.sms.domain.SMS;
import com.jitong.sms.service.SMSService;

public class DailyTimer extends BaseJob {
	private static Logger logger = Logger.getLogger(DailyTimer.class);

	public void executeDo() throws JTException {
		logger.debug("DailyTimer is executing.");
		checkAndSendBirthDayGreeting();
		checkAndSendDeptBirthDayGreeting();
		TimedSMSDailyChecker.checkTodaysSMS();
		logger.debug("DailyTimer finished executing.");
	}

	/**
	 * 生日慰问
	 * 
	 * @throws JTException
	 */
	private void checkAndSendBirthDayGreeting() throws JTException {
		SMSService smsService = new SMSService();
		UserService userService = new UserService();
		List<User> userList = userService.queryUsers();
		Iterator<User> it = userList.iterator();
		String currentTime = DateUtil.getCurrentTime(JitongConstants.JT_DATETIME_FORMAT);

		BirthdayService birthdayService = new BirthdayService();
		BirthdaySetting birthdaySetting = birthdayService.findBirthdaySetting();
		//disabled, do not sent birthday SMS
		if(!"1".equals(birthdaySetting.getEnable())){
			return;
		}
		if (birthdaySetting == null) {
			logger.error("生日提醒设置为空，不能发送生日慰问。");
			return;
		}

		String currentDateMMDD = DateUtil.getCurrentTime(JitongConstants.JT_DATE_FORMAT).substring(5, 10);
		while (it.hasNext()) {
			User user = (User) it.next();
			String birthday = user.getBirthday();
			String birthdayMMDD = null;
			if (birthday != null && birthday.length() >= 10) {
				birthdayMMDD = birthday.substring(5, 10);
			} else {
				continue;
			}

			if (currentDateMMDD.equals(birthdayMMDD)) {
				SMS sms = new SMS();
				sms.setBusinessType("birthday");
				sms.setPhoneNumber(user.getPhoneNumber());
				sms.setRecipientId(user.getId());
				sms.setRequestTime(currentTime);
				String format = birthdaySetting.getFormat();
				format = format.replaceAll(BirthdaySetting.BIRTHDAY, StringUtil.trim(user.getBirthday()));
				format = format.replaceAll(BirthdaySetting.DEPT, StringUtil.trim(user.getDept()));
				format = format.replaceAll(BirthdaySetting.UNIT, StringUtil.trim(user.getUnit()));
				format = format.replaceAll(BirthdaySetting.NAME, StringUtil.trim(user.getName()));

				sms.setSMSContent(format);
				sms.setSenderInfo("程序发送");
				
				
				smsService.insertSMS(sms);
			}
		}
	}

	/**
	 * 部门生日慰问
	 * 
	 * @throws JTException
	 */
	private void checkAndSendDeptBirthDayGreeting() throws JTException {
		SMSService smsService = new SMSService();
		UserService userService = new UserService();
		/*
		 * List<User> userList = userService.queryUsers(); Iterator<User> it =
		 * userList.iterator();
		 */
		String currentTime = DateUtil.getCurrentTime(JitongConstants.JT_DATETIME_FORMAT);

		DeptBirthdayService service = new DeptBirthdayService();
		List<DeptBirthday> deptBirthdayList = service.queryDeptBirthdays();
		if (deptBirthdayList == null || deptBirthdayList.isEmpty()) {
			logger.info("没有部门生日慰问设置。");
			return;
		}
		String currentDateMMDD = DateUtil.getCurrentTime(JitongConstants.JT_DATE_FORMAT).substring(5, 10);
		for (DeptBirthday deptBirthday : deptBirthdayList) {
			List<User> persons = service.queryPersonsByDeptBirthdayId(deptBirthday.getId());
			if (persons != null) {
				for (User person : persons) {
					String birthday = person.getBirthday();
					String birthdayMMDD = null;
					if (birthday != null && birthday.length() >= 10) {
						birthdayMMDD = birthday.substring(5, 10);
					} else {
						continue;
					}
					if (currentDateMMDD.equals(birthdayMMDD)) {
						SMS sms = new SMS();
						sms.setBusinessType("deptbirthday");
						sms.setBusinessId(deptBirthday.getId());
						sms.setPhoneNumber(person.getPhoneNumber());
						sms.setRecipientId(person.getId());
						sms.setRequestTime(currentTime);
						String format = deptBirthday.getFormat();
						format = format.replaceAll(BirthdaySetting.BIRTHDAY, StringUtil.trim(person.getBirthday()));
						format = format.replaceAll(BirthdaySetting.DEPT, StringUtil.trim(person.getDept()));
						format = format.replaceAll(BirthdaySetting.UNIT, StringUtil.trim(person.getUnit()));
						format = format.replaceAll(BirthdaySetting.NAME, StringUtil.trim(person.getName()));

						sms.setSMSContent(format);
						sms.setSenderInfo("程序发送");
						smsService.insertSMS(sms);
					}
				}
			}
		}

	}

	public static void main(String[] args) throws JTException {
		DailyTimer t = new DailyTimer();
		t.checkAndSendDeptBirthDayGreeting();

	}
}
