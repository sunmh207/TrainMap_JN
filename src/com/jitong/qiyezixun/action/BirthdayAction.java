package com.jitong.qiyezixun.action;

import java.util.ArrayList;
import java.util.List;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.qiyezixun.domain.BirthdaySetting;
import com.opensymphony.xwork2.Preparable;

public class BirthdayAction extends JITActionBase implements Preparable {
	private BirthdaySetting birthdaySetting;
	private static BaseService service;
	private String qryName = "";
	public String businessClass = "User";

	public void prepare() throws JTException {
		if (service == null) {
			service = new BaseService();
		}
		
		List<Object> l = (List<Object>) service.queryAll(BirthdaySetting.class);
		if(l!=null&&l.size()>0){
			birthdaySetting = (BirthdaySetting) l.get(0);
		}else{
			birthdaySetting = new BirthdaySetting();
		}
		
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		return INPUT;
	}

	public String save() throws Exception {
		if (birthdaySetting.getId() == null || "".equals(birthdaySetting.getId())) {
			service.createBo(birthdaySetting);
		} else {
			service.updateBo(birthdaySetting);
		}
		return SUCCESS;
	}

	public String getListHQL(ArrayList<Object> params) {
		return "from SMSVO s where s.recipientName like '%" + qryName + "%' and s.mgrIds like '%"+this.getLoginUser().getId()+"%' and s.businessType='birthday' order by s.requestTime desc";
	}

	public String getQryName() {
		return qryName;
	}

	public void setQryName(String qryName) {
		this.qryName = qryName;
	}

	public String getCategoryId() {
		return "qiyezixun.birthday";
	}

	public BirthdaySetting getBirthdaySetting() {
		return birthdaySetting;
	}

	public void setBirthdaySetting(BirthdaySetting birthdaySetting) {
		this.birthdaySetting = birthdaySetting;
	}

	public String getBusinessClass() {
		return businessClass;
	}

	public void setBusinessClass(String businessClass) {
		this.businessClass = businessClass;
	}

}
