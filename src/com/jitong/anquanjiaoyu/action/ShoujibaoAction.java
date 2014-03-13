package com.jitong.anquanjiaoyu.action;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.anquanjiaoyu.domain.Shoujibao;
import com.jitong.anquanjiaoyu.form.ShoujibaoSearchForm;
import com.jitong.anquanjiaoyu.service.ShoujibaoService;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.console.domain.User;

public class ShoujibaoAction extends JITActionBase {
	private Shoujibao shoujibao;
	private String id;
	private String sendToIds;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSendToIds() {
		return sendToIds;
	}

	public void setSendToIds(String sendToIds) {
		this.sendToIds = sendToIds;
	}

	private ShoujibaoSearchForm searchForm;

	public Shoujibao getShoujibao() {
		return shoujibao;
	}

	public void setShoujibao(Shoujibao shoujibao) {
		this.shoujibao = shoujibao;
	}

	public ShoujibaoSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(ShoujibaoSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public String save() throws JTException {
		if (id != null && id.trim().length() > 0) {
			Session s = DBtools.getSession();
			BaseDAO dao = new BaseDAO(s);
			shoujibao = (Shoujibao) dao.findBoById(
					Shoujibao.class, id);
			
		}
		return SUCCESS;
	}

	public String doSave() throws JTException {
		
		if (shoujibao != null) {
			BaseService service = new BaseService();
			Transaction t = service.beginTransaction();
			BaseDAO dao = new BaseDAO(service.getS());
			shoujibao.setCreateTime(DateUtil.getCurrentTime());
			String content = shoujibao.getContent();
			if(content!=null&&content.length()>590){
				content = content.substring(0,590)+"...";
			}
			shoujibao.setContent(content);
			User loginUser = this.getLoginUser();
			shoujibao.setCreatorId(loginUser.getId());
			shoujibao.setUnitName(loginUser.getUnit());
			
			if(shoujibao.getId()!=null&&shoujibao.getId().trim().length()>0){
				dao.updateBo(shoujibao);
			}else{
				dao.createBo(shoujibao);
			}
			service.commitTransaction(t);
		}
		return "thankYou";
	}

	public String delete() throws JTException {
		BaseService service = new BaseService();
		BaseDAO dao = new BaseDAO(service.getS());
		Transaction t = service.beginTransaction();
		Object a = dao.findBoById(Shoujibao.class, id);
		dao.deletePo(a);
		service.commitTransaction(t);
		return "thankYou";
	}
	public String details() throws JTException {
		Session s = DBtools.getSession();
		BaseDAO dao = new BaseDAO(s);
		shoujibao = (Shoujibao) dao.findBoById(
				Shoujibao.class, id);
		return SUCCESS;
	}
	public String getListHQL(ArrayList<Object> params) throws JTException {
		if(searchForm==null){
			searchForm = new ShoujibaoSearchForm();
		}
		searchForm.setUnitName(this.getLoginUser().getUnit());
		Class<?> domainClass = Shoujibao.class;
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName() + " me " + hqlsufix + " order by me.sendTime desc, me.createTime desc";
		return hql;
	}
	public String send() throws JTException{
		Session s = DBtools.getSession();
		BaseDAO dao = new BaseDAO(s);
		shoujibao = (Shoujibao) dao.findBoById(
				Shoujibao.class, id);
		String content = shoujibao.getContent();
		shoujibao.setContent(content==null?"":content.replace("\n", "\n<br>"));
		return SUCCESS;
	}
	
	public String doSend() throws JTException{
		ShoujibaoService service = new ShoujibaoService();
		service.sendAsMMS(id, sendToIds);
		return "thankYou";
	}
	
	
}
