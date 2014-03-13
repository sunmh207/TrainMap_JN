package com.stanley.qry;

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

public class JCQueryAction extends JITActionBase implements Preparable {
	private String qrych;
	
	public void prepare() throws JTException {
	}

	public String list() throws JTException {
		return super.list();
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "from GudaoHistory me ";
		if(!StringUtil.isEmpty(qrych)){
			hql+=" where me.ch like '%"+qrych+"%'";
		}
		return hql;
	}

	public String getQrych() {
		return qrych;
	}

	public void setQrych(String qrych) {
		this.qrych = qrych;
	}


}
