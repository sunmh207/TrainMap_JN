package com.jitong.yunshuzhihui.domain;

import com.jitong.common.domain.SMSProducer;
import com.jitong.common.form.JTField;

public class YunshuzhihuiManual extends SMSProducer{
	
	@Override
	@JTField(chineseName="发送时间",order=1)
	public String getSendTime() {
		return super.getSendTime();
	}
	@Override
	@JTField(chineseName="发送人",order=2)
	public String getSendByName() {
		return super.getSendByName();
	}
	@Override
	@JTField(chineseName="发送对象",order=3)
	public String getSendToNames() {
		return super.getSendToNames();
	}
	@JTField(chineseName="标题",order=4)
	public String getTitle() {
		return  super.getTitle();
	}
	@Override
	@JTField(chineseName="内容",order=5)
	public String getContent() {
		return super.getContent();
	}

	@Override
	public String getBusinessType() {
		return "YUNSHUZHIHUI";
	}
	
	
}
