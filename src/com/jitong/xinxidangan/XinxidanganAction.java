package com.jitong.xinxidangan;

import static com.jitong.JitongConstants.ZILIAOLEIXING_GUZHANGCHULI;
import static com.jitong.JitongConstants.ZILIAOLEIXING_QITAZILIAO;
import static com.jitong.JitongConstants.ZILIAOLEIXING_SHIGUZHIDAO;
import static com.jitong.JitongConstants.ZILIAOLEIXING_XINCHEBANFA;
import static com.jitong.JitongConstants.ZILIAOLEIXING_ZILIAOGUIZHANG;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JTabbedPane;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import com.jitong.JitongConstants;
import com.jitong.anquanjiancha.action.ZiliaokuAction;
import com.jitong.anquanjiancha.domain.Ziliaoku;
import com.jitong.anquanjiancha.form.ZiliaokuSearchForm;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.form.SearchForm;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.common.util.StringUtil;

public class XinxidanganAction extends JITActionBase {
	private String id;
	private static Logger logger = Logger.getLogger(ZiliaokuAction.class);

	private static LinkedHashMap<String, String> kinds = new LinkedHashMap<String, String>();
	static {

		kinds.put("xinxidangan.guzhangchuli", JitongConstants.ZILIAOLEIXING_GUZHANGCHULI);// "故障处理资料");
		kinds.put("xinxidangan.xinchebanfa", JitongConstants.ZILIAOLEIXING_XINCHEBANFA);// "行车办法资料");
		kinds.put("xinxidangan.tieluguizhang", JitongConstants.ZILIAOLEIXING_ZILIAOGUIZHANG);// "铁路规章资料");
		kinds.put("xinxidangan.yingjijiuyuan", JitongConstants.ZILIAOLEIXING_SHIGUZHIDAO);// "应急救援资料");
		kinds.put("xinxidangan.wenjianzhidu", JitongConstants.ZILIAOLEIXING_QITAZILIAO);// "文件制度资料");
		kinds.put("shigongxunjian.zuoyeguicheng", JitongConstants.ZILIAOLEIXING_ZUOYEGUICHENG);// "作业规程资料");
		kinds.put("equipment.jianxiuguzangchuli", JitongConstants.EQUIPMENT_JIANXIUGUZHANGCHULI);// "检修故障处理");
	}

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
		XinxidanganAction.kinds = kinds;
	}

	public String save() {
		if (id != null) {
			try {
				BaseService service = new BaseService();
				ziliaoku = (Ziliaoku) service.findBoById(Ziliaoku.class, id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			ziliaoku = new Ziliaoku();
		}

		String categoryId = getCategoryId();
		ziliaoku.setKind(kinds.get(categoryId));
		return SUCCESS;
	}

	public String delete() throws JTException {
		BaseService service = new BaseService();
		service.deleteBo(Ziliaoku.class, id);
		return "thankYou";
	}

	public String doSave() throws JTException {
		if (ziliaoku != null) {
			BaseDAO dao = new BaseDAO(DBtools.getSession());
			BaseService service = new BaseService();
			Transaction t = service.beginTransaction();
			String categoryId = getCategoryId();
			ziliaoku.setKind(kinds.get(categoryId));
			if (ziliaoku.getId() != null && !StringUtil.isEmpty(ziliaoku.getId())) {
				dao.updateBo(ziliaoku);
			} else {
				dao.createBo(ziliaoku);
			}
			service.commitTransaction(t);
			return "thankYou";
		} else {
			return INPUT;
		}

	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		logger.debug("searchForm: " + searchForm);
		if (searchForm == null) {
			searchForm = new ZiliaokuSearchForm();
		}
		String categoryId = getCategoryId();
		searchForm.setKind(kinds.get(categoryId));
		Class<?> domainClass = Ziliaoku.class;
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName() + " me " + hqlsufix;
		return hql;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
