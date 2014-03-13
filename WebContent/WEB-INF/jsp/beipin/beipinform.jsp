<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">备用品字典</td>
    </tr>
</table>
<s:actionerror />
<s:actionmessage />
<s:form action="beipininput!save.do" method="post">
<s:hidden name="beipin.id" value="%{beipin.id}" />
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
	<tr>
    	<td width="90" height="24" align="right" class="td_lable">备品名称</td>
        <td class="td_edit">		
			<s:textfield name="beipin.beipinName" value="%{beipin.beipinName}" size="40"/> 
			<s:fielderror ><s:param>beipin.beipinName</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">发放类型</td>
        <td class="td_edit">		
			<s:select  name="beipin.issueType" list="issueTypeList"/> 
			<s:fielderror ><s:param>beipin.issueType</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">适用车型</td>
        <td class="td_edit">		
			<s:textfield name="beipin.locomodel" value="%{beipin.locomodel}" size="40"/>
			<s:fielderror ><s:param>beipin.locomodel</s:param></s:fielderror>
        </td>
    </tr>
 		<tr>
    	<td width="90" height="24" align="right" class="td_lable">备注</td>
        <td class="td_edit">		
			<s:textarea name="beipin.note" value="%{beipin.note}" cols="80" rows="5"/>
			<s:fielderror ><s:param>beipin.note</s:param></s:fielderror>
        </td>
    </tr>
	<tr>
      <td width="90" height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <s:submit value="确定"/>
   	   <s:submit value="取消" name="redirectAction:beipin"/>
      </td>
    </tr>	
</table>
    
</s:form>
</body>
</html>