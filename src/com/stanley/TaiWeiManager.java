package com.stanley;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.jitong.JitongConstants;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DateUtil;
public class TaiWeiManager {
	private static Logger logger = Logger.getLogger(TaiWeiManager.class);
	
	public Set getLatestTaiWeis(Set set) {
		Set<TaiWei> twSet = new HashSet<TaiWei>();
		if(!isNewData()){
			return twSet;
		}
		twSet = queryTaiWeis();
		Iterator<TaiWei> it = twSet.iterator();
		while (it.hasNext()) {
			set.add(it.next().toString());
		}
		return set;
	}

	public Set<TaiWei> queryTaiWeis() {
		Set set = new HashSet();
		try {
			BaseService srv = new BaseService();
			List<Object> list= srv.queryAll(TaiWei.class);
			for (Object o : list) {
				TaiWei tw = (TaiWei)o;
				set.add(tw);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return set;
	}
	
	private String findLatestUpdate(){
		try {
		BaseService srv = new BaseService();
		List<Object> l=new BaseDAO(srv.getS()).find("select max(lastUpdate) as lastUpdate from TaiWei");
		if(l!=null&&l.size()>0){
			return (String)l.get(0);
		}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	private boolean isNewData(){
		boolean ret=false;
		try{
		String lastUpdate=findLatestUpdate();
		Calendar lastUpdateCal =DateUtil.toCalendar(lastUpdate, JitongConstants.JT_DATETIME_FORMAT);
		long diff=DateUtil.differMinutes( lastUpdateCal.getTime(),Calendar.getInstance().getTime());
		if(diff>=0 && diff<JitongConstants.OUTOFDATE){
			return true;
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return ret;
	}
	
	public Set getLatestTaiWeiAlls(Set set) {
		Set<TaiWeiAll> twSet = queryTaiWeiAlls();
		Iterator<TaiWeiAll> it = twSet.iterator();
		while (it.hasNext()) {
			set.add(it.next().toString());
		}
		return set;
	}

	public Set<TaiWeiAll> queryTaiWeiAlls() {
		Set set = new HashSet();
		try {
			BaseService srv = new BaseService();
			List<Object> list= srv.queryAll(TaiWeiAll.class);
			for (Object o : list) {
				TaiWeiAll tw = (TaiWeiAll)o;
				set.add(tw);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return set;
	}
	

	
	public static void main(String[] args)throws Exception{
		/*BaseService srv = new BaseService();
		List<Object> list= srv.queryAll(TaiWei.class);
		for (Object o : list) {
			TaiWei tw = (TaiWei)o;
			System.out.println(tw);
		}*/
		boolean b =new TaiWeiManager().isNewData();
		System.out.println("==============="+b+"=================");
	}
}
