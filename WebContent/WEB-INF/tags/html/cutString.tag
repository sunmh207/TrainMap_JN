<%@ tag pageEncoding="utf-8"%>
<%@tag import="com.jitong.JitongConstants"%>
<%@tag import="com.jitong.common.util.StringUtil"%>
<%@tag import="com.jitong.console.domain.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="content" type="java.lang.String" required="false"%>
<%@ attribute name="maxLength" type="java.lang.String"	required="false"%>
<%
if (maxLength!=null&&!"".equals(maxLength.trim())) {
	out.print(StringUtil.cutEndWithEllipsis(content,Integer.parseInt(maxLength)));
}else{
	out.print(StringUtil.cutEndWithEllipsis(content,JitongConstants.CONTENT_SHOW_MAX_LENGTH));
}
%>