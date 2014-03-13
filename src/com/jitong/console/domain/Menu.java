package com.jitong.console.domain;

public class Menu implements Comparable {
	public static final String MODULEID_YiDongXinXiPingTai = "YiDongXinXiPingTai";
	public static final String MODULEID_QiYeXinXiZiXun = "QiYeXinXiZiXun";
	public static final String MODULEID_HuiYiBanGongGuanLi = "HuiYiBanGongGuanLi";
	public static final String MODULEID_YiDongXinXiBanGong = "YiDongXinXiBanGong";
	public static final String MODULEID_AnQuanJianChaGuanLi = "AnQuanJianChaGuanLi";
	public static final String MODULEID_ShiGuJiuYuanChuLi = "ShiGuJiuYuanChuLi";
	public static final String MODULEID_ZhiGongAnQuanJiaoYu = "ZhiGongAnQuanJiaoYu";
	public static final String MODULEID_ZeRenRenCheGuanLi = "ZeRenRenCheGuanLi";
	public static final String MODULEID_YunShuZhiHuiDiaoDu = "YunShuZhiHuiDiaoDu";
	public static final String MODULEID_CheLiangJianXiuGuanLi = "CheLiangJianXiuGuanLi";
	public static final String MODULEID_ShiGongXunJianGuanLi = "ShiGongXunJianGuanLi";
	public static final String MODULEID_XinXiDangAnZiDian = "XinXiDangAnZiDian";
	public static final String MODULEID_PMS = "PMS";
	public static final String MODULEID_CONSOLE = "CONSOLE";
	public static final String MODULEID_WAP = "WAP";

	public static final String[][] MODULES = new String[][] { { MODULEID_YiDongXinXiPingTai, "移动通信平台" }, { MODULEID_QiYeXinXiZiXun, "企业资讯平台" },
			{ MODULEID_HuiYiBanGongGuanLi, "会议办公管理" }, { MODULEID_YiDongXinXiBanGong, "移动信息办公" }, { MODULEID_AnQuanJianChaGuanLi, "安全检查管理" },
			{ MODULEID_ShiGuJiuYuanChuLi, "事故救援处理" }, { MODULEID_ZhiGongAnQuanJiaoYu, "职工安全教育" },
			{ MODULEID_ZeRenRenCheGuanLi, "关健人员监控" },// original name is 责任人车
			{ MODULEID_YunShuZhiHuiDiaoDu, "运输指挥调度" }, { MODULEID_CheLiangJianXiuGuanLi, "车辆检修管理" }, { MODULEID_ShiGongXunJianGuanLi, "施工巡检管理" },
			{ MODULEID_XinXiDangAnZiDian, "信息档案字典" }, { MODULEID_PMS, "用户权限管理" }, { MODULEID_CONSOLE, "系统后台管理" }, { MODULEID_WAP, "Wap系统" } };

	private String menuId;
	private String moduleId;
	private String name;
	private String desc;
	private String url;
	private String order;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		return menuId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof Menu)) {
			return false;
		}
		Menu menu = (Menu) obj;
		return menuId.equals(menu.getMenuId());
	}

	public int compareTo(Object arg0) {
		if (arg0 == null || (!(arg0 instanceof Menu))) {
			return 1;
		}
		Menu menu = (Menu) arg0;
		return this.getOrder().compareTo(menu.getOrder());
	}
}
