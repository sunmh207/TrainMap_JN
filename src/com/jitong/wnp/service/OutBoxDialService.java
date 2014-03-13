package com.jitong.wnp.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.BeanUtil;
import com.jitong.common.util.SysCache;
import com.jitong.wnp.domain.OutBoxDial;

public class OutBoxDialService extends BaseService {
	BaseDAO dao;

	public OutBoxDialService(Session s) {
		super(s);
		dao= new BaseDAO(s);
	}

	public OutBoxDialService() throws JTException {
		super();
		dao = new BaseDAO(s);
	}

	/*public OutBoxDial findOutBoxDial(int id) throws JTException {

		try {
			List list = dao.find("from OutBoxDial o where o.id = " + id);
			if (!list.isEmpty()) {
				OutBoxDial outboxDial = new OutBoxDial();
				BeanUtil.copyProperties(outboxDial, list.get(0));
				return outboxDial;
			}
			return null;
		} catch (HibernateException e) {
			throw new JTException("Error occured when query OutBoxDial.", e, this.getClass());
		}
	}*/

	public OutBoxDial findOutBoxDial(String oubboxdialId) throws JTException {

		try {
			List list = dao.find("from OutBoxDial o where o.oubboxdialId = " + oubboxdialId);
			if (!list.isEmpty()) {
				OutBoxDial outboxDial = new OutBoxDial();
				BeanUtil.copyProperties(outboxDial, list.get(0));
				return outboxDial;
			}
			return null;
		} catch (HibernateException e) {
			throw new JTException("Error occured when query OutBoxDial.", e, this.getClass());
		}
	}
	public void insertOutBoxDial(OutBoxDial outboxDial) throws JTException {
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			//set mgrIds
			outboxDial.setMgrIds(SysCache.getManagerIds(outboxDial.getRecipientId()));
			dao.createBo(outboxDial);
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("新增失败", e, this.getClass());
		}
	}

	public void insertOutBoxDials(OutBoxDial[] outboxDials) throws JTException {
		if (outboxDials == null || outboxDials.length == 0)
			return;
		Transaction tx = null;
		try {
			tx = this.beginTransaction();
			for (OutBoxDial outboxDial : outboxDials) {
				insertOutBoxDial(outboxDial);
			}
			this.commitTransaction(tx);
		} catch (JTException e) {
			this.rollbackTransaction(tx);
			throw new JTException("新增失败", e, this.getClass());
		}
	}

	/*public void insertOutBoxDials(OutBoxDial[] outboxDials) throws JTException {
		if (outboxDials == null || outboxDials.length == 0)
			return;
		try {
			//Connection conn = DBtools.getSession().connection();
			Connection conn = s.connection();
			PreparedStatement ps = conn
					.prepareStatement("insert into outboxdial(mobile,msg,startvoicefile,endvoicefile,finger,purpose,ip,verify,operator_id) values(?,?,?,?,?,?,?,?,?)");
			for (int i = 0; i < outboxDials.length; i++) {
				OutBoxDial outboxDial = outboxDials[i];
				ps.setString(1, outboxDial.getMobile());
				ps.setString(2, outboxDial.getMsg());
				ps.setString(3, outboxDial.getStartVoiceFile());
				ps.setString(4, outboxDial.getEndVoiceFile());
				ps.setString(5, outboxDial.getFinger());
				ps.setString(6, outboxDial.getPurpose());
				ps.setString(7, outboxDial.getIp());
				ps.setString(8, outboxDial.getVerify());
				ps.setString(9, outboxDial.getOperatorId());
				ps.addBatch();
			}
			ps.executeBatch();
			conn.commit();
			ps.close();
			//conn.close();
		} catch (Exception e) {
			throw new JTException("新增失败", e, this.getClass());
		}
	}*/
	/*public void insertOutBoxDial(OutBoxDial outboxDial) throws JTException {
		if (outboxDial == null )
			return;
		try {
			Connection conn = s.connection();
			PreparedStatement ps = conn
					.prepareStatement("insert into outboxdial(mobile,msg,startvoicefile,endvoicefile,finger,purpose,ip,verify,operator_id) values(?,?,?,?,?,?,?,?,?)");
				ps.setString(1, outboxDial.getMobile());
				ps.setString(2, outboxDial.getMsg());
				ps.setString(3, outboxDial.getStartVoiceFile());
				ps.setString(4, outboxDial.getEndVoiceFile());
				ps.setString(5, outboxDial.getFinger());
				ps.setString(6, outboxDial.getPurpose());
				ps.setString(7, outboxDial.getIp());
				ps.setString(8, outboxDial.getVerify());
				ps.setString(9, outboxDial.getOperatorId());
				ps.executeUpdate();
			//conn.commit();
			ps.close();
			//conn.close();
		} catch (Exception e) {
			throw new JTException("新增失败", e, this.getClass());
		}
	}*/
	
	
	/*public void insertOutBoxDials(OutBoxDial[] outboxDials) throws JTException {
		if (outboxDials == null || outboxDials.length == 0)
			return;
		try {
			Connection conn = DBtools.getExclusiveSession().connection();
			String sqlStr = "insert into outboxdial(mobile,msg,startvoicefile,endvoicefile,dtcreate,finger,purpose,ip) values(";
			for (int i = 0; i < outboxDials.length; i++) {
				OutBoxDial outboxDial = outboxDials[i];
				String temp =  sqlStr+"'"+outboxDial.getMobile()+"','"+outboxDial.getMsg()+"','','',sysdate"+","+"'0','"+outboxDial.getPurpose()+"','"+outboxDial.getIp()+"')";
				Statement st1 = conn.createStatement();
				st1.execute(temp);
			}
			conn.close();
		} catch (Exception e) {
			throw new JTException("新增失败", e, this.getClass());
		}
	}*/

	public static void main(String[] args) throws Exception{
		OutBoxDialService service = new OutBoxDialService();
		OutBoxDial dial = new OutBoxDial();
		dial.setMobile("15000000001");
		dial.setRecipientId("402881e72e3f809e012e3f8156b70001");
		service.insertOutBoxDial(dial);
	}
}
