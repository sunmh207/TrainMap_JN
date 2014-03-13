package com.jitong.shigongxunjian.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.FileTypeUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.shigongxunjian.domain.Shigongtu;
import com.jitong.shigongxunjian.form.ShigongtuSearchForm;
import com.jitong.shigongxunjian.form.ShigongtuUploadForm;
import com.jitong.sms.domain.MMSAttachment;
import com.jitong.sms.service.SMSService;

public class ShigongtuAction extends JITActionBase {

	private static Logger logger = Logger
			.getLogger(ShigongtuAction.class);

	private ShigongtuUploadForm uploadForm;
	private ShigongtuSearchForm searchForm;

	private String mmsId;
	private String attId;
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private InputStream pictureStream;

	public String getMmsId() {
		return mmsId;
	}

	public void setMmsId(String mmsId) {
		this.mmsId = mmsId;
	}

	public String getAttId() {
		return attId;
	}

	public void setAttId(String attId) {
		this.attId = attId;
	}

	public InputStream getPictureStream() {
		return pictureStream;
	}

	public void setPictureStream(InputStream pictureStream) {
		this.pictureStream = pictureStream;
	}

	public ShigongtuUploadForm getUploadForm() {
		return uploadForm;
	}

	public void setUploadForm(ShigongtuUploadForm uploadForm) {
		this.uploadForm = uploadForm;
	}

	public ShigongtuSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(ShigongtuSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public String save() {
		return SUCCESS;
	}

	public String doSave() throws JTException {
		logger.debug(uploadForm);
		if (uploadForm != null) {
			List<String> errors = new ArrayList<String>();
 			Shigongtu xianchangtupian = uploadForm.toShigongtu(errors);
 			if (StringUtil.isEmpty(xianchangtupian.getSendToIds())){
 				addActionError("请选择接收人");
				return INPUT;
			}
 			
 			xianchangtupian.setOperatorId(this.getLoginUser().getId());
 			if(errors.size()>0){
 				for (String error : errors) {
					addFieldError("uploadForm.picture", error);
				}
 				return INPUT;
 			}
			logger.debug(xianchangtupian);
			SMSService service = new SMSService();	
			service.saveMMSProducer(xianchangtupian);
		}
		return "thankYou";
	}
	public String delete() throws JTException {
		BaseService service = new BaseService();
		BaseDAO dao = new BaseDAO(service.getS());
		Transaction t = service.beginTransaction();
		Object a = dao.findBoById(Shigongtu.class, id);
		dao.deletePo(a);
		service.commitTransaction(t);
		return "thankYou";
	}

	public String listAtt() throws JTException {
		String mmsId = getMmsId();
		if (!StringUtil.isEmpty(mmsId)) {
			SMSService service = new SMSService();
			List<MMSAttachment> atts = service.retrieveMMSAttachmentList(mmsId);
			logger.debug(atts);
			request.setAttribute("atts", atts);
		}

		return "listAtt";
	}

	public String viewAtt() throws JTException {
		String attId = getAttId();
		boolean asPic = "true".equals(request.getParameter("asPic"));
		SMSService service = new SMSService();
		MMSAttachment att = service.getMMSAttachment(attId);
		try {
			if (att != null) {
				String pictureType = att.getContentType();
				if(asPic&&!FileTypeUtil.isImage(pictureType)){
					return "notAPic";
				}
				pictureStream = att.getContent().getBinaryStream();
				String ext = pictureType;
				return "viewPictrue-"+ext;
			} else {
				return "pictureNotFound";
			}
		} catch (Exception e) {
			throw new JTException("打开图片失败！", e, ShigongtuAction.class);
		}
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		Class<?> domainClass = Shigongtu.class;
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from " + domainClass.getName() + " me " + hqlsufix+" order by me.sendTime desc";
		return hql;
	}
}
