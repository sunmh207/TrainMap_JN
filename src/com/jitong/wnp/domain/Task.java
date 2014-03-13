package com.jitong.wnp.domain;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.jitong.JitongConstants;
import com.jitong.common.form.JTField;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;

public class Task {
	private static Logger logger = Logger.getLogger(Task.class);
	public static String REPEATS_DAILY = "daily";
	public static String REPEATS_WEEKDAY = "weekday";
	public static String REPEATS_WEEKLY = "weekly";
	public static String REPEATS_MONTHLY = "monthly";
	public static String REPEATS_YEARLY = "yearly";

	public static String IS_REPEATS_YES = "1";
	public static String IS_REPEATS_NO = "0";

	private String taskId;
	private String startTime;
	private String endTime;

	// 0:no repeat; 1:repeat;
	private String isRepeat;
	/**
	 * daily,week day(MON-FRI), weekly,monthly,yearly
	 */
	private String repeats;
	//private String cronExp;

	private String content;
	/**
	 * 提前提醒时间，the default is 5 minutes
	 */
	private String reminderNumber = "" + 5;
	private String reminderUnit = "" + Calendar.MINUTE;
	private String operatorId;

	private String businessType="Task";
	
	
	private String creatorId;
	private String unitName;
	
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	@JTField(chineseName="提醒内容",order=6)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReminderNumber() {
		return reminderNumber;
	}

	public void setReminderNumber(String reminderNumber) {
		this.reminderNumber = reminderNumber;
	}

	public String getReminderUnit() {
		return reminderUnit;
	}

	public void setReminderUnit(String reminderUnit) {
		this.reminderUnit = reminderUnit;
	}

	public String getReminderUnitTXT() {
		if (reminderUnit == null)
			return null;
		int unit = Integer.parseInt(reminderUnit);
		switch (unit) {
		case Calendar.DAY_OF_MONTH:
			return "天";
		case Calendar.HOUR_OF_DAY:
			return "小时";
		case Calendar.MINUTE:
			return "分钟";
		}
		return reminderUnit;
	}
	@JTField(chineseName="提前提醒",order=3)
	public String getReminderNumberUnitTXT() {
		return getReminderNumber()+" "+getReminderUnitTXT();
	}
	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	@JTField(chineseName="开始时间",order=1)
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	@JTField(chineseName="结束时间",order=2)
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getIsRepeat() {
		return isRepeat;
	}
	@JTField(chineseName="循环",order=4)
	public String getIsRepeatTXT() {
		if (!IS_REPEATS_YES.equals(isRepeat)) {
			return "单次";
		} else if (REPEATS_DAILY.equals(repeats)) {
			return "每天";
		} else if (REPEATS_WEEKDAY.equals(repeats)) {
			return "每个工作日";
		} else if (REPEATS_WEEKLY.equals(repeats)) {
			return "每周";
		} else if (REPEATS_MONTHLY.equals(repeats)) {
			return "每月";
		} else if (REPEATS_YEARLY.equals(repeats)) {
			return "每年";
		}
		return "";
	}

	public void setIsRepeat(String isRepeat) {
		this.isRepeat = StringUtil.boolString2IntString(isRepeat);
	}

	public String getRepeats() {
		return repeats;
	}

	public void setRepeats(String repeats) {
		this.repeats = repeats;
	}

	/*
	 * public String getCronExp() { return cronExp; }
	 * 
	 * public void setCronExp(String cronExp) { this.cronExp = cronExp; }
	 */

	public String getCronExp() {
		return generateCronExp();
	}


	private String generateCronExp() {
		String cronExp = null;
		try {
			String firstReminderTime = DateUtil.datePlus(startTime, JitongConstants.JT_DATETIME_FORMAT, Integer.parseInt(reminderUnit), 0-Integer
					.parseInt(reminderNumber));
			int sec = DateUtil.getCalendarFieldValue(firstReminderTime, JitongConstants.JT_DATETIME_FORMAT, Calendar.SECOND);
			int min = DateUtil.getCalendarFieldValue(firstReminderTime, JitongConstants.JT_DATETIME_FORMAT, Calendar.MINUTE);
			int hour = DateUtil.getCalendarFieldValue(firstReminderTime, JitongConstants.JT_DATETIME_FORMAT, Calendar.HOUR_OF_DAY);
			int dayofmonth = DateUtil.getCalendarFieldValue(firstReminderTime, JitongConstants.JT_DATETIME_FORMAT, Calendar.DAY_OF_MONTH);
			int month = DateUtil.getCalendarFieldValue(firstReminderTime, JitongConstants.JT_DATETIME_FORMAT, Calendar.MONTH);
			month += 1;
			int dayofweek = DateUtil.getCalendarFieldValue(firstReminderTime, JitongConstants.JT_DATETIME_FORMAT, Calendar.DAY_OF_WEEK);
			String dayofweekStr = convertDayOfWeek(dayofweek);
			int year = DateUtil.getCalendarFieldValue(firstReminderTime, JitongConstants.JT_DATETIME_FORMAT, Calendar.YEAR);

			if (!IS_REPEATS_YES.equals(isRepeat)) {
				cronExp = sec + " " + min + " " + " " + hour + " " + dayofmonth + " " + month + " " + "?" + " " + year;
			} else {
				if (REPEATS_DAILY.equals(repeats)) {
					// 1 15 10 1 12 ? 
					cronExp = sec + " " + min + " " + " " + hour + " " + "*" + " " + "*" + " " + "?" ;
				} else if (REPEATS_WEEKDAY.equals(repeats)) {
					// 0 15 10 ? * MON-FRI
					cronExp = sec + " " + min + " " + " " + hour + " " + "?" + " " + "*" + " " + "MON-FRI";
				} else if (REPEATS_WEEKLY.equals(repeats)) {
					// 0 15 10 ? * MON, 0 15 10 ? * TUE,
					cronExp = sec + " " + min + " " + " " + hour + " " + "?" + " " + "*" + " " + dayofweekStr;
				} else if (REPEATS_MONTHLY.equals(repeats)) {
					// 0 15 10 15 * ?
					cronExp = sec + " " + min + " " + " " + hour + " " + dayofmonth + " " + "*" + " " + "?";
				} else if (REPEATS_YEARLY.equals(repeats)) {
					// 0 15 10 15 12 ? *
					cronExp = sec + " " + min + " " + " " + hour + " " + dayofmonth + " " + month + " " + "?" + " " + "*";
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return cronExp;
	}

	private String convertDayOfWeek(int calendarField) {
		switch (calendarField) {
		case 1:
			return "SUN";
		case 2:
			return "MON";
		case 3:
			return "TUE";
		case 4:
			return "WED";
		case 5:
			return "THU";
		case 6:
			return "FRI";
		case 7:
			return "SAT";
		}
		return "";
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	
	public boolean isValidTime(){
		if(this.getStartTime()==null||this.getEndTime()==null){
			return false;
		}
		try{
		Calendar endCal = DateUtil.toCalendar(this.getEndTime(), JitongConstants.JT_DATETIME_FORMAT);
		if(endCal.before(Calendar.getInstance())){
			return false;
		}
		}catch(Exception e){
			return false;
		}
		return true;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	
}
