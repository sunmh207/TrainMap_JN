package com.jitong.shiguchuli.action;

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
		if (queryUser == null) {
			queryUser = (User) session.get(JitongConstants.USER);
		}
		if (session.get("message") != null) {
			request.setAttribute("message", (String) session.get("message"));
			session.remove("message");
		}
		
		setUpRealKind();
		return SUCCESS;
	}

	public String send() throws JTException {
		setUpRealKind();
		ZiliaochaxunService service = new ZiliaochaxunService();
		logger.debug("in send():" + this);
		User u = this.getLoginUser();
		service.doSendMSM(idsToSend,sendToIds, sendToNames, queryUser, keyword, reason,u.getId());
		session.put("message", "您查询的资料已通过短信发送，请稍后注意查收。");
		return getCategoryId().substring("shiguchuli.".length());
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		Class<?> domainClass = Ziliaoku.class;
		searchForm.setContent(keyword);
		setUpRealKind();
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName() + " me " + hqlsufix;
		hql = hql.replaceAll("me.content", "concat(me.content,me.title)");
		return hql;
	}

	private void setUpRealKind() {
		if(searchForm!=null&&searchForm.getKind()!=null){
			return;
		}
		if(searchForm==null)searchForm = new ZiliaochaxunSearchForm();
		String kind = null;
		String categoryId = super.getCategoryId();
		if("shiguchuli.guzhangchaxun".equals(categoryId)){
			kind = JitongConstants.ZILIAOLEIXING_GUZHANGCHULI;
		}
		if("shiguchuli.chulizhidao".equals(categoryId)){
			kind = JitongConstants.ZILIAOLEIXING_SHIGUZHIDAO;
		}
		if("shiguchuli.banfachaxun".equals(categoryId)){
			kind = JitongConstants.ZILIAOLEIXING_XINCHEBANFA;
		}
		if( "shiguchuli.guizhangchaxuan".equals(categoryId)){
			kind = JitongConstants.ZILIAOLEIXING_ZILIAOGUIZHANG;
		}
		searchForm.setKind(kind);
	}

	@Override
	public String getCategoryId() {
		if (super.getCategoryId() != null)
			return super.getCategoryId();
		String kind = searchForm.getKind();
		if (JitongConstants.ZILIAOLEIXING_GUZHANGCHULI.equals(kind)) {
			return "shiguchuli.guzhangchaxun";
		}
		if (JitongConstants.ZILIAOLEIXING_SHIGUZHIDAO.equals(kind)) {
			return "shiguchuli.chulizhidao";
		}
		if (JitongConstants.ZILIAOLEIXING_XINCHEBANFA.equals(kind)) {
			return "shiguchuli.banfachaxun";
		}
		if (JitongConstants.ZILIAOLEIXING_ZILIAOGUIZHANG.equals(kind)) {
			return "shiguchuli.guizhangchaxuan";
		}
		return "shiguchuli.guizhangchaxuan";
	}
}
