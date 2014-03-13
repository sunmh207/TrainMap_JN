package com.jitong.common.timer;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.jitong.JitongConstants;
import com.jitong.anquanjiaoyu.domain.Wenda;
import com.jitong.anquanjiaoyu.service.WendaService;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.qiyezixun.domain.Poll;
import com.jitong.qiyezixun.service.PollService;
import com.jitong.sms.domain.InBox;
import com.jitong.sms.domain.SMS;
import com.jitong.sms.service.InBoxService;
import com.jitong.sms.service.SMSService;
import com.jitong.wnp.domain.ReceivedMMS;
import com.jitong.wnp.domain.ReceivedSMS;

/*
 * execute frequently (every 5-10 minutes)
 */
public class FrequentlyJob extends BaseJob {
	private static Logger logger = Logger.getLogger(FrequentlyJob.class);

	public void executeDo() throws JTException {
		calculatePoll();
		replyWenda();
		calculateWenda();
		updateSMSMgrIds();
	}

	private void calculatePoll() throws JTException {
		logger.info("开始统计投票结果....");
		PollService service = new PollService();
		List<Poll> polls = service.queryRunningPolls();
		if (polls != null && !polls.isEmpty()) {
			InBoxService inboxService = new InBoxService();
			// for each poll
			for (Poll poll : polls) {
				Map<String, Integer> resultMap = new TreeMap<String, Integer>();
				List<InBox> inboxs = inboxService.queryInBoxByBusinessInfo(JitongConstants.BUSINESS_TYPE_TOUPIAO, poll.getNumber(),poll.getStartDate()+" 00:00:00",poll.getEndDate()+" 23:59:59");
				if (inboxs != null && !inboxs.isEmpty()) {

					for (InBox inbox : inboxs) {
						if (inbox == null)
							continue;
						String result = inbox.getContent();
						if (result == null)
							continue;
						Integer count = resultMap.get(result);
						if (count != null) {
							resultMap.put(result, new Integer(count + 1));
						} else {
							resultMap.put(result, new Integer(1));
						}
					}

				}
				Iterator<String> it = resultMap.keySet().iterator();
				StringBuffer resultbf = new StringBuffer("投票结果：<br>");
				while (it.hasNext()) {
					String key = it.next();
					Integer value = resultMap.get(key);
					resultbf.append(key + ":" + value + "票<br>");
				}

				poll.setResult(resultbf.toString());
				service.updatePoll(poll);
			}// for each poll
		}
		logger.info("投票统计结束");
	}

