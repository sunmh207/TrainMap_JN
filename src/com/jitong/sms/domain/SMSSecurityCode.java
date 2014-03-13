package com.jitong.sms.domain;

import java.util.Calendar;

import com.jitong.common.util.StringUtil;

public class SMSSecurityCode {
	public SMSSecurityCode(int validPeriod){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE)+validPeriod);
		this.expiredTime = c;
		this.code=StringUtil.randomString(6);
	}
	
	private String code;
	private Calendar expiredTime;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

	public Calendar getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Calendar expiredTime) {
		this.expiredTime = expiredTime;
	}

	public boolean isValid() {
		if (expiredTime == null) {
			return false;
		}
		Calendar c = Calendar.getInstance();
		return c.before(expiredTime);
	}

}
