package com.stanley;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.jitong.JitongConstants;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DateUtil;
import com.jitong.console.dao.RolePersonDAO;
public class TaiWeiManager_QD extends BaseService{
	BaseDAO dao;
	public TaiWeiManager_QD() throws JTException {
		super();
	}
	public TaiWeiManager_QD(Session s) {
		super(s);
		dao = new BaseDAO(s);
	}

	private static Logger logger = Logger.getLogger(TaiWeiManager_QD.class);
	
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
			
			List<Object> list= this.queryByHql("from TaiWei where didian='青岛'");
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
			List<Object> l=this.queryByHql("select max(lastUpdate) as lastUpdate from TaiWei where didian='青岛'");
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
			if(lastUpdate==null) {
				return false;
			}
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
			List<Object> list= this.queryByHql("from TaiWeiAll where didian='青岛'");
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
	//======================Gudao====================
	public Gudao findGudaoByGDName(String gdName){
		try {
			List<Object> list= this.queryByHql("from Gudao where gdName='"+gdName+"' and didian='青岛'");
			if(list!=null&&!list.isEmpty()){
				return (Gudao)list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	//=======================JicheYunxingInfo================
	public JicheYunxingInfo findJicheYunxingInfoByCH(String CH){
		try {
			List<Object> list= this.queryByHql("from JicheYunxingInfo where CH='"+CH+"' ");
			if(list!=null&&!list.isEmpty()){
				return (JicheYunxingInfo)list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	public static void main(String[] args)throws Exception{
		/*BaseService srv = new BaseService();
		List<Object> list= srv.queryAll(TaiWei.class);
		for (Object o : list) {
			TaiWei tw = (TaiWei)o;
			System.out.println(tw);
		}*/
		boolean b =new TaiWeiManager_QD().isNewData();
		System.out.println("==============="+b+"=================");
	}
}
