package com.jitong.wnp.form;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.FileTypeUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.shiguchuli.domain.Xianchangtupian;
import com.jitong.shiguchuli.form.XianchangtupianUploadForm;
import com.jitong.sms.domain.MMSAttachment;

public class WNPUploadForm {
	private String description;
	//private String sendDate;
	private String sendToId;
	private String sendToName;
	private String sendToPhones;
	private String title;
	
	private File picture[];
	private String pictureFileName[];
	private String pictureContentType[];
	
	
	
	public String getSendToPhones() {
		return sendToPhones;
	}
	public void setSendToPhones(String sendToPhones) {
		this.sendToPhones = sendToPhones;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/*public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}*/
	public String getSendToId() {
		return sendToId;
	}
	public void setSendToId(String sendToId) {
		this.sendToId = sendToId;
	}
	public String getSendToName() {
		return sendToName;
	}
	public void setSendToName(String sendToName) {
		this.sendToName = sendToName;
	}
	public File[] getPicture() {
		return picture;
	}
	public void setPicture(File[] picture) {
		this.picture = picture;
	}
	public String[] getPictureFileName() {
		return pictureFileName;
	}
	public void setPictureFileName(String[] pictureFileName) {
		this.pictureFileName = pictureFileName;
	}
	public String[] getPictureContentType() {
		return pictureContentType;
	}
	public void setPictureContentType(String[] pictureContentType) {
		this.pictureContentType = pictureContentType;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public WNPMMS toWNPMMS(List<String> errors) throws JTException{
		WNPMMS wnpmms= new WNPMMS();
		wnpmms.setDescription(this.getDescription());
		wnpmms.setTitle(this.getTitle());
		if((this.getDescription()==null||this.getDescription().trim().length()==0)&&(picture==null || picture.length==0)){
			errors.add("短信内容或者图片至少填写一项。");
		}
		MMSAttachment[] atts = new MMSAttachment[0];
		List<MMSAttachment> listAtts = new ArrayList<MMSAttachment>(); 
		try {
			MMSAttachment att = new MMSAttachment();
			if(!StringUtil.isEmpty(description)){
				att.setContent(Hibernate.createBlob(description.getBytes()));
				att.setContentType("txt");
				listAtts.add(att);
			}
			for (int i = 0; picture!=null && i < picture.length; i++) {
				String name = pictureFileName[i];
				if(StringUtil.isEmpty(name))
					continue;
				String ext = FileTypeUtil.getExtensionByName(name);
				if(!FileTypeUtil.isValidType(ext)){
					errors.add("不允许上传"+ext+"格式文件。");
					break;
				}
				 att = new MMSAttachment();
				FileInputStream is = new FileInputStream(picture[i]);
				att.setContent(Hibernate.createBlob(is));
				att.setContentType(ext.toLowerCase());
				listAtts.add(att);
			}
			
		} catch (IOException e) {
			throw new JTException("保存图片失败", this.getClass());
		}
		if(listAtts.size()==0&&errors.size()==0){
			errors.add("请至少上传一个附件。");
		}else{
			atts = listAtts.toArray(new MMSAttachment[0]);
		}
		wnpmms.setAttachments(atts);
		//wnpmms.setSendTime(this.getSendDate());
		wnpmms.setSendTime(DateUtil.getCurrentTime());// use current time
		if(this.getSendToId()==null||this.getSendToId().trim().length()==0){
			errors.add("请选择人员。");
		}
		
		wnpmms.setSendToIds(this.getSendToId());
		wnpmms.setSendToNames(this.getSendToName());
		return wnpmms;
	}
}
