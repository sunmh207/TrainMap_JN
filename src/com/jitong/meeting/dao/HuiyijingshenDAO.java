package com.jitong.meeting.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.meeting.domain.Huiyijingshen;

public class HuiyijingshenDAO extends BaseDAO{
	public HuiyijingshenDAO(Session session) {
		super(session);
	}

	public Huiyijingshen findHuiyijingshen(String huiyijingshenId) throws JTException {
		try {
			return (Huiyijingshen) super.findBoById(Huiyijingshen.class, huiyijingshenId);
		} catch (HibernateException e) {
			throw new JTException("Error occured when find Huiyijingshen. huiyijingshenId=" + huiyijingshenId, e, this.getClass());
		}
	}

	public List<Huiyijingshen> queryHuiyijingshens() throws JTException {
        try {
           return (List<Huiyijingshen>)super.find("from Huiyijingshen u");
        } catch (HibernateException e) {
            throw new JTException("Error occured when query Huiyijingshens.", e, this.getClass());
        }
    }
}
