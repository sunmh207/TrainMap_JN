package com.jitong.console.action;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.console.domain.Switch;

public class SwitchAction extends JITActionBase {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		return "from Switch ";
	}

	public String open() throws JTException {
		if (!StringUtil.isEmpty(id)) {
			BaseService service = new BaseService();
			Switch o = (Switch) service.findBoById(Switch.class, id);
			o.setFlag("1");
			o.setStartdt(DateUtil.getCurrentTime());
			service.updateBo(o);
		}
		return list();
	}

	public String close() throws JTException {
		if (!StringUtil.isEmpty(id)) {
			BaseService service = new BaseService();
			Switch o = (Switch) service.findBoById(Switch.class, id);
			o.setFlag("0");
			o.setClosedt(DateUtil.getCurrentTime());
			service.updateBo(o);
		}
		return list();
	}
}