	private void replyWenda() throws JTException {
		logger.info("短信答题：开始给用户回复....");
		SMSService smsService = new SMSService();
		WendaService service = new WendaService();
		List<Wenda> wendas = service.queryRunningWendas();
		if (wendas != null && !wendas.isEmpty()) {
			InBoxService inboxService = new InBoxService();
			// for each Wenda
			for (Wenda wenda : wendas) {
				List<InBox> inboxs = inboxService.queryNotReplyInBox(JitongConstants.BUSINESS_TYPE_DATI, wenda.getNumber(),wenda.getStartDate()+" 00:00:00",wenda.getEndDate()+" 23:59:59");
				if (inboxs != null && !inboxs.isEmpty()) {
					String officialAnswer = wenda.getAnswer();
					if(StringUtil.isEmpty(officialAnswer)) continue;
					
					for (InBox inbox : inboxs) {
						String answer  = inbox.getContent();
						//answer is correct
						if(officialAnswer.equals(answer)){
							SMS sms = new SMS();
							sms.setBusinessType("FrequentlyJob");
							sms.setBusinessId(inbox.getBusinessId());
							sms.setPhoneNumber(inbox.getPhoneNumber());
							User u =  SysCache.interpertUserByPhone(inbox.getPhoneNumber());
							sms.setRecipientId(u==null?"":u.getId());
							sms.setRequestTime(DateUtil.getCurrentTime());
							sms.setSenderInfo("程序发送");
							sms.setSMSContent("你回答的 "+wenda.getContent()+" 问题，回答正确");
							smsService.insertSMS(sms);
							inbox.setReply("1");
							inboxService.updateBo(inbox);
						}else{//answer is wrong
							SMS sms = new SMS();
							sms.setBusinessType("FrequentlyJob");
							sms.setBusinessId(inbox.getBusinessId());
							sms.setPhoneNumber(inbox.getPhoneNumber());
							User u =  SysCache.interpertUserByPhone(inbox.getPhoneNumber());
							sms.setRecipientId(u==null?"":u.getId());
							sms.setRequestTime(DateUtil.getCurrentTime());
							sms.setSenderInfo("程序发送");
							sms.setSMSContent("你回答的 "+wenda.getContent()+" 问题，回答错误。 正确答案为："+wenda.getAnswer());
							smsService.insertSMS(sms);
							inbox.setReply("1");
							inboxService.updateBo(inbox);
						}
						
					}
				}
			}// for each wenda
		}
		logger.info("短信答题：回复结束");
	}
	private void calculateWenda() throws JTException {
		logger.info("开始统计答题结果....");
		WendaService service = new WendaService();
		List<Wenda> wendas = service.queryRunningWendas();
		if (wendas != null && !wendas.isEmpty()) {
			InBoxService inboxService = new InBoxService();
			// for each Wenda
			for (Wenda wenda : wendas) {
				//List<InBox> inboxs = inboxService.queryInBoxByBusinessInfo(JitongConstants.BUSINESS_TYPE_DATI, wenda.getNumber(),wenda.getStartDate()+" 00:00:00",wenda.getEndDate()+" 23:59:59");
				List<InBox> inboxs = inboxService.queryInBoxByBusinessInfo(JitongConstants.BUSINESS_TYPE_DATI, wenda.getNumber());
				int correctCount = 0;
				int wrongCount = 0;
				int noreplyCount = 0;
				if (inboxs != null && !inboxs.isEmpty()) {
					for (InBox inbox : inboxs) {
						if (inbox==null) continue;
						if(!"1".equals(inbox.getReply())){
							noreplyCount++;
							continue;
						}
						boolean correct = wenda.getAnswer().equals(inbox.getContent());
						if (correct) {
							correctCount++;
						} else {
							wrongCount++;
						}
					}
				}
				wenda.setCorrectCount("" + correctCount);
				wenda.setWrongCount("" + wrongCount);
				wenda.setNoreplyCount(""+noreplyCount);
				service.updateWenda(wenda);
			}// for each wenda
		}
		logger.info("答题结果统计结束");
	}

/**
 * mgrId为空的短信进行批量设置
 * @throws JTException
 */
	private void updateSMSMgrIds() throws JTException{
		logger.info("开始更新短信MgrIds.....");
		SMSService service = new SMSService();
		//SMS
		List smsList = service.queryByHql("from SMS s where s.mgrIds is null and s.recipientId is not null");
		if(smsList!=null&&!smsList.isEmpty()){
			for(Object o:smsList){
				SMS sms = (SMS)o;
				sms.setMgrIds(SysCache.getManagerIds(sms.getRecipientId()));
				service.updateSMS(sms);
			}
		}
		//ReceivedSMS
		List rsmsList = service.queryByHql("from ReceivedSMS s where s.mgrIds is null and s.phoneNumber is not null");
		if(rsmsList!=null&&!rsmsList.isEmpty()){
			for(Object o:rsmsList){
				ReceivedSMS rsms = (ReceivedSMS)o;
				String phoneNumber =rsms.getPhoneNumber();
				User recipient = SysCache.interpertUserByPhone(phoneNumber);
				if(recipient!=null){
					rsms.setMgrIds(SysCache.getManagerIds(recipient.getId()));
				}else{
					logger.error("在JTMOBILE_SMS_RECEIVED表中，电话号码"+phoneNumber+"没有对应的用户！");
				}
				service.updateBo(rsms);
			}
		}
		//ReceivedMMS
		List rmmsList = service.queryByHql("from ReceivedMMS s where s.mgrIds is null and s.ccPhoneNumber is not null");
		if(rmmsList!=null&&!rmmsList.isEmpty()){
			for(Object o:rmmsList){
				ReceivedMMS rmms = (ReceivedMMS)o;
				String phoneNumber =rmms.getCcPhoneNumber();
				User recipient = SysCache.interpertUserByPhone(phoneNumber);
				if(recipient!=null){
					rmms.setMgrIds(SysCache.getManagerIds(recipient.getId()));
				}else{
					logger.error("在JTMOBILE_MMS_RECEIVED表中，电话号码"+phoneNumber+"没有对应的用户！");
				}
				service.updateBo(rmms);
			}
		}
		//TODO JTMOBILE_INBOX...?
		logger.info("更新短信MgrIds.....[结束]");
	}
	
	public static void main(String[] args) throws JTException {
		// new FrequentlyJob().calculateWenda();
		new FrequentlyJob().calculatePoll();
	}
}
