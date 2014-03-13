package com.jitong.qiyezixun.action;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.console.domain.User;
import com.jitong.qiyezixun.domain.DeptBirthday;
import com.jitong.qiyezixun.service.DeptBirthdayService;
import com.opensymphony.xwork2.Preparable;

public class DeptBirthdayAction extends JITActionBase implements Preparable {
	private DeptBirthday deptBirthday;
	
	private static String businessType="deptbirthday";
	private DeptBirthdayService service;
	
	public void prepare() throws JTException {
		if (service == null) {
			service = new DeptBirthdayService();
		}

		if (deptBirthday!=null&& deptBirthday.getId() != null ) {
			deptBirthday = (DeptBirthday)service.findBoById(DeptBirthday.class, deptBirthday.getId());
		}
	}

	public String input() throws JTException {
		return INPUT;
	}
	public String delete() throws Exception{
		if (deptBirthday!=null){
			service.deleteDeptBirthday(deptBirthday.getId());
		}
		return list();
	}
	public String save() throws JTException {
		User user =  this.getLoginUser();
		deptBirthday.setCreatorId(user.getId());
		deptBirthday.setUnitDept(user.getUnitDept());
		if (deptBirthday!=null&& deptBirthday.getId() != null &&deptBirthday.getId().trim().length()>0) {
			service.updateBo(deptBirthday);
		}else{
			service.createBo(deptBirthday);
		}
		return list();
	}

	public static String getBusinessType() {
		return businessType;
	}

	public static void setBusinessType(String businessType) {
		DeptBirthdayAction.businessType = businessType;
	}
	
	
	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "from DeptBirthday me where me.creatorId ='"+this.getLoginUser().getId()+"'";
		return hql;
	}

	public DeptBirthday getDeptBirthday() {
		return deptBirthday;
	}

	public void setDeptBirthday(DeptBirthday deptBirthday) {
		this.deptBirthday = deptBirthday;
	}
	public String getCategoryId() {
		return "qiyezixun.DeptBirthday";
	}

	
}
