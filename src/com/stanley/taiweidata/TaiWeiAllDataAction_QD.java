package com.stanley.taiweidata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.opensymphony.xwork2.Preparable;
import com.stanley.Gudao;
import com.stanley.JicheYunxingInfo;
import com.stanley.TaiWei;
import com.stanley.TaiWeiAll;
import com.stanley.TaiWeiManager;
import com.stanley.TaiWeiManager_QD;
import com.stanley.locomotivedict.LocomotiveService;

public class TaiWeiAllDataAction_QD extends JITActionBase implements Preparable {
	private TaiWeiManager_QD svr_qd;
	private TaiWeiManager svr;
	private LocomotiveService locoSvr;
	//private TaiWeiAll taiweiall;
	private Gudao gudao;
	private String gdName;
	private String CH;
	private JicheYunxingInfo jicheYunxingInfo;
	private List<String> locoList;
	private Map<String,String> locoWithoutTaiweiMap;
	private Map<String,String> gdNameWithoutLocoMap;
	
	private String newGDName;
	
	private String qryGDName;
	private String qryCH;
	
	public void prepare() throws JTException {
		if (svr_qd == null) {
			svr_qd = new TaiWeiManager_QD();
		}
		if (svr == null) {
			svr = new TaiWeiManager();
		}
		if (locoSvr == null) {
			locoSvr = new LocomotiveService();
		}
		if (gdName != null&& !"".equals(gdName)) {
			gudao = svr_qd.findGudaoByGDName(gdName);
		}
	}
	public String forMove() {
		try{
			List<Object> emptyTaiweis= svr_qd.queryByHql("from TaiWeiAll where currentCH is null and didian='青岛' order by gdName");
			gdNameWithoutLocoMap = new TreeMap<String,String>();
			for(Object o:emptyTaiweis){
				gdNameWithoutLocoMap.put(((TaiWeiAll)o).getGdName(), ((TaiWeiAll)o).getGdName());
			}
			
		}catch(Exception e){
			this.logger.error(e.getClass());
		}
		return "move";
	}
	
	public String input(){
		try{
			//locoList=locoSvr.queryLocalLocoList("青岛");
			locoList=locoSvr.queryLocalLocoList();
			locoWithoutTaiweiMap = new TreeMap<String,String>();
			for(String ch:locoList){
				locoWithoutTaiweiMap.put(ch,ch);
			}
			Set<TaiWei> taiweialls=svr.queryTaiWeis();
			Iterator<TaiWei> it =taiweialls.iterator();
			while(it.hasNext()){
				TaiWei t=it.next();
				locoWithoutTaiweiMap.remove(t.getCurrentCH());
			}
		}catch(Exception e){
			this.logger.error(e.getClass());
		}
		return INPUT;
	}
	public String save() throws Exception {
		try {
			if(gudao!=null){
				gudao.setCH(CH);
				gudao.setLastUpdate(DateUtil.getCurrentTime());
				svr_qd.updateBo(gudao);
				
				jicheYunxingInfo = svr_qd.findJicheYunxingInfoByCH(CH);
				if(jicheYunxingInfo==null){
					jicheYunxingInfo=new JicheYunxingInfo();
					jicheYunxingInfo.setCH(CH);
					svr_qd.createBo(jicheYunxingInfo);
				}
			}
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}
	public String move() throws Exception {
		try {
			Gudao oldGudao = svr_qd.findGudaoByGDName(gdName);
			if(oldGudao!=null){
				oldGudao.setCH(null);
				oldGudao.setLastUpdate(DateUtil.getCurrentTime());
				svr_qd.updateBo(oldGudao);
			
			}
			Gudao newGudao = svr_qd.findGudaoByGDName(newGDName);
			if(newGudao!=null){
					newGudao.setCH(CH);
					newGudao.setLastUpdate(DateUtil.getCurrentTime());
					svr_qd.updateBo(newGudao);
				
			}else{
				this.addActionError(newGudao+"股道不存在");
			}
		} catch (Exception e) {
			this.addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}
	
	public String deleteLoco() throws Exception {
		try {
			if(gudao!=null){
				gudao.setCH(null);
				gudao.setLastUpdate(DateUtil.getCurrentTime());
				svr_qd.updateBo(gudao);
			
				/*jicheYunxingInfo = svr.findJicheYunxingInfoByCH(CH);
				if(jicheYunxingInfo!=null){
					svr.deleteBo(JicheYunxingInfo.class,jicheYunxingInfo.getId());
				}*/
			}
		} catch (Exception e) {
			this.addActionError(e.getMessage());
		}
		return list();
	}
	
	/*public String getListHQL(ArrayList<Object> params) throws JTException {
		return "from TaiWeiAll where didian='青岛' order by gdName";
	}*/
	public String getListHQL(ArrayList<Object> params) throws JTException {
		StringBuffer hqlb=new StringBuffer("from TaiWeiAll where didian='青岛'");
		if(qryGDName!=null&&!qryGDName.trim().equals("")){
			hqlb.append(" and gdName like '%"+qryGDName+"%'");
		}
		if(qryCH!=null&&!qryCH.trim().equals("")){
			hqlb.append(" and currentCH like '%"+qryCH+"%'");
		}
		return hqlb.append(" order by gdName").toString();
	}
	
	public Gudao getGudao() {
		return gudao;
	}
	public void setGudao(Gudao gudao) {
		this.gudao = gudao;
	}
	public String getGdName() {
		return gdName;
	}
	public void setGdName(String gdName) {
		this.gdName = gdName;
	}
	public String getCH() {
		return CH;
	}
	public void setCH(String cH) {
		CH = cH;
	}

	public JicheYunxingInfo getJicheYunxingInfo() {
		return jicheYunxingInfo;
	}
	public void setJicheYunxingInfo(JicheYunxingInfo jicheYunxingInfo) {
		this.jicheYunxingInfo = jicheYunxingInfo;
	}
	public List<String> getLocoList() {
		return locoList;
	}
	public void setLocoList(List<String> locoList) {
		this.locoList = locoList;
	}
	public Map<String, String> getLocoWithoutTaiweiMap() {
		return locoWithoutTaiweiMap;
	}
	public void setLocoWithoutTaiweiMap(Map<String, String> locoWithoutTaiweiMap) {
		this.locoWithoutTaiweiMap = locoWithoutTaiweiMap;
	}
	public Map<String, String> getGdNameWithoutLocoMap() {
		return gdNameWithoutLocoMap;
	}
	public void setGdNameWithoutLocoMap(Map<String, String> gdNameWithoutLocoMap) {
		this.gdNameWithoutLocoMap = gdNameWithoutLocoMap;
	}
	public String getNewGDName() {
		return newGDName;
	}
	public void setNewGDName(String newGDName) {
		this.newGDName = newGDName;
	}
	public String getQryGDName() {
		return qryGDName;
	}
	public void setQryGDName(String qryGDName) {
		this.qryGDName = qryGDName;
	}
	public String getQryCH() {
		return qryCH;
	}
	public void setQryCH(String qryCH) {
		this.qryCH = qryCH;
	}
	
	
}
