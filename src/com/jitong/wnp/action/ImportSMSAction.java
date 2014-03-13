package com.jitong.wnp.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.FileTypeUtil;
import com.jitong.sms.domain.SMS;
import com.jitong.sms.service.SMSService;
import com.jitong.wnp.util.WnpUtil;
import com.opensymphony.xwork2.Preparable;

public class ImportSMSAction extends JITActionBase implements Preparable {
	private static SMSService service;
	private File uploadfile;
	private String uploadfileFileName;
	List<SMS> smslist;

	public void prepare() throws JTException {
		if (service == null) {
			service = new SMSService();
		}
	}

	public List<SMS> getSmslist() {
		return smslist;
	}

	public void setSmslist(List<SMS> smslist) {
		this.smslist = smslist;
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		session.put("smslist", null);
		return INPUT;
	}

	public String importSMS() throws Exception {
		smslist=(ArrayList<SMS>)session.get("smslist");
		if(smslist!=null){
			for(SMS sms:smslist){
				sms.setSenderInfo(this.getLoginUserInfo());
				sms.setOperatorId(this.getLoginUser().getId());
				service.insertSMS(sms);
			}
			session.put("smslist", null);
			smslist=null;
			this.addActionError("导入成功！");
		}
		return SUCCESS;
	}
	public String uploadSMS() throws Exception {			
			if (uploadfile != null) {
				String ext = FileTypeUtil.getExtensionByName(uploadfileFileName);
				if(!"xls".equals(ext)){
					this.addActionError("请上传扩展名为.xls的Excel文件");
					return INPUT;
				}

				FileInputStream in = new FileInputStream(uploadfile);
				 smslist = new ArrayList<SMS>();
				 try{
				WnpUtil.readExcelFile(in, 1, 3, smslist);
				 }catch(Exception e){
					 this.addActionError("不支持您上传的文件!");
				 }
				session.put("smslist", smslist);
			}
			return SUCCESS;
	}


	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getUploadfileFileName() {
		return uploadfileFileName;
	}

	public void setUploadfileFileName(String uploadfileFileName) {
		this.uploadfileFileName = uploadfileFileName;
	}
	
	public void addActionError(String anErrorMessage) {
        //改从国际化里取值
        if (anErrorMessage
                .startsWith("the request was rejected because its size")) {
            super.addActionError("上传文件太大！(要求不能超过2M)");
        } else {
            super.addActionError(anErrorMessage);
        }
    }

}