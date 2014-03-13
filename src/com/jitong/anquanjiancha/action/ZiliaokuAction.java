package com.jitong.anquanjiancha.action;

import static com.jitong.JitongConstants.ZILIAOLEIXING_GUZHANGCHULI;
import static com.jitong.JitongConstants.ZILIAOLEIXING_QITAZILIAO;
import static com.jitong.JitongConstants.ZILIAOLEIXING_SHIGUZHIDAO;
import static com.jitong.JitongConstants.ZILIAOLEIXING_XINCHEBANFA;
import static com.jitong.JitongConstants.ZILIAOLEIXING_ZILIAOGUIZHANG;
import static com.jitong.JitongConstants.ZILIAOLEIXING_ZUOYEGUICHENG;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.anquanjiancha.domain.Ziliaoku;
import com.jitong.anquanjiancha.form.ZiliaokuSearchForm;
import com.jitong.anquanjiaoyu.domain.Shoujibao;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.SearchFormUtil;
public class ZiliaokuAction extends JITActionBase{
	


	private static Logger logger = Logger.getLogger(ZiliaokuAction.class);
	
	private static LinkedHashMap<String, String> kinds = new LinkedHashMap<String, String>();
	static {
		kinds.put(ZILIAOLEIXING_GUZHANGCHULI,ZILIAOLEIXING_GUZHANGCHULI);
		kinds.put(ZILIAOLEIXING_XINCHEBANFA,ZILIAOLEIXING_XINCHEBANFA);
		kinds.put(ZILIAOLEIXING_ZILIAOGUIZHANG,ZILIAOLEIXING_ZILIAOGUIZHANG);
		kinds.put(ZILIAOLEIXING_SHIGUZHIDAO,ZILIAOLEIXING_SHIGUZHIDAO);
		kinds.put(ZILIAOLEIXING_QITAZILIAO,ZILIAOLEIXING_QITAZILIAO);
		kinds.put(ZILIAOLEIXING_ZUOYEGUICHENG,ZILIAOLEIXING_ZUOYEGUICHENG);
	}
	private String id;
	private Ziliaoku ziliaoku;
	private ZiliaokuSearchForm searchForm;
	
	public Ziliaoku getZiliaoku() {
		return ziliaoku;
	}
	public void setZiliaoku(Ziliaoku ziliaoku) {
		this.ziliaoku = ziliaoku;
	}
	
	public ZiliaokuSearchForm getSearchForm() {
		return searchForm;
	}
	public void setSearchForm(ZiliaokuSearchForm searchForm) {
		this.searchForm = searchForm;
	}
	public LinkedHashMap<String, String> getKinds() {
		return kinds;
	}
	public void setKinds(LinkedHashMap<String, String> kinds) {
		ZiliaokuAction.kinds = kinds;
	}
	
	public String save() throws JTException {
		if (id != null && id.trim().length() > 0) {
			Session s = DBtools.getSession();
			BaseDAO dao = new BaseDAO(s);
			ziliaoku = (Ziliaoku) dao.findBoById(Ziliaoku.class, id);
		}
		return SUCCESS;
	}
	public String doSave() throws JTException{
		if(ziliaoku!=null){
			BaseDAO dao = new BaseDAO(DBtools.getSession());
			BaseService service = new BaseService();
			Transaction t = service.beginTransaction();
			dao.createBo(ziliaoku);
			service.commitTransaction(t);
			return "thankYou";
		}else {
			return INPUT;
		}
	
	}
	public String delete() throws JTException {
		BaseService service = new BaseService();
		service.deleteBo(Ziliaoku.class, id);
		return "thankYou";
	}
	public String getListHQL(ArrayList<Object> params) throws JTException {
		logger.debug("searchForm: "+searchForm);
		Class<?> domainClass = Ziliaoku.class;
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName()+" me "+hqlsufix;
		return hql;
	}
}
