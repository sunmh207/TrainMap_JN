<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">备用品</td>
    </tr>
</table>
<form id="searchForm" action="${root}/beipinrelease.do">
<div style="float: left; padding-bottom: 0px;">
	备品名称 <input name="qryBeipinName" value='${qryBeipinName}' size="10">
	机车号 <input name="qryCh" value='${qryCh}' size="11">&nbsp;&nbsp;
	领取人 <input name="qryGetPerson" value='${qryGetPerson}' size="11">&nbsp;&nbsp;
	领取时间<input name="qryGetTime_start" value='${qryGetTime_start}' size="11" class="date"> ~
	<input name="qryGetTime_end" value='${qryGetTime_end}' size="11" class="date">
	<input type="button" value="搜索" onclick="javascript:goPage(1)">  
</div>
<html:pagination exportExcel="true"/>
</form>
<s:actionerror/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">序号</td>
		<td class="td_header">领取时间</td>
		<td class="td_header">领取人</td>
		<td class="td_header">机车号</td>
		<td class="td_header">备品名称</td>
		<td class="td_header">接收人</td>
		<td class="td_header">接收时间</td>
		<td class="td_header">是否良好</td>
		<td class="td_header">交还人</td>
		<td class="td_header">交还时间</td>
		<td class="td_header">发放类型</td>
		<td class="td_header">单据添写</td>
		<td class="td_header">完成</td>
		<td class="td_header">领取方式</td>
		<!-- <td class="td_header">批次号</td> -->
		<td class="td_header">领取人编号</td>
		<td class="td_header">交还人编号</td>
		<td class="td_header">备注</td>
	</tr>
	<c:forEach items="${objectList}" var="beipin" varStatus="status">
		<tr>
			<td align="center" class="td_header">${status.index+pager.startRecord}</td>
			<td align="left" class='td_body'>${beipin.getTime}</td>
			<td align="left" class='td_body'>${beipin.getPerson}</td>
			<td align="left" class='td_body'>${beipin.ch}</td>
			<td align="left" class='td_body'>${beipin.beipinName}</td>
			<td align="left" class='td_body'>${beipin.recipient}</td>
			<td align="left" class='td_body'>${beipin.receiveTime}</td>
			<td align="left" class='td_body'>${beipin.isOKTXT}</td>
			<td align="left" class='td_body'>${beipin.returnPerson}</td>
			<td align="left" class='td_body'>${beipin.returnTime}</td>
			<td align="left" class='td_body'>${beipin.issueType}</td>
			<td align="left" class='td_body'>${beipin.hasReceiptsTXT}</td>
			<td align="left" class='td_body'>${beipin.isDone}</td>
			<td align="left" class='td_body'>${beipin.getMode}</td>
			<!-- <td align="left" class='td_body'>${beipin.batch}</td>-->
			<td align="left" class='td_body'>${beipin.getPersoncode}</td>
			<td align="left" class='td_body'>${beipin.returnPersoncode}</td>
			<td align="left" class='td_body'>${beipin.note}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>