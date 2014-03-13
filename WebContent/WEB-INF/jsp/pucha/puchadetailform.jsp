<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">普查详细</td>
    </tr>
</table>			
<html:topNav/>
<s:actionerror />
<s:actionmessage />
<s:form action="puchadetail!save.do" method="post">
<s:hidden name="puchadanId" value="%{puchadanId}"/>
<s:hidden name="puchadetail.id" value="%{puchadetail.id}"/>
<s:hidden name="puchadetail.puchadanId" value="%{puchadetail.puchadanId}"/>
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
 	<tr>
        <td width="90" height="24" align="right" class="td_lable">车号</td>
        <td class="td_edit">		
			<s:textfield name="puchadetail.ch" value="%{puchadetail.ch}" size="40"/>
			<s:fielderror ><s:param>puchadetail.ch</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
        <td width="90" height="24" align="right" class="td_lable">检查项目</td>
        <td class="td_edit">		
			<s:textfield name="puchadetail.item" value="%{puchadetail.item}" size="40"/>
			<s:fielderror ><s:param>puchadetail.item</s:param></s:fielderror>
        </td>
    </tr>
	<tr>
        <td width="90" height="24" align="right" class="td_lable">是否完成</td>
        <td class="td_edit">		
			<s:select  name="puchadetail.isDone" list="isdoneList"/> 
			<s:fielderror ><s:param>puchadetail.isDone</s:param></s:fielderror> <br>
        </td>
    </tr>  
	<tr>
        <td width="90" height="24" align="right" class="td_lable">普查人</td>
        <td class="td_edit">		
			<s:textfield name="puchadetail.puchaPerson" value="%{puchadetail.puchaPerson}" size="40"/>
			<s:fielderror ><s:param>puchadetail.puchaPerson</s:param></s:fielderror> <br>
        </td>
    </tr>  
    <tr>
    	<td width="90" height="24" align="right" class="td_lable">普查时间</td>
        <td class="td_edit">		
			<s:textfield cssClass="date"  name="puchadetail.puchaTime" value="%{puchadetail.puchaTime}" size="40"/>
			<s:fielderror ><s:param>puchadetail.puchaTime</s:param></s:fielderror>
        </td>
    </tr>
	<tr>
      <td width="90" height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <s:submit value="确定" cssClass="button"/>
   		<input type="button" name="cancel" class="button" value="取消" onclick="location.href='${root}/puchadetail.do?puchadanId=${puchadanId}'"/>
      </td>
    </tr>	
</table>
</s:form>
</body>
</html>