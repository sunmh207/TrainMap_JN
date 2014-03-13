package com.jitong.qiyezixun.domain;

import com.jitong.JitongConstants;
import com.jitong.common.form.JTField;
import com.jitong.common.util.StringUtil;

/**
 * 公告
 * @author stanley.sun
 *
 */
public class Announcement {
	private String announcementId;
	private String announcementDate;
	private String topic;
	private String content;
	private String sender;
	private String creatorId;
	private String unitName;
	
	
	public String getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(String announcementId) {
		this.announcementId = announcementId;
	}
	@JTField(chineseName="时间",order=0)
	public String getAnnouncementDate() {
		return announcementDate;
	}
	public String getAnnouncementDateMMdd() {
		if(announcementDate==null) return announcementDate;
		if(announcementDate.length()==10){
			return announcementDate.substring(5, 9);
		}
		return announcementDate;
	}
	public void setAnnouncementDate(String announcementDate) {
		this.announcementDate = announcementDate;
	}
	@JTField(chineseName="标题",order=5)
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	@JTField(chineseName="内容",order=10)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@JTField(chineseName="发送人",order=15)
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

}
