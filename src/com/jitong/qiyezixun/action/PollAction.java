package com.jitong.qiyezixun.action;

import java.util.ArrayList;
import java.util.List;

import com.jitong.JitongConstants;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.qiyezixun.domain.Poll;
import com.jitong.qiyezixun.form.PollSearchForm;
import com.jitong.qiyezixun.service.PollService;
import com.jitong.sms.domain.InBox;
import com.jitong.sms.domain.SMS;
import com.jitong.sms.service.InBoxService;
import com.jitong.sms.service.SMSService;
import com.opensymphony.xwork2.Preparable;

public class PollAction extends JITActionBase implements Preparable {
	private Poll poll;
	private PollSearchForm searchForm;

	private static String businessType = "poll";
	private PollService service;
	private SMSService smsService;
	private InBoxService inboxService;

	private List<Poll> polls;
	
	private String pollId;
	public void prepare() throws JTException {
		if (service == null) {
			service = new PollService();
		}
		if (smsService == null) {
			smsService = new SMSService();
		}
		if (poll != null && poll.getPollId() != null) {
			poll = service.findPoll(poll.getPollId());
		}
	}

	public String input() throws JTException {
		return INPUT;
	}
	
	public String delete() throws Exception{
		if (pollId!=null){
			service.deletePoll(pollId);
		}
		return list();
	}

	public String save() throws JTException {

		if (inboxService == null) {
			inboxService = new InBoxService();
		}
		poll.setCreatorId(this.getLoginUser().getId());
		poll.setUnitName(this.getLoginUser().getUnit());
		String pollId = service.insertPoll(poll);

		// insert pollDetail
		String idString = request.getParameter("useridStr");
		String[] ids = StringUtil.parseString2Array(idString, ",");
		for (int i = 0; i < ids.length; i++) {
			// send SMS
			SMS sms = new SMS();
			sms.setBusinessType(businessType);
			sms.setBusinessId(pollId);
			User u = SysCache.interpertUser(ids[i]);
			sms.setPhoneNumber(u.getPhoneNumber());
			sms.setRequestTime(DateUtil.getCurrentTime(JitongConstants.JT_DATETIME_FORMAT));
			sms.setSMSContent(poll.getDesc());
			sms.setRecipientId(ids[i]);
			
			User loginUser = (User)session.get(JitongConstants.USER);
			sms.setSenderInfo(loginUser.getName()+"-"+loginUser.getUnitDept()+"-"+loginUser.getLastLoginIp());
			smsService.insertSMS(sms);

			// insert empty record to inbox
			InBox inbox = new InBox();
			inbox.setBusinessType(JitongConstants.BUSINESS_TYPE_TOUPIAO);
			inbox.setBusinessId(poll.getNumber());
			inbox.setPhoneNumber(u.getPhoneNumber());
			inboxService.insertInBox(inbox);
		}

		return SUCCESS;
	}

	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

	public List<Poll> getPolls() {
		return polls;
	}

	public void setPolls(List<Poll> polls) {
		this.polls = polls;
	}

	public String getCategoryId() {
		return "qiyezixun.poll";
	}

	public PollSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(PollSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public static String getBusinessType() {
		return businessType;
	}

	public static void setBusinessType(String businessType) {
		PollAction.businessType = businessType;
	}

	public String getPollId() {
		return pollId;
	}

	public void setPollId(String pollId) {
		this.pollId = pollId;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		if(searchForm==null){
			searchForm = new PollSearchForm();
		}
		searchForm.setUnitName(this.getLoginUser().getUnit());
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from Poll me " + hqlsufix + " order by me.startDate desc";
		return hql;
	}
}
