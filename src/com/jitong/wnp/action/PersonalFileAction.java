package com.jitong.wnp.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;

import com.jitong.JitongConstants;
import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.service.BaseService;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.FileTypeUtil;
import com.jitong.common.util.SearchFormUtil;
import com.jitong.console.domain.User;
import com.jitong.equipment.form.PersonalFileSearchForm;
import com.jitong.wnp.domain.PersonalFile;
import com.opensymphony.xwork2.Preparable;

public class PersonalFileAction extends JITActionBase implements Preparable {
	private PersonalFileSearchForm searchForm;
	private PersonalFile personalFile;
	private static BaseService service;
	private File uploadfile;
	private String uploadfileFileName;

	public void prepare() throws JTException {
		if (service == null) {
			service = new BaseService();
		}
		if (personalFile != null && personalFile.getId() != null) {
			personalFile = (PersonalFile) service.findBoById(PersonalFile.class, personalFile.getId());
		}
	}

	public String list() throws JTException {
		return super.list();
	}

	public String input() {
		return INPUT;
	}

	public String delete() throws JTException {
		service.deleteBo(PersonalFile.class, personalFile.getId());
		return list();
	}

	public String save() throws Exception {
		if (personalFile.getId() == null || "".equals(personalFile.getId())) {
			personalFile.setCreateTime(DateUtil.getCurrentTime());
			User u = this.getLoginUser();
			personalFile.setCreatorId(u.getId());
			personalFile.setCreatorName(u.getName());
			if (uploadfile != null) {
				String ext = FileTypeUtil.getExtensionByName(uploadfileFileName);
				if(FileTypeUtil.isForbiddenUploadType(ext)){
					this.addActionError("禁止上传 "+ext+" 文件");
					return INPUT;
				}
				personalFile.setExt(ext);

				FileInputStream is = new FileInputStream(uploadfile);
				personalFile.setData(Hibernate.createBlob(is));
			}
			service.createBo(personalFile);
		} else {
			if (uploadfile != null) {
				String ext = FileTypeUtil.getExtensionByName(uploadfileFileName);
				if(FileTypeUtil.isForbiddenUploadType(ext)){
					this.addActionError("禁止上传 "+ext+" 文件");
					return INPUT;
				}
				personalFile.setExt(ext);

				FileInputStream is = new FileInputStream(uploadfile);
				personalFile.setData(Hibernate.createBlob(is));
			}
			service.updateBo(personalFile);
		}
		return list();
	}

	public String getListHQL(ArrayList<Object> params) throws JTException {
		User user = this.getLoginUser();
		String hqlsufix = SearchFormUtil.toHQLSuffix(searchForm, params);
		String hql = "from PersonalFile me " + hqlsufix + " where me.creatorId='"+user.getId()+"' order by me.createTime desc";
		return hql;
	}

	public String download() {
		session.put(JitongConstants.SESSION_OBJECT, personalFile);
		return "download";
	}

	public String getCategoryId() {
		return "wnp.personalfile";
	}

	public PersonalFileSearchForm getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(PersonalFileSearchForm searchForm) {
		this.searchForm = searchForm;
	}

	public PersonalFile getPersonalFile() {
		return personalFile;
	}

	public void setPersonalFile(PersonalFile personalFile) {
		this.personalFile = personalFile;
	}

	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getUploadfileFileName() {
		return uploadfileFileName;
	}

	public void setUploadfileFileName(String uploadfileFileName) {
		this.uploadfileFileName = uploadfileFileName;
	}
	
	public void addActionError(String anErrorMessage) {
        //改从国际化里取值
        if (anErrorMessage
                .startsWith("the request was rejected because its size")) {
            super.addActionError("上传文件太大！(要求不能超过2M)");
        } else {
            super.addActionError(anErrorMessage);
        }
    }

}