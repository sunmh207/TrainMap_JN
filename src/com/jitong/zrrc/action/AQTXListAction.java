package com.jitong.zrrc.action;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;

public class AQTXListAction extends JITActionBase {

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "from Relationship me order by me.createTime desc";
		return hql;
	}

	public String getCategoryId() {
		return "zrrc.aqtxlist";
	}


}
