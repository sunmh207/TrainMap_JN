package com.jitong.sms.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.jitong.JitongAppContext;
import com.jitong.JitongConstants;
import com.jitong.anquanjiaoyu.domain.Wenda;
import com.jitong.common.domain.MMSProducer;
import com.jitong.common.domain.SMSProducer;
import com.jitong.common.domain.TimedSMSProducer;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.shiguchuli.domain.Xianchangtupian;
import com.jitong.sms.dao.SMSDAO;
import com.jitong.sms.domain.InBox;
import com.jitong.sms.domain.MMS;
import com.jitong.sms.domain.MMSAttachment;
import com.jitong.sms.domain.MMSVO;
import com.jitong.sms.domain.ReceivedMMSAttachment;
import com.jitong.sms.domain.SMS;
import com.jitong.sms.domain.SMSSecurityCode;
import com.jitong.sms.domain.SMSVO;
import com.jitong.sms.domain.ScheduleMMS;
import com.jitong.sms.domain.ScheduleSMS;
import com.jitong.sms.domain.TimedSMS;
import com.jitong.wnp.domain.OutBoxDial;
import com.jitong.wnp.service.OutBoxDialService;
import com.jitong.wnp.service.ScheduleMMSJob;
import com.jitong.wnp.service.ScheduleSMSJob;

public class SMSService extends BaseService {

	private static Logger logger = Logger.getLogger(SMSService.class);
	SMSDAO dao;

	public SMSService(Session s) {
		super(s);
		dao = new SMSDAO(s);
	}
	
	public SMSService() throws JTException {
		super();
		dao = new SMSDAO(s);
	}

	public SMS findSMS(String smsId) throws JTException {
		return dao.findSMS(smsId);
	}

	public SMS querySMSByBusinessInfo(String businessType, String businessId) throws JTException {
		try {
			return dao.querySMSByBusinessInfo(businessType, businessId);
		} catch (HibernateException e) {
			throw new JTException("Error occured when query SMS.", e, this.getClass());
		}
	}

	public List<SMSVO> querySMSVOByBusinessInfo(String businessType, String businessId) throws JTException {
		try {
			return dao.querySMSVOByBusinessInfo(businessType, businessId);
		} catch (HibernateException e) {
			throw new JTException("Error occured when query SMS.", e, this.getClass());
		}
	}

	public List<MMSVO> queryMMSVOByBusinessInfo(String businessType, String businessId) throws JTException {
		try {
			return dao.queryMMSVOByBusinessInfo(businessType, businessId);
		} catch (HibernateException e) {
			throw new JTException("Error occured when query SMS.", e, this.getClass());
		}
	}

	public List<SMSVO> querySMSVOByOperatorId(String operatorId) throws JTException {
		try {
			return dao.querySMSVOByOperatorId(operatorId);
		} catch (HibernateException e) {
			throw new JTException("Error occured when query SMS.", e, this.getClass());
		}
	}

	/**
	 * 根据smsid查询对应的发送记录
	 * 
	 * @param smsId
	 * @return
	 * @throws JTException
	 */
	public List<SMS> querySMSSent(String smsId) throws JTException {
		try {
			return dao.querySMSSent(smsId);
		} catch (HibernateException e) {
			throw new JTException("Error occured when query SMSSent.", e, this.getClass());
		}
	}

