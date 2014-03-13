package com.jitong.wap.action;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.StringUtil;
import com.jitong.console.domain.User;
import com.jitong.qiyezixun.domain.NewsPaper;
import com.jitong.qiyezixun.service.NewsPaperService;

public class WapNewsPaperAction extends JITActionBase{
	private String newspaperId;
	private NewsPaper newspaper;
	
	public NewsPaper getNewspaper() {
		return newspaper;
	}

	public void setNewspaper(NewsPaper newspaper) {
		this.newspaper = newspaper;
	}
	private String qryName = "";

	public String getQryName() {
		return qryName;
	}

	public void setQryName(String qryName) {
		this.qryName = qryName;
	}


	public String getNewspaperId() {
		return newspaperId;
	}

	public void setNewspaperId(String newspaperId) {
		this.newspaperId = newspaperId;
	}

	public String execute() throws JTException {
		return super.list();
	}

	public String getListHQL(ArrayList<Object> params) throws JTException{
		if (StringUtil.isEmpty(qryName)) {
			return "from NewsPaper q where q.enable='true' order by q.date desc";// show all (first 20)
		} else {
			return "from NewsPaper q where q.enable='true' and q.name like '%" + qryName + "%' order by q.date desc";
		}
	}
	public String showDetail()throws JTException{
		 NewsPaperService service=new NewsPaperService();
		 newspaper = service.findNewsPaper(newspaperId);
		return "detail";
	}
}
