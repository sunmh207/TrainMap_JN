package com.jitong.anquanjiaoyu.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.JitongConstants;
import com.jitong.anquanjiaoyu.domain.Dati;
import com.jitong.anquanjiaoyu.domain.DatiAnswer;
import com.jitong.common.domain.SMSProducer;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.console.domain.User;
import com.jitong.sms.dao.SMSDAO;
import com.jitong.sms.domain.SMS;
import com.jitong.sms.service.SMSService;

public class DatiService extends SMSService{
	private static Logger logger = Logger.getLogger(DatiService.class);
	SMSDAO dao;

	public DatiService(Session s) {
		super(s);
		dao = new SMSDAO(s);
	}

	public DatiService() throws JTException {
		super();
		dao = new SMSDAO(s);
	}
	
	public void saveDati(Dati dati) throws JTException {
		Transaction t2 = super.beginTransaction();
		logger.debug("Transaction: " + t2);
		logger.debug(s);
		saveSMSProducerSelf(dati);
		List<User> sendToList = retrieveUserList(dati);
		for (User user : sendToList) {
			SMS sms = new SMS();
			DatiAnswer answer = new DatiAnswer();
			generateDatiAnswerFromProducer(dati, user, answer);
			dao.createBo(answer);
			
			String host = JitongConstants.getHOST();
			if(!host.endsWith("/")){
				host+="/";
			}
			String url = host;
			generateSMSFromProducer(dati, user, sms);
			sms.setSMSContent("集通铁路信息化平台邀您参加WAP互动答题，请访问"+url+"答题。");
			dao.createBo(sms);
		}

		super.commitTransaction(t2);
	}

	private void generateDatiAnswerFromProducer(Dati dati,
			User user, DatiAnswer answer) {
		String secCode = StringUtil.randomString();
		answer.setDatiId(dati.getId());
		answer.setSecurityCode(secCode);
		answer.setDept(user.getUnitDept());
		answer.setName(user.getName());
		answer.setPhone(user.getPhoneNumber());
		answer.setTitle(dati.getTitle());
		answer.setUserId(user.getId());
		answer.setCreateDate(DateUtil.getCurrentTime());
	}
	
	public int getCorrectAnswerNumber(String datiId){
		int ret=0;
		Session s = null;
		try {
			s = DBtools.getExclusiveSession();
			Connection conn = s.connection();
			conn.setAutoCommit(true);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select count(*) as num from jtmobile_dati d, jtmobile_dati_answer a where d.wenda_id= a.dati_id and a.CORRECT_FLAG='1' and d.wenda_id='"+datiId+"'");
			if(rs.next()) {
				ret=rs.getInt("num");
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
		return ret;
	}
	public int getWrongAnswerNumber(String datiId){
		int ret=0;
		Session s = null;
		try {
			s = DBtools.getExclusiveSession();
			Connection conn = s.connection();
			conn.setAutoCommit(true);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select count(*) as num from jtmobile_dati d, jtmobile_dati_answer a where d.wenda_id= a.dati_id and a.CORRECT_FLAG='0' and d.wenda_id='"+datiId+"'");
			if(rs.next()) {
				ret=rs.getInt("num");
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
		return ret;
	}
	public int getNotAnswerNumber(String datiId){
		int ret=0;
		Session s = null;
		try {
			s = DBtools.getExclusiveSession();
			Connection conn = s.connection();
			conn.setAutoCommit(true);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select count(*) as num from jtmobile_dati d, jtmobile_dati_answer a where d.wenda_id= a.dati_id and a.CORRECT_FLAG is null and d.wenda_id='"+datiId+"'");
			if(rs.next()) {
				ret=rs.getInt("num");
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
		return ret;
	}
}
