package com.jitong.wap.action;

import java.util.List;

import com.jitong.common.dao.BaseDAO;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DBtools;
import com.jitong.console.domain.User;
import com.jitong.shigongxunjian.action.ShigongtuAction;
import com.jitong.shiguchuli.action.XianchangtupianAction;
import com.jitong.sms.service.SMSService;

public class WapShigongtuAction extends ShigongtuAction {

	private String searchName;
	private String searchDept;
	private String[] sendToIds;
	private List<User> users;

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getSearchDept() {
		return searchDept;
	}

	public void setSearchDept(String searchDept) {
		this.searchDept = searchDept;
	}

	
	
	public String[] getSendToIds() {
		return sendToIds;
	}

	public void setSendToIds(String[] sendToIds) {
		this.sendToIds = sendToIds;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String wapSave() {
		return SUCCESS;
	}

	public String doWapSave() throws JTException {
		return doSave();
	}

	@SuppressWarnings("unchecked")
	public String queryUser() throws JTException {
		
		String nameWhere = "";
		boolean searchNameProvided = searchName!=null&&searchName.trim().length()!=0;
		boolean searchDeptProvided = searchDept!=null && searchDept.trim().length()!=0;
		if((!searchNameProvided)&&!searchDeptProvided){
			addFieldError("uploadForm.picture", "请输入至少一个搜索条件。");
			return "thankYou";
		}
		
		if(searchNameProvided){
			String[] names = searchName.split("[ , ，；;]+");
			for (String name : names) {
				if(!nameWhere.equals("")){
					nameWhere += " or ";
				}
				nameWhere+=" u.name like '%"+(name.trim())+"%'";
			}
		}
		String deptWhere = "";
		if(searchDeptProvided){
			String[] depts = searchDept.split("[ , ，；;]+");
			for (String dept : depts) {
				if(!deptWhere.equals("")){
					deptWhere += " or ";
				}
				deptWhere+=" u.unitDept like '%"+(dept.trim())+"%'";
			}
		}
		String mid = " or ";
		if(nameWhere.length()==0||deptWhere.length()==0){
			mid = "";
		}
		String sql = "from User u where "+nameWhere+mid+deptWhere;
		BaseDAO dao = new BaseDAO(DBtools.getSession());
		users = dao.find(sql);
		if(users.size()>200 || users.size()==0){
			
			String message = "符合条件的人员过多，无法一次性发送，请使用更精确的搜索条件。（您可以在发送给一批人员后，重复将图片继续发给其他人）";
			if(users.size()==0){
				message = "未找到符合条件的人员。";
			}
			addFieldError("uploadForm.picture", message);
			return "thankYou";
		}
		return SUCCESS;
	}
	
	public String doSend() throws JTException{
		if(sendToIds!=null && sendToIds.length>0){
			SMSService service = new SMSService();
			service.sendShigongtu(sendToIds, getId());
		}
		return SUCCESS;
	}
	public String queryEntry(){
		return "thankYou";
	}
}
