<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">防护用品</td>
    </tr>
</table>
<s:actionerror />
<s:actionmessage />
<s:form action="trialiteminput!save.do" method="post">
<s:hidden name="trialitem.id" value="%{trialitem.id}" />
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">试验名称</td>
        <td class="td_edit">		
			<s:textfield name="trialitem.trialName" value="%{trialitem.trialName}" size="40"/>
			<s:fielderror ><s:param>trialitem.trialName</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">试验内容</td>
        <td class="td_edit">		
			<s:textarea name="trialitem.trialContent" value="%{trialitem.trialContent}" cols="80" rows="5"/>
			<s:fielderror ><s:param>trialitem.trialContent</s:param></s:fielderror>
        </td>
    </tr>
	<tr>
    	<td width="90" height="24" align="right" class="td_lable">试验要求</td>
        <td class="td_edit">		
			<s:textarea name="trialitem.requirement" value="%{trialitem.requirement}" cols="80" rows="5"/>
			<s:fielderror ><s:param>trialitem.requirement</s:param></s:fielderror>
        </td>
    </tr>
    <tr>
    	<td width="90" height="24" align="right" class="td_lable">适用车型</td>
        <td class="td_edit">		
			<s:textfield name="trialitem.locomodel" value="%{trialitem.locomodel}" size="40"/>
			<s:fielderror ><s:param>trialitem.locomodel</s:param></s:fielderror>
        </td>
    </tr>
	<tr>
      <td width="90" height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <s:submit value="确定"/>
   	   <s:submit value="取消" name="redirectAction:trialitem"/>
      </td>
    </tr>	
</table>
    
</s:form>
</body>
</html>