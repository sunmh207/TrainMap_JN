<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">普查详细</td>
    </tr>
</table>
<form id="searchForm">

<s:hidden name="puchadanId" value="%{puchadanId}" />
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
 	<tr>
        <td width="90" height="24" align="right" class="td_lable">时间</td>
        <td class="td_edit">		
			<s:label name="puchadan.puchaTime"/>
        </td>
    </tr>
 	<tr>
        <td width="90" height="24" align="right" class="td_lable">发起部门</td>
        <td class="td_edit">		
			<s:label name="puchadan.faqiDept"/>
        </td>
    </tr>
 	<tr>
        <td width="90" height="24" align="right" class="td_lable">发起人</td>
        <td class="td_edit">		
			<s:label name="puchadan.faqiPerson"/>
        </td>
    </tr>
 </table>   

	<input type="button" class="button" onclick="location.href='${root}/puchadetail!input.do?puchadanId=${puchadanId}'" value="新增普查明细">
	<input type="button" class="button" onclick="location.href='${root}/puchadan.do'" value="返回">
<html:pagination exportExcel="true"/>
</form>

<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">序号</td>
		<td class="td_header">车号</td>
		<td class="td_header">检查项目</td>
		<td class="td_header">是否完成</td>
		<td class="td_header">普查人</td>
		<td class="td_header">普查时间</td>
		<td class="td_header">操作</td>
	</tr>
	<c:forEach items="${objectList}" var="puchadetail" varStatus="status">
		<tr>
			<td align="center" class="td_header">${status.index+pager.startRecord}</td>
			<td align="left" class='td_body'>${puchadetail.ch}</td>
			<td align="left" class='td_body'>${puchadetail.item}</td>
			<td align="left" class='td_body'>${puchadetail.isDoneTXT}</td>
			<td align="left" class='td_body'>${puchadetail.puchaPerson}</td>
			<td align="left" class='td_body'>${puchadetail.puchaTime}</td>
			<td align="left" class='td_body'>
			<a href="${root}/puchadetail!input.do?puchadanId=${puchadanId}&puchadetail.id=${puchadetail.id}">编辑</a>&nbsp;&nbsp;
			<a href="${root}/puchadetail!delete.do?puchadanId=${puchadanId}&puchadetail.id=${puchadetail.id}"  onclick="return fDelCheck()">删除</a>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>