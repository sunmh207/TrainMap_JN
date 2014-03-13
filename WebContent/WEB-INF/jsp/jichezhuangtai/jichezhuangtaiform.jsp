<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td class="page_title">机车状态</td>
    </tr>
</table>
<s:actionerror />
<s:actionmessage />
<s:form action="jichezhuangtaiinput!save.do" method="post">
<s:hidden name="jichezhuangtai.id" value="%{jichezhuangtai.id}" />
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">车号</td>
        <td class="td_edit">		
			<s:textfield name="jichezhuangtai.ch" value="%{jichezhuangtai.ch}" size="40"/>
			<s:fielderror ><s:param>jichezhuangtai.ch</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">分类</td>
        <td class="td_edit">		
			<s:textfield name="jichezhuangtai.type" value="%{jichezhuangtai.type}" size="40"/>
			<s:fielderror ><s:param>jichezhuangtai.type</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">内容</td>
        <td class="td_edit">		
			<s:textarea name="jichezhuangtai.content" value="%{jichezhuangtai.content}"  rows="8" cols="100"/> 
			<s:fielderror ><s:param>jichezhuangtai.content</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">提交人</td>
        <td class="td_edit">		
        	
			<s:textfield name="jichezhuangtai.submitPerson" value="%{jichezhuangtai.submitPerson}" size="40"/>
			<s:fielderror ><s:param>jichezhuangtai.submitPerson</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">提报部门</td>
        <td class="td_edit">		
			<s:textfield name="jichezhuangtai.submitDept" value="%{jichezhuangtai.submitDept}" size="40"/>
			<s:fielderror ><s:param>jichezhuangtai.submitDept</s:param></s:fielderror>
        </td>
    </tr>
 	<tr>
    	<td width="90" height="24" align="right" class="td_lable">提交时间</td>
        <td class="td_edit">	
        	<s:label name="jichezhuangtai.submitTime"/>	
        </td>
    </tr>
	<tr>
    	<td width="90" height="24" align="right" class="td_lable">是否最新</td>
        <td class="td_edit">		
			<s:select  name="jichezhuangtai.isLast" list="isLastList"/> 
			<s:fielderror ><s:param>jichezhuangtai.isLast</s:param></s:fielderror>
        </td>
	<tr>
      <td width="90" height="24" align="right" class="td_lable"></td>
      <td class="td_edit">	
       <s:submit value="确定"/>
   	   <s:submit value="取消" name="redirectAction:jichezhuangtai"/>
      </td>
    </tr>	
</table>
    
</s:form>
</body>
</html>