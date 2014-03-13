package com.jitong.qiyezixun.action;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.StringUtil;
import com.jitong.qiyezixun.domain.Poll;
import com.opensymphony.xwork2.Preparable;

public class PollDetailAction extends JITActionBase implements Preparable {
	private static BaseService service;
	private Poll poll;
	private String qryName = "";
	private String businessType;
	private String businessId;

	public void prepare() throws JTException {
		if (service == null) {
			service = new BaseService();
		}
		if (poll != null && poll.getPollId() != null) {
			poll = (Poll)service.findBoById(Poll.class, poll.getPollId());
		}
	}

	public String list() throws JTException {
		return super.list();
	}

	public String getListHQL(ArrayList<Object> params) {
		if (StringUtil.isEmpty(qryName)) {
			return "from InBoxVO s where  s.businessType='" + businessType + "' and s.businessId='" + businessId + "' order by s.GH desc";
		} else {
			return "from InBoxVO s where s.senderName like '%" + qryName + "%' and s.businessType='" + businessType + "' and s.businessId='" + businessId
					+ "' order by s.GH desc";
		}
	}

	public String getQryName() {
		return qryName;
	}

	public void setQryName(String qryName) {
		this.qryName = qryName;
	}

	public String getCategoryId() {
		return "qiyezixun.poll";
	}

	public String getBusinessClass() {
		return businessClass;
	}

	public void setBusinessClass(String businessClass) {
		this.businessClass = businessClass;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

}
