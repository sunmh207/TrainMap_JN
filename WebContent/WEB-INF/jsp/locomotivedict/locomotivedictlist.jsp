<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">机车</td>
    </tr>
</table>
<form id="searchForm" action="${root}/locomotivedict.do">
<div style="float: left; padding-bottom: 0px;">
	车号 <input name="qryCh" value='${qryCh}' size="10">
	<input type="button" value="搜索" onclick="javascript:goPage(1)">  
	<input type="button" onclick="location.href='${root}/locomotivedictinput!input.do'" value="新增机车">
</div>
<html:pagination exportExcel="true"/>
</form>
<s:actionerror/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">序号</td>
		<td class="td_header">机车型号</td>
		<td class="td_header">机车号</td>
		<td class="td_header">缩位编号</td>
		<td class="td_header">备注</td>
		<td class="td_header">段</td>
		<td class="td_header">操作</td>
	</tr>
	<c:forEach items="${objectList}" var="locomotive" varStatus="status">
		<tr>
			<td align="center" class="td_header">${status.index+pager.startRecord}</td>
			<td align="left" class='td_body'>${locomotive.locomodel}</td>
			<td align="left" class='td_body'>${locomotive.loconumber}</td>
			<td align="left" class='td_body'>${locomotive.loconumberCode}</td>
			<td align="left" class='td_body'>${locomotive.other}</td>
			<td align="left" class='td_body'>${locomotive.didian}</td>
			<td align="left" class='td_body'>
			<a href="${root}/locomotivedictinput!input.do?locomotiveId=${locomotive.id}">编辑</a>&nbsp;&nbsp;
			<a href="${root}/locomotivedict!delete.do?locomotiveId=${locomotive.id}" onclick="return fDelCheck()">删除</a>&nbsp;&nbsp;
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>