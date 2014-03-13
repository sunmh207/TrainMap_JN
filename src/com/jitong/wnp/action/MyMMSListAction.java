package com.jitong.wnp.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jitong.JitongConstants;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.FileTypeUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.console.domain.User;
import com.jitong.shiguchuli.action.XianchangtupianAction;
import com.jitong.sms.domain.MMSAttachment;
import com.jitong.sms.service.SMSService;
import com.jitong.wnp.form.MMSVOSearchForm;
import com.jitong.wnp.form.SMSVOSearchForm;

public class MyMMSListAction extends JITActionBase {
	private static Logger logger = Logger.getLogger(MyMMSListAction.class);
	private static String businessType = "mymmslist";
	private String mmsId;
	private String attId;
	private InputStream pictureStream;
	private MMSVOSearchForm searchForm;

	public MMSVOSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(MMSVOSearchForm searchForm) {
		this.searchForm = searchForm;
	}
	
	public String getListHQL(ArrayList<Object> params) throws JTException {
		if(searchForm==null){
			searchForm = new MMSVOSearchForm();
		}
		searchForm.setMgrIds(this.getLoginUser().getId());
		String hqlsufix= SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from MMSVO me " + hqlsufix + " order by me.requestTime desc";
		
		/*if (StringUtil.isEmpty(hqlsufix)) {
		hql = "from MMSVO me where me.operatorId='" + getLoginUser().getId() + "' order by me.requestTime desc";
		} else {
			hql = "from MMSVO me " + hqlsufix + " and me.operatorId='" + getLoginUser().getId() + "' order by me.requestTime desc";
		}*/
		return hql;
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
				if (asPic && !FileTypeUtil.isImage(pictureType)) {
					return "notAPic";
				}
				if(att==null||att.getContent()==null){
					return "notAPic";
				}
				pictureStream = att.getContent().getBinaryStream();
				String ext = pictureType;
				return "viewPictrue-" + ext;
			} else {
				return "pictureNotFound";
			}
		} catch (Exception e) {
			throw new JTException("打开图片失败！", e, XianchangtupianAction.class);
		}
	}
}
