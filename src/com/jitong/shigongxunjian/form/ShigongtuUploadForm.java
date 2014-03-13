package com.jitong.shigongxunjian.form;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Hibernate;

import com.jitong.common.exception.JTException;
import com.jitong.common.util.FileTypeUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.shigongxunjian.domain.Shigongtu;
import com.jitong.sms.domain.MMSAttachment;

public class ShigongtuUploadForm{
	private String time;
	private String zone;
	private String description;
	private String sendDate;
	private String sendToId;
	private String sendToName;
	private String sendToPhones;
	private File picture[];
	private String pictureFileName[];
	private String pictureContentType[];
	

	public String getTime() {
		return time;
	}
	public void setTime(String accidentDate) {
		this.time = accidentDate;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String accidentPlace) {
		this.zone = accidentPlace;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
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

	public String getSendToPhones() {
		return sendToPhones;
	}
	public void setSendToPhones(String sendToPhones) {
		this.sendToPhones = sendToPhones;
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
	
	
	@Override
	public String toString() {
		return "XianchangtupianUploadForm [accidentDate=" + time
				+ ", accidentPlace=" + zone + ", description="
				+ description + ", sendDate=" + sendDate + ", sendToId="
				+ sendToId + ", sendToName=" + sendToName + ", picture="
				+ Arrays.toString(picture) + ", pictureFileName="
				+ Arrays.toString(pictureFileName) + ", pictureContentType="
				+ Arrays.toString(pictureContentType) + "]";
	}
	public Shigongtu toShigongtu(List<String> errors) throws JTException{
		Shigongtu shigongtu= new Shigongtu();
		shigongtu.setTime(this.getTime());
		shigongtu.setZone(this.getZone());
		shigongtu.setDescription(this.getDescription());
		shigongtu.setTitle("施工现场图");
		
		MMSAttachment[] atts = new MMSAttachment[0];
		
		List<MMSAttachment> listAtts = new ArrayList<MMSAttachment>(); 
		try {
			MMSAttachment att = new MMSAttachment();
			att.setContent(Hibernate.createBlob(description.getBytes()));
			att.setContentType("txt");
			listAtts.add(att);
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
				att.setContentType(ext);
				listAtts.add(att);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new JTException("保存图片失败", ShigongtuUploadForm.class);
		}
		if(listAtts.size()==0&&errors.size()==0){
			errors.add("请至少上传一个附件。");
		}else{
			atts = listAtts.toArray(new MMSAttachment[0]);
		}
		shigongtu.setAttachments(atts);
		shigongtu.setSendTime(this.getSendDate());
		shigongtu.setSendToIds(this.getSendToId());
		shigongtu.setSendToNames(this.getSendToName());
		return shigongtu;
	}
}
