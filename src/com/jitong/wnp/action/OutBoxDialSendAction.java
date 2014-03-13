package com.jitong.wnp.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jitong.JitongConstants;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.ActionUtil;
import com.jitong.common.util.BeanUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.common.util.SysUtil;
import com.jitong.console.domain.User;
import com.jitong.wnp.domain.OutBoxDial;
import com.jitong.wnp.service.OutBoxDialService;
import com.opensymphony.xwork2.Preparable;

public class OutBoxDialSendAction extends JITActionBase implements Preparable {
	private List<String> voicefilelist = SysUtil.getVoiceFileList();
	private OutBoxDial outboxdial;
	private static OutBoxDialService service;
	private Map<String, String> purposeMap = new HashMap<String, String>();

	public void prepare() throws JTException {
		if (service == null) {
			service = new OutBoxDialService();
		}

		if (outboxdial != null && outboxdial.getOutboxdialId() != null) {
			outboxdial = (OutBoxDial) service.findOutBoxDial(outboxdial.getOutboxdialId());
		}

		purposeMap.put("1", "电话");
		purposeMap.put("2", "短信");
		purposeMap.put("3", "电话+短信");
	}

	public String input() {
		return INPUT;
	}

	public String send() throws Exception {
		String idString = request.getParameter("useridStr");
		if (StringUtil.isEmpty(idString)) {
			addActionError("人员不能为空");
		}
		User user = (User) session.get(JitongConstants.USER);
		String[] ids = StringUtil.parseString2Array(idString, ",");
		OutBoxDial[] newoutboxdials = new OutBoxDial[ids.length];
		for (int i = 0; i < ids.length; i++) {
			User person = SysCache.interpertUser(ids[i]);
			String phoneNumber = person.getPhoneNumber();
			if (StringUtil.isEmpty(phoneNumber)) {
				addActionError(person.getName() + "的电话号码为空，不能发送");
				return INPUT;
			}

			OutBoxDial newoutboxdial = new OutBoxDial();
			BeanUtil.copyProperties(newoutboxdial, outboxdial);

			newoutboxdial.setDtCreate(new Date());
			//newoutboxdial.setDtCalled(new Date());
			//newoutboxdial.setIp("登录用户：" + user.getName() + "登录IP：" + ActionUtil.getRemoteAddr(request));
			newoutboxdial.setSenderInfo(this.getLoginUserInfo());
			newoutboxdial.setMobile(phoneNumber);
			newoutboxdial.setOperatorId(user.getId());
			newoutboxdial.setRecipientId(ids[i]);
			newoutboxdial.setBusinessType("wnp");
			//set mgrIds
			String mgrIds = SysCache.getManagerIds(ids[i]);
			newoutboxdial.setMgrIds(mgrIds);
			
			newoutboxdials[i] = newoutboxdial;
		}
		service.insertOutBoxDials(newoutboxdials);
		addActionError("发送成功！");
		return INPUT;

	}

	public String getCategoryId() {
		return "wnp.outboxsend";
	}

	public OutBoxDial getOutboxdial() {
		return outboxdial;
	}

	public void setOutboxdial(OutBoxDial outboxdial) {
		this.outboxdial = outboxdial;
	}

	public Map<String, String> getPurposeMap() {
		return purposeMap;
	}

	public void setPurposeMap(Map<String, String> purposeMap) {
		this.purposeMap = purposeMap;
	}

	public List<String> getVoicefilelist() {
		return voicefilelist;
	}

	public void setVoicefilelist(List<String> voicefilelist) {
		this.voicefilelist = voicefilelist;
	}
}
