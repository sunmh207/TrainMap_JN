<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">有活预报</td>
    </tr>
</table>
<s:actionerror />
<s:actionmessage />
<s:form action="youhuoyubaoinput!save.do" method="post">
<s:hidden name="youhuoyubao.id" value="%{youhuoyubao.id}" />
<s:hidden name="youhuoyubao.infoType" value="%{youhuoyubao.infoType}" />
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
	<tr>
    	<td width="90" height="24" align="right" class="td_lable">机车号</td>
        <td class="td_edit">		
			<s:select  name="youhuoyubao.ch" list="locoList"/> 
			<s:fielderror ><s:param>youhuoyubao.ch</s:param></s:fielderror>
        </td>
    </tr>
	<tr>
    	<td width="90" height="24" align="right" class="td_lable">提报时间</td>
        <td class="td_edit">	
        		<s:textfield cssClass="date" name="youhuoyubao.reportTime" value="%{youhuoyubao.reportTime}"/>
        </td>
    </tr>
    <tr>
    	<td width="90" height="24" align="right" class="td_lable">是否在图上显示</td>
        <td class="td_edit">		
			<s:select  name="youhuoyubao.needShow" list="needShowMap"/> 
			<s:fielderror ><s:param>youhuoyubao.needShow</s:param></s:fielderror>
        </td>
    </tr>
    <tr>
    	<td width="90" height="24" align="right" class="td_lable">故障内容</td>
        <td class="td_edit">		
			<s:textarea name="youhuoyubao.content" value="%{youhuoyubao.content}" rows="8" cols="100"/>
			<s:fielderror ><s:param>youhuoyubao.ch</s:param></s:fielderror>
        </td>
    </tr>
    
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">提交人</td>
        <td class="td_edit">		
			<s:textfield name="youhuoyubao.reporter" value="%{youhuoyubao.reporter}" size="40"/>
			<s:fielderror ><s:param>youhuoyubao.reporter</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">过期时间</td>
        <td class="td_edit">	
        		<s:textfield cssClass="date" name="youhuoyubao.expireTime" value="%{youhuoyubao.expireTime}"/>
        </td>
    </tr>
    <tr>
    	<td width="90" height="24" align="right" class="td_lable">处理方式</td>
        <td class="td_edit">		
			<s:textfield name="youhuoyubao.processType" value="%{youhuoyubao.processType}" size="40"/>
			<s:fielderror ><s:param>youhuoyubao.processType</s:param></s:fielderror>
        </td>
    </tr>
	<tr>
      <td width="90" height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <s:submit value="确定"/>
   	   <s:submit value="取消" name="redirectAction:youhuoyubao"/>
      </td>
    </tr>	
</table>
    
</s:form>
</body>
</html>