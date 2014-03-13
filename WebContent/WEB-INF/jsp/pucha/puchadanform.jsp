<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">普查单</td>
    </tr>
</table>
<s:actionerror />
<s:actionmessage />
<s:form action="puchadaninput!save.do" method="post">
<s:hidden name="puchadan.id" value="%{puchadan.id}" />
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
	<tr>
    	<td width="90" height="24" align="right" class="td_lable">时间</td>
        <td class="td_edit">	
        		<s:textfield cssClass="date" name="puchadan.puchaTime" value="%{puchadan.puchaTime}"/>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">发起部门</td>
        <td class="td_edit">		
			<s:textfield name="puchadan.faqiDept" value="%{puchadan.faqiDept}" size="40"/>
			<s:fielderror ><s:param>puchadan.faqiDept</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">发起人</td>
        <td class="td_edit">		
			<s:textfield name="puchadan.faqiPerson" value="%{puchadan.faqiPerson}" size="40"/>
			<s:fielderror ><s:param>puchadan.faqiPerson</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">普查名称</td>
        <td class="td_edit">		
			<s:textfield name="puchadan.puchaName" value="%{puchadan.puchaName}" size="40"/>
			<s:fielderror ><s:param>puchadan.puchaName</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">机车号</td>
        <td class="td_edit">	
        	<s:select  name="puchadan.chs" list="locoList" multiple="true" size="8"/> 	
			<s:fielderror ><s:param>puchadan.chs</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">是否完成</td>
        <td class="td_edit">		
			<s:select  name="puchadan.isDone" list="isdoneList"/> 
			<s:fielderror ><s:param>puchadan.isDone</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">普查项目</td>
        <td class="td_edit">		
        	
			<s:textfield name="puchadan.items" value="%{puchadan.items}" size="40"/>
			<s:fielderror ><s:param>puchadan.items</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">完成时间</td>
        <td class="td_edit">		
			<s:textfield cssClass="date"  name="puchadan.doneTime" value="%{puchadan.doneTime}" size="40"/>
			<s:fielderror ><s:param>puchadan.doneTime</s:param></s:fielderror>
        </td>
    </tr>
	<tr>
      <td width="90" height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <s:submit value="确定"/>
   	   <s:submit value="取消" name="redirectAction:puchadan"/>
      </td>
    </tr>	
</table>
    
</s:form>
</body>
</html>