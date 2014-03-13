package com.jitong.common.timer;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

import com.jitong.common.exception.JTException;
import com.jitong.common.util.DBtools;



public abstract class BaseJob extends GenericServlet implements Job {
    private static Logger log = Logger.getLogger(BaseJob.class);

    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            JobDetail jobDetail = new JobDetail(getClass().getName(), getClass().getName() + " Generation", getClass());
            jobDetail.getJobDataMap().put("type", "FULL");
            
            //CronTrigger(String name, String group, String cronExpression) 
            CronTrigger cronTrigger = new CronTrigger(getClass().getName(), getClass().getName() + " Generation");
            String cronExpr = getInitParameter("cronExpr");
            cronTrigger.setCronExpression(cronExpr);
            Scheduler sched = StdSchedulerFactory.getDefaultScheduler();
            sched.scheduleJob(jobDetail, cronTrigger);
        } catch (Exception e) {
            log.debug("init quartz scheduler failed！");
            e.printStackTrace();
        }
    }

 
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        try {
            log.info("The timer "+this.getClass()+" is starting...");
            executeDo();
            DBtools.closeSession();
            log.info("The timer "+this.getClass()+" is end.");
        } catch (JTException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    /**
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            log.info("Timer "+this.getClass()+" is starting...");
            executeDo();
            DBtools.closeSession();
            log.info("Timer "+this.getClass()+" is done.");
        } catch (JTException e) {
        	log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     *子类定时器实现这个方法
     */
    public abstract void executeDo() throws JTException;
}