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
<s:form action="fanghuyongpininput!save.do" method="post">
<s:hidden name="fanghuyongpin.id" value="%{fanghuyongpin.id}" />
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
	<tr>
    	<td width="90" height="24" align="right" class="td_lable">检验日期</td>
        <td class="td_edit">		
			<s:textfield cssClass="date" name="fanghuyongpin.issueDate" value="%{fanghuyongpin.issueDate}" size="40"/>
			<s:fielderror ><s:param>fanghuyongpin.issueDate</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">车号</td>
        <td class="td_edit">		
			<s:select  name="fanghuyongpin.ch" list="locoList" /> 
			<s:fielderror ><s:param>fanghuyongpin.ch</s:param></s:fielderror>
			
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">用品名称</td>
        <td class="td_edit">		
			<s:select name="fanghuyongpin.ypName" list="%{ypMap}"/>
			<s:fielderror ><s:param>fanghuyongpin.ypName</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">数量</td>
        <td class="td_edit">		
			<s:textfield name="fanghuyongpin.quantity" value="%{fanghuyongpin.quantity}" size="40"/>
			<s:fielderror ><s:param>fanghuyongpin.quantity</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">上车时间</td>
        <td class="td_edit">		
			<s:textfield cssClass="date" name="fanghuyongpin.setupTime" value="%{fanghuyongpin.setupTime}" size="40"/>
			<s:fielderror ><s:param>fanghuyongpin.setupTime</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">到期日期</td>
        <td class="td_edit">		
			<s:textfield cssClass="date" name="fanghuyongpin.changeTime" value="%{fanghuyongpin.changeTime}"/> 
			<s:fielderror ><s:param>fanghuyongpin.changeTime</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">更换人</td>
        <td class="td_edit">		
			<s:textfield name="fanghuyongpin.changePerson" value="%{fanghuyongpin.changePerson}" size="40"/>
			<s:fielderror ><s:param>fanghuyongpin.changePerson</s:param></s:fielderror>
        </td>
    </tr>
	<tr>
    	<td width="90" height="24" align="right" class="td_lable">备注</td>
        <td class="td_edit">		
			<s:textarea name="fanghuyongpin.note" value="%{fanghuyongpin.note}" cols="80" rows="5"/>
			<s:fielderror ><s:param>fanghuyongpin.note</s:param></s:fielderror>
        </td>
    </tr>
    <tr>
    	<td width="90" height="24" align="right" class="td_lable">是否更换</td>
        <td class="td_edit">		
			<s:select name="fanghuyongpin.isChanged" list="%{isChangedMap}"/>
			<s:fielderror ><s:param>fanghuyongpin.isChanged</s:param></s:fielderror>
        </td>
    </tr>
	<tr>
      <td width="90" height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <s:submit value="确定"/>
   	   <s:submit value="取消" name="redirectAction:fanghuyongpin"/>
      </td>
    </tr>	
</table>
    
</s:form>
</body>
</html>