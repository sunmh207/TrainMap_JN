package com.jitong.wnp.form;

import com.jitong.common.domain.MMSProducer;

public class WNPMMS extends MMSProducer {
	
	private String description;
	private String title;
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getContent() {
		return getDescription();
	}

	@Override
	public String getBusinessType() {
		return "WNPMMS";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
