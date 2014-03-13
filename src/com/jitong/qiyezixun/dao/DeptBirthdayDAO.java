package com.jitong.qiyezixun.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.BeanUtil;
import com.jitong.console.domain.User;
import com.jitong.console.domain.UserRole;
import com.jitong.qiyezixun.domain.DeptBirthday;
import com.jitong.qiyezixun.domain.DeptBirthdayPerson;

public class DeptBirthdayDAO extends BaseDAO {

	public DeptBirthdayDAO(Session s) {
		super(s);
	}

	public List<DeptBirthday> queryDeptBirthdays()throws JTException {
		try {
			String sql = "from DeptBirthday";
			return super.find(sql);

		} catch (Exception e) {
			throw new JTException("查询失败", e, this.getClass());
		}
	}
	/**
	 * 查询指定角色的人员权限列表
	 * 
	 * @param roleID
	 *            角色ID
	 * @return List
	 */
	public List<User> queryPersonsByDeptBirthdayId(String deptBirthdayId) throws JTException {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select u from User u,DeptBirthdayPerson up where u.id=up.personId and up.deptBirthdayId='").append(deptBirthdayId).append("'");
			String s = sb.toString();
			List<User> list = super.find(s);
			List rList = new ArrayList();

			if (list != null) {
				int len = list.size();
				for (int i = 0; i < len; i++) {
					User u = new User();
					Object o = list.get(i);
					BeanUtil.copyProperties(u, o);
					rList.add(u);
				}
			}
			return rList;

		} catch (Exception e) {
			throw new JTException("读取数据错误", e, this.getClass());
		}
	}

	public void deletePersonByDeptBirthday(String deptBirthdayId) throws JTException {
		try {
			String sql = "from DeptBirthdayPerson u where u.deptBirthdayId='" + deptBirthdayId + "'";
			List<UserRole> list = super.find(sql);
			super.deleteAll(list);

		} catch (Exception e) {
			throw new JTException("移出人员失败", e, this.getClass());
		}
	}
	/**
	 * 将人员从角色中移除
	 * 
	 * @param ids
	 *            人员ID数组
	 * @param roleId
	 *            角色ID
	 * @throws JTException
	 */

	public void deletePersons(String[] ids, String deptBirthdayId) throws JTException {
		try {
			String InStr = "";
			for (int i = 0; i < ids.length; i++) {
				if (i == 0)
					InStr += "'" + ids[i] + "'";
				else
					InStr += ",'" + ids[i] + "'";
			}
			String sql = "from DeptBirthdayPerson u where u.personId in (" + InStr + ") and u.deptBirthdayId='" + deptBirthdayId + "'";
			List<UserRole> list = super.find(sql);
			super.deleteAll(list);

		} catch (Exception e) {
			throw new JTException("移出人员失败", e, this.getClass());
		}

	}

	public void deletePerson(String personId, String deptBirthdayId) throws JTException {
		try {
			String sql = "from DeptBirthdayPerson u where u.personId = '" + personId + "'  and u.deptBirthdayId='" + deptBirthdayId + "'";
			List<DeptBirthdayPerson> list = super.find(sql);
			super.deleteAll(list);

		} catch (Exception e) {
			throw new JTException("移出人员失败", e, this.getClass());
		}

	}

	public DeptBirthdayPerson findDeptBirthdayPerson(String personId, String deptBirthdayId) throws JTException {
		try {
			String sql = "from DeptBirthdayPerson u where u.personId = '" + personId + "'  and u.deptBirthdayId='" + deptBirthdayId + "'";
			List<DeptBirthdayPerson> list = super.find(sql);
			if (list != null && !list.isEmpty()) {
				return (DeptBirthdayPerson) list.get(0);
			}
			return null;
		} catch (Exception e) {
			throw new JTException("查询人员失败", e, this.getClass());
		}
	}
}
