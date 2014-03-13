package com.jitong.common;

import javax.servlet.ServletConfig;

import com.jitong.JitongConstants;
import com.jitong.common.util.DateUtil;

public class CacheServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	public void init(ServletConfig config) {
		loadData();
	}

	private void loadData() {
		System.out.println("Start to load system data to cache ... "+DateUtil.getCurrentTime("yyyy-MM-dd hh:mm:ss"));
		try {
			JitongConstants.init(); 
			//SysCache.loadSysCache();
			 
		} catch (Exception e) {
		}
		System.out.println("Load system data to cache end. "+DateUtil.getCurrentTime("yyyy-MM-dd hh:mm:ss"));
	}
}
