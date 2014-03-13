<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">机车状态</td>
    </tr>
</table>
<form id="searchForm" action="${root}/jichezhuangtai.do">
<div style="float: left; padding-bottom: 0px;">
	车号 <input name="qryCh" value='${qryCh}' size="10">
	类型 <input name="qryType" value='${qryType}' size="11">
	<input type="button" value="搜索" onclick="javascript:goPage(1)">  
	<input type="button" onclick="location.href='${root}/jichezhuangtaiinput!input.do'" value="新增机车信息">
</div>
<html:pagination exportExcel="false"/>
</form>
<s:actionerror/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">序号</td>
		<td class="td_header">机车号</td>
		<td class="td_header">分类</td>
		<td class="td_header">状态内容</td>
		<td class="td_header">提交人</td>
		<td class="td_header">提交时间</td>
		<td class="td_header">提交部门</td>
		<td class="td_header">是否最新</td>
		<td class="td_header">操作</td>
	</tr>
	<c:forEach items="${objectList}" var="jichezhuangtai" varStatus="status">
		<tr>
			<td align="center" class="td_header">${status.index+pager.startRecord}</td>
			<td align="left" class='td_body'>${jichezhuangtai.ch}</td>
			<td align="left" class='td_body'>${jichezhuangtai.type}</td>
			<td align="left" class='td_body'>${jichezhuangtai.content}</td>
			<td align="left" class='td_body'>${jichezhuangtai.submitPerson}</td>
			<td align="left" class='td_body'>${jichezhuangtai.submitTime}</td>
			<td align="left" class='td_body'>${jichezhuangtai.submitDept}</td>
			<td align="left" class='td_body'>${jichezhuangtai.isLastTxt}</td>
			<td align="left" class='td_body'>
			
			<a href="${root}/jichezhuangtaiinput!input.do?jichezhuangtai.id=${jichezhuangtai.id}">编辑</a>&nbsp;&nbsp;
			<a href="${root}/jichezhuangtai!delete.do?jichezhuangtai.id=${jichezhuangtai.id}" onclick="return fDelCheck()">删除</a>&nbsp;&nbsp;
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>