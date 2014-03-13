package com.jitong.anquanjiaoyu.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.JitongAppContext;
import com.jitong.anquanjiaoyu.domain.Shoujibao;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.console.domain.User;
import com.jitong.sms.dao.SMSDAO;
import com.jitong.sms.domain.MMS;
import com.jitong.sms.domain.MMSAttachment;
import com.jitong.sms.service.SMSService;

public class ShoujibaoService extends SMSService {
	private static Logger logger = Logger.getLogger(SMSService.class);
	SMSDAO dao;

	public ShoujibaoService(Session s) {
		super(s);
		dao = new SMSDAO(s);
	}

	public ShoujibaoService() throws JTException {
		super();
		dao = new SMSDAO(s);
	}

	public void sendAsMMS(String id, String sendToIds) throws JTException {
		Shoujibao shoujibao = (Shoujibao) dao.findBoById(Shoujibao.class, id);
		List<User> sendToList = retrieveUserList(sendToIds);
		List<MMS> mmses = new ArrayList<MMS>(sendToList.size());
		if (sendToList.size() == 0)
			return;
		for (User user : sendToList) {
			MMS sms = new MMS();
			sms.setBusinessId(id);
			sms.setBusinessType(shoujibao.getBusinessType());
			//sms.setRequestTime(shoujibao.getSendTime());
			sms.setRequestTime(DateUtil.getCurrentTime());
			sms.setPhoneNumber(user.getPhoneNumber());
			sms.setRecipientId(user.getId());
			sms.setOperatorId(JitongAppContext.getOperator().getId());// stanley
			sms.setSenderInfo(JitongAppContext.getSenderInfo());
			sms.setRealMmsId(id);
			mmses.add(sms);
		}

		Transaction t2 = super.beginTransaction();
		dao.saveOrUpdateAll(mmses);
		super.commitTransaction(t2);

		Transaction t3 = super.beginTransaction();
		MMSAttachment mmsAttachment = new MMSAttachment();
		mmsAttachment.setMmsId(id);
		byte[] bytes = shoujibao.getContent().getBytes();
		mmsAttachment.setContent(Hibernate.createBlob(bytes));
		mmsAttachment.setContentType("txt");
		dao.createBo(mmsAttachment);
		super.commitTransaction(t3);
	}
}
