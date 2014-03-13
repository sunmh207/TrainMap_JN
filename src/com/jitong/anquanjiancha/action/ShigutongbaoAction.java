package com.jitong.anquanjiancha.action;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.anquanjiancha.domain.Shigutongbao;
import com.jitong.anquanjiancha.form.ShigutongbaoSearchForm;
import com.jitong.anquanjiaoyu.domain.Shoujibao;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.ActionUtil;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.console.domain.User;
import com.jitong.sms.service.SMSService;

public class ShigutongbaoAction extends JITActionBase {

	private Shigutongbao shigutongbao;
	private String id;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private ShigutongbaoSearchForm searchForm;

	public Shigutongbao getShigutongbao() {
		return shigutongbao;
	}

	public void setShigutongbao(Shigutongbao shigutongbao) {
		this.shigutongbao = shigutongbao;
	}

	public ShigutongbaoSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(ShigutongbaoSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public String save() {
		shigutongbao = new Shigutongbao();
		shigutongbao.setSendTime(DateUtil.getCurrentTime("yyyy-MM-dd"));
		User loginUser = getLoginUser();
		shigutongbao.setSendByID(loginUser.getId());
		shigutongbao.setSendByName(loginUser.getName());
		shigutongbao.setDepartment(loginUser.getUnitDept());
		shigutongbao.setCreatorId(this.getLoginUser().getId());
		shigutongbao.setUnitName(this.getLoginUser().getUnit());
		return SUCCESS;
	}

	public String doSave() throws JTException {
		if (shigutongbao != null) {
			SMSService service = new SMSService();
			shigutongbao.setCreatorId(this.getLoginUser().getId());
			shigutongbao.setUnitName(this.getLoginUser().getUnit());
			service.saveSMSProducer(shigutongbao);
		}
		return "thankYou";
	}

	public String delete() throws JTException {
		BaseService service = new BaseService();
		BaseDAO dao = new BaseDAO(service.getS());
		Transaction t = service.beginTransaction();
		Object a = dao.findBoById(Shigutongbao.class, id);
		dao.deletePo(a);
		service.commitTransaction(t);
		return "thankYou";
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		if(searchForm==null){
			searchForm = new ShigutongbaoSearchForm();
		}
		searchForm.setUnitName(this.getLoginUser().getUnit());
		Class<?> domainClass = ActionUtil.retireDomainClassNameFromAction(this);
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName() + " me " + hqlsufix+" order by me.sendTime desc";
		return hql;
	}

}
