package com.jitong.wap.action;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import com.jitong.anquanjiancha.domain.Meitiantixing;
import com.jitong.anquanjiaoyu.domain.Dati;
import com.jitong.anquanjiaoyu.domain.DatiAnswer;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;

public class WapDatiAction extends JITActionBase {
	Logger logger = Logger.getLogger(WapDatiAction.class);
	private String id;
	private String answer;
	private String securityCode;
	private String message;
	private DatiAnswer datiAnswer;
	private Dati dati;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public DatiAnswer getDatiAnswer() {
		return datiAnswer;
	}

	public void setDatiAnswer(DatiAnswer datiAnswer) {
		this.datiAnswer = datiAnswer;
	}

	public Dati getDati() {
		return dati;
	}

	public void setDati(Dati dati) {
		this.dati = dati;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String wap() throws JTException {
		try {
			BaseDAO dao = new BaseDAO(DBtools.getSession());
			datiAnswer = (DatiAnswer) dao.findBoById(DatiAnswer.class, id);
			dati = (Dati) dao.findBoById(Dati.class, datiAnswer.getDatiId());
			logger.debug(dati.getId());
			return SUCCESS;
		} catch (Exception e) {
			return "badURL";
		}
	}

	public String doAnswer() throws JTException {
		BaseService service = new BaseService();
		Transaction t = service.beginTransaction();
		datiAnswer = (DatiAnswer) service.findBoById(DatiAnswer.class, id);
		datiAnswer.setAnswer(answer);
		datiAnswer.setReplyTime(DateUtil.getCurrentTime());
		service.updateBo(id, datiAnswer);
		t.commit();
		return SUCCESS;

	}

	@Override
	public String getListHQL(ArrayList<Object> params) throws JTException {
		String currentDt = DateUtil.getCurrentDate();
		String currentId = getLoginUser().getId();
		params.add(currentId);
		String hql = "select answer from " + DatiAnswer.class.getName()
				+ " answer, Dati dati where answer.datiId=dati.id and dati.startDate<='"+currentDt+"' and dati.endDate>='"+currentDt+"' and answer.userId=? order by answer.createDate desc";
		return hql;
	}

	public static void main(String[] args) throws JTException {
		BaseService service = new BaseService();
		Transaction t = service.beginTransaction();
		DatiAnswer datiAnswer = (DatiAnswer) service.findBoById(
				DatiAnswer.class, "402881e72ed0725f012ed0740dde0008");
		datiAnswer.setAnswer("ddsadsadd");
		datiAnswer.setReplyTime(DateUtil.getCurrentTime());
		service.updateBo("402881e72ed0725f012ed0740dde0008", datiAnswer);
		t.commit();
	}
}
