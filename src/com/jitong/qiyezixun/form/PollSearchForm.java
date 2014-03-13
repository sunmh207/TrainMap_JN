package com.jitong.qiyezixun.form;

import com.jitong.common.form.FieldCondition;
import com.jitong.common.form.Operation;
import com.jitong.common.form.SameUnitSearchForm;

public class PollSearchForm extends SameUnitSearchForm {
	private String topic;
	private String desc;

	@FieldCondition(op = Operation.like)
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	@FieldCondition(op = Operation.like)
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
