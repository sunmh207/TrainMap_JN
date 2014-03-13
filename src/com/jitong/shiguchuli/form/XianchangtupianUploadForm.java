package com.jitong.shiguchuli.form;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Hibernate;

import com.jitong.common.exception.JTException;
import com.jitong.common.util.FileTypeUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.shiguchuli.domain.Xianchangtupian;
import com.jitong.sms.domain.MMSAttachment;

public class XianchangtupianUploadForm{
	private String accidentDate;
	private String accidentPlace;
	private String description;
	private String sendDate;
	private String sendToId;
	private String sendToName;
	private String sendToPhones;
	private File picture[];
	private String pictureFileName[];
	private String pictureContentType[];
	

	public String getAccidentDate() {
		return accidentDate;
	}
	public void setAccidentDate(String accidentDate) {
		this.accidentDate = accidentDate;
	}
	public String getAccidentPlace() {
		return accidentPlace;
	}
	public void setAccidentPlace(String accidentPlace) {
		this.accidentPlace = accidentPlace;
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
		return "XianchangtupianUploadForm [accidentDate=" + accidentDate
				+ ", accidentPlace=" + accidentPlace + ", description="
				+ description + ", sendDate=" + sendDate + ", sendToId="
				+ sendToId + ", sendToName=" + sendToName + ", picture="
				+ Arrays.toString(picture) + ", pictureFileName="
				+ Arrays.toString(pictureFileName) + ", pictureContentType="
				+ Arrays.toString(pictureContentType) + "]";
	}
	public Xianchangtupian toXianchangtupian(List<String> errors) throws JTException{
		Xianchangtupian xianchangtupian= new Xianchangtupian();
		xianchangtupian.setAccidentDate(this.getAccidentDate());
		xianchangtupian.setAccidentPlace(this.getAccidentPlace());
		xianchangtupian.setDescription(this.getDescription());
		
		MMSAttachment[] atts = new MMSAttachment[0];
		
		List<MMSAttachment> listAtts = new ArrayList<MMSAttachment>(); 
		try {
			MMSAttachment att = new MMSAttachment();
			if(StringUtil.isEmpty(description)){
				description="无内容";
			}
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
			throw new JTException("保存图片失败", XianchangtupianUploadForm.class);
		}
		if(listAtts.size()==0&&errors.size()==0){
			errors.add("请至少上传一个附件。");
		}else{
			atts = listAtts.toArray(new MMSAttachment[0]);
		}
		xianchangtupian.setAttachments(atts);
		xianchangtupian.setSendTime(this.getSendDate());
		xianchangtupian.setSendToIds(this.getSendToId());
		xianchangtupian.setSendToNames(this.getSendToName());
		return xianchangtupian;
	}
}
