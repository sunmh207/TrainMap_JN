package com.jitong.wnp.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jitong.JitongConstants;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.ActionUtil;
import com.jitong.console.domain.User;
import com.jitong.sms.service.SMSService;
import com.jitong.wnp.form.WNPMMS;
import com.jitong.wnp.form.WNPUploadForm;

public class MMSSenderAction extends JITActionBase {
	private static String businessType = "mmssender";
	private static Logger logger = Logger.getLogger(MMSSenderAction.class);

	private WNPUploadForm uploadForm;

	private String mmsId;
	private String attId;
	
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

	public WNPUploadForm getUploadForm() {
		return uploadForm;
	}

	public void setUploadForm(WNPUploadForm uploadForm) {		
		this.uploadForm = uploadForm;		
	}

	/*public String doSave() throws JTException {
		return save();
	}*/
	public String save() throws JTException {
		logger.debug(uploadForm);
		if (uploadForm != null) {
			List<String> errors = new ArrayList<String>();
 			WNPMMS mmsProducer = uploadForm.toWNPMMS(errors);
 			User user  = this.getLoginUser();
 			mmsProducer.setOperatorId(user.getId());
 			mmsProducer.setSenderInfo(this.getLoginUserInfo());
 			if(errors.size()>0){
 				for (String error : errors) {
					addFieldError("uploadForm.picture", error);
				}
 				return INPUT;
 			}
			logger.debug(mmsProducer);
			SMSService service = new SMSService();	
			service.saveMM(mmsProducer);
		}
		addActionError("发送成功");
		return INPUT;
	}
	public String getCategoryId() {
		return "wnp.mmssender";
	}
	@Override
    public void addActionError(String anErrorMessage) {
        //改从国际化里取值
        if (anErrorMessage
                .startsWith("the request was rejected because its size")) {
            super.addActionError(getText("struts.multipart.maxSize.limit"));
        } else {
            super.addActionError(anErrorMessage);
        }
    }
}
