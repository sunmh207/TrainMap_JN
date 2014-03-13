package com.jitong.equipment.service;

import org.hibernate.Session;

import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;

public class RepairPlanService extends BaseService{
	public RepairPlanService(Session s) {
		super(s);
	}

	public RepairPlanService() throws JTException {
		super();
	}
	
}
