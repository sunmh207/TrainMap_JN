<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">整备试验单</td>
    </tr>
</table>
<s:actionerror />
<s:actionmessage />
<s:form action="trialreportinput!save.do" method="post">
<s:hidden name="trialreport.id" value="%{trialreport.id}" />
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">试验日期</td>
        <td class="td_edit">		
			<s:textfield cssClass="date" name="trialreport.trialDate" value="%{trialreport.trialDate}" size="40"/>
			<s:fielderror ><s:param>trialreport.trialDate</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">机车号</td>
        <td class="td_edit">		
			<s:textfield name="trialreport.ch" value="%{trialreport.ch}" size="40"/>
			<s:fielderror ><s:param>trialreport.ch</s:param></s:fielderror>
        </td>
    </tr>
	<tr>
    	<td width="90" height="24" align="right" class="td_lable">试验人员</td>
        <td class="td_edit">		
			<s:textfield name="trialreport.person" value="%{trialreport.person}" size="40"/>
			<s:fielderror ><s:param>trialreport.person</s:param></s:fielderror>
        </td>
    </tr>
    <tr>
    	<td width="90" height="24" align="right" class="td_lable">实验项目</td>
        <td class="td_edit">		
			<s:textarea name="trialreport.person" value="%{trialreport.person}" cols="80" rows="5"/>
			<s:fielderror ><s:param>trialreport.locomodel</s:param></s:fielderror>
        </td>
    </tr>
	<tr>
      <td width="90" height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <s:submit value="确定"/>
   	   <s:submit value="取消" name="redirectAction:trialreport"/>
      </td>
    </tr>	
</table>
    
</s:form>
</body>
</html>