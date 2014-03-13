
<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">防护用品</td>
    </tr>
</table>
<form id="searchForm" action="${root}/fanghuyongpin_view.do">
<div style="float: left; padding-bottom: 0px;">
	车号 <input name="qryCh" value='${qryCh}' size="10">
	用品名称 <input name="qryYpName" value='${qryYpName}' size="11">&nbsp;&nbsp;
	更换时间<input name="qryChangeTime_start" value='${qryChangeTime_start}' size="11" class="date"> ~
	<input name="qryChangeTime_end" value='${qryChangeTime_end}' size="11" class="date">
	<input type="button" value="搜索" onclick="javascript:goPage(1)">  
</div>
<html:pagination exportExcel="true"/>
</form>
<s:actionerror/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">序号</td>
		<td class="td_header">检验日期</td>
		<td class="td_header">机车号</td>
		<td class="td_header">用品名称</td>
		<td class="td_header">数量</td>
		<td class="td_header">上车时间</td>
		<td class="td_header">到期日期</td>
		<td class="td_header">是否更换</td>
		<td class="td_header">更换人</td>
		<td class="td_header">备注</td>
	</tr>
	<c:forEach items="${objectList}" var="fanghuyongpin" varStatus="status">
		<tr>
			<td align="center" class="td_header">${status.index+pager.startRecord}</td>
			<td align="left" class='td_body'>${fanghuyongpin.issueDate}</td>
			<td align="left" class='td_body'>${fanghuyongpin.ch}</td>
			<td align="left" class='td_body'>${fanghuyongpin.ypName}</td>
			<td align="left" class='td_body'>${fanghuyongpin.quantity}</td>
			<td align="left" class='td_body'>${fanghuyongpin.setupTime}</td>
			<td align="left" class='td_body'>${fanghuyongpin.changeTime}</td>
			<td align="left" class='td_body'>${fanghuyongpin.isChanged}</td>
			<td align="left" class='td_body'>${fanghuyongpin.changePerson}</td>
			<td align="left" class='td_body'>${fanghuyongpin.note}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>