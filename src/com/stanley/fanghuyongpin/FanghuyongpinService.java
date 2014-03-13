package com.stanley.fanghuyongpin;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.console.dao.UserDAO;

public class FanghuyongpinService extends BaseService {
	UserDAO dao;

	public FanghuyongpinService(Session s) {
		super(s);
		dao = new UserDAO(s);
	}

	public FanghuyongpinService() throws JTException {
		super();
		dao = new UserDAO(s);
	}

	

	public FanghuyongpinDict getFanghuyongpinDictByYpnameLocomodel(String ypName,String locomodel) throws JTException {
		List<Object> list = queryByHql("from FanghuyongpinDict l where l.ypName='"+ypName+"'  and l.locomodel='"+locomodel+"' ");
		if(list!=null&list.size()>0){
			return (FanghuyongpinDict)list.get(0);
		}
		return null;
	}
	
}
