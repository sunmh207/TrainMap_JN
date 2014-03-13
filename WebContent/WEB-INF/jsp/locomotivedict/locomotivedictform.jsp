<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">机车</td>
    </tr>
</table>
<s:actionerror />
<s:actionmessage />
<s:form action="locomotivedictinput!save.do" method="post">
<s:hidden name="locomotive.id" value="%{locomotive.id}" />
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
	<tr>
    	<td width="90" height="24" align="right" class="td_lable">机车型号</td>
        <td class="td_edit">		
			<s:textfield name="locomotive.locomodel" value="%{locomotive.locomodel}" size="40"/>
			<s:fielderror ><s:param>locomotive.locomodel</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">机车号</td>
        <td class="td_edit">		
			<s:textfield name="locomotive.loconumber" value="%{locomotive.loconumber}" size="40"/>
			<s:fielderror ><s:param>locomotive.loconumber</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">缩位编号</td>
        <td class="td_edit">		
			<s:textfield name="locomotive.loconumberCode" value="%{locomotive.loconumberCode}" size="40"/>
			<s:fielderror ><s:param>locomotive.loconumberCode</s:param></s:fielderror>
        </td>
    </tr>
    <tr>
    	<td width="90" height="24" align="right" class="td_lable">段</td>
        <td class="td_edit">		
			<s:select  name="locomotive.didian" list="didianList"/> 
			<s:fielderror ><s:param>locomotive.didian</s:param></s:fielderror>
        </td>
	<tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">备注</td>
        <td class="td_edit">		
			<s:textarea name="locomotive.other" value="%{locomotive.other}" cols="80" rows="5"/>
			<s:fielderror ><s:param>locomotive.other</s:param></s:fielderror>
        </td>
    </tr>
 	
	<tr>
      <td width="90" height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <s:submit value="确定"/>
   	   <s:submit value="取消" name="redirectAction:locomotivedict"/>
      </td>
    </tr>	
</table>
    
</s:form>
</body>
</html>