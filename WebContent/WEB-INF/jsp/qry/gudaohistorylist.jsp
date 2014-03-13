<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%> 
<html:pageHead />
<body>
<html:topNav/>
<form id="searchForm" action="${root}/qry.do">
<div style="float: left; padding-bottom: 0px;">
	机车号 <input name="qrych" value='${qrych}' size="50">
	<input type="button" value="搜索" onclick="javascript:goPage(1)">  
</div>
<html:pagination exportExcel="false"/>
</form>
<s:actionerror/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">序号</td>
		<td class="td_header">机车号</td>
		<td class="td_header">股道</td>
		<td class="td_header">最后更新时间</td>
		<td class="td_header">地点</td>
	</tr>
	<c:forEach items="${objectList}" var="gudaohistory" varStatus="status">
		<tr>
			<td align="center" class="td_header">${status.index+pager.startRecord}</td>
			<td align="left" class='td_body'>${gudaohistory.ch}</td>
			<td align="left" class='td_body'>${gudaohistory.gdName}</td>
			<td align="left" class='td_body'>${gudaohistory.lastUpdate}</td>
			<td align="left" class='td_body'>${gudaohistory.didian}</td>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>