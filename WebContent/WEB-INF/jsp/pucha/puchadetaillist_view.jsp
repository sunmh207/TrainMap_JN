<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<script type="text/javascript">
function fDoneCheck(){
	return window.confirm("确定已经完成？"); 
} 
</script>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">普查详情</td>
    </tr>
</table>
<form id="searchForm" action="${root}/puchadetail_view.do">
<div style="float: left; padding-bottom: 0px;">
	普查名称 <input name="qryPuchaName" value='${qryPuchaName}' size="11">
	车号 <input name="qryCh" value='${qryCh}' size="10">
	是否完成<select name="qryIsDone">
			<option value="">全部</option>
			<option value="0">否</option>
			<option value="1">是</option>
		</select>
	<input type="button" value="搜索" onclick="javascript:goPage(1)">  
</div>
<html:pagination exportExcel="true"/>
</form>
<s:actionerror/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">序号</td>
		<td class="td_header">时间</td>
		<td class="td_header">发起部门</td>
		<td class="td_header">发起人</td>
		<td class="td_header">普查名称</td>
		<td class="td_header">普查项目</td>

		<td class="td_header">车号</td>
		<td class="td_header">检查项目</td>
		<td class="td_header">是否完成</td>
		<td class="td_header">普查人</td>
		<td class="td_header">普查时间</td>
	</tr>
	<c:forEach items="${objectList}" var="puchadetailv" varStatus="status">
		<tr>
			<td align="center" class="td_header">${status.index+pager.startRecord}</td>
			<td align="left" class='td_body'>${puchadetailv.puchadan_puchaTime}</td>
			<td align="left" class='td_body'>${puchadetailv.faqiDept}</td>
			<td align="left" class='td_body'>${puchadetailv.faqiPerson}</td>
			<td align="left" class='td_body'>${puchadetailv.puchaName}</td>
			<td align="left" class='td_body'>${puchadetailv.items}</td>

			<td align="left" class='td_body'>${puchadetailv.ch}</td>
			<td align="left" class='td_body'>${puchadetailv.item}</td>
			<td align="left" class='td_body'>${puchadetailv.puchadetail_isDoneTXT}</td>
			<td align="left" class='td_body'>${puchadetailv.puchaPerson}</td>
			<td align="left" class='td_body'>${puchadetailv.puchadetail_puchaTime}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>