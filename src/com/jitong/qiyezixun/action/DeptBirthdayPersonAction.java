package com.jitong.qiyezixun.action;

import java.util.ArrayList;
import java.util.List;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.StringUtil;
import com.jitong.console.domain.User;
import com.jitong.qiyezixun.domain.DeptBirthday;
import com.jitong.qiyezixun.service.DeptBirthdayService;
import com.opensymphony.xwork2.Preparable;

public class DeptBirthdayPersonAction extends JITActionBase implements Preparable {
	private DeptBirthday deptBirthday;
	
	private static String businessType="deptbirthday";
	private DeptBirthdayService service;
	private String personId;
	
	private List<User> persons;
	private String deptBirthdayPersonId;
	public void prepare() throws JTException {
		if (service == null) {
			service = new DeptBirthdayService();
		}

		if (deptBirthday!=null && deptBirthday.getId()!=null) {
			deptBirthday = (DeptBirthday)service.findBoById(DeptBirthday.class, deptBirthday.getId());
		}
	}

	public String input() throws JTException {
		return listPersons();
	}
	
	public String listPersons() throws JTException {
		if (deptBirthday != null && deptBirthday.getId() != null) {
			persons = service.queryPersonsByDeptBirthdayId(deptBirthday.getId());
		}
		return INPUT;
	}
	
	public String deletePerson() throws JTException {
		service.deletePerson(personId, deptBirthday.getId());
		return listPersons();
	}
	
	public String deletePersons() throws JTException {
		String[] userIds = request.getParameterValues("chk");
		if(userIds.length>0){
			for(String uid: userIds){
				service.deletePerson(uid, deptBirthday.getId());
			}
		}
		return listPersons();
	}

	public String addPersons() throws JTException {
		String idString = request.getParameter("useridStr");
		String[] ids = StringUtil.parseString2Array(idString, ",");
		if (ids != null && ids.length > 0) {
			service.addPersons(ids, deptBirthday.getId());
		}
		return listPersons();
	}


	public static String getBusinessType() {
		return businessType;
	}

	public static void setBusinessType(String businessType) {
		DeptBirthdayPersonAction.businessType = businessType;
	}
	
	
	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "from DeptBirthday me";
		return hql;
	}


	public DeptBirthday getDeptBirthday() {
		return deptBirthday;
	}


	public void setDeptBirthday(DeptBirthday deptBirthday) {
		this.deptBirthday = deptBirthday;
	}


	public String getPersonId() {
		return personId;
	}


	public void setPersonId(String personId) {
		this.personId = personId;
	}


	public List<User> getPersons() {
		return persons;
	}


	public void setPersons(List<User> persons) {
		this.persons = persons;
	}


	public String getDeptBirthdayPersonId() {
		return deptBirthdayPersonId;
	}


	public void setDeptBirthdayPersonId(String deptBirthdayPersonId) {
		this.deptBirthdayPersonId = deptBirthdayPersonId;
	}
	public String getCategoryId() {
		return "qiyezixun.deptbirthday";
	}
	
}
