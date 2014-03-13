package com.jitong.wnp.domain;

import java.sql.Blob;

import com.jitong.common.form.JTField;

/**
 * 个人资料
 * 
 * @author stanley.sun
 * 
 */
public class PersonalFile {
	private String id;
	private String name;
	private String ext;
	private String desc;
	private String createTime;
	private String creatorId;
	private String creatorName;
	private Blob data;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@JTField(chineseName="名称",order=0)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JTField(chineseName="描述",order=10)
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	@JTField(chineseName="创建时间",order=15)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	@JTField(chineseName="创建者",order=20)
	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Blob getData() {
		return data;
	}

	public void setData(Blob data) {
		this.data = data;
	}
	@JTField(chineseName="文件类型",order=5)
	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

}
