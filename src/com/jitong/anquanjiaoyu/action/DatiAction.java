package com.jitong.anquanjiaoyu.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import com.jitong.JitongConstants;
import com.jitong.anquanjiancha.domain.Ziliaoku;
import com.jitong.anquanjiaoyu.domain.Dati;
import com.jitong.anquanjiaoyu.domain.DatiAnswer;
import com.jitong.anquanjiaoyu.form.DatiSearchForm;
import com.jitong.anquanjiaoyu.service.DatiService;
import com.jitong.common.Pager;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.console.domain.User;
import com.jitong.sms.domain.SMS;
import com.jitong.sms.service.SMSService;

public class DatiAction extends JITActionBase {
	private static Logger logger = Logger.getLogger(TixingxuexiAction.class);
	private Dati dati;
	private String keyword;
	private String id;
	private String ziliaokuId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private DatiSearchForm searchForm;

	public Dati getDati() {
		return dati;
	}

	public void setDati(Dati dati) {
		this.dati = dati;
	}

	public String getZiliaokuId() {
		return ziliaokuId;
	}

	public void setZiliaokuId(String ziliaokuId) {
		this.ziliaokuId = ziliaokuId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public DatiSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(DatiSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public String save() throws JTException {
		try{
		SMSService service = new SMSService();
		BaseDAO dao = new BaseDAO(service.getS());
		if (id != null && id.trim().length() > 0) {
			dati = (Dati) dao.findBoById(Dati.class, id);
			service.enhanceSMSProducer(dati);
		} else {
			dati = new Dati();
			dati.setStartDate(DateUtil.getCurrentDate());
			
			dati.setEndDate(DateUtil.datePlus(DateUtil.getCurrentDate(), JitongConstants.JT_DATE_FORMAT, Calendar.DAY_OF_MONTH, 7));
			
			if (ziliaokuId != null && ziliaokuId.trim().length() > 0) {
				Ziliaoku ziliaoku = (Ziliaoku) dao.findBoById(Ziliaoku.class, ziliaokuId);
				dati.setContent(ziliaoku.getContent());
				dati.setTitle(ziliaoku.getTitle());
			}
		}
		}catch(Exception e){
			addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	public String doSave() throws JTException {
		if (dati != null) {
			DatiService service = new DatiService();
			User loginUser = this.getLoginUser();
			dati.setCreatorId(loginUser.getId());
			dati.setUnitName(loginUser.getUnit());
			dati.setCreateTime(DateUtil.getCurrentTime());
			service.saveDati(dati);
			calcAndUpdateAnserResult(dati.getId());
		}
		return "thankYou";
	}

	public String delete() throws JTException {
		BaseService service = new BaseService();
		BaseDAO dao = new BaseDAO(service.getS());
		Transaction t = service.beginTransaction();
		Object o = dao.findBoById(Dati.class, id);
		dao.deletePo(o);
		service.commitTransaction(t);
		return "thankYou";
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		if(searchForm==null){
			searchForm = new DatiSearchForm();
		}
		searchForm.setUnitName(this.getLoginUser().getUnit());

		session.put("isListAnswer", Boolean.FALSE);
		Class<?> domainClass = Dati.class;
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName() + " me " + hqlsufix + " order by me.createTime desc";
		return hql;
	}

	public String pick() throws JTException {
		BaseDAO dao = new BaseDAO(DBtools.getSession());
		Pager pager = new Pager(this.getCurrentPage(), this.getPageSize());

		List<?> list = null;
		ArrayList<Object> params = new ArrayList<Object>();
		logger.debug("----------->" + keyword);
		params.add("%" + keyword + "%");
		params.add("%" + keyword + "%");
		String listHQL = "from Ziliaoku me where me.title like ? or me.content like ?";
		Object[] arrayParams = params.toArray();
		list = dao.findWithPager(listHQL, pager, arrayParams);
		request.setAttribute("objectList", list);
		request.setAttribute("pager", pager);
		return SUCCESS;
	}

	public String listAnswer() throws JTException {
		String correctFlag = request.getParameter("correctFlag");
		if (!StringUtil.isEmpty(correctFlag)) {
			updateAnswer();
		}
		BaseDAO dao = new BaseDAO(DBtools.getSession());
		Pager pager = new Pager(this.getCurrentPage(), this.getPageSize());

		List<?> list = null;

		String listHQL = "from DatiAnswer me where me.datiId=? order by replyTime";
		list = dao.findWithPager(listHQL, pager, new Object[] { id });
		request.setAttribute("objectList", list);
		request.setAttribute("pager", pager);
		session.put("isListAnswer", Boolean.TRUE);
		return SUCCESS;
	}

	public void updateAnswer() throws JTException {
		String answerId = request.getParameter("answerId");
		String correctFlag = request.getParameter("correctFlag");
		BaseService service = new BaseService();
		DatiAnswer answer = (DatiAnswer) service.findBoById(DatiAnswer.class, answerId);
		answer.setCorrectFlag(correctFlag);
		service.updateBo(answer);
		calcAndUpdateAnserResult(answer.getDatiId());

		//if answer is wrong, send the correct answer to user
		if ("0".equals(correctFlag)) {
			Dati dati = (Dati) service.findBoById(Dati.class, answer.getDatiId());
			SMS sms = new SMS();
			sms.setBusinessId(answerId);
			sms.setBusinessType(DatiAnswer.class.getSimpleName());
			sms.setOperatorId(this.getLoginUser().getId());
			sms.setPhoneNumber(answer.getPhone());
			sms.setRecipientId(answer.getUserId());
			sms.setRequestTime(DateUtil.getCurrentTime());
			sms.setSenderInfo(this.getLoginUserInfo());
			sms.setSMSContent("您的网上答题回答错误。\n 问题:" + dati.getContent() + "\n 正确答案:" + dati.getOfficialAnswer());
			SMSService smsService = new SMSService();
			smsService.insertSMS(sms);
		}

	}

	private void calcAndUpdateAnserResult(String datiId) throws JTException {
		DatiService datiServ = new DatiService();
		int correct = datiServ.getCorrectAnswerNumber(datiId);
		int wrong = datiServ.getWrongAnswerNumber(datiId);
		int no = datiServ.getNotAnswerNumber(datiId);
		Dati dati = (Dati) datiServ.findBoById(Dati.class, datiId);
		String result = "正确:" + correct + "人\n" + "错误:" + wrong + "人\n" + "未答:" + no + "人";
		User loginUser = this.getLoginUser();
		dati.setCreatorId(loginUser.getId());
		dati.setUnitName(loginUser.getUnit());
		dati.setResult(result);
		datiServ.updateBo(dati);
	}

	@Override
	public String exportExcel() throws JTException {
		Boolean isListingAnswer = (Boolean) session.get("isListAnswer");
		if (Boolean.TRUE.equals(isListingAnswer)) {
			BaseDAO dao = new BaseDAO(DBtools.getSession());
			Pager pager = new Pager(1, JitongConstants.MAX_PAGE_SIZE);

			List<?> list = null;

			String listHQL = "from DatiAnswer me where me.datiId=? order by replyTime";
			list = dao.findWithPager(listHQL, pager, new Object[] { id });
			request.setAttribute("objectList", list);
			request.setAttribute("pager", pager);
			session.put(JitongConstants.SESSION_OBJECT, list);
			return "exportExcel";
		} else {
			return super.exportExcel();
		}
	}
}
