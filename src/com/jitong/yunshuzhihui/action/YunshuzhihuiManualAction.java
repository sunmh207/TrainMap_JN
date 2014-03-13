package com.jitong.yunshuzhihui.action;

import com.jitong.anquanjiancha.domain.Kaohetongzhi;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.DateUtil;
import com.jitong.console.domain.User;
import com.jitong.sms.service.SMSService;
import com.jitong.yunshuzhihui.domain.YunshuzhihuiManual;

public class YunshuzhihuiManualAction extends JITActionBase{
	private YunshuzhihuiManual yunshuzhihuiManual;

	public YunshuzhihuiManual getYunshuzhihuiManual() {
		return yunshuzhihuiManual;
	}

	public void setYunshuzhihuiManual(YunshuzhihuiManual yunshuzhihuiManual) {
		this.yunshuzhihuiManual = yunshuzhihuiManual;
	}
	public String save(){
		yunshuzhihuiManual = new YunshuzhihuiManual();
		yunshuzhihuiManual.setSendTime(DateUtil.getCurrentTime("yyyy-MM-dd"));
		User loginUser = getLoginUser();
		yunshuzhihuiManual.setSendByID(loginUser.getId());
		yunshuzhihuiManual.setSendByName(loginUser.getName());
		yunshuzhihuiManual.setDepartment(loginUser.getUnitDept());
		return SUCCESS;
	}
	public String doSave() throws JTException{
		if(yunshuzhihuiManual!=null){
			SMSService service = new SMSService();
			service.saveSMSProducer(yunshuzhihuiManual);
		}
		return "thankYou";
	}
	
}
