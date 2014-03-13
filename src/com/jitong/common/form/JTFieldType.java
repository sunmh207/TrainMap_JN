package com.jitong.common.form;

public enum JTFieldType {
	String,//普通字符串：
	UserID,//用户ID：
	RoleID,//角色ID：
	SMSStatus,//短信状态：JitongConstants.SMS_STATUS_INIT->初始；JitongConstants.SMS_STATUS_SUCCESS->发送成功;JitongConstants.SMS_STATUS_FAILED->发送失败
	StringBool,//字符串bool型：JitongConstants.STRING_TRUE->是;JitongConstants.STRING_FALSE->否
	PhoneNumber2UserName;//根据电话号码得出的姓名
}
