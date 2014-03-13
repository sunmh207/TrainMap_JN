package com.jitong.wnp.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.jitong.JitongConstants;
import com.jitong.anquanjiaoyu.domain.Shoujibao;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.sms.domain.ScheduleSMS;
import com.jitong.sms.service.SMSService;
import com.jitong.wnp.domain.Task;
import com.jitong.wnp.form.TaskSearchForm;
import com.opensymphony.xwork2.Preparable;

public class TaskAction extends JITActionBase implements Preparable {
	protected SMSService service;
	protected Task task;
	private Map<String, String> unitlist;
	private List<String> hourlist;
	private List<String> minitelist;
	private Map<String, String> repeatslist;

	private List<ScheduleSMS> scheduleSMSList;
	private String scheduleSMSID;
	private TaskSearchForm searchForm;
	
	private String startDate;
	private String startHour;
	private String startMinite;
	private String endDate;
	private String endHour;
	private String endMinite;

	private String isRepeat;

	public void prepare() throws JTException {
		if (service == null) {
			service = new SMSService();
		}
		if (task != null && !StringUtil.isEmpty(task.getTaskId())) {
			task = (Task) service.findBoById(Task.class, task.getTaskId());
			try {
				startDate = "" + DateUtil.convertDateFormat(task.getStartTime(), JitongConstants.JT_DATETIME_FORMAT, JitongConstants.JT_DATE_FORMAT);
				int istartHour = DateUtil.getCalendarFieldValue(task.getStartTime(), JitongConstants.JT_DATETIME_FORMAT, Calendar.HOUR_OF_DAY);
				startHour = istartHour >= 10 ? ("" + istartHour) : ("0" + istartHour);
				int istartMinite = DateUtil.getCalendarFieldValue(task.getStartTime(), JitongConstants.JT_DATETIME_FORMAT, Calendar.MINUTE);
				startMinite = istartMinite >= 10 ? ("" + istartMinite) : ("0" + istartMinite);

				endDate = "" + DateUtil.convertDateFormat(task.getEndTime(), JitongConstants.JT_DATETIME_FORMAT, JitongConstants.JT_DATE_FORMAT);
				int iendHour = DateUtil.getCalendarFieldValue(task.getEndTime(), JitongConstants.JT_DATETIME_FORMAT, Calendar.HOUR_OF_DAY);
				endHour = iendHour >= 10 ? ("" + iendHour) : ("0" + iendHour);
				int iendMinite = DateUtil.getCalendarFieldValue(task.getEndTime(), JitongConstants.JT_DATETIME_FORMAT, Calendar.MINUTE);
				endMinite = iendMinite >= 10 ? ("" + iendMinite) : ("0" + iendMinite);
			} catch (ParseException e) {
				addActionError("日期格式不正确");
			}
		} else {
			try{			
			String time = DateUtil.datePlus(DateUtil.getCurrentTime(), JitongConstants.JT_DATETIME_FORMAT, Calendar.HOUR_OF_DAY, 1);
			Calendar c = DateUtil.toCalendar(time,JitongConstants.JT_DATETIME_FORMAT);
			startDate = DateUtil.getCurrentDate();
			startHour = "" + c.get(Calendar.HOUR_OF_DAY);
			startMinite = "" + c.get(Calendar.MINUTE);

			endDate = DateUtil.getCurrentDate();
			endDate = DateUtil.datePlus(endDate, JitongConstants.JT_DATE_FORMAT, Calendar.DAY_OF_MONTH, 1);
			endHour = startHour;
			endMinite = startMinite;
			} catch (ParseException e) {
					addActionError("日期格式不正确");
			}
		}
		unitlist = new LinkedHashMap<String, String>();
		unitlist.put("" + Calendar.MINUTE, "分钟");
		unitlist.put("" + Calendar.HOUR_OF_DAY, "小时");
		unitlist.put("" + Calendar.DAY_OF_MONTH, "天");

		repeatslist = new LinkedHashMap<String, String>();
		repeatslist.put(Task.REPEATS_DAILY, "每天");
		repeatslist.put(Task.REPEATS_WEEKDAY, "每个工作日(周一 至  周五)");
		repeatslist.put(Task.REPEATS_WEEKLY, "每周");
		repeatslist.put(Task.REPEATS_MONTHLY, "每月");
		repeatslist.put(Task.REPEATS_YEARLY, "每年");

		hourlist = new ArrayList<String>();
		for (int i = 0; i < 24; i++) {
			if (i < 10) {
				hourlist.add("0" + i);
			} else {
				hourlist.add("" + i);
			}

		}
		minitelist = new ArrayList<String>();
		for (int i = 0; i < 60; i = i + 5) {
			if (i < 10) {
				minitelist.add("0" + i);
			} else {
				minitelist.add("" + i);
			}
		}
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() throws JTException {
		return INPUT;
	}

	/**
	 * for update
	 * 
	 * @return
	 * @throws JTException
	 */
	public String listUsers() throws JTException {
		if (task != null && !StringUtil.isEmpty(task.getTaskId())) {
			scheduleSMSList = service.queryScheduleSMSByBusinessInfo(this.getBusinessType(), task.getTaskId());
			request.setAttribute("scheduleSMSList", scheduleSMSList);
		}
		return INPUT;
	}

	public String deleteUser() throws Exception {
		if (scheduleSMSID != null) {
			service.deleteScheduleSMS(scheduleSMSID);
		}
		return listUsers();
	}

	public String deleteUsers() throws Exception {
		String[] smsids = request.getParameterValues("chk");
		if (smsids.length > 0) {
			for (String smsid : smsids) {
				service.deleteScheduleSMS(smsid);
			}
		}
		return listUsers();
	}

	public String addUsers() throws JTException {
		String idString = request.getParameter("useridStr");
		String[] ids = StringUtil.parseString2Array(idString, ",");
		for (String id : ids) {
			if (service.existScheduleSMS(this.getBusinessType(), task.getTaskId(), id)) {
				continue;
			}

			for (int i = 0; i < ids.length; i++) {
				User u = SysCache.interpertUser(ids[i]);
				ScheduleSMS shedSMS = new ScheduleSMS();
				shedSMS.setPhoneNumber(u.getPhoneNumber());
				shedSMS.setRequestTime(DateUtil.getCurrentTime());
				shedSMS.setSMSContent(task.getContent());
				shedSMS.setBusinessType(this.getBusinessType());
				shedSMS.setBusinessId(task.getTaskId());
				shedSMS.setRecipientId(u.getId());
				shedSMS.setCronExp(task.getCronExp());
				shedSMS.setStartTime(task.getStartTime());
				shedSMS.setEndTime(task.getEndTime());
				shedSMS.setSenderInfo(this.getLoginUserInfo());
				shedSMS.setOperatorId(u.getId());
				service.createScheduleSMS(shedSMS);
				service.startReminder(shedSMS);
			}
		}
		return listUsers();
	}

	public String delete() throws Exception {
		if (task != null && !StringUtil.isEmpty(task.getTaskId())) {
			service.deleteBo(Task.class, task.getTaskId());
			service.deleteScheduleSMS(this.getBusinessType(), task.getTaskId());
		}
		return list();
	}

	public String save() throws Exception {
		User loginUser = this.getLoginUser();
		task.setCreatorId(loginUser.getId());
		task.setUnitName(loginUser.getUnit());
		task.setBusinessType(this.getBusinessType());
		task.setStartTime(startDate + " " + startHour + ":" + startMinite + ":00");
		task.setEndTime(endDate + " " + endHour + ":" + endMinite + ":00");
		task.setOperatorId(this.getLoginUser().getId());
		task.setIsRepeat("on".equals(isRepeat) ? "1" : "0");
		if (task != null && !StringUtil.isEmpty(task.getTaskId())) {
			service.updateBo(task);
		} else {
			service.createBo(task);
		}
		String idString = request.getParameter("useridStr");
		String[] ids = StringUtil.parseString2Array(idString, ",");

		for (int i = 0; i < ids.length; i++) {
			if (service.existScheduleSMS(this.getBusinessType(), task.getTaskId(), ids[i])) {
				continue;
			}
			User u = SysCache.interpertUser(ids[i]);
			ScheduleSMS shedSMS = new ScheduleSMS();
			shedSMS.setPhoneNumber(u.getPhoneNumber());
			shedSMS.setRequestTime(DateUtil.getCurrentTime());
			shedSMS.setSMSContent(task.getContent());
			shedSMS.setBusinessType(this.getBusinessType());
			shedSMS.setBusinessId(task.getTaskId());
			shedSMS.setRecipientId(u.getId());
			shedSMS.setCronExp(task.getCronExp());
			shedSMS.setStartTime(task.getStartTime());
			shedSMS.setEndTime(task.getEndTime());
			shedSMS.setSenderInfo(this.getLoginUserInfo());
			shedSMS.setOperatorId(u.getId());
			service.createScheduleSMS(shedSMS);
			service.startReminder(shedSMS);
		}
		return list();
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		if(searchForm==null){
			searchForm = new TaskSearchForm();
		}
		searchForm.setUnitName(this.getLoginUser().getUnit());
		searchForm.setBusinessType(this.getBusinessType());
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from Task me " + hqlsufix + " order by me.startTime desc";
		return hql;
	}

	public String getCategoryId() {
		return "wnp.task";
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Map<String, String> getUnitlist() {
		return unitlist;
	}

	public void setUnitlist(Map<String, String> unitlist) {
		this.unitlist = unitlist;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getStartMinite() {
		return startMinite;
	}

	public void setStartMinite(String startMinite) {
		this.startMinite = startMinite;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	public String getEndMinite() {
		return endMinite;
	}

	public void setEndMinite(String endMinite) {
		this.endMinite = endMinite;
	}

	public List<String> getHourlist() {
		return hourlist;
	}

	public void setHourlist(List<String> hourlist) {
		this.hourlist = hourlist;
	}

	public List<String> getMinitelist() {
		return minitelist;
	}

	public void setMinitelist(List<String> minitelist) {
		this.minitelist = minitelist;
	}

	public Map<String, String> getRepeatslist() {
		return repeatslist;
	}

	public void setRepeatslist(Map<String, String> repeatslist) {
		this.repeatslist = repeatslist;
	}

	public String getIsRepeat() {
		return isRepeat;
	}

	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}

	public List<ScheduleSMS> getScheduleSMSList() {
		return scheduleSMSList;
	}

	public void setScheduleSMSList(List<ScheduleSMS> scheduleSMSList) {
		this.scheduleSMSList = scheduleSMSList;
	}

	public String getScheduleSMSID() {
		return scheduleSMSID;
	}

	public void setScheduleSMSID(String scheduleSMSID) {
		this.scheduleSMSID = scheduleSMSID;
	}
	
	public String getBusinessType(){
		return "Task";
	}
}
