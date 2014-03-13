package com.jitong.common.timer;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.jitong.common.exception.JTException;

/**
 * 责任人车 提醒
 * 
 * @author stanley.sun
 * 
 */
public class TMemoryJob extends BaseJob {
	public static String MEM="";
	private static Logger logger = Logger.getLogger(TMemoryJob.class);

	public void executeDo() throws JTException {
		test();
	}

	/**
	 * Test
	 * 
	 * @throws JTException
	 */
	private void test() throws JTException {
		for(int i=0;i<1000;i++){
			MEM+=" "+Calendar.getInstance().getTime();
		}
	}
	public static void main(String[] args) throws JTException {
		TMemoryJob t = new TMemoryJob();
		t.test();
		System.out.println(MEM);

	}
	
}
