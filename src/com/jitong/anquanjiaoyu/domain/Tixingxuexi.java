package com.jitong.anquanjiaoyu.domain;

import com.jitong.common.domain.SMSProducer;
import com.jitong.common.form.JTField;

public class Tixingxuexi extends SMSProducer {
	@Override
	@JTField(chineseName = "日期", order = 1)
	public String getSendTime() {
		return super.getSendTime();
	}

	@Override
	@JTField(chineseName = "标题", order = 2)
	public String getTitle() {
		return super.getTitle();
	}

	@Override
	@JTField(chineseName = "提醒对象", order = 3)
	public String getSendToNames() {
		return super.getSendToNames();
	}

	@Override
	@JTField(chineseName = "内容", order = 4)
	public String getContent() {
		return super.getContent();
	}

	@Override
	public String getBusinessType() {
		return "TIXINGXUEXI";
	}

}
