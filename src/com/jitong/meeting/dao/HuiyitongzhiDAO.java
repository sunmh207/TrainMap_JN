package com.jitong.meeting.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.meeting.domain.Huiyitongzhi;

public class HuiyitongzhiDAO extends BaseDAO {
	public HuiyitongzhiDAO(Session session) {
		super(session);
	}

	public Huiyitongzhi findHuiyitongzhi(String huiyitongzhiId) throws JTException {
		try {
			return (Huiyitongzhi) super.findBoById(Huiyitongzhi.class, huiyitongzhiId);
		} catch (HibernateException e) {
			throw new JTException("Error occured when find Huiyitongzhi. huiyitongzhiId=" + huiyitongzhiId, e, this.getClass());
		}
	}

	public List<Huiyitongzhi> queryHuiyitongzhis() throws JTException {
		try {
			return (List<Huiyitongzhi>) super.find("from Huiyitongzhi u");
		} catch (HibernateException e) {
			throw new JTException("Error occured when query Huiyitongzhis.", e, this.getClass());
		}
	}

}
