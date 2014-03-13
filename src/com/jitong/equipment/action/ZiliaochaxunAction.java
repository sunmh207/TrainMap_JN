package com.jitong.equipment.action;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.jitong.JitongConstants;
import com.jitong.anquanjiancha.domain.Ziliaoku;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.console.domain.User;
import com.jitong.shiguchuli.ZiliaochaxunService;
import com.jitong.shiguchuli.form.ZiliaochaxunSearchForm;

public class ZiliaochaxunAction extends JITActionBase {

	private static Logger logger = Logger.getLogger(ZiliaochaxunAction.class);
	private ZiliaochaxunSearchForm searchForm;
	private String keyword;
	private String sendToIds;
	private String sendToNames;
	private User queryUser;
	private String reason;

	private String[] idsToSend;

	public User getQueryUser() {
		return queryUser;
	}

	public String getReason() {
		return reason;
	}

	public void setQueryUser(User queryUser) {
		this.queryUser = queryUser;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public ZiliaochaxunSearchForm getSearchForm() {
		return searchForm;
	}

	public String getKeyword() {
		return keyword;
	}

	public String getSendToIds() {
		return sendToIds;
	}

	public void setSendToIds(String sendToIds) {
		this.sendToIds = sendToIds;
	}

	public String getSendToNames() {
		return sendToNames;
	}

	public void setSendToNames(String sendToNames) {
		this.sendToNames = sendToNames;
	}

	public void setSearchForm(ZiliaochaxunSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String[] getIdsToSend() {
		return idsToSend;
	}

	public void setIdsToSend(String[] idsToSend) {
		this.idsToSend = idsToSend;
	}

	public String query() {
		return SUCCESS;
	}



	public String getListHQL(ArrayList<Object> params) throws JTException {
		Class<?> domainClass = Ziliaoku.class;
		if(searchForm==null)searchForm=new ZiliaochaxunSearchForm();
		searchForm.setContent(keyword);
		searchForm.setKind(JitongConstants.ZILIAOLEIXING_GUZHANGCHULI);
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName() + " me " + hqlsufix;
		hql = hql.replaceAll("me.content", "concat(me.content,me.title)");
		return hql;
	}
}
