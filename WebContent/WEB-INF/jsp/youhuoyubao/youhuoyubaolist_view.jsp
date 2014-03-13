<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<script type="text/javascript">
function search(){
	var frm=document.forms[0];
	frm.qryLocalOnly.value='0';
	goPage(1)
}
function searchLocal(){
	var frm=document.forms[0];
	frm.qryLocalOnly.value='1';
	goPage(1)
}
</script>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">有活预报</td>
    </tr>
</table>
<form id="searchForm" action="${root}/youhuoyubao_view.do">

<div style="float: left; padding-bottom: 0px;">
<input type="hidden" name="qryLocalOnly" value='${qryLocalOnly}' >

	机车号 <input name="qryCh" value='${qryCh}' size="10">
	信息类型 	<s:select  name="qryInfoType" list="infoTypeList"/> &nbsp;&nbsp;
	提报时间<input name="qryReportTime_start" value='${qryReportTime_start}' size="11" class="date"> ~
	<input name="qryReportTime_end" value='${qryReportTime_end}' size="11" class="date">
	<input type="button" value="搜索" onclick="search()">  
	<input type="button" value="搜索段内机车有活预报" onclick="searchLocal()">  
</div>
<html:pagination exportExcel="true"/>
</form>
<s:actionerror/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">序号</td>
		<td class="td_header">机车号</td>
		<td class="td_header">提报时间</td>
		<td class="td_header">是否在图上显示</td>
		<td class="td_header">故障内容</td>
		<td class="td_header">提报人</td>
		<td class="td_header">过期时间</td>
		<td class="td_header">数据提交时间</td>
		<td class="td_header">处理方式</td>
		<td class="td_header">信息类型</td>
	</tr>
	<c:forEach items="${objectList}" var="obj" varStatus="status">
		<tr>
			<td align="center" class="td_header">${status.index+pager.startRecord}</td>
			<td align="left" class='td_body'>${obj.ch}</td>
			<td align="left" class='td_body'>${obj.reportTime}</td>
			<td align="left" class='td_body'>${obj.needShowTXT}</td>
			<td align="left" class='td_body'>${obj.content}</td>
			<td align="left" class='td_body'>${obj.reporter}</td>
			<td align="left" class='td_body'>${obj.expireTime}</td>
			<td align="left" class='td_body'>${obj.sysTime}</td>
			<td align="left" class='td_body'>${obj.processType}</td>
			<td align="left" class='td_body'>${obj.infoType}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>