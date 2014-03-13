package com.jitong.zrrc.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.jitong.common.exception.JTException;
import com.jitong.common.service.DefaultBaseService;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.console.domain.User;
import com.jitong.zrrc.domain.Relationship;

public class ZRRCService extends DefaultBaseService {
	public ZRRCService(Session s) {
		super(s);
	}

	public ZRRCService() throws JTException {
		super();
	}

	/**
	 * 查询用户的包保人，并放入list
	 * 
	 * @param userId
	 * @return
	 * @throws JTException
	 */
	public List<User> queryManagers(String userId) throws JTException {
		List<User> mgrList = new ArrayList<User>();
		List<Object> l = super.queryByHql("from Relationship r where r.personId='" + userId + "'");

		if (l != null && !l.isEmpty()) {
			Relationship r = (Relationship) l.get(0);
			String mgrIdstr = r.getManagerIds();
			if (!StringUtil.isEmpty(mgrIdstr)) {
				String[] ids = StringUtil.parseString2Array(mgrIdstr, ",");
				for (String id : ids) {
					mgrList.add(SysCache.interpertUser(id));
				}
			}
		}

		return mgrList;
	}

	public List<Relationship> queryRelationships() throws JTException {
		List<Relationship> rlist = new ArrayList<Relationship>();
		List<Object> l = super.queryByHql("from Relationship");

		if (l != null && !l.isEmpty()) {
			for (Object r : l) {
				rlist.add((Relationship) r);
			}
		}
		return rlist;
	}

	public Relationship findRelationshipByPerId(String personId) throws JTException {
		List<Object> l = super.queryByHql("from Relationship r where r.personId='" + personId + "'");

		if (l != null && !l.isEmpty()) {
			return (Relationship) l.get(0);
		}
		return null;
	}
}
