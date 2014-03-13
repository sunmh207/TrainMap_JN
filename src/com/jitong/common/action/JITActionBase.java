package com.jitong.common.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.jitong.JitongConstants;
import com.jitong.common.Pager;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.ActionUtil;
import com.jitong.common.util.CookieUtil;
import com.jitong.common.util.DBtools;
import com.jitong.common.util.StringUtil;
import com.jitong.console.domain.User;
import com.opensymphony.xwork2.ActionSupport;

public class JITActionBase extends ActionSupport implements ServletRequestAware, SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static Logger logger = Logger.getLogger(JITActionBase.class);
	protected HttpServletRequest request;
	protected Map<String, Object> session;

	private String categoryId;
	protected int currentPage = 1;
	// protected int pageSize = 20;
	public String businessClass;

	private List objectList;
	
	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String pageId0) {
		this.categoryId = pageId0;
	}

	public List getObjectList() {
		return objectList;
	}

	public void setObjectList(List objectList) {
		this.objectList = objectList;
	}

	public HttpServletRequest getRequest() {
		return this.request;
	}

	public Map<String, Object> getSession() {
		return this.session;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public int getCurrentPage() {
		String categoryId = getCategoryId();
		if (categoryId == null) {
			return this.currentPage;//if no cetegoryId, use the value which saved in action.
		}
		Integer p = (Integer) session.get("currentPage-" + categoryId);
		if (p != null) {
			return p;
		} else {
			return 1;
		}
	}

	public void setCurrentPage(int pageNumber) {
		session.put("currentPage-" + getCategoryId(), Integer.valueOf(pageNumber));
		this.currentPage=pageNumber;
	}

	/*
	 * public int getCurrentPage() { return this.currentPage; } public void
	 * setCurrentPage(int pageNumber) { this.currentPage=pageNumber; }
	 */

	protected void nextPage() {
		int i = getCurrentPage();
		session.put("currentPage-" + getCategoryId(), Integer.valueOf(i + 1));
	}

	protected void previousPage() {
		int i = getCurrentPage();
		session.put("currentPage-" + getCategoryId(), Integer.valueOf(i - 1));
	}

	public User getLoginUser() {
		return (User) session.get(JitongConstants.USER);
	}

	public int getPageSize() {
		int pageSize;
		Integer p = (Integer) session.get("pageSize-" + getCategoryId());
		if (p != null) {
			pageSize = p;
		} else {
			pageSize = 20;
		}

		if (pageSize < 1) {
			String cookiePageSize = CookieUtil.getCookie(request, "pageSize");
			logger.debug("cookiePageSize" + cookiePageSize);
			if (cookiePageSize != null && cookiePageSize.matches("[0-9]{1,3}")) {
				pageSize = Integer.parseInt(cookiePageSize);
			}
		}
		if (pageSize < 1)
			pageSize = 20;
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		session.put("pageSize-" + getCategoryId(), Integer.valueOf(pageSize));
	}

	public String getBusinessClass() {

		return businessClass;
	}

	public void setBusinessClass(String businessClass) {
		this.businessClass = businessClass;
	}

	public String list() throws JTException {
		BaseDAO dao = new BaseDAO(DBtools.getSession());
		Pager pager = new Pager(this.getCurrentPage(), this.getPageSize());
		List<?> list = null;
		ArrayList<Object> params = new ArrayList<Object>();
		String listHQL = getListHQL(params);
		logger.debug("listHQL:" + listHQL);
		Object[] arrayParams = params.toArray();
		if (StringUtil.isEmpty(listHQL)) {
			Class<?> domainClass = ActionUtil.retireDomainClassNameFromAction(this);
			list = dao.findWithPager("from " + domainClass.getName(), pager, arrayParams);
		} else {
			list = dao.findWithPager(listHQL, pager, arrayParams);
		}
		request.setAttribute("objectList", list);
		request.setAttribute("pager", pager);
		this.setObjectList(list);
		return SUCCESS;
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		Class<?> domainClass = ActionUtil.retireDomainClassNameFromAction(this);
		String hql = "from " + domainClass.getName();
		return hql;
	}

	public String getRemoteAddr() {
		return ActionUtil.getRemoteAddr(request);
	}

	public String getLoginUserInfo() {
		return "用户名：" + getLoginUser().getName() + " IP:" + getRemoteAddr();
	}

	public String exportExcel() throws JTException {
		BaseDAO dao = new BaseDAO(DBtools.getSession());
		Pager pager = new Pager(1, JitongConstants.MAX_PAGE_SIZE);
		List<?> list = null;
		ArrayList<Object> params = new ArrayList<Object>();
		String listHQL = getListHQL(params);
		logger.debug("listHQL:" + listHQL);
		Object[] arrayParams = params.toArray();
		if (StringUtil.isEmpty(listHQL)) {
			Class<?> domainClass = ActionUtil.retireDomainClassNameFromAction(this);
			list = dao.findWithPager("from " + domainClass.getName(), pager, arrayParams);
		} else {
			list = dao.findWithPager(listHQL, pager, arrayParams);
		}

		session.put(JitongConstants.SESSION_OBJECT, list);
		return "exportExcel";
	}

}
