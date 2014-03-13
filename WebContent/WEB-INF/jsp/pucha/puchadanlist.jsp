<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">普查单</td>
    </tr>
</table>
<form id="searchForm" action="${root}/puchadan.do">
<div style="float: left; padding-bottom: 0px;">
	普查名称 <input name="qryPuchaName" value='${qryPuchaName}' size="11">
	车号 <input name="qryCh" value='${qryCh}' size="10">
	
	<input type="button" value="搜索" onclick="javascript:goPage(1)">  
	<input type="button" onclick="location.href='${root}/puchadaninput!input.do'" value="新增普查单">
</div>
<html:pagination exportExcel="false"/>
</form>
<s:actionerror/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">序号</td>
		<td class="td_header">时间</td>
		<td class="td_header">发起部门</td>
		<td class="td_header">发起人</td>
		<td class="td_header">普查名称</td>
		<!--  <td class="td_header">机车号</td> -->
		<td class="td_header">普查项目</td>
		<td class="td_header">是否完成</td>
		<td class="td_header">完成时间</td>
		<td class="td_header">操作</td>
	</tr>
	<c:forEach items="${objectList}" var="puchadan" varStatus="status">
		<tr>
			<td align="center" class="td_header">${status.index+pager.startRecord}</td>
			<td align="left" class='td_body'>${puchadan.puchaTime}</td>
			<td align="left" class='td_body'>${puchadan.faqiDept}</td>
			<td align="left" class='td_body'>${puchadan.faqiPerson}</td>
			<td align="left" class='td_body'>${puchadan.puchaName}</td>
			<!-- <td align="left" class='td_body'>${puchadan.chs}</td>  -->
			<td align="left" class='td_body'>${puchadan.items}</td>
			<td align="left" class='td_body'>${puchadan.isDoneTxt}</td>
			<td align="left" class='td_body'>${puchadan.doneTime}</td>
			<td align="left" class='td_body'>
			
			<!-- <a href="${root}/puchadaninput!input.do?puchadan.id=${puchadan.id}">编辑</a>&nbsp;&nbsp; -->
			<a href="${root}/puchadan!delete.do?puchadan.id=${puchadan.id}" onclick="return fDelCheck()">删除</a>&nbsp;&nbsp;
			<a href="${root}/puchadetail.do?puchadanId=${puchadan.id}">普查详细</a>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>