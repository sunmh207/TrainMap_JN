package com.stanley.youhuoyubao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.opensymphony.xwork2.Preparable;
import com.stanley.locomotivedict.LocomotiveService;

public class YouhuoyubaoAction extends JITActionBase implements Preparable {
	private String qryLocalOnly;
	private String qryCh;
	//private String defaultInfoTypeCode;
	private String defaultInfoType;
	private String qryInfoType;
	private String qryReportTime_start;
	private String qryReportTime_end;
	private Map<String,String> needShowMap;
	private List<String> locoList;
	private List<String> infoTypeList;
	private static LocomotiveService svr;
	private Youhuoyubao youhuoyubao;
	

	public void prepare() throws JTException {
		if (svr == null) {
			svr = new LocomotiveService();
		}
		if (youhuoyubao != null && youhuoyubao.getId() != null) {
			youhuoyubao = (Youhuoyubao) svr.findBoById(Youhuoyubao.class, youhuoyubao.getId());
		}
		needShowMap = new HashMap<String,String>();
		needShowMap.put("1","是");
		needShowMap.put("0","否");
		infoTypeList = new ArrayList<String>();
		infoTypeList.add("");
		infoTypeList.add("120提交");
		infoTypeList.add("整备地勤");
		infoTypeList.add("乘务员反馈");
		infoTypeList.add("技术科");
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		try{
		locoList = svr.queryLocalLocoList();
		}catch(Exception e){
			this.logger.error(e.getClass());
		}
		return INPUT;

	}

	public String delete() throws JTException {
		svr.deleteBo(Youhuoyubao.class,youhuoyubao.getId());
		return list();
	}

	public String save() throws Exception {
		try {
			// new
			if (youhuoyubao.getId() == null || "".equals(youhuoyubao.getId())) {
				youhuoyubao.setSysTime(DateUtil.getCurrentTime());
				//youhuoyubao.setInfoType(convertCode2InfoType((String)session.get("defaultInfoTypeCode")));
				youhuoyubao.setInfoType(((String)session.get("defaultInfoType")));
				svr.createBo(youhuoyubao);
				// modify
			} else {
				youhuoyubao.setSysTime(DateUtil.getCurrentTime());
				svr.updateBo(youhuoyubao);
			}
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql =null;
		//only query the youhuoyubao with the locomotive on Map
		if("1".equals(qryLocalOnly)){
			hql = "select me from Youhuoyubao me, TaiWei tw where me.ch=tw.currentCH ";
		}else{
			 hql = " from Youhuoyubao me where 1=1 ";
		}
			if (!StringUtil.isEmpty(qryCh) && !"null".equals(qryCh)) {
				hql += " and me.ch like '%" + qryCh + "%'";
			}
			if (!StringUtil.isEmpty(qryInfoType) && !"null".equals(qryInfoType)) {
				hql += " and me.infoType = '" + qryInfoType + "'";
			}
			if (!StringUtil.isEmpty(qryReportTime_start) && !"null".equals(qryReportTime_start)) {
				hql += " and me.reportTime >= '" + qryReportTime_start + "'";
			}
			if (!StringUtil.isEmpty(qryReportTime_end) && !"null".equals(qryReportTime_end)) {
				hql += " and me.reportTime <= '" + qryReportTime_end + "'";
			}
		hql +=" order by me.expireTime desc ";
		return hql;
	}

	public String getQryCh() {
		return qryCh;
	}

	public void setQryCh(String qryCh) {
		this.qryCh = qryCh;
	}

	public Map<String, String> getNeedShowMap() {
		return needShowMap;
	}

	public void setNeedShowMap(Map<String, String> needShowMap) {
		this.needShowMap = needShowMap;
	}

	public Youhuoyubao getYouhuoyubao() {
		return youhuoyubao;
	}

	public void setYouhuoyubao(Youhuoyubao youhuoyubao) {
		this.youhuoyubao = youhuoyubao;
	}

	public List<String> getLocoList() {
		return locoList;
	}

	public void setLocoList(List<String> locoList) {
		this.locoList = locoList;
	}


	/*public String getDefaultInfoTypeCode() {
		return defaultInfoTypeCode;
	}

	public void setDefaultInfoTypeCode(String defaultInfoTypeCode) {
		this.defaultInfoTypeCode = defaultInfoTypeCode;
		if(defaultInfoTypeCode!=null&&!"".equals(defaultInfoTypeCode)){
			session.put("defaultInfoTypeCode", defaultInfoTypeCode);
		}
	}*/

	/*private String convertCode2InfoType(String infoTypeCode){
		String infoType="";
		if("120".equals(infoTypeCode)){
			infoType="120提交";
		}else if("dq".equals(infoTypeCode)){
			infoType="整备地勤";
		}else if("feedback".equals(infoTypeCode)){
			infoType="乘务员反馈";
		}else if("engineeringoffice".equals(infoTypeCode)){
			infoType="技术科";
		}
		return infoType;
	}*/

	public String getQryInfoType() {
		return qryInfoType;
	}

	public String getDefaultInfoType() {
		return defaultInfoType;
	}

	public void setDefaultInfoType(String defaultInfoType) {
		this.defaultInfoType = defaultInfoType;
		if(defaultInfoType!=null&&!"".equals(defaultInfoType)){
			session.put("defaultInfoType", defaultInfoType);
		}
	}

	public void setQryInfoType(String qryInfoType) {
		this.qryInfoType = qryInfoType;
	}

	public String getQryReportTime_start() {
		return qryReportTime_start;
	}

	public void setQryReportTime_start(String qryReportTime_start) {
		this.qryReportTime_start = qryReportTime_start;
	}

	public String getQryReportTime_end() {
		return qryReportTime_end;
	}

	public void setQryReportTime_end(String qryReportTime_end) {
		this.qryReportTime_end = qryReportTime_end;
	}

	public List<String> getInfoTypeList() {
		return infoTypeList;
	}

	public void setInfoTypeList(List<String> infoTypeList) {
		this.infoTypeList = infoTypeList;
	}

	public String getQryLocalOnly() {
		return qryLocalOnly;
	}

	public void setQryLocalOnly(String qryLocalOnly) {
		this.qryLocalOnly = qryLocalOnly;
	}
	
}
