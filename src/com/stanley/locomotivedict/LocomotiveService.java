package com.stanley.locomotivedict;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.console.dao.UserDAO;

public class LocomotiveService extends BaseService {
	UserDAO dao;

	public LocomotiveService(Session s) {
		super(s);
		dao = new UserDAO(s);
	}

	public LocomotiveService() throws JTException {
		super();
		dao = new UserDAO(s);
	}

	
/**
 * Query from LOCOMOTIVE_DIST where ismanager=1
 * @return List<String:Loconumber> 
 * @throws JTException
 */
	public List<String> queryLocalLocoList(String didian) throws JTException {
		List<String> locoList = new ArrayList<String>();
		try{
			List<Object> list = queryByHql("from Locomotive l where l.didian='"+didian+"' order by l.loconumber ");
			if(list!=null){
				for(Object o:list){
					Locomotive l=(Locomotive) o;
					locoList.add(l.getLoconumber());
				}
			}
		}catch(Exception e){
			throw new JTException("查询本段机车字典失败", e, this.getClass());
		}
		return locoList;
	}
	public List<Locomotive> queryLocomotiveList(String didian) throws JTException {
		List<Locomotive> locoList = new ArrayList<Locomotive>();
		try{
			List<Object> list = queryByHql("from Locomotive l where l.didian='"+didian+"' order by l.loconumber ");
			if(list!=null){
				for(Object o:list){
					Locomotive l=(Locomotive) o;
					locoList.add(l);
				}
			}
		}catch(Exception e){
			throw new JTException("查询本段机车字典失败", e, this.getClass());
		}
		return locoList;
	}
	public List<String> queryLocalLocoList() throws JTException {
		List<String> locoList = new ArrayList<String>();
		try{
			List<Object> list = queryByHql("from Locomotive l order by l.loconumber ");
			if(list!=null){
				for(Object o:list){
					Locomotive l=(Locomotive) o;
					locoList.add(l.getLoconumber());
				}
			}
		}catch(Exception e){
			throw new JTException("查询本段机车字典失败", e, this.getClass());
		}
		return locoList;
	}
	public List<Locomotive> queryLocomotiveList() throws JTException {
		List<Locomotive> locoList = new ArrayList<Locomotive>();
		try{
			List<Object> list = queryByHql("from Locomotive l order by l.loconumber ");
			if(list!=null){
				for(Object o:list){
					Locomotive l=(Locomotive) o;
					locoList.add(l);
				}
			}
		}catch(Exception e){
			throw new JTException("查询本段机车字典失败", e, this.getClass());
		}
		return locoList;
	}
	/*public List<String> queryLocalLocomodels() throws JTException {
		List<String> locomodelList = new ArrayList<String>();
		try{
			List<Object> list = queryByHql("select distinct l.locomodel from Locomotive l where l.ismanager='1' ");
			if(list!=null&&list.size()>0){
				for(Object o:list){
					String locomodel=(String) o;
					locomodelList.add(locomodel);
				}
			}
		}catch(Exception e){
			throw new JTException("查询本段机车字典失败", e, this.getClass());
		}
		return locomodelList;
	}*/
	
	public Locomotive getLocomotiveByCh(String ch) throws JTException {
		List<Object> list = queryByHql("from Locomotive l where l.loconumber='"+ch+"' ");
		if(list!=null&&list.size()>0){
			return (Locomotive)list.get(0);
		}
		return null;
	}
	
}
