<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">试验项目</td>
    </tr>
</table>
<form id="searchForm" action="${root}/trialreport.do">
<div style="float: left; padding-bottom: 0px;">
	实验名称<input name="qryTrialName" value='${qryTrialName}' size="10">
	适用车型<input name="qryLocomodel" value='${qryLocomodel}' size="11">
	<input type="button" value="搜索" onclick="javascript:goPage(1)">  
	<input type="button" onclick="location.href='${root}/trialreportinput!input.do'" value="新增试验项目">
</div>
<html:pagination exportExcel="false"/>
</form>
<s:actionerror/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">序号</td>
		<td class="td_header">试验日期</td>
		<td class="td_header">车号</td>
		<td class="td_header">试验人员</td>
		<td class="td_header">试验名称</td>
		<td class="td_header">试验内容</td>
		<td class="td_header">试验要求</td>
		<td class="td_header">适用车型</td>
		<td class="td_header">操作</td>
	</tr>
	<c:forEach items="${objectList}" var="trialreport" varStatus="status">
		<tr>
			<td align="center" class="td_header">${status.index+pager.startRecord}</td>
			<td align="left" class='td_body'>${trialreport.trialDate}</td>
			<td align="left" class='td_body'>${trialreport.ch}</td>
			<td align="left" class='td_body'>${trialreport.person}</td>
			<td align="left" class='td_body'>${trialreport.trialName}</td>
			<td align="left" class='td_body'>${trialreport.trialContent}</td>
			<td align="left" class='td_body'>${trialreport.requirement}</td>
			<td align="left" class='td_body'>${trialreport.locomodel}</td>
			<td align="left" class='td_body'>
			<a href="${root}/trialreportinput!input.do?trialreportId=${trialreport.id}">编辑</a>&nbsp;&nbsp;
			<a href="${root}/trialreport!delete.do?trialreportId=${trialreport.id}" onclick="return fDelCheck()">删除</a>&nbsp;&nbsp;
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>