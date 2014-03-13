package com.jitong.console.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.console.domain.Feedback;
import com.opensymphony.xwork2.Preparable;

public class FeedbackAction extends JITActionBase implements Preparable {
	private Feedback feedback;
	private String feedbackid;

	private Map fixedList;
	public String getFeedbackid() {
		return feedbackid;
	}

	public void setFeedbackid(String feedbackid) {
		this.feedbackid = feedbackid;
	}

	public void prepare() throws JTException {
		if (!StringUtil.isEmpty(feedbackid)) {
			BaseService service = new BaseService();
			feedback = (Feedback) service.findBoById(Feedback.class, feedbackid);
		}
		fixedList = new HashMap();
		fixedList.put("0", "未解决");
		fixedList.put("1", "已解决");
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() throws JTException {
		return INPUT;
	}

	public String edit() throws JTException {
		return "edit";
	}

	/**
	 * 提交反馈
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		BaseService service = new BaseService();
		feedback.setTime(DateUtil.getCurrentTime());
		feedback.setRespondentId(this.getLoginUser().getId());
		service.createBo(feedback);
		addActionError("提交成功,感谢您对本系统提供反馈！");
		return INPUT;
	}

	/**
	 * 更新（解决）反馈
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		BaseService service = new BaseService();
		feedback.setOperatorId(this.getLoginUser().getId());
		service.updateBo(feedback);
		return list();
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "from Feedback me order by me.time desc";
		return hql;
	}

	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public Map getFixedList() {
		return fixedList;
	}

	public void setFixedList(Map fixedList) {
		this.fixedList = fixedList;
	}

}
