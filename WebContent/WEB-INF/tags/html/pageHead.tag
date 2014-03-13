<%@ attribute name="title" required="false" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" content="no-cache"/>
<link href="${root}/css/style.css" rel="stylesheet" type="text/css"></link>
<link href="${root}/css/tab.css" rel="stylesheet" type="text/css"></link>
<link href="${root}/css/jquery-ui-1.8.7.custom.css" rel="stylesheet" type="text/css"></link>
<link href="${root}/css/domcollapse.css" rel="stylesheet" type="text/css"></link>

<script type="text/javascript" src="${root}/js/jquery.min.js"></script>
<script type="text/javascript" src="${root}/js/jquery-ui-1.8.7.custom.min.js"></script>
<script type="text/javascript" src="${root}/js/PopupCalendar.js"></script>
<script type="text/javascript" src="${root}/js/domcollapse.js"></script>
<script type="text/javascript" src="${root}/js/jtmobile.js"></script>
<title>
<c:choose>
	<c:when test="${empty title}">${category.display} - 整备外勤管理系统</c:when>
	<c:otherwise>${title} - 整备外勤管理系统</c:otherwise>
</c:choose>
</title>
<script>
$(function() {
	$( "input.date" ).datepicker({
		showOn: "button",
		buttonImage: "${root}/images/calendar.gif",
		buttonImageOnly: true,
		showMonthAfterYear: true,
		shortYearCutoff:30,
		dayNamesMin:["日","一","二","三","四","五","六"],
	 	dateFormat: 'yy-mm-dd',
	 	monthNames: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
	 	nextText:">",
	 	prevText:"<",
	 	yearSuffix: "年"
	});
});
</script>
<jsp:doBody/>
</head>