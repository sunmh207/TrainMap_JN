package com.stanley.fanghuyongpin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jitong.JitongConstants;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.opensymphony.xwork2.Preparable;
import com.stanley.locomotivedict.Locomotive;
import com.stanley.locomotivedict.LocomotiveService;

public class FanghuyongpinAction extends JITActionBase implements Preparable {
	private String qryCh;
	private String qryYpName;
	private String qryChangeTime_start;
	private String qryChangeTime_end;
	private String fanghuyongpinId;
	private List<String> locoList;
	private Map<String,String> ypMap;
	private static LocomotiveService lsvr;
	private static FanghuyongpinService fhypsvr;
	private Fanghuyongpin fanghuyongpin;

	private Map<String,String> isChangedMap;
	public void prepare() throws JTException {
		if (lsvr == null) {
			lsvr = new LocomotiveService();
		}
		if (fhypsvr == null) {
			fhypsvr = new FanghuyongpinService();
		}
		if (fanghuyongpinId != null ) {
			fanghuyongpin = (Fanghuyongpin) lsvr.findBoById(Fanghuyongpin.class, fanghuyongpinId);
		}
		isChangedMap = new HashMap<String,String>();
		isChangedMap.put("0", "否");
		isChangedMap.put("1", "是");
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		ypMap = new HashMap<String,String>();
		try{
			locoList = lsvr.queryLocalLocoList();
			List<Object> l= fhypsvr.queryAll(FanghuyongpinDict.class);
			for(Object o:l){
				FanghuyongpinDict dict = (FanghuyongpinDict)o;
				String key = dict.getYpName();
				String value = dict.getYpName()+",期限："+dict.getDuration()+"天,适用机型:"+dict.getLocomodel();
				ypMap.put(key, value);
			}
		}catch(Exception e){
			this.logger.error(e.getClass());
		}
		return INPUT;
	}

	public String edit() {
		input();
		return "edit";
	}
	
	public String delete() throws JTException {
		lsvr.deleteBo(Fanghuyongpin.class, fanghuyongpinId);
		return list();
	}

	public String save() throws Exception {
		try {
			// new
			if (fanghuyongpin.getId() == null || "".equals(fanghuyongpin.getId())) {
				String chs = fanghuyongpin.getCh();
				if(chs!=null){
					String[] chsArry = chs.split(",");
					for(String ch0: chsArry){
						String ch = StringUtil.trim(ch0);
						Fanghuyongpin f= new Fanghuyongpin();
						//if don't provide change time, calculate it.
						if(fanghuyongpin.getChangeTime()==null||"".equals(fanghuyongpin.getChangeTime())){
							Locomotive loco = lsvr.getLocomotiveByCh(ch);
							if(loco!=null){
								String locomodel=loco.getLocomodel();
								FanghuyongpinDict fhypDict = fhypsvr.getFanghuyongpinDictByYpnameLocomodel(fanghuyongpin.getYpName(), locomodel);
								if(fhypDict!=null){
									String duration = fhypDict.getDuration();
									int durationNumber = Integer.parseInt(duration);
									String issueDate= fanghuyongpin.getIssueDate();
									
									String expiredDate = DateUtil.datePlus(issueDate, JitongConstants.JT_DATE_FORMAT, Calendar.DAY_OF_MONTH, durationNumber);
									f.setChangeTime(expiredDate);
									f.setNote(StringUtil.trim(fhypDict.getNote()));//dict.note -> fhyp.note
								}//end if(fhypDict!=null)
							}
						}else{
							f.setChangeTime(fanghuyongpin.getChangeTime());
						}
						
						f.setCh(ch);
						f.setChangePerson(fanghuyongpin.getChangePerson());
						f.setIsChanged(fanghuyongpin.getIsChanged());
						f.setIssueDate(fanghuyongpin.getIssueDate());//检验日期
						f.setNote(fanghuyongpin.getNote()+f.getNote());
						f.setQuantity(fanghuyongpin.getQuantity());
						f.setSetupTime(fanghuyongpin.getSetupTime());
						f.setYpName(fanghuyongpin.getYpName());
						lsvr.createBo(f);
					}
				}
				//svr.createBo(fanghuyongpin);
				// modify
			} else {
				lsvr.updateBo(fanghuyongpin);
			}
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = " from Fanghuyongpin me where 1=1 ";

		if (!StringUtil.isEmpty(qryCh) && !"null".equals(qryCh)) {
			hql += " and me.ch like '%" + qryCh + "%'";
		}
		if (!StringUtil.isEmpty(qryYpName) && !"null".equals(qryYpName)) {
			hql += " and me.ypName like '%" + qryYpName + "%'";
		}
		if (!StringUtil.isEmpty(qryChangeTime_start) && !"null".equals(qryChangeTime_start)) {
			hql += " and me.changeTime>=  '" + qryChangeTime_start + "'";
		}
		if (!StringUtil.isEmpty(qryChangeTime_end) && !"null".equals(qryChangeTime_end)) {
			hql += " and me.changeTime<=  '" + qryChangeTime_end + "'";
		}
		hql +=" order by me.changeTime desc ";
		return hql;
	}

	public String getQryCh() {
		return qryCh;
	}

	public void setQryCh(String qryCh) {
		this.qryCh = qryCh;
	}


	public String getQryYpName() {
		return qryYpName;
	}

	public void setQryYpName(String qryYpName) {
		this.qryYpName = qryYpName;
	}

	public Fanghuyongpin getFanghuyongpin() {
		return fanghuyongpin;
	}

	public void setFanghuyongpin(Fanghuyongpin fanghuyongpin) {
		this.fanghuyongpin = fanghuyongpin;
	}

	public String getFanghuyongpinId() {
		return fanghuyongpinId;
	}

	public void setFanghuyongpinId(String fanghuyongpinId) {
		this.fanghuyongpinId = fanghuyongpinId;
	}

	public List<String> getLocoList() {
		return locoList;
	}

	public void setLocoList(List<String> locoList) {
		this.locoList = locoList;
	}

	public String getQryChangeTime_start() {
		return qryChangeTime_start;
	}

	public void setQryChangeTime_start(String qryChangeTime_start) {
		this.qryChangeTime_start = qryChangeTime_start;
	}

	public String getQryChangeTime_end() {
		return qryChangeTime_end;
	}

	public void setQryChangeTime_end(String qryChangeTime_end) {
		this.qryChangeTime_end = qryChangeTime_end;
	}

	public Map<String, String> getIsChangedMap() {
		return isChangedMap;
	}

	public void setIsChangedMap(Map<String, String> isChangedMap) {
		this.isChangedMap = isChangedMap;
	}

	public Map<String, String> getYpMap() {
		return ypMap;
	}

	public void setYpMap(Map<String, String> ypMap) {
		this.ypMap = ypMap;
	}

}
