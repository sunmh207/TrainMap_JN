<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">防护用品字典</td>
    </tr>
</table>
<form id="searchForm" action="${root}/fanghuyongpindict.do">
<div style="float: left; padding-bottom: 0px;">
	用品名称 <input name="qryYpName" value='${qryYpName}' size="11">&nbsp;&nbsp;
	<input type="button" value="搜索" onclick="javascript:goPage(1)">  
	<input type="button" onclick="location.href='${root}/fanghuyongpindictinput!input.do'" value="新增防护用品">
</div>
<html:pagination exportExcel="true"/>
</form>
<s:actionerror/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">序号</td>
		<td class="td_header">用品名称</td>
		<td class="td_header">期限</td>
		<td class="td_header">适用车型</td>
		<td class="td_header">备注</td>
		<td class="td_header">操作</td>
	</tr>
	<c:forEach items="${objectList}" var="fanghuyongpindict" varStatus="status">
		<tr>
			<td align="center" class="td_header">${status.index+pager.startRecord}</td>
			<td align="left" class='td_body'>${fanghuyongpindict.ypName}</td>
			<td align="left" class='td_body'>${fanghuyongpindict.duration}</td>
			<td align="left" class='td_body'>${fanghuyongpindict.locomodel}</td>
			<td align="left" class='td_body'>${fanghuyongpindict.note}</td>
			<td align="left" class='td_body'>
			<a href="${root}/fanghuyongpindictinput!input.do?fanghuyongpindictId=${fanghuyongpindict.id}">编辑</a>&nbsp;&nbsp;
			<a href="${root}/fanghuyongpindict!delete.do?fanghuyongpindictId=${fanghuyongpindict.id}" onclick="return fDelCheck()">删除</a>&nbsp;&nbsp;
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>