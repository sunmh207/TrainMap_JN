package com.jitong.shiguchuli.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Transaction;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.common.util.SysCache;
import com.jitong.common.util.SysUtil;
import com.jitong.console.domain.User;
import com.jitong.shiguchuli.domain.Jiuyuantongzhi;
import com.jitong.shiguchuli.form.JiuyuantongzhiSearchForm;
import com.jitong.sms.service.SMSService;
import com.jitong.wnp.domain.OutBoxDial;
import com.jitong.wnp.service.OutBoxDialService;

public class JiuyuantongzhiAction extends JITActionBase {
	private List<String> voicefilelist = SysUtil.getVoiceFileList();
	private Jiuyuantongzhi jiuyuantongzhi;
	private String id;

	public List<String> getVoicefilelist() {
		return voicefilelist;
	}

	public void setVoicefilelist(List<String> voicefilelist) {
		this.voicefilelist = voicefilelist;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private JiuyuantongzhiSearchForm searchForm;

	public Jiuyuantongzhi getJiuyuantongzhi() {
		return jiuyuantongzhi;
	}

	public void setJiuyuantongzhi(Jiuyuantongzhi jiuyuantongzhi) {
		this.jiuyuantongzhi = jiuyuantongzhi;
	}

	public JiuyuantongzhiSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(JiuyuantongzhiSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public String save() {
		jiuyuantongzhi = new Jiuyuantongzhi();
		jiuyuantongzhi.setSendTime(DateUtil.getCurrentTime("yyyy-MM-dd"));
		User loginUser = getLoginUser();
		jiuyuantongzhi.setSendByID(loginUser.getId());
		jiuyuantongzhi.setSendByName(loginUser.getName());
		jiuyuantongzhi.setDepartment(loginUser.getDept());
		jiuyuantongzhi.setCreatorId(loginUser.getId());
		jiuyuantongzhi.setUnitName(loginUser.getUnit());
		return SUCCESS;
	}

	public String doSave() throws JTException {
		if (jiuyuantongzhi != null) {
			SMSService service = new SMSService();
			User loginUser = getLoginUser();
			jiuyuantongzhi.setSendByID(loginUser.getId());
			jiuyuantongzhi.setSendByName(loginUser.getName());
			jiuyuantongzhi.setCreatorId(loginUser.getId());
			jiuyuantongzhi.setUnitName(loginUser.getUnit());
			service.saveSMSProducer(jiuyuantongzhi);
			OutBoxDialService outdialService = new OutBoxDialService();
			String[] ids = StringUtil.parseString2Array(
					jiuyuantongzhi.getSendToIds(), ",");
			OutBoxDial[] newoutboxdials = new OutBoxDial[ids.length];
			for (int i = 0; i < ids.length; i++) {
				User person = SysCache.interpertUser(ids[i]);
				String phoneNumber = person.getPhoneNumber();
				OutBoxDial outboxdial = new OutBoxDial();

				outboxdial.setMsg(jiuyuantongzhi.getContent());
//				outboxdial.setStartVoiceFile(jiuyuantongzhi.getStartVoiceFile());
				outboxdial.setVerify(jiuyuantongzhi.getNeedConfirm());
				outboxdial.setMsgType(OutBoxDial.MSG_TYPE_TELL);

				outboxdial.setBusinessId(jiuyuantongzhi.getId());
				outboxdial.setBusinessType(jiuyuantongzhi.getBusinessType());

				outboxdial.setDtCreate(new Date());
				outboxdial.setSenderInfo(this.getLoginUserInfo());
				outboxdial.setMobile(phoneNumber);
				outboxdial.setOperatorId(loginUser.getId());
				outboxdial.setRecipientId(ids[i]);
				newoutboxdials[i] = outboxdial;
			}
			outdialService.insertOutBoxDials(newoutboxdials);
		}
		return "thankYou";
	}

	public String delete() throws JTException {
		BaseService service = new BaseService();
		BaseDAO dao = new BaseDAO(service.getS());
		Transaction t = service.beginTransaction();
		Object a = dao.findBoById(Jiuyuantongzhi.class, id);
		dao.deletePo(a);
		service.commitTransaction(t);
		return "thankYou";
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		if(searchForm==null){
			searchForm = new JiuyuantongzhiSearchForm();
		}
		searchForm.setUnitName(this.getLoginUser().getUnit());
		Class<?> domainClass = Jiuyuantongzhi.class;
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName() + " me " + hqlsufix + " order by sendTime desc";
		return hql;
	}

}
