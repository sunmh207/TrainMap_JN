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
import com.jitong.qiyezixun.domain.BirthdaySetting;
import com.jitong.qiyezixun.service.BirthdayService;
import com.jitong.sms.domain.SMS;
import com.jitong.sms.service.SMSService;
import com.jitong.zrrc.domain.Relationship;
import com.jitong.zrrc.service.ZRRCService;

/**
 * 责任人车 提醒
 * 
 * @author stanley.sun
 * 
 */
public class ZRRCJob extends BaseJob {
	private static Logger logger = Logger.getLogger(ZRRCJob.class);

	public void executeDo() throws JTException {
		logger.debug("ZRRCTimer is executing.");
		aqtx();
		logger.debug("ZRRCTimer finished executing.");
	}

	/**
	 * 安全天数提醒
	 * 
	 * @throws JTException
	 */
	private void aqtx() throws JTException {
		SMSService smsService = new SMSService();
		ZRRCService zrrcService = new ZRRCService();
		List<Relationship> rList = zrrcService.queryRelationships();
		Iterator<Relationship> it = rList.iterator();

		String currentDate = DateUtil.getCurrentTime(JitongConstants.JT_DATE_FORMAT);
		String currentTime = DateUtil.getCurrentTime(JitongConstants.JT_DATETIME_FORMAT);

		while (it.hasNext()) {
			Relationship r = (Relationship) it.next();
			String lastEventDate = r.getLastEventDate();
			if (StringUtil.isEmpty(lastEventDate)) {
				continue;
			}

			int aqts = DateUtil.dateDayMinus(currentDate, JitongConstants.JT_DATE_FORMAT, lastEventDate, JitongConstants.JT_DATE_FORMAT);

			SMS sms = new SMS();
			sms.setBusinessType("ZRRC.AQTX");
			sms.setBusinessId(r.getId());
			User user = SysCache.interpertUser(r.getPersonId());
			sms.setPhoneNumber(user.getPhoneNumber());
			sms.setRecipientId(user.getId());
			sms.setRequestTime(currentTime);

			sms.setSMSContent("安全天数提醒：您的安全天数为 " + aqts + " 天");
			sms.setSenderInfo("程序发送");
			smsService.insertSMS(sms);
		}
	}

	public static void main(String[] args) throws JTException {
		SysCache.loadSysCache();
		ZRRCJob t = new ZRRCJob();
		t.aqtx();

	}
}
