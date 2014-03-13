package com.jitong.shiguchuli;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.jitong.anquanjiancha.domain.Ziliaochaxun;
import com.jitong.anquanjiancha.domain.Ziliaoku;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.console.domain.User;
import com.jitong.sms.service.SMSService;

public class ZiliaochaxunService extends BaseService{
	
	private static Logger logger = Logger.getLogger(ZiliaochaxunService.class);
	BaseDAO dao;
	
	public ZiliaochaxunService(Session s) {
		super(s);
		dao = new BaseDAO(s);
	}

	public ZiliaochaxunService() throws JTException {
		super();
		dao = new BaseDAO(s);
	}
	
	public void doSendMSM(String[] ziliaokuIds, String sendToIds, String sendToNames, User queryUser,String keyword, String reason, String operatorId) throws JTException{
		if(ziliaokuIds==null) return;
		SMSService smsService = new SMSService();
		for (String ziliaokuId : ziliaokuIds) {
			logger.debug(ziliaokuId);
			Ziliaoku ziliaoku = (Ziliaoku) dao.findBoById(Ziliaoku.class, ziliaokuId);
			logger.debug(ziliaoku);
			if(ziliaoku==null)continue;
			Ziliaochaxun ziliaochaxun = new Ziliaochaxun();
			ziliaochaxun.setSendToIds(sendToIds);
			ziliaochaxun.setSendToNames(sendToNames);
			ziliaochaxun.setReason(reason);
			ziliaochaxun.setKeyword(keyword);
			ziliaochaxun.setContent(ziliaoku.getContent());
			ziliaochaxun.setTitle(ziliaoku.getTitle());
			ziliaochaxun.setOperatorId(operatorId);
			smsService.saveSMSProducer(ziliaochaxun);
		}
	}
}
