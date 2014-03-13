package com.stanley.console.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.opensymphony.xwork2.Preparable;
import com.stanley.locomotivedict.Locomotive;
import com.stanley.locomotivedict.LocomotiveService;

public class PopLocomotiveQueryAction extends JITActionBase implements Preparable {
	private static LocomotiveService svr;
	//private List<User> users;
	private User loginUser;
	
	private Map<String,String> cxMap ;
	private String qryCX;
	private String qryCH;

	


	public void prepare() throws JTException {
		if (svr == null) {
			svr = new LocomotiveService();
		}
		cxMap = new TreeMap<String,String>();
		try{
			List<Locomotive> locoList=svr.queryLocomotiveList();
			for(Locomotive loco:locoList){
				cxMap.put(loco.getLocomodel(), loco.getLocomodel());
			}
		
		}catch(JTException e){
			e.printStackTrace();
			this.addActionError("错误:"+e.getMessage());
		}
		
	}

	public String list() throws JTException {
		/*String pageSizeStr = request.getParameter("pageSize");
		if(pageSizeStr==null||pageSizeStr.trim().length()==0){
			setPageSize(500);
		}
		this.setBusinessClass("com.jitong.console.domain.User");*/
		return super.list();
	}

	public String select() throws JTException {
		String[] ids = request.getParameterValues("chk");
		if (ids != null && ids.length > 0) {
			String idStr = "";
			String nameStr = "";
			for (int i = 0; i < ids.length; i++) {
				if (i > 0) {
					idStr += ",";
					nameStr += ",";
				}
				idStr += ids[i];
				nameStr += SysCache.interpertUserName(ids[i]);
			}
			request.setAttribute("idStr", idStr);
			request.setAttribute("nameStr", nameStr);
		}
		return SUCCESS;
	}


	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "";
		
		hql ="from AvailableLocomotiveV loco where 1=1 ";
		
		if (!StringUtil.isEmpty(qryCX) && !"null".equals(qryCX)) {
			hql += " and loco.locomodel like '%" + qryCX + "%'";
		}
		if (!StringUtil.isEmpty(qryCH) && !"null".equals(qryCH)) {
			hql += " and loco.loconumber like '%" + qryCH + "%'";
		}		
		
		hql += " order by loco.loconumber ";
		return hql;
	}

	public String getCategoryId() {
		return "console.PopTrainQuery";
	}

	public Map<String, String> getCxMap() {
		return cxMap;
	}

	public void setCxMap(Map<String, String> cxMap) {
		this.cxMap = cxMap;
	}

	public String getQryCX() {
		return qryCX;
	}

	public void setQryCX(String qryCX) {
		this.qryCX = qryCX;
	}

	public String getQryCH() {
		return qryCH;
	}

	public void setQryCH(String qryCH) {
		this.qryCH = qryCH;
	}

}
