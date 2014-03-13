package com.jitong.meeting.action;

import java.util.List;

import com.jitong.JitongConstants;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.meeting.domain.Huiyitongzhi;
import com.jitong.meeting.service.HuiyitongzhiService;
import com.opensymphony.xwork2.Preparable;

public class HuiyitongzhiReuseAction extends JITActionBase implements Preparable {
	private HuiyitongzhiService service;
	private String huiyitongzhiId;
	private List<Huiyitongzhi> huiyitongzhis;

	public void prepare() throws JTException {
		if (service == null) {
			service = new HuiyitongzhiService();
		}
		huiyitongzhis = service.queryReuseHuiyitongzhis();
	}

	public String list() throws JTException {
		//huiyitongzhis = service.queryReuseHuiyitongzhis();
		request.setAttribute("objectList", huiyitongzhis);
		return SUCCESS;
	}

	public String delete() throws JTException {
		Huiyitongzhi h= service.findHuiyitongzhi(huiyitongzhiId);
		h.setReuseFlag(JitongConstants.STRING_FALSE);
		service.updateHuiyitongzhi(h);
		return list();
	}

	public List<Huiyitongzhi> getHuiyitongzhis() {
		return huiyitongzhis;
	}

	public void setHuiyitongzhis(List<Huiyitongzhi> huiyitongzhis) {
		this.huiyitongzhis = huiyitongzhis;
	}

	public String getCategoryId() {
		return "meeting.huiyitongzhi";
	}

	public String getHuiyitongzhiId() {
		return huiyitongzhiId;
	}

	public void setHuiyitongzhiId(String huiyitongzhiId) {
		this.huiyitongzhiId = huiyitongzhiId;
	}
	
}
