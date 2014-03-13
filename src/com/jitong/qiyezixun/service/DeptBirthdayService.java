package com.jitong.qiyezixun.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.console.domain.User;
import com.jitong.qiyezixun.dao.DeptBirthdayDAO;
import com.jitong.qiyezixun.domain.DeptBirthday;
import com.jitong.qiyezixun.domain.DeptBirthdayPerson;

public class DeptBirthdayService extends BaseService {
	DeptBirthdayDAO dao;

	public DeptBirthdayService(Session s) {
		super(s);
		dao = new DeptBirthdayDAO(s);
	}

	public DeptBirthdayService() throws JTException {
		super();
		dao = new DeptBirthdayDAO(s);
	}

	public List<DeptBirthday> queryDeptBirthdays() throws JTException {
		return dao.queryDeptBirthdays();
	}
	
	public void deleteDeptBirthday(String deptBirthdayId)throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deletePersonByDeptBirthday(deptBirthdayId);
			dao.deleteBo(DeptBirthday.class, deptBirthdayId);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("删除失败", e, this.getClass());
		}
	}
	
	
	public List<User> queryPersonsByDeptBirthdayId(String deptBirthdayId) throws JTException {
		return dao.queryPersonsByDeptBirthdayId(deptBirthdayId);
	}

	/**
	 * delete persons from the role
	 * 
	 * @param ids
	 * @param roleId
	 * @throws JTException
	 */
	public void deletePersons(String[] ids, String deptBirthdayId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deletePersons(ids, deptBirthdayId);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("删除失败", e, this.getClass());
		}
	}

	/**
	 * delete a person from the role
	 * 
	 * @param personId
	 * @param deptBirthdayId
	 * @throws JTException
	 */
	public void deletePerson(String personId, String deptBirthdayId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			dao.deletePerson(personId, deptBirthdayId);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("删除失败", e, this.getClass());
		}
	}

	/**
	 * add a person to the role
	 * 
	 * @param personId
	 * @param deptBirthdayId
	 * @throws JTException
	 */
	public void addPerson(String personId, String deptBirthdayId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			if (this.existPerson(personId, deptBirthdayId)) {
				return;
			}
			DeptBirthdayPerson rp = new DeptBirthdayPerson();
			rp.setDeptBirthdayId(deptBirthdayId);
			rp.setPersonId(personId);
			dao.createBo(rp);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("新增失败", e, this.getClass());
		}
	}

	/**
	 * add persons to the role
	 * 
	 * @param personIds
	 * @param deptBirthdayId
	 * @throws JTException
	 */
	public void addPersons(String[] personIds, String deptBirthdayId) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			if (personIds != null && personIds.length > 0) {
				for (int i = 0; i < personIds.length; i++) {
					addPerson(personIds[i], deptBirthdayId);
				}
			}
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("新增失败", e, this.getClass());
		}
	}

	public DeptBirthdayPerson findDeptBirthdayPerson(String personId, String deptBirthdayId) throws JTException {
		return dao.findDeptBirthdayPerson(personId, deptBirthdayId);
	}

	public boolean existPerson(String personId, String deptBirthdayId) throws JTException {
		DeptBirthdayPerson ur = this.findDeptBirthdayPerson(personId, deptBirthdayId);
		if (ur != null) {
			return true;
		}
		return false;
	}
}
