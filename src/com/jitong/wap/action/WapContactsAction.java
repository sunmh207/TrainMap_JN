package com.jitong.wap.action;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.StringUtil;
import com.opensymphony.xwork2.Preparable;

public class WapContactsAction extends JITActionBase {

	private String qryName = "";
	private String qryNumber = "";
	private String qryDept = "";

	public String getQryName() {
		return qryName;
	}

	public void setQryName(String qryName) {
		this.qryName = qryName;
	}

	public String getQryNumber() {
		return qryNumber;
	}

	public void setQryNumber(String qryNumber) {
		this.qryNumber = qryNumber;
	}

	public String getQryDept() {
		return qryDept;
	}

	public void setQryDept(String qryDept) {
		this.qryDept = qryDept;
	}

	public String execute() throws JTException {
		return super.list();
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		if(StringUtil.isEmpty(qryName)&&StringUtil.isEmpty(qryNumber)&&StringUtil.isEmpty(qryDept)){
			return " from User person where 1=2";// show nothing
		}
		String sql=" from User person where 1=1 ";
		if (!StringUtil.isEmpty(qryName)) {
			sql+= " and person.name like '%" + qryName + "%'";
		}
		if (!StringUtil.isEmpty(qryNumber)) {
			sql+= " and person.phoneNumber like '%" + qryNumber + "%'";
		}
		if (!StringUtil.isEmpty(qryDept)) {
			sql+= " and person.unitDept like '%" + qryDept + "%'";
		}
		return sql;
	}

}
