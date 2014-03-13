package com.jitong.sms.domain;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

//import org.apache.commons.lang.xwork.builder.EqualsBuilder;
//import org.apache.commons.lang.xwork.builder.HashCodeBuilder;

public class InBox implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String businessType; 
	private String businessId; 
	private String phoneNumber;
	private String reachTime;
	private String content;
	private String reply="0";

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getReachTime() {
		return reachTime;
	}

	public void setReachTime(String reachTime) {
		this.reachTime = reachTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof InBox)) {
			return false;
		} else {
			InBox inBox = (InBox) obj;
			return new EqualsBuilder().appendSuper(super.equals(obj)).append(this.businessType, inBox.businessType).append(this.businessId, inBox.businessId)
					.append(this.phoneNumber, inBox.phoneNumber).isEquals();
		}
	}

	public int hashCode() {
		return new HashCodeBuilder(-528253723, -475504089).appendSuper(super.hashCode()).append(this.businessType).append(this.businessId).append(
				this.phoneNumber).toHashCode();
	}
}
