package com.jitong.shiguchuli.action;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.jitong.JitongConstants;
import com.jitong.anquanjiancha.domain.Ziliaochaxun;
import com.jitong.anquanjiancha.domain.Ziliaoku;
import com.jitong.common.Pager;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.sms.domain.SMS;
import com.jitong.sms.service.SMSService;

public class UrgencyRescueAction extends JITActionBase {
	private static Logger logger = Logger.getLogger(UrgencyRescueAction.class);
	private String content;
	private String ziliaokuId;
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getZiliaokuId() {
		return ziliaokuId;
	}

	public void setZiliaokuId(String ziliaokuId) {
		this.ziliaokuId = ziliaokuId;
	}

	public String getCategoryId() {
		return "shiguchuli.UrgencyRescue";
	}

	public String getBusinessType() {
		return "shiguchuli.UrgencyRescue";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String setZiliaoku() throws JTException {
		SMSService service = new SMSService();
		if (ziliaokuId != null && ziliaokuId.trim().length() > 0) {
			Ziliaoku ziliaoku = (Ziliaoku) service.findBoById(Ziliaoku.class, ziliaokuId);
			this.setContent(ziliaoku.getContent());
		}
		return INPUT;
	}
	public String input()throws Exception{
		return INPUT;
	}
	
	public String save() throws Exception {
		SMSService service = new SMSService();		
		String idString = request.getParameter("useridStr");
		if (StringUtil.isEmpty(idString)) {
			addActionError("请选择人员");
			return INPUT;
		}
		String[] ids = StringUtil.parseString2Array(idString, ",");
		
		
		Ziliaochaxun ziliaochaxun = new Ziliaochaxun();
		ziliaochaxun.setContent(this.getContent());
		ziliaochaxun.setSendTime(DateUtil.getCurrentTime());
		String toNames = request.getParameter("userNameStr");
		ziliaochaxun.setSendToNames(toNames);
		service.createBo(ziliaochaxun);

		for (int i = 0; i < ids.length; i++) {
			SMS sms = new SMS();
			User u = SysCache.interpertUser(ids[i]);
			sms.setBusinessId(ziliaochaxun.getId());
			sms.setPhoneNumber(u.getPhoneNumber());
			sms.setBusinessType(this.getBusinessType());
			sms.setOperatorId(this.getLoginUser().getId());
			sms.setRecipientId(ids[i]);
			sms.setRequestTime(DateUtil.getCurrentTime());
			sms.setSenderInfo(this.getLoginUserInfo());
			sms.setSMSContent(this.getContent());
			service.insertSMS(sms);
		}
		
		addActionError("发送成功");
		return INPUT;
	}
	
	/**
	 * list all YingJiJiuYuan zi liao
	 */

	public String pick() throws JTException {
		BaseDAO dao = new BaseDAO(DBtools.getSession());
		Pager pager = new Pager(this.getCurrentPage(), this.getPageSize());

		List<?> list = null;
		ArrayList<Object> params = new ArrayList<Object>();
		logger.debug("----------->" + keyword);
		params.add("%" + keyword + "%");
		params.add("%" + keyword + "%");
		String listHQL = "from Ziliaoku me where (me.title like ? or me.content like ?) and me.kind='"+JitongConstants.ZILIAOLEIXING_SHIGUZHIDAO+"'";
		Object[] arrayParams = params.toArray();
		list = dao.findWithPager(listHQL, pager, arrayParams);
		request.setAttribute("objectList", list);
		request.setAttribute("pager", pager);
		return "pick";
	}
}
