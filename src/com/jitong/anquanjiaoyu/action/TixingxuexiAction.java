package com.jitong.anquanjiaoyu.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.JitongConstants;
import com.jitong.anquanjiancha.domain.Ziliaoku;
import com.jitong.anquanjiaoyu.domain.Shoujibao;
import com.jitong.anquanjiaoyu.domain.Tixingxuexi;
import com.jitong.anquanjiaoyu.form.TixingxuexiSearchForm;
import com.jitong.common.Pager;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.sms.service.SMSService;
import com.opensymphony.xwork2.Preparable;

public class TixingxuexiAction extends JITActionBase implements Preparable {
	private static Logger logger = Logger.getLogger(TixingxuexiAction.class);
	private Tixingxuexi tixingxuexi;
	private String id;
	private String keyword;
	private String kind;
	private String ziliaokuId;
	
	private Map<String,String> kindMap;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getZiliaokuId() {
		return ziliaokuId;
	}

	public void setZiliaokuId(String ziliaokuId) {
		this.ziliaokuId = ziliaokuId;
	}

	private TixingxuexiSearchForm searchForm;

	public Tixingxuexi getTixingxuexi() {
		return tixingxuexi;
	}

	public void setTixingxuexi(Tixingxuexi Tixingxuexi) {
		this.tixingxuexi = Tixingxuexi;
	}

	public TixingxuexiSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(TixingxuexiSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public void prepare() throws JTException {
		kindMap = new TreeMap<String,String>();
		kindMap.put("","--选择--");
		kindMap.put(JitongConstants.ZILIAOLEIXING_QITAZILIAO,"文件制度资料");
		kindMap.put(JitongConstants.ZILIAOLEIXING_SHIGUZHIDAO,"应急救援资料");
		kindMap.put(JitongConstants.ZILIAOLEIXING_ZILIAOGUIZHANG,"铁路规章资料");
		kindMap.put(JitongConstants.ZILIAOLEIXING_XINCHEBANFA,"行车办法资料");
		kindMap.put(JitongConstants.ZILIAOLEIXING_GUZHANGCHULI,"故障处理资料");
		kindMap.put(JitongConstants.ZILIAOLEIXING_ZUOYEGUICHENG,"作业规程资料");
		kindMap.put(JitongConstants.EQUIPMENT_JIANXIUGUZHANGCHULI,"检修故障处理");
	}
	
	public String save() throws JTException {
		SMSService service = new SMSService();
		BaseDAO dao = new BaseDAO(service.getS());
		if (id != null && id.trim().length() > 0) {
			tixingxuexi = (Tixingxuexi) dao.findBoById(Tixingxuexi.class, id);
			service.enhanceSMSProducer(tixingxuexi);
		} else {
			tixingxuexi = new Tixingxuexi();
			tixingxuexi.setSendTime(DateUtil.getCurrentDate());
			if(ziliaokuId!=null&&ziliaokuId.trim().length()>0){
				Ziliaoku ziliaoku = (Ziliaoku) dao.findBoById(Ziliaoku.class, ziliaokuId);
				tixingxuexi.setContent(ziliaoku.getContent());
				tixingxuexi.setTitle(ziliaoku.getTitle());
			}
		}
		return SUCCESS;
	}

	/*public String save() throws JTException{
		Session s = DBtools.getSession();
		BaseDAO dao = new BaseDAO(s);
		tixingxuexi = (Tixingxuexi) dao.findBoById(Tixingxuexi.class, id);
		String content = tixingxuexi.getContent();
		tixingxuexi.setContent(content==null?"":content.replace("\n", "\n<br>"));
		return SUCCESS;
	}*/
	
	public String doSave() throws JTException {
		if (tixingxuexi != null) {
			if(StringUtil.isEmpty(tixingxuexi.getSendToIds())){
				addActionError("请选择提醒人员");
				return INPUT;
			}
			SMSService service = new SMSService();
			logger.debug(tixingxuexi);
			service.saveSMSProducer(tixingxuexi);
		}
		return "thankYou";
	}

	public String details() throws JTException {
		Session s = DBtools.getSession();
		BaseDAO dao = new BaseDAO(s);
		tixingxuexi = (Tixingxuexi) dao.findBoById(Tixingxuexi.class, id);
		return SUCCESS;
	}

	public String delete() throws JTException {
		BaseService service = new BaseService();
		BaseDAO dao = new BaseDAO(service.getS());
		Transaction t = service.beginTransaction();
		Object o = dao.findBoById(Tixingxuexi.class, id);
		dao.deletePo(o);
		service.commitTransaction(t);
		return "thankYou";
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		Class<?> domainClass = Tixingxuexi.class;
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName() + " me " + hqlsufix +" order by me.sendTime desc";
		return hql;
	}

	public String pick() throws JTException {
		BaseDAO dao = new BaseDAO(DBtools.getSession());
		Pager pager = new Pager(this.getCurrentPage(), this.getPageSize());

		List<?> list = null;
		ArrayList<Object> params = new ArrayList<Object>();
		logger.debug("----------->" + keyword);
		params.add("%" + keyword + "%");
		params.add("%" + keyword + "%");
		String listHQL = "from Ziliaoku me where (me.title like ? or me.content like ?)";
		
		if(kind!=null&&!kind.equals("")){
			params.add(kind);
			listHQL+=" and me.kind=?";
		}
		listHQL+=" order by me.kind";
		
		Object[] arrayParams = params.toArray();
		list = dao.findWithPager(listHQL, pager, arrayParams);
		request.setAttribute("objectList", list);
		request.setAttribute("pager", pager);
		return SUCCESS;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Map<String, String> getKindMap() {
		return kindMap;
	}

	public void setKindMap(Map<String, String> kindMap) {
		this.kindMap = kindMap;
	}

	
}
