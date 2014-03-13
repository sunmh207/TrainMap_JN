package com.jitong;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import com.jitong.console.domain.User;


public class JitongConstants {
	
	public static final String USER = "user";
	public static String ADMIN="admin";
	public static String PASSWORD="admin";
	public static int OUTOFDATE=20;
	
	public static Properties properties = null;
	public static void loadProps() {
		properties = new Properties();
		InputStream in = null;
		try {
			in = JitongConstants.class.getResourceAsStream("/config.properties");
			properties.load(in);
			ADMIN = getProp("ADMIN", "admin");
			PASSWORD = getProp("PASSWORD", "admin");
			String outofdate = getProp("OUTOFDATE", "20");
			OUTOFDATE = Integer.parseInt(outofdate);
		} catch (Exception ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String getProp(String name, String defaultValue) {
		if (properties == null) {
			return null;
		}
		String property = null;
		try {
			property = new String(properties.getProperty(name).getBytes("ISO-8859-1"), "gb2312");
		} catch (UnsupportedEncodingException e) {
			property = properties.getProperty(name);
		} catch (Exception e) {
			property = properties.getProperty(name);
		}

		if (property == null) {
			return defaultValue;
		}
		return property.trim();
	}

	public static void init() {
		loadProps();
	}

	
	public static final String SESSION_OBJECT="SESSION_OBJECT";
	public static final int MAX_PAGE_SIZE=20000;
	
	public static final String JT_DATE_FORMAT="yyyy-MM-dd"; 
	public static final String JT_DATETIME_FORMAT="yyyy-MM-dd HH:mm:ss";
	
	
	
	/*The status of SMS*/
	public static final String SMS_STATUS_INIT="0";
	public static final String SMS_STATUS_SUCCESS="1";
	public static final String SMS_STATUS_FAILED="2";
	
	public static final String STRING_TRUE="1";
	public static final String STRING_FALSE="0";

	public static final String ZILIAOLEIXING_QITAZILIAO = "WenJianZhiDuZiLiao";//"文件制度资料";
	public static final String ZILIAOLEIXING_SHIGUZHIDAO = "YingJiJiuYuanZiLiao";//"应急救援资料";
	public static final String ZILIAOLEIXING_ZILIAOGUIZHANG = "TieLuGuiZhangZiLiao";//"铁路规章资料";
	public static final String ZILIAOLEIXING_XINCHEBANFA = "XingCheBanFaZiLiao";//"行车办法资料";
	public static final String ZILIAOLEIXING_GUZHANGCHULI = "GuZhangChuLiZiLiao";//"故障处理资料";
	public static final String ZILIAOLEIXING_ZUOYEGUICHENG = "ZuoYeGuiChengZiLiao";//"作业规程资料";
	public static final String EQUIPMENT_JIANXIUGUZHANGCHULI = "JianXiuGuZhangChuLi";//"检修故障处理"; 
	
	
	/*public static final String BUSINESS_TYPE_TOUPIAO = "TP";
	public static final String BUSINESS_TYPE_DATI = "DT";*/
	public static final String BUSINESS_TYPE_TOUPIAO = "T";
	public static final String BUSINESS_TYPE_DATI = "D";
	/**
	 * super user name
	 */
	public static User ADMIN_USER=null;// load from SysCache
	
	public static final String SELECT_BY_ALL = "all";
	public static final String SELECT_BY_DEPT = "dept";
	public static final String SELECT_BY_PERSON = "person";
	
	//------properties load from config.properties-------------------
	public static String SPID;
	
	private static String HOST;
	public static String SMS_SECURITY_CODE_ENABLED;
	
	/**
	 * If the content (String) is longer thant CONTENT_SHOW_MAX_LENGTH, cut the end.
	 */
	public static int CONTENT_SHOW_MAX_LENGTH=200;
	public static String getHOST() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
