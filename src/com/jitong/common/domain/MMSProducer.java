package com.jitong.common.domain;

import com.jitong.sms.domain.MMSAttachment;

public abstract class MMSProducer extends SMSProducer {
	private String mmsId;
	private MMSAttachment[] attachments;

	public String getMmsId() {
		return mmsId;
	}

	public void setMmsId(String mmsId) {
		this.mmsId = mmsId;
	}

	public MMSAttachment[] getAttachments() {
		return attachments;
	}

	public void setAttachments(MMSAttachment[] attachments) {
		this.attachments = attachments;
	}

}
