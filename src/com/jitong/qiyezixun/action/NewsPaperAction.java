package com.jitong.qiyezixun.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.ActionUtil;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.qiyezixun.domain.NewsPaper;
import com.jitong.qiyezixun.domain.NewsPaperMMSProducer;
import com.jitong.qiyezixun.form.NewsPaperSearchForm;
import com.jitong.qiyezixun.service.NewsPaperService;
import com.jitong.sms.domain.MMSAttachment;
import com.jitong.sms.service.SMSService;
import com.jitong.wnp.form.SMSVOSearchForm;
import com.opensymphony.xwork2.Preparable;

public class NewsPaperAction extends JITActionBase implements Preparable {
	private NewsPaperSearchForm searchForm;
	private NewsPaper newsPaper;

	private static NewsPaperService service;
	private List<NewsPaper> newsPapers;

	private String qryName = "";
	public String businessClass = "NewsPaper";

	public void prepare() throws JTException {
		if (service == null) {
			service = new NewsPaperService();
		}
		if (newsPaper != null && newsPaper.getId() != null) {
			newsPaper = service.findNewsPaper(newsPaper.getId());
		}
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		return INPUT;
	}

	public String delete() throws JTException {
		service.deleteNewsPaper(newsPaper.getId());
		list();
		return SUCCESS;
	}

	public String save() throws Exception {
		if (newsPaper.getId() == null || "".equals(newsPaper.getId())) {
			newsPaper.setCreatorId(this.getLoginUser().getId());
			newsPaper.setUnitName(this.getLoginUser().getUnit());
			service.insertNewsPaper(newsPaper);

			String idString = request.getParameter("useridStr");
			String userNameStr = request.getParameter("userNameStr");
			if (!StringUtil.isEmpty(idString)) {
				sendMMS(idString, userNameStr);
			}
		} else {
			service.updateNewsPaper(newsPaper);
			String idString = request.getParameter("useridStr");
			String userNameStr = request.getParameter("userNameStr");
			if (!StringUtil.isEmpty(idString)) {
				sendMMS(idString, userNameStr);
			}
		}
		return list();
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		if(searchForm==null){
			searchForm = new NewsPaperSearchForm();
		}
		searchForm.setUnitName(this.getLoginUser().getUnit());
		
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from NewsPaper me " + hqlsufix + " order by me.date desc";
		return hql;
	}

	public String getQryName() {
		return qryName;
	}

	public void setQryName(String qryName) {
		this.qryName = qryName;
	}

	public String getCategoryId() {
		return "qiyezixun.newspaper";
	}

	public List<NewsPaper> getNewsPapers() {
		return newsPapers;
	}

	public void setNewsPapers(List<NewsPaper> newsPapers) {
		this.newsPapers = newsPapers;
	}

	public String getBusinessClass() {
		return businessClass;
	}

	public void setBusinessClass(String businessClass) {
		this.businessClass = businessClass;
	}

	public NewsPaper getNewsPaper() {
		return newsPaper;
	}

	public void setNewsPaper(NewsPaper newsPaper) {
		this.newsPaper = newsPaper;
	}

	public NewsPaperSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(NewsPaperSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	private void sendMMS(String idString, String userNameStr) throws JTException {
		NewsPaperMMSProducer newspaperMMSProducer = new NewsPaperMMSProducer();

		String content = newsPaper.getContent();
		newspaperMMSProducer.setId(newsPaper.getId());
		newspaperMMSProducer.setSendTime(DateUtil.getCurrentTime());
		newspaperMMSProducer.setSendToIds(idString);
		newspaperMMSProducer.setSendToNames(userNameStr);
		newspaperMMSProducer.setContent(content);
		newspaperMMSProducer.setSenderInfo(this.getLoginUserInfo());

		List<MMSAttachment> listAtts = new ArrayList<MMSAttachment>();
		MMSAttachment att = new MMSAttachment();
		att.setContent(Hibernate.createBlob(content == null ? "".getBytes() : content.getBytes()));
		att.setContentType("txt");
		listAtts.add(att);
		MMSAttachment[] atts = new MMSAttachment[0];
		atts = listAtts.toArray(new MMSAttachment[0]);
		newspaperMMSProducer.setAttachments(atts);
		SMSService service = new SMSService();
		service.saveMM(newspaperMMSProducer);
	}
}