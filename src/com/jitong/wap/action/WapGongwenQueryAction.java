package com.jitong.wap.action;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.StringUtil;

public class WapGongwenQueryAction extends JITActionBase{
	
	private String qryName = "";
	private String title;
	public String getQryName() {
		return qryName;
	}

	public void setQryName(String qryName) {
		this.qryName = qryName;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String execute() throws JTException {
		return super.list();
	}
	
	public String getListHQL(ArrayList<Object> params) throws JTException{
		if (StringUtil.isEmpty(qryName)) {
			return "from GongwenQuery q  order by q.draftDate desc";// show all(first 20)
		} else {
			return "from GongwenQuery q where q.title like '%" + qryName + "%' order by q.draftDate desc";
		}
	}
	public String detail(){
		return "detail";
	}
}
