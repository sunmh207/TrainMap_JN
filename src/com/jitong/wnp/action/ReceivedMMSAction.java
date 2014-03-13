package com.jitong.wnp.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.FileTypeUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.sms.domain.ReceivedMMSAttachment;
import com.jitong.sms.service.SMSService;
import com.jitong.wnp.form.MMSVOSearchForm;
import com.jitong.wnp.form.ReceivedMMSSearchForm;

public class ReceivedMMSAction extends JITActionBase {
	private ReceivedMMSSearchForm searchForm;
	private static Logger logger = Logger.getLogger(ReceivedMMSAction.class);
	private String mmsId;
	private String attId;
	private InputStream pictureStream;
	
	
	public ReceivedMMSSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(ReceivedMMSSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public String list() throws JTException {
		return super.list();
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		if(searchForm==null){
			searchForm = new ReceivedMMSSearchForm();
		}
		searchForm.setMgrIds(this.getLoginUser().getId());
		String hqlsufix= SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from ReceivedMMS me "+hqlsufix +" order by me.receiveTime desc";
		return hql;
	}

	public String getCategoryId() {
		return "wnp.receivedmmslist";
	}
	
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

	public String listAtt() throws JTException {
		String mmsId = getMmsId();
		if (!StringUtil.isEmpty(mmsId)) {
			SMSService service = new SMSService();
			List<ReceivedMMSAttachment> atts = service.retrieveReceivedMMSAttachmentList(mmsId);
			logger.debug(atts);
			request.setAttribute("atts", atts);
		}

		return "listAtt";
	}
	public String viewAtt() throws JTException {
		String attId = getAttId();
		boolean asPic = "true".equals(request.getParameter("asPic"));
		SMSService service = new SMSService();
		ReceivedMMSAttachment att = service.getReceivedMMSAttachment(attId);
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
			throw new JTException("打开图片失败！", e, ReceivedMMSAction.class);
		}
	}
}
