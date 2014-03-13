<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">备用品</td>
    </tr>
</table>
<form id="searchForm" action="${root}/beipin.do">
<div style="float: left; padding-bottom: 0px;">
	备品名称 <input name="qryBeipinName" value='${qryBeipinName}' size="10">
	发放类型 	<s:select  name="qryIssueType" list="issueTypeList"/> &nbsp;&nbsp;
	适用机型 <input name="qryLocomodel" value='${qryLocomodel}' size="11">&nbsp;&nbsp;
	<input type="button" value="搜索" onclick="javascript:goPage(1)">  &nbsp;&nbsp;
	<input type="button" onclick="location.href='${root}/beipininput!input.do'" value="新增备用品">
</div>
<html:pagination exportExcel="true"/>
</form>
<s:actionerror/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">序号</td>
		<td class="td_header">备品名称</td>
		<td class="td_header">发放类型</td>
		<td class="td_header">适用机型</td>
		<td class="td_header">备注</td>
	</tr>
	<c:forEach items="${objectList}" var="beipin" varStatus="status">
		<tr>
			<td align="center" class="td_header">${status.index+pager.startRecord}</td>
			<td align="left" class='td_body'>${beipin.beipinName}</td>
			<td align="left" class='td_body'>${beipin.issueType}</td>
			<td align="left" class='td_body'>${beipin.locomodel}</td>
			<td align="left" class='td_body'>${beipin.note}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>