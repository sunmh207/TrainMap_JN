package com.jitong.anquanjiaoyu.action;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import com.jitong.anquanjiancha.domain.Ziliaoku;
import com.jitong.anquanjiaoyu.domain.Tixingxuexi;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.sms.domain.MMSAttachment;
import com.jitong.sms.domain.ScheduleMMS;
import com.jitong.sms.service.SMSService;
import com.jitong.wnp.action.TaskAction;
import com.jitong.wnp.domain.Task;

public class StudyReminderAction extends TaskAction {
	private List<ScheduleMMS> scheduleMMSList;
	private String scheduleMMSID;
	private String ziliaokuId;
	
	public List<ScheduleMMS> getScheduleMMSList() {
		return scheduleMMSList;
	}
	public void setScheduleMMSList(List<ScheduleMMS> scheduleMMSList) {
		this.scheduleMMSList = scheduleMMSList;
	}
	public String getScheduleMMSID() {
		return scheduleMMSID;
	}
	public void setScheduleMMSID(String scheduleMMSID) {
		this.scheduleMMSID = scheduleMMSID;
	}
	public String getZiliaokuId() {
		return ziliaokuId;
	}
	public void setZiliaokuId(String ziliaokuId) {
		this.ziliaokuId = ziliaokuId;
	}
	public String getCategoryId() {
		return "anquanjiaoyu.StudyReminder";
	}
	public String getBusinessType(){
		return "anquanjiaoyu.StudyReminder";
	}
	
	public String listUsers() throws JTException {
		if (task != null && !StringUtil.isEmpty(task.getTaskId())) {
			scheduleMMSList = service.queryScheduleMMSByBusinessInfo(this.getBusinessType(), task.getTaskId());
			request.setAttribute("scheduleMMSList", scheduleMMSList);
		}
		return INPUT;
	}
	public String setZiliaoku() throws JTException {
		SMSService service = new SMSService();
		BaseDAO dao = new BaseDAO(service.getS());
		if (task != null && !StringUtil.isEmpty(task.getTaskId())) {
			task = (Task) service.findBoById(Task.class, task.getTaskId());
		} else {
			//tixingxuexi = new Tixingxuexi();
			//tixingxuexi.setSendTime(DateUtil.getCurrentDate());
			if(ziliaokuId!=null&&ziliaokuId.trim().length()>0){
				Ziliaoku ziliaoku = (Ziliaoku) dao.findBoById(Ziliaoku.class, ziliaokuId);
				task = new Task();
				task.setContent(ziliaoku.getContent());
				//task.setTitle(ziliaoku.getTitle());
			}
		}
		return listUsers();
	}
	public String deleteUser() throws Exception {
		if (scheduleMMSID != null) {
			service.deleteScheduleMMS(scheduleMMSID);
		}
		return listUsers();
	}

	public String deleteUsers() throws Exception {
		String[] mmsids = request.getParameterValues("chk");
		if (mmsids.length > 0) {
			for (String mmsid : mmsids) {
				service.deleteScheduleMMS(mmsid);
			}
		}
		return listUsers();
	}

	public String addUsers() throws JTException {
		String idString = request.getParameter("useridStr");
		String[] ids = StringUtil.parseString2Array(idString, ",");
		for (String id : ids) {
			if (service.existScheduleMMS(this.getBusinessType(), task.getTaskId(), id)) {
				continue;
			}

			for (int i = 0; i < ids.length; i++) {
				User u = SysCache.interpertUser(ids[i]);
				ScheduleMMS shedMMS = new ScheduleMMS();
				shedMMS.setPhoneNumber(u.getPhoneNumber());
				shedMMS.setRequestTime(DateUtil.getCurrentTime());
				shedMMS.setTitle("业务提醒学习");
				shedMMS.setBusinessType(this.getBusinessType());
				shedMMS.setBusinessId(task.getTaskId());
				shedMMS.setRecipientId(u.getId());
				shedMMS.setCronExp(task.getCronExp());
				shedMMS.setStartTime(task.getStartTime());
				shedMMS.setEndTime(task.getEndTime());
				shedMMS.setSenderInfo(this.getLoginUserInfo());
				shedMMS.setOperatorId(this.getLoginUser().getId());
				shedMMS.setRealMMSID(task.getTaskId());
				service.saveOrUpdateScheduleMMS(shedMMS);
				service.startReminder(shedMMS);
			}
		}
		return listUsers();
	}