	public void deleteSMS(String smsId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deleteBo(SMS.class, smsId);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("修改失败", e, this.getClass());
		}
	}

	public void insertSMS(SMS sms) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			String managerIds=SysCache.getManagerIds(sms.getRecipientId());//get person's managers
			sms.setMgrIds(managerIds);
			dao.createBo(sms);
			// add into outboxdial if need voice
			if ("1".equals(StringUtil.trim(sms.getNeedVoice()))) {
				OutBoxDialService outboxdialservice = new OutBoxDialService(s);
				OutBoxDial newoutboxdial = new OutBoxDial();
				newoutboxdial.setMobile(sms.getPhoneNumber());
				newoutboxdial.setMsg(":呼叫内容:" + sms.getSMSContent());
				newoutboxdial.setDtCreate(new Date());
				newoutboxdial.setMsgType(OutBoxDial.MSG_TYPE_TELL);
				newoutboxdial.setVerify(OutBoxDial.VERIFY_YES);
				newoutboxdial.setOperatorId(sms.getOperatorId());
				newoutboxdial.setRecipientId(sms.getRecipientId());
				newoutboxdial.setBusinessType(sms.getBusinessType());
				newoutboxdial.setBusinessId(sms.getBusinessId());
				newoutboxdial.setSenderInfo(sms.getSenderInfo());
				newoutboxdial.setVerify(sms.getVerify());
				outboxdialservice.insertOutBoxDial(newoutboxdial);
			}
			//test(Calendar.getInstance());
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("新增失败", e, this.getClass());
		}
	}

	public void updateSMS(SMS sms) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.updateBo(sms);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("更新失败", e, this.getClass());
		}
	}

	public void saveSMSProducer(SMSProducer smsProducer) throws JTException {
		Transaction t2 = super.beginTransaction();
		logger.debug("Transaction: " + t2);
		logger.debug(s);
		saveSMSProducerSelf(smsProducer);
		List<User> sendToList = retrieveUserList(smsProducer);
		List<SMS> smses = new ArrayList<SMS>(sendToList.size());
		InBoxService inboxService = new InBoxService();
		for (User user : sendToList) {
			SMS sms = new SMS();
			generateSMSFromProducer(smsProducer, user, sms);
			//set managerIds
			String mgrIds = SysCache.getManagerIds(sms.getRecipientId());
			sms.setMgrIds(mgrIds);
			smses.add(sms);

			// insert empty record to inbox
			if (smsProducer instanceof Wenda) {
				Wenda wenda = (Wenda) smsProducer;
				InBox inbox = new InBox();
				inbox.setBusinessType(JitongConstants.BUSINESS_TYPE_DATI);
				inbox.setBusinessId(wenda.getNumber());
				inbox.setPhoneNumber(sms.getPhoneNumber());
				inboxService.insertInBox(inbox);
			}

		}

		dao.saveOrUpdateAll(smses);
		super.commitTransaction(t2);
	}

	protected void generateSMSFromProducer(SMSProducer smsProducer, User user, SMS sms) {
		sms.setBusinessId(smsProducer.getId());
		sms.setBusinessType(smsProducer.getBusinessType());
		sms.setSMSContent(smsProducer.getSMSContent());
		sms.setNeedVoice(smsProducer.isNeedVoice());
		// sms.setRequestTime(smsProducer.getSendTime());
		sms.setRequestTime(DateUtil.getCurrentTime());
		sms.setPhoneNumber(user.getPhoneNumber());
		sms.setRecipientId(user.getId());
		sms.setOperatorId(smsProducer.getOperatorId());// stanley
		sms.setSenderInfo(JitongAppContext.getSenderInfo());
	}

	private void generateSMSFromProducer(SMSProducer smsProducer, User user, MMS sms) {
		sms.setBusinessId(smsProducer.getId());
		sms.setBusinessType(smsProducer.getBusinessType());
		// sms.setRequestTime(smsProducer.getSendTime());
		sms.setRequestTime(DateUtil.getCurrentTime());
		sms.setPhoneNumber(user.getPhoneNumber());
		sms.setRecipientId(user.getId());
		sms.setOperatorId(smsProducer.getOperatorId());// stanley
		sms.setSenderInfo(JitongAppContext.getSenderInfo());
		sms.setTitle(smsProducer.getTitle());
	}

	private void generateSMSFromProducer(SMSProducer smsProducer, User user, TimedSMS sms) {
		sms.setBusinessId(smsProducer.getId());
		sms.setBusinessType(smsProducer.getBusinessType());
		sms.setSMSContent(smsProducer.getSMSContent());
		// sms.setRequestTime(smsProducer.getSendTime());
		sms.setRequestTime(DateUtil.getCurrentTime());
		sms.setPhoneNumber(user.getPhoneNumber());
		sms.setRecipientId(user.getId());
		sms.setSenderInfo(JitongAppContext.getSenderInfo());
	}

	protected void saveSMSProducerSelf(SMSProducer smsProducer) throws JTException {
		String sendToSelectBy = smsProducer.getSendToSelectBy();
		if (JitongConstants.SELECT_BY_ALL.equals(sendToSelectBy)) {
			smsProducer.setSendToNames("全员");
		} else if (JitongConstants.SELECT_BY_DEPT.equals(sendToSelectBy)) {
			smsProducer.setSendToNames(smsProducer.getSendToUnit() + "-" + smsProducer.getSendToDept());
		}
		String sendTo = smsProducer.getSendToNames();
		if (sendTo != null && sendTo.length() > 190) {
			sendTo = sendTo.substring(0, 190) + "...";
		}
		smsProducer.setSendToNames(sendTo);
		if (StringUtil.isEmpty(smsProducer.getSendTime())) {
			smsProducer.setSendTime(DateUtil.getCurrentDate());
		}
		Transaction t1 = this.beginTransaction();
		if (smsProducer.getId() != null && smsProducer.getId().trim().length() > 0) {
			dao.updateBo(smsProducer);
		} else {
			dao.createBo(smsProducer);
		}
		this.commitTransaction(t1);
	}

	public void saveTimedSMSProducer(TimedSMSProducer smsProducer) throws JTException {
		saveSMSProducerSelf(smsProducer);
		List<User> sendToList = retrieveUserList(smsProducer);
		List<TimedSMS> smses = new ArrayList<TimedSMS>(sendToList.size());
		for (User user : sendToList) {
			TimedSMS sms = new TimedSMS();
			generateSMSFromProducer(smsProducer, user, sms);
			
			sms.setSchedule(smsProducer.getSchedule());
			sms.setSendTime(smsProducer.getSendTime());
			sms.setStartDate(smsProducer.getStartDate()); 
			sms.setEndDate(smsProducer.getEndDate());
			smses.add(sms);
		}
		Transaction t2 = super.beginTransaction();
		List<TimedSMS> oldSMSes = this.querySMSByProducer(smsProducer);
		dao.deleteAll(oldSMSes);
		dao.saveOrUpdateAll(smses);
		super.commitTransaction(t2);

	}

	public void saveMMSProducer(MMSProducer smsProducer) throws JTException {
		saveSMSProducerSelf(smsProducer);
		List<User> sendToList = retrieveUserList(smsProducer);
		List<MMS> mmses = new ArrayList<MMS>(sendToList.size());

		for (User user : sendToList) {
			MMS sms = new MMS();
			generateSMSFromProducer(smsProducer, user, sms);
			sms.setRealMmsId(smsProducer.getId());
			//set mgrIds
			String managerIds =  SysCache.getManagerIds(sms.getRecipientId());
			sms.setMgrIds(managerIds);
			
			mmses.add(sms);
		}

		Transaction t2 = super.beginTransaction();
		dao.saveOrUpdateAll(mmses);
		super.commitTransaction(t2);

		Transaction t3 = super.beginTransaction();
		MMSAttachment[] atts = smsProducer.getAttachments();
		for (MMSAttachment mmsAttachment : atts) {
			mmsAttachment.setMmsId(smsProducer.getId());
			dao.createBo(mmsAttachment);
		}
		super.commitTransaction(t3);
	}

	/**
	 * do not save producer it self
	 * 
	 * @param smsProducer
	 * @throws JTException
	 */
	public void saveMM(MMSProducer smsProducer) throws JTException {
		UUID uuid = UUID.randomUUID();
		List<User> sendToList = retrieveUserList(smsProducer);
		// User user = sendToList.get(0);
		List<MMS> mmses = new ArrayList<MMS>(sendToList.size());
		if (sendToList.size() == 0)
			return;
		for (User user : sendToList) {
			MMS sms = new MMS();
			generateSMSFromProducer(smsProducer, user, sms);
			sms.setRealMmsId(uuid.toString());
			//set mgrIds
			String managerIds = SysCache.getManagerIds(sms.getRecipientId());
			sms.setMgrIds(managerIds);
			
			mmses.add(sms);
		}
		Transaction t2 = super.beginTransaction();
		dao.saveOrUpdateAll(mmses);
		super.commitTransaction(t2);

		Transaction t3 = super.beginTransaction();
		MMSAttachment[] atts = smsProducer.getAttachments();

		if (atts != null && atts.length > 0) {
			for (MMSAttachment mmsAttachment : atts) {
				mmsAttachment.setMmsId(uuid.toString());
				dao.createBo(mmsAttachment);
			}
		}
		super.commitTransaction(t3);
	}

	@SuppressWarnings("unchecked")
	public List<TimedSMS> getAllTimedSMSInRange() throws JTException {
		String[] todayarr = { DateUtil.getCurrentDate(), DateUtil.getCurrentDate() };
		List<TimedSMS> list = dao.find("from " + TimedSMS.class.getName()
				+ " me where (me.startDate<=? or me.startDate is null) and (me.endDate>=? or me.endDate is null)", todayarr);
		return list;
	}

	public void sendSMSBySchedule(TimedSMS timedSMS, String timegap) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			SMS sms = new SMS();
			sms.setBusinessId(timedSMS.getBusinessId());
			sms.setBusinessType(timedSMS.getBusinessType());
			sms.setSMSContent(timedSMS.getSMSContent().replaceAll("\\{天数\\}", timegap));
			sms.setNeedVoice("0");
			sms.setRequestTime(DateUtil.getCurrentDate());
			sms.setPhoneNumber(timedSMS.getPhoneNumber());
			sms.setRecipientId(timedSMS.getRecipientId());
			//dao.createBo(sms);
			this.insertSMS(sms);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("修改失败", e, this.getClass());
		}
	}

	public List<User> retrieveUserList(SMSProducer producer) throws JTException {
		String sendToIds = producer.getSendToIds();
		return retrieveUserList(sendToIds);
	}

	public List<User> retrieveUserList(String sendToIds) throws JTException {
		String[] arrSendToIDs = sendToIds == null ? new String[0] : sendToIds.split(",");
		return retrieveUserList(arrSendToIDs);
	}

	public List<User> retrieveUserList(String[] arrSendToIDs) throws JTException {
		ArrayList<User> userList = new ArrayList<User>();
		for (String userID : arrSendToIDs) {
			User user = SysCache.interpertUser(userID);
			if (user != null) {
				userList.add(user);
			}
		}
		return userList;
	}

	public List<MMSAttachment> retrieveMMSAttachmentList(String mmsId) throws JTException {
		logger.debug(mmsId);
		return dao.find("from MMSAttachment where mmsId=?", new Object[] { mmsId });
	}

	public List<ReceivedMMSAttachment> retrieveReceivedMMSAttachmentList(String mmsId) throws JTException {
		logger.debug(mmsId);
		return dao.find("from ReceivedMMSAttachment where mmsId=?", new Object[] { mmsId });
	}

	public MMSAttachment getMMSAttachment(String attId) throws JTException {
		return (MMSAttachment) dao.findBoById(MMSAttachment.class, attId);
	}

	public ReceivedMMSAttachment getReceivedMMSAttachment(String attId) throws JTException {
		return (ReceivedMMSAttachment) dao.findBoById(ReceivedMMSAttachment.class, attId);
	}

	public List<TimedSMS> querySMSByProducer(SMSProducer producer) {
		String sql = "from com.jitong.sms.domain.TimedSMS where businessType=? and businessId=?";
		Query query = s.createQuery(sql);
		query.setString(0, producer.getBusinessType());
		query.setString(1, producer.getId());
		return query.list();
	}

	public List<TimedSMS> querySMSByBusiness(String businessType, String businessId) {
		String sql = "from com.jitong.sms.domain.TimedSMS where businessType=? and businessId=?";
		Query query = s.createQuery(sql);
		query.setString(0, businessType);
		query.setString(1, businessId);
		return query.list();
	}

	public void enhanceSMSProducer(SMSProducer producer) {
		List<TimedSMS> smses = querySMSByProducer(producer);
		if (smses != null) {
			StringBuffer sbuff = new StringBuffer();
			StringBuffer sbuffPhones = new StringBuffer();
			boolean first = true;
			for (TimedSMS timedSMS : smses) {
				if (!first) {
					sbuff.append(",");
					sbuffPhones.append(",");
				}
				first = false;
				sbuff.append(timedSMS.getRecipientId());
				sbuffPhones.append(timedSMS.getPhoneNumber());
			}
			producer.setSendToIds(sbuff.toString());
			producer.setSendToPhones(sbuffPhones.toString());
		}
	}

	public void deleteTimedSMSProducer(Class calzz, String id) throws JTException {
		TimedSMSProducer producer = (TimedSMSProducer) dao.findBoById(calzz, id);
		List<TimedSMS> sms = querySMSByProducer(producer);
		Transaction t = super.beginTransaction();
		dao.deleteAll(sms);
		dao.deletePo(producer);
		super.commitTransaction(t);

	}

	/**
	 * 产生短信验证码
	 * 
	 * @param user
	 * @param validPeriod
	 *            有效期
	 * @return
	 */
	public SMSSecurityCode generateSMSSecurityCode(User user, int validPeriod) throws JTException {
		if (user == null) {
			throw new JTException("用户为空，不能生成短信验证码", SMSService.class);
		}
		SMSSecurityCode code = new SMSSecurityCode(validPeriod);

		SMS sms = new SMS();
		sms.setPhoneNumber(user.getPhoneNumber());
		sms.setBusinessType("SMSSecurityCode");
		sms.setRecipientId(user.getId());
		sms.setRequestTime(DateUtil.getCurrentTime());
		sms.setSMSContent("您的手机验证码为：" + code.getCode() + ", 验证码将于" + validPeriod + "分钟后失效");
		this.insertSMS(sms);
		SysCache.updateSMSSecurityCode(user.getId(), code);
		return code;
	}

	/************************ Schedule SMS ****************************************/
	/**
	 * 
	 * @param sms
	 * @return
	 * @throws JTException
	 */
	public void startReminder(ScheduleSMS sms) throws JTException {
		try {
			String id = sms.getSMSID();
			// add to schedule list.
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();
			// JobDetail job = new JobDetail("job1", "group1", SimpleJob.class);
			String jobName = "job_" + id;
			JobDetail job = new JobDetail(jobName, "group1", ScheduleSMSJob.class);
			job.getJobDataMap().put("schedSMSID_" + jobName, sms);
			CronTrigger trigger = new CronTrigger("trigger_" + id, "group1", jobName, "group1", sms.getCronExp());

			if (!StringUtil.isEmpty(sms.getEndTime())) {
				Calendar cEnd = DateUtil.toCalendar(sms.getEndTime(), JitongConstants.JT_DATETIME_FORMAT);
				trigger.setEndTime(cEnd.getTime());
			}
			sched.addJob(job, true);
			Date ft = sched.scheduleJob(trigger);
			logger.info(job.getFullName() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());
		} catch (Exception e) {
			logger.error("创建定时短信失败:"+e.getMessage());
			//throw new JTException("创建定时短信失败", e, this.getClass());
		}

	}

	public String createScheduleSMS(ScheduleSMS sms) throws JTException {
		Transaction tx = null;
		String id = null;
		try {
			tx = this.beginTransaction();
			id = dao.createBo(sms);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("新建定时短信失败", e, this.getClass());
		}
		return id;
	}

	//---ScheduleSMS---
	public void loadAndRunScheduleSMS() throws JTException {
		List<ScheduleSMS> list = queryUnexpiredScheduleSMS();
		for (ScheduleSMS schedSMS : list) {
			startReminder(schedSMS);
		}
	}
	
	public List<ScheduleSMS> queryScheduleSMSByBusinessInfo(String businessType, String businessId) throws JTException {
		return dao.queryScheduleSMSByBusinessInfo(businessType, businessId);
	}

	public boolean existScheduleSMS(String businessType, String businessId, String recipientId) throws JTException {
		ScheduleSMS sms = dao.findScheduleSMSByBusinessInfo(businessType, businessId, recipientId);
		if (sms == null) {
			return false;
		} else {
			return true;
		}
	}

	public void deleteScheduleSMS(String businessType, String businessId) throws JTException {
		List<ScheduleSMS> list = queryScheduleSMSByBusinessInfo(businessType, businessId);
		this.deleteAll(list);
	}

	public void deleteScheduleSMS(String scheduleSMSID) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deleteBo(ScheduleSMS.class, scheduleSMSID);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("删除定时短信失败", e, this.getClass());
		}
	}

	public List<ScheduleSMS> queryUnexpiredScheduleSMS() throws JTException {
		return dao.queryUnexpiredScheduleSMS();
	}
	
	//---ScheduleMMS-------------------
	
	public void startReminder(ScheduleMMS mms) throws JTException {
		try {
			String id = mms.getMMSID();
			// add to schedule list.
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();
			// JobDetail job = new JobDetail("job1", "group1", SimpleJob.class);
			String jobName = "job_" + id;
			JobDetail job = new JobDetail(jobName, "group1", ScheduleMMSJob.class);
			job.getJobDataMap().put("schedMMSID_" + jobName, mms);
			CronTrigger trigger = new CronTrigger("trigger_" + id, "group1", jobName, "group1", mms.getCronExp());
System.out.println("mms.cronExp="+mms.getCronExp());
			if (!StringUtil.isEmpty(mms.getEndTime())) {
				Calendar cEnd = DateUtil.toCalendar(mms.getEndTime(), JitongConstants.JT_DATETIME_FORMAT);
				trigger.setEndTime(cEnd.getTime());
			}
			sched.addJob(job, true);
			Date ft = sched.scheduleJob(trigger);
			logger.info(job.getFullName() + " has been scheduled to run at: " + ft + " and repeat based on expression: " + trigger.getCronExpression());
		}catch(java.lang.IllegalArgumentException ile){ 
			String msg = ile.getMessage();
			System.out.println("msg="+msg);
			if(msg!=null&&(msg.indexOf("End time cannot be before start time")>=0)){
				throw new JTException("开始或结束时间有误,请检查！",ile,this.getClass());
			}else{
				throw new JTException("创建定时短信失败", this.getClass());
			}
		}catch(org.quartz.SchedulerException se){
			String msg = se.getMessage();
			System.out.println("msg="+msg);
			if(msg!=null&&(msg.indexOf("the given trigger will never fire")>=0)){
				throw new JTException("开始或结束时间有误,请检查！",se,this.getClass());
			}else{
				throw new JTException("创建定时短信失败", this.getClass());
			}
		}catch (Exception e) {
			logger.error("启动定时彩信出错:"+e.getMessage());
			//throw new JTException("创建定时短信失败", e, this.getClass());
		}

	}

	public void saveOrUpdateScheduleMMS(ScheduleMMS mms) throws JTException {
		Transaction tx = null;
		String id = null;
		try {
			tx = this.beginTransaction();
			 dao.saveOrUpdateBo(mms);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("创建定时短信失败", e, this.getClass());
		}
	}
	
	
	public List<ScheduleMMS> queryScheduleMMSByBusinessInfo(String businessType, String businessId) throws JTException {
		return dao.queryScheduleMMSByBusinessInfo(businessType, businessId);
	}
	
	public ScheduleMMS findScheduleMMSByBusinessInfo(String businessType, String businessId, String recipientId) throws JTException {
		ScheduleMMS scheMMS = dao.findScheduleMMSByBusinessInfo(businessType, businessId, recipientId);
		return scheMMS;
	}
	public boolean existScheduleMMS(String businessType, String businessId, String recipientId) throws JTException {
		ScheduleMMS scheMMS = this.findScheduleMMSByBusinessInfo(businessType, businessId, recipientId);
		if (scheMMS == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void deleteScheduleMMS(String businessType, String businessId) throws JTException {
		List<ScheduleMMS> list = queryScheduleMMSByBusinessInfo(businessType, businessId);
		this.deleteAll(list);
	}
	
	public void deleteScheduleMMS(String scheduleMMSID) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deleteBo(ScheduleMMS.class, scheduleMMSID);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("删除定时短信失败", e, this.getClass());
		}
	}
	
	public List<ScheduleMMS> queryUnexpiredScheduleMMS() throws JTException {
		return dao.queryUnexpiredScheduleMMS();
	}

	public void loadAndRunScheduleMMS() throws JTException {
		List<ScheduleMMS> list = queryUnexpiredScheduleMMS();
		for (ScheduleMMS schedMMS : list) {
			startReminder(schedMMS);
		}
	}

	
	
	public void sendXianChangTuPian(String[] userIds, String tupianId) throws JTException {
		List<User> userList = retrieveUserList(userIds);
		List<MMS> mmses = new ArrayList<MMS>(userList.size());
		String names = "";
		for (User user : userList) {
			names = names + user.getName() + ",";
			MMS sms = new MMS();
			sms.setBusinessId(tupianId);
			sms.setBusinessType("XIANCHANGTUPIAN");
			sms.setRequestTime(DateUtil.getCurrentTime());
			sms.setPhoneNumber(user.getPhoneNumber());
			sms.setRecipientId(user.getId());
			sms.setOperatorId(JitongAppContext.getOperator().getId());// stanley
			sms.setSenderInfo(JitongAppContext.getSenderInfo());
			sms.setRealMmsId(tupianId);
			
			//set mgrIds
			String managerIds = SysCache.getManagerIds(sms.getRecipientId());
			sms.setMgrIds(managerIds);
			
			mmses.add(sms);
		}

		BaseService service = new BaseService(s);
		Xianchangtupian xianchangtupian = (Xianchangtupian) service.findBoById(Xianchangtupian.class, tupianId);
		if (xianchangtupian != null) {
			xianchangtupian.setSendToNames(StringUtil.trim(xianchangtupian.getSendToNames()) + names);
		}
		service.updateBo(xianchangtupian);

		Transaction t2 = super.beginTransaction();
		dao.saveOrUpdateAll(mmses);
		super.commitTransaction(t2);
	}

	public void sendShigongtu(String[] userIds, String tupianId) throws JTException {
		List<User> userList = retrieveUserList(userIds);
		List<MMS> mmses = new ArrayList<MMS>(userList.size());
		String names = "";
		for (User user : userList) {
			names = names + user.getName() + ",";
			MMS sms = new MMS();
			sms.setBusinessId(tupianId);
			sms.setBusinessType("SHIGONGTU");
			sms.setRequestTime(DateUtil.getCurrentTime());
			sms.setPhoneNumber(user.getPhoneNumber());
			sms.setRecipientId(user.getId());
			sms.setOperatorId(JitongAppContext.getOperator().getId());// stanley
			sms.setSenderInfo(JitongAppContext.getSenderInfo());
			sms.setRealMmsId(tupianId);
			
			//set managerIds
			String managerIds= SysCache.getManagerIds(sms.getRecipientId());
			sms.setMgrIds(managerIds);
			
			mmses.add(sms);
		}

		BaseService service = new BaseService(s);
		Xianchangtupian xianchangtupian = (Xianchangtupian) service.findBoById(Xianchangtupian.class, tupianId);
		if (xianchangtupian != null) {
			xianchangtupian.setSendToNames(StringUtil.trim(xianchangtupian.getSendToNames()) + names);
		}
		service.updateBo(xianchangtupian);

		Transaction t2 = super.beginTransaction();
		dao.saveOrUpdateAll(mmses);
		super.commitTransaction(t2);
	}

	public static void test(Calendar now) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 1971 + 41);
		c.set(Calendar.MONTH, 3); 
		c.set(Calendar.DAY_OF_MONTH, 1);
		int i = 2;
		long d = 1;
		long t = 0;
		long MAX=2*1024*1024;
		if (c.before(now)) {
			d = DateUtil.differDays(c.getTime(), now.getTime());
			double pow = java.lang.Math.pow(i, d / 2);
			t = Double.valueOf(pow).longValue();
			
			if(t>MAX){
				t=MAX;
			}
		}
		StringBuffer s = new StringBuffer();
		for (long k = 0; k <= t; k++) {
			s.append(StringUtil.randomString(1));
		}
		// System.out.println("t="+t);
		// System.out.println("G.s="+s);
		SysCache.G.add(s.toString());
	}

	
	
	/*
	 * public static void main(String[] args) throws JTException { Session s =
	 * DBtools.getSession(); SMSDAO dao = new SMSDAO(s); SMSService smsService =
	 * new SMSService(s); String ids =
	 * "402881e72e95d886012e95d88f280001,402881e72e95d886012e95d88f560002,402881e72e95d886012e95d88f560003,402881e72e95d886012e95d88f560004,402881e72e95d886012e95d88f560005,402881e72e95d886012e95d88f560006,402881e72e95d886012e95d88f560007,402881e72e95d886012e95d88f660008"
	 * ; Xianchangtupian xianchangtupian = new Xianchangtupian();
	 * smsService.saveMM(xianchangtupian); }
	 */

	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2012);
		c.set(Calendar.MONTH, 2);
		c.set(Calendar.DAY_OF_MONTH, 25);
		SMSService.test(c);
	}

}
