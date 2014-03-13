<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="idName" type="java.lang.String" required="true"%>
<%@ attribute name="nameName" type="java.lang.String" required="true"%>
<%@ attribute name="idValue" type="java.lang.String" required="false"%>
<%@ attribute name="nameValue" type="java.lang.String" required="false"%>
<%@ attribute name="dept" type="java.lang.String" required="false"%>
<div>
<input type="hidden" name="<%=idName%>" id="locomotiveId" value="<%=idValue%>">
<input type="text" name="<%=nameName%>" id="CH" value="<%= com.jitong.common.util.StringUtil.trim(nameValue)%>" readonly size="20">

<input name="popUserDlg" type="button"	class="button_select" onClick="fPopUpLocomotiveDlg('<%=nameName%>','<%=idName%>',true,null)">
</div>
