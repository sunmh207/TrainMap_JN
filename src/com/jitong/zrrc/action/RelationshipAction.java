package com.jitong.zrrc.action;

import java.util.ArrayList;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DateUtil;
import com.jitong.qiyezixun.service.NewsPaperService;
import com.jitong.zrrc.domain.Relationship;
import com.opensymphony.xwork2.Preparable;

public class RelationshipAction extends JITActionBase implements Preparable {
	private Relationship relationship;

	private static BaseService service;

	public void prepare() throws JTException {
		if (service == null) {
			service = new NewsPaperService();
		}
		if (relationship != null && relationship.getId() != null) {
			relationship = (Relationship) service.findBoById(Relationship.class, relationship.getId());
		}
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		return INPUT;
	}

	public String delete() throws JTException {
		service.deleteBo(Relationship.class, relationship.getId());
		return list();
	}

	public String save() throws Exception {
		if (relationship.getId() == null || "".equals(relationship.getId())) {
			relationship.setCreateTime(DateUtil.getCurrentTime());
			service.createBo(relationship);
		} else {
			service.updateBo(relationship);
		}
		return list();
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		String hql = "from Relationship me order by me.createTime desc";
		return hql;
	}

	public String getCategoryId() {
		return "zrrc.relationship";
	}

	public Relationship getRelationship() {
		return relationship;
	}

	public void setRelationship(Relationship relationship) {
		this.relationship = relationship;
	}

}
