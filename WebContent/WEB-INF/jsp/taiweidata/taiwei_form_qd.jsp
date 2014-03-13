<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<% String idValue=(String)request.getAttribute("locomotiveId"); %>
<% String nameValue=(String)request.getAttribute("CH"); %>
<html:pageHead />
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">台位-机车 编辑</td>
    </tr>
</table>
<s:actionerror />
<s:actionmessage />
<s:form action="taiweialldatainput_qd!save.do" method="post">
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
	<tr>
    	<td width="90" height="24" align="right" class="td_lable">台位</td>
        <td class="td_edit">		
			<s:property  value="%{gdName}"/>
			<s:hidden name="gdName"  value="%{gdName}"/>
			<s:fielderror ><s:param>gdName</s:param></s:fielderror>
        </td>
    </tr>
	<tr>
    	<td width="90" height="24" align="right" class="td_lable">机车号</td>
        <td class="td_edit">		
			<%-- <s:select  name="CH" list="locoWithoutTaiweiMap"/>  --%>
			<html:locomotivePicker idName="locomotiveId" nameName="CH" idValue="<%=idValue%>" nameValue="<%=nameValue %>"/>
			<s:fielderror ><s:param>CH</s:param></s:fielderror>
        </td>
    </tr>
	
	<tr>
      <td width="90" height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <s:submit value="确定"/>
   	   <s:submit value="取消" name="redirectAction:taiweialldata_qd"/>
      </td>
    </tr>	
</table>
    
</s:form>
</body>
</html>