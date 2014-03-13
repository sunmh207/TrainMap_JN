package com.jitong.anquanjiaoyu.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.JitongAppContext;
import com.jitong.anquanjiaoyu.domain.Shoujibao;
import com.jitong.anquanjiaoyu.domain.Tixingxuexi;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.console.domain.User;
import com.jitong.sms.dao.SMSDAO;
import com.jitong.sms.domain.MMS;
import com.jitong.sms.domain.MMSAttachment;
import com.jitong.sms.service.SMSService;

public class TixingxuexiService extends SMSService {
	private static Logger logger = Logger.getLogger(TixingxuexiService.class);
	SMSDAO dao;

	public TixingxuexiService(Session s) {
		super(s);
		dao = new SMSDAO(s);
	}

	public TixingxuexiService() throws JTException {
		super();
		dao = new SMSDAO(s);
	}

	public void sendAsMMS(String id, String sendToIds) throws JTException {
		Tixingxuexi tixingxuexi = (Tixingxuexi) dao.findBoById(Tixingxuexi.class, id);
		List<User> sendToList = retrieveUserList(sendToIds);
		List<MMS> mmses = new ArrayList<MMS>(sendToList.size());
		if (sendToList.size() == 0)
			return;
		for (User user : sendToList) {
			MMS mms = new MMS();
			mms.setBusinessId(id);
			mms.setBusinessType(tixingxuexi.getBusinessType());
			//sms.setRequestTime(shoujibao.getSendTime());
			mms.setRequestTime(DateUtil.getCurrentTime());
			mms.setPhoneNumber(user.getPhoneNumber());
			mms.setRecipientId(user.getId());
			mms.setOperatorId(JitongAppContext.getOperator().getId());// stanley
			mms.setSenderInfo(JitongAppContext.getSenderInfo());
			mms.setRealMmsId(id);
			mmses.add(mms);
		}

		Transaction t2 = super.beginTransaction();
		dao.saveOrUpdateAll(mmses);
		super.commitTransaction(t2);

		Transaction t3 = super.beginTransaction();
		MMSAttachment mmsAttachment = new MMSAttachment();
		mmsAttachment.setMmsId(id);
		byte[] bytes = tixingxuexi.getContent().getBytes();
		mmsAttachment.setContent(Hibernate.createBlob(bytes));
		mmsAttachment.setContentType("txt");
		dao.createBo(mmsAttachment);
		super.commitTransaction(t3);
	}
}
