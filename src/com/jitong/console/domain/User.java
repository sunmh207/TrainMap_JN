package com.jitong.console.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jitong.common.domain.CategoryItem;
import com.jitong.common.form.JTField;
import com.jitong.common.util.StringUtil;

public class User {
	private String id;
	private String name;
	private String loginName;
	private String password;
	private String isAdmin;
	private String phoneNumber;
	// private String unitDept;// 单位-部门，= unit+"-"+dept
	private String unit;// 单位
	private String dept;// 部门

	private String GH;// 工号
	private String gender;// 性别
	private String birthday;

	private String lastLoginIp;
	private String lastLoginTime;
	private String creatorId;
	
	private String gongzhong;//工种
	private String gangwei;//岗位
	private String zhiwu;//职务
	private String jianzhi;//兼职
	
	
	
	/**
	 * 角色权限
	 * Map<moduleId, List<Menu>>
	 */
	//private Map<String, RoleMenu> menusMap = new HashMap<String, RoleMenu>();
	private Map<String, List<Menu>> menusMap = new HashMap<String, List<Menu>>();
	/**
	 * 人员权限
	 * Map<person id, User>
	 */
	private Map<String, User> personMap = new HashMap<String, User>();
	
	
	public User() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@JTField(chineseName="姓名",order=0)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	@JTField(chineseName="手机号",order=30)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@JTField(chineseName="单位",order=15)
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	@JTField(chineseName="部门",order=20)
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getUnitDept() {
		return StringUtil.trim(unit) + "-" + StringUtil.trim(dept);
	}

	/*public void setUnitDept(String unitDept) {
		if (StringUtil.isEmpty(unitDept)) {
			unit = null;
			dept = null;
		} else {
			String[] udArray = StringUtil.parseString2Array(unitDept, "-");
			if (udArray.length == 1) {
				if (unitDept.indexOf("-") > 0) {// XXX-
					unit = udArray[0];
					dept = null;
				} else {// -XXX
					unit = null;
					dept = udArray[0];
				}
			} else if (udArray.length == 2) {// XXX-YYY
				unit = udArray[0];
				dept = udArray[1];
			}
		}
	}*/
	@JTField(chineseName="工号",order=10)
	public String getGH() {
		return GH;
	}

	public void setGH(String gH) {
		GH = gH;
	}
	@JTField(chineseName="性别",order=5)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	@JTField(chineseName="出生日期",order=25)
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Map<String, List<Menu>> getMenusMap() {
		return menusMap;
	}

	public void setMenusMap(Map<String, List<Menu>> menusMap) {
		this.menusMap = menusMap;
	}

	public Map<String, User> getPersonMap() {
		return personMap;
	}

	public void setPersonMap(Map<String, User> personMap) {
		this.personMap = personMap;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
	
	
	
	public String getGongzhong() {
		return gongzhong;
	}

	public void setGongzhong(String gongzhong) {
		this.gongzhong = gongzhong;
	}

	public String getGangwei() {
		return gangwei;
	}

	public void setGangwei(String gangwei) {
		this.gangwei = gangwei;
	}

	public String getZhiwu() {
		return zhiwu;
	}

	public void setZhiwu(String zhiwu) {
		this.zhiwu = zhiwu;
	}

	public String getJianzhi() {
		return jianzhi;
	}

	public void setJianzhi(String jianzhi) {
		this.jianzhi = jianzhi;
	}

	@Override
	public int hashCode() {
		return id==null?"".hashCode():id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof User)) {
			return false;
		}
		if(id==null){
			return false;
		}
		User u = (User) obj;
		return id.equals(u.getId());
	}
}