	public String delete() throws Exception {
		if (task != null && !StringUtil.isEmpty(task.getTaskId())) {
			service.deleteBo(Task.class, task.getTaskId());
			service.deleteScheduleMMS(this.getBusinessType(), task.getTaskId());
		}
		return list();
	}

	public String save() throws Exception {
		User loginUser = this.getLoginUser();
		task.setCreatorId(loginUser.getId());
		task.setUnitName(loginUser.getUnit());
		task.setBusinessType(this.getBusinessType());
		task.setStartTime(this.getStartDate() + " " + this.getStartHour() + ":" + this.getStartMinite() + ":00");
		task.setEndTime(this.getEndDate() + " " + this.getEndHour() + ":" + this.getEndMinite() + ":00");
		if(!task.isValidTime()){
			addActionError("开始或结束时间不对，请检查！");
			return listUsers();
		}
		
		
		task.setOperatorId(this.getLoginUser().getId());
		task.setIsRepeat("on".equals(this.getIsRepeat()) ? "1" : "0");
		if (task != null && !StringUtil.isEmpty(task.getTaskId())) {
			service.updateBo(task);
		} else {
			service.createBo(task);
		}
		String idString = request.getParameter("useridStr");
		String[] ids = StringUtil.parseString2Array(idString, ",");
		if(StringUtil.isEmpty(idString)){
			addActionError("请选择人员");
			//return INPUT;
			return listUsers();
		}

		MMSAttachment att = new MMSAttachment();
		if(!StringUtil.isEmpty(task.getContent())){
			att.setContent(Hibernate.createBlob(task.getContent().getBytes()));
			att.setContentType("txt");
			att.setMmsId(task.getTaskId());
			service.saveOrUpdateBo(att);
		}else{
				addActionError("请填写日程内容");
				//return INPUT;
				return listUsers();
		}
		
		for (int i = 0; i < ids.length; i++) {
			User u = SysCache.interpertUser(ids[i]);
			ScheduleMMS shedMMS=null;
			if (service.existScheduleMMS(this.getBusinessType(), task.getTaskId(), ids[i])) {
				shedMMS = service.findScheduleMMSByBusinessInfo(this.getBusinessType(), task.getTaskId(), ids[i]);				
			}else{			
				shedMMS = new ScheduleMMS();
			}
			shedMMS.setPhoneNumber(u.getPhoneNumber());
			shedMMS.setRequestTime(DateUtil.getCurrentTime());
			shedMMS.setTitle("业务提醒学习");
			shedMMS.setBusinessType(this.getBusinessType());
			shedMMS.setBusinessId(task.getTaskId());
			shedMMS.setRecipientId(u.getId());
			shedMMS.setCronExp(task.getCronExp());
			shedMMS.setStartTime(task.getStartTime());
			shedMMS.setEndTime(task.getEndTime());
			shedMMS.setSenderInfo(this.getLoginUserInfo());
			shedMMS.setOperatorId(this.getLoginUser().getId());
			shedMMS.setRealMMSID(task.getTaskId());//use task id as realMMSID, make sure all mms can use one ATT.
			service.saveOrUpdateScheduleMMS(shedMMS);
			
			try{
			service.startReminder(shedMMS);		
			}catch(JTException e){
				addActionError(e.getMessage());
				return listUsers();
			}
		}
		return list();
	}
}
