package com.jitong.qiyezixun.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.form.UserMMSSearchForm;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.qiyezixun.domain.NewsPaper;
import com.jitong.qiyezixun.domain.NewsPaperMMSProducer;
import com.jitong.qiyezixun.service.NewsPaperService;
import com.jitong.sms.domain.MMSAttachment;
import com.jitong.sms.domain.MMSVO;
import com.jitong.sms.service.SMSService;
import com.opensymphony.xwork2.Preparable;

public class NewsPaperMMSAction extends JITActionBase implements Preparable {
	private NewsPaper newsPaper;
	private static NewsPaperService service;

	private String businessType = "NewsPaperMMS";
	private String qryName = "";
	

	public void prepare() throws JTException {
		if (service == null) {
			service = new NewsPaperService();
		}
		if (newsPaper != null && newsPaper.getId() != null) {
			newsPaper = service.findNewsPaper(newsPaper.getId());
		}
	}


	public String addPersons() throws JTException {
		String idString = request.getParameter("useridStr");
		String userNameStr = request.getParameter("userNameStr");
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
		att.setContent(Hibernate.createBlob(content==null?"".getBytes():content.getBytes()));
		att.setContentType("txt");
		listAtts.add(att);
		MMSAttachment[] atts = new MMSAttachment[0];
		atts = listAtts.toArray(new MMSAttachment[0]);
		newspaperMMSProducer.setAttachments(atts);
		SMSService service = new SMSService();
		service.saveMM(newspaperMMSProducer);
		return list();
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		UserMMSSearchForm searchForm = new UserMMSSearchForm();
		searchForm.setBusinessId(newsPaper.getId());
		searchForm.setBusinessType(businessType);
		searchForm.setRecipientName(qryName);
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		System.out.println(params);
		String hql = "from " + MMSVO.class.getName() + " me " + hqlsufix;
		//String hql ="from MMSVO me where me.businessId ='"+newsPaper.getId()+"' and me.businessType='"+businessType+"' order by me.requestTime desc";
		return hql;
	}

	public String getCategoryId() {
		return "qiyezixun.newspaper";
	}

	public NewsPaper getNewsPaper() {
		return newsPaper;
	}

	public void setNewsPaper(NewsPaper newsPaper) {
		this.newsPaper = newsPaper;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getQryName() {
		return qryName;
	}

	public void setQryName(String qryName) {
		this.qryName = qryName;
	}

}