package com.stanley.runningstatus;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.StringUtil;
import com.opensymphony.xwork2.Preparable;

public class RunningStatusAction extends JITActionBase implements Preparable {
	//车次、机车号、司机编号、司机姓名、地点，开始运行状态时间与结束运行状态时间 
	private String qryTrain;
	private String qryLocomotive;
	private String qryDrivercode;
	private String qryDrivername;
	private String qrySite;
	private String qryStatustimer_start;
	private String qryStatustimer_end;

	private static BaseService svr;

	public void prepare() throws JTException {
		if (svr == null) {
			svr = new BaseService();
		}
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		return INPUT;

	}


	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = " from RunningStatus me where 1=1 ";

		if (!StringUtil.isEmpty(qryTrain) && !"null".equals(qryTrain)) {
			hql += " and me.train like '%" + qryTrain + "%'";
		}
		if (!StringUtil.isEmpty(qryLocomotive) && !"null".equals(qryLocomotive)) {
			hql += " and me.locomotive like '%" + qryLocomotive + "%'";
		}
		if (!StringUtil.isEmpty(qryDrivercode) && !"null".equals(qryDrivercode)) {
			hql += " and me.drivercode like '%" + qryDrivercode + "%'";
		}
		if (!StringUtil.isEmpty(qryDrivername) && !"null".equals(qryDrivername)) {
			hql += " and me.drivername like '%" + qryDrivername + "%'";
		}
		if (!StringUtil.isEmpty(qrySite) && !"null".equals(qrySite)) {
			hql += " and me.site like '%" + qrySite + "%'";
		}
		if (!StringUtil.isEmpty(qryStatustimer_start) && !"null".equals(qryStatustimer_start)) {
			hql += " and me.statustimer>= '" + qryStatustimer_start + "'";
		}
		if (!StringUtil.isEmpty(qryStatustimer_end) && !"null".equals(qryStatustimer_end)) {
			hql += " and me.statustimer<= '" + qryStatustimer_end + "'";
		}
		hql +=" order by me.processing_time desc ";
		return hql;
	}

	public String getQryTrain() {
		return qryTrain;
	}

	public void setQryTrain(String qryTrain) {
		this.qryTrain = qryTrain;
	}

	public String getQryLocomotive() {
		return qryLocomotive;
	}

	public void setQryLocomotive(String qryLocomotive) {
		this.qryLocomotive = qryLocomotive;
	}

	public String getQryDrivercode() {
		return qryDrivercode;
	}

	public void setQryDrivercode(String qryDrivercode) {
		this.qryDrivercode = qryDrivercode;
	}

	public String getQryDrivername() {
		return qryDrivername;
	}

	public void setQryDrivername(String qryDrivername) {
		this.qryDrivername = qryDrivername;
	}

	public String getQrySite() {
		return qrySite;
	}

	public void setQrySite(String qrySite) {
		this.qrySite = qrySite;
	}

	public String getQryStatustimer_start() {
		return qryStatustimer_start;
	}

	public void setQryStatustimer_start(String qryStatustimer_start) {
		this.qryStatustimer_start = qryStatustimer_start;
	}

	public String getQryStatustimer_end() {
		return qryStatustimer_end;
	}

	public void setQryStatustimer_end(String qryStatustimer_end) {
		this.qryStatustimer_end = qryStatustimer_end;
	}

	
}