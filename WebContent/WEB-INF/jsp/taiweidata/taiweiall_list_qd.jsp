<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%> 
<html:pageHead />
<body>
<html:topNav/>
<form id="searchForm" action="${root}/taiweialldata_qd.do">
 <div style="float: left; padding-bottom: 0px;">
	台位 <input name="qryGDName" value='${qryGDName}' size="10">
	机车号 <input name="qryCH" value='${qryCH}' size="10"> 
	<input type="button" value="搜索" onclick="javascript:goPage(1)">  
</div> 
<html:pagination exportExcel="false"/>
</form>
<s:actionerror/>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">序号</td>
		<td class="td_header">台位</td>
		<td class="td_header">机车号</td>
		<td class="td_header">最后更新时间</td>
		<td class="td_header">地点</td>
		<td class="td_header">操作</td>
	</tr>
		<s:iterator value="objectList" status="status">
		<tr>
			<td align="center" class="td_header">${status.index+pager.startRecord}</td>
			<td align="left" class='td_body'><s:property value="gdName"/></td>
			<td align="left" class='td_body'><s:property value="currentCH"/></td>
			<td align="left" class='td_body'><s:property value="lastUpdate"/></td>
			<td align="left" class='td_body'><s:property value="didian"/></td>
			<td align="left" class='td_body'>
			<s:if test="currentCH==null">
				<a href=${root}/taiweialldatainput_qd!input.do?gdName=<s:property value="gdName"/>&CH=<s:property value="currentCH"/>>+添加机车</a>
			</s:if>
			<s:else>
				<a href=${root}/taiweialldata_qd!deleteLoco.do?gdName=<s:property value="gdName"/>&CH=<s:property value="currentCH"/> onclick="return fDelCheck()">-移除机车</a>&nbsp;&nbsp;
				<a href=${root}/taiweialldatainput_qd!forMove.do?gdName=<s:property value="gdName"/>&CH=<s:property value="currentCH"/> >->移动机车</a>
			</s:else>
			</td>
		</tr>
	</s:iterator>
</table>
</body>
</html>