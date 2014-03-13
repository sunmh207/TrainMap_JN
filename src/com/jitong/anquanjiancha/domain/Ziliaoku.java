package com.jitong.anquanjiancha.domain;

import com.jitong.JitongConstants;
import com.jitong.common.form.JTField;

public class Ziliaoku {
	private String id;
	private String kind;
	private String title;
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKind() {
		return kind;
	}

	@JTField(chineseName="资料类型",order=1)
	public String getKindCN() {
		if (JitongConstants.ZILIAOLEIXING_QITAZILIAO.equals(getKind())) {
			return "文件制度资料";
		} else if (JitongConstants.ZILIAOLEIXING_SHIGUZHIDAO.equals(getKind())) {
			return "应急救援资料";
		} else if (JitongConstants.ZILIAOLEIXING_ZILIAOGUIZHANG.equals(getKind())) {
			return "铁路规章资料";
		} else if (JitongConstants.ZILIAOLEIXING_XINCHEBANFA.equals(getKind())) {
			return "行车办法资料";
		} else if (JitongConstants.ZILIAOLEIXING_GUZHANGCHULI.equals(getKind())) {
			return "故障处理资料";
		} else if (JitongConstants.ZILIAOLEIXING_ZUOYEGUICHENG.equals(getKind())) {
			return "作业规程资料";
		} else if (JitongConstants.EQUIPMENT_JIANXIUGUZHANGCHULI.equals(getKind())) {
			return "检修故障处理";
		}else{
			return getKind();
		}
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	@JTField(chineseName = "主题", order = 2)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JTField(chineseName = "内容", order = 3)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Ziliaoku [id=" + id + ", title=" + title + ", content=" + content + "]";
	}

}
