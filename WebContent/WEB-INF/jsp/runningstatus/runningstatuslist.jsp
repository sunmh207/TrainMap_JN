<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">机车运行状态</td>
    </tr>
</table>
<form id="searchForm" action="${root}/runningstatus.do">
<div style="float: left; padding-bottom: 0px;">
	车次 <input name="qryTrain" value='${qryTrain}' size="10">
	机车号 <input name="qryLocomotive" value='${qryLocomotive}' size="11">
	司机编号<input name="qryDrivercode" value='${qryDrivercode}' size="11">
	司机姓名<input name="qryDrivername" value='${qryDrivername}' size="11">
	站点<input name="qrySite" value='${qrySite}' size="11">
	开始日期 <input name="qryStatustimer_start" value='${qryStatustimer_start}' size="11"  class='date'>
	结束日期 <input name="qryStatustimer_end" value='${qryStatustimer_end}' size="11"  class='date'>
	<input type="button" value="搜索" onclick="javascript:goPage(1)"> 
</div>
<html:pagination exportExcel="true"/>
</form>
<s:actionerror/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">序号</td>
		<td class="td_header">车次</td>
		<td class="td_header">机车号</td>
		<td class="td_header">	司机编号</td>
		<td class="td_header">司机姓名</td>
		<td class="td_header">副司机编号</td>
		<td class="td_header">副司机姓名</td>
		<td class="td_header">运行状态</td>
		<td class="td_header">运行状态时间</td>
		<td class="td_header">地点</td>
		<td class="td_header">备注</td>
	</tr>
	<c:forEach items="${objectList}" var="obj" varStatus="status">
		<tr>
			<td align="center" class="td_header">${status.index+pager.startRecord}</td>
			<td align="left" class='td_body'>${obj.train}</td>
			<td align="left" class='td_body'>${obj.locomotive}</td>
			<td align="left" class='td_body'>${obj.drivercode}</td>
			<td align="left" class='td_body'>${obj.drivername}</td>
			<td align="left" class='td_body'>${obj.vicedrivercode}</td>
			<td align="left" class='td_body'>${obj.vicedrivername}</td>
			<td align="left" class='td_body'>${obj.status}</td>
			<td align="left" class='td_body'>${obj.statustimer}</td>
			<td align="left" class='td_body'>${obj.site}</td>
			<td align="left" class='td_body'>${obj.other}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>