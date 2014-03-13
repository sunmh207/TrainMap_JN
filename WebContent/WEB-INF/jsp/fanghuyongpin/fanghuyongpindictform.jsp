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
<s:form action="fanghuyongpindictinput!save.do" method="post">
<s:hidden name="fanghuyongpindict.id" value="%{fanghuyongpindict.id}" />
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
	
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">用品名称</td>
        <td class="td_edit">		
			<s:textfield name="fanghuyongpindict.ypName" value="%{fanghuyongpindict.ypName}" size="40"/>
			<s:fielderror ><s:param>fanghuyongpindict.ypName</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">期限</td>
        <td class="td_edit">		
			<s:textfield name="fanghuyongpindict.duration" value="%{fanghuyongpindict.duration}" size="40"/>天
			<s:fielderror ><s:param>fanghuyongpindict.duration</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">适用机型</td>
        <td class="td_edit">		
			<s:select name="fanghuyongpindict.locomodel" list="%{locomodelList}"/>
			<s:fielderror ><s:param>fanghuyongpindict.locomodel</s:param></s:fielderror>
        </td>
    </tr>
	<tr>
    	<td width="90" height="24" align="right" class="td_lable">备注</td>
        <td class="td_edit">		
			<s:textarea name="fanghuyongpindict.note" value="%{fanghuyongpindict.note}" cols="80" rows="5"/>
			<s:fielderror ><s:param>fanghuyongpindict.note</s:param></s:fielderror>
        </td>
    </tr>
    
	<tr>
      <td width="90" height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <s:submit value="确定"/>
   	   <s:submit value="取消" name="redirectAction:fanghuyongpindict"/>
      </td>
    </tr>	
</table>
    
</s:form>
</body>
</html>