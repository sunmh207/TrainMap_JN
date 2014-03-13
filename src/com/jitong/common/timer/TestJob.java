package com.jitong.common.timer;

import org.apache.log4j.Logger;

import com.jitong.common.exception.JTException;

/**
 * 责任人车 提醒
 * 
 * @author stanley.sun
 * 
 */
public class TestJob extends BaseJob {
	private static Logger logger = Logger.getLogger(TestJob.class);

	public void executeDo() throws JTException {
		test();
	}

	/**
	 * Test
	 * 
	 * @throws JTException
	 */
	private void test() throws JTException {
		//System.exit(0);
		logger.info("Test Job is triggered!!");
	}

	public static void main(String[] args) throws JTException {
		TestJob t = new TestJob();
		t.test();

	}
}
