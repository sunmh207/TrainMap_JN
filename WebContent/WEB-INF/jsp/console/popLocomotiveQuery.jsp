<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%> 
<html:pageHead >
<style>
html {
	overflow-x: hidden;
	overflow-y: auto;
}
</style>
</html:pageHead>
<script type="text/javascript">

    <%
    String act=request.getParameter("act");
    if("select".equals(act)){
        String idStr= (String)request.getAttribute("idStr");
        String nameStr= (String)request.getAttribute("nameStr");
    %>    
    window.returnValue = "<%=nameStr%>||<%=idStr%>";
    window.close();
    <% } %>
</script>
<% 
	boolean single = "true".equals(request.getParameter("single"));
%>

<script type="text/javascript">
	<%String cul = request.getParameter("checkedUserList");
	  cul = cul==null?"":cul.trim();
	%>
var checkedUserList = "<%=cul%>";
function initialCheckers(){
	if(!checkedUserList)return;
	var ids = checkedUserList.split("||")[1].split(",");
	for(var i=0;i<ids.length;i++){
		var chk=$("#chk_"+ids[i]);
		if(chk.length>0){
			chk.attr("checked",true);
		}
	}
}

function saveCheck(){
	var ids = "";
	var names = "";
	//var phones ="";
	if(checkedUserList){
		var arr = checkedUserList.split("||"); 
		names = arr[0];
		ids = arr[1];
		//phones = arr[2];
	}
	$("input[name='chk']").each(function (){
		if(this.checked){
			if(ids.indexOf(this.value)>=0){
				return;
			}
			if(ids!=""){
				ids+=",";
				names+=",";
				//phones+=",";
			}
			ids += this.value;
			names +=$("#lb_"+this.value).text();			
			//phones+=$("#lbPhone_"+this.value).text();
		}
	});
	//var cul = names+"||"+ids+"||"+phones;
	var cul = names+"||"+ids;
	//alert(cul);
	$("#checkedUserList").val(cul);
}

function forSel() {
	$("#act").val("select");
	saveCheck();
	var ids = ($("#checkedUserList").val()+"").replace(/(^[\\s]*)|([\\s]*$)/g, "");
	if(ids.replace(/\|/g,"")){	
		
		window.returnValue = ids;
		if(opener)opener.selectedStr = ids;
		window.close();
	} else {
		alert("请选择机车");
		return false;
	}
	
}

function forSelectAll(){
	var frm = document.getElementById("searchForm");
	selectAll(frm.all,frm.chk);
}
</script>
<base target="_self">
<body style="overflow:hidden;" rightmargin="0px" onload="initialCheckers();">
<form id="searchForm" method="post" onsubmit="saveCheck()">
 <input type="hidden" name="act"/>
<div  style="float: left; padding:0px 20px;top:1px;position:fixed;background-color:#F3F3F3;">
	车间<s:select  name="qryCX" list="cxMap"/> 
	车号<input name="qryCH" value='${qryCH}' size="10">
	<input type="submit" value="搜索" class="button">  <input type="button" value=" 确定 " onclick="forSel()" class="button"/>

<html:pagination large="true"/>
</div>
<input type="hidden" name="checkedUserList" id="checkedUserList" value="<%=cul%>">

<br>
<br>
<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="40" align="center" class="td_header">
			<input type="checkbox" name="all" onClick="forSelectAll()">
		</td>
		<td class="td_header">机车型号</td>
		<td class="td_header">机车号</td>
		<td class="td_header">缩位编号</td>
		<td class="td_header">备注</td>
		<td class="td_header">段</td>
	</tr>
	<c:forEach items="${objectList}" var="locomotive" varStatus="status">
		<tr>
			<td align="left" class='td_body'>
				<input type="<%=single?"radio":"checkbox"%>" name="chk" value="${locomotive.id}" id="chk_${locomotive.id}" 	<%if(single)out.print("onchange='forSel()'");%>>
			</td>
			<td align="left" class='td_body'>${locomotive.locomodel}</td>
			<td align="left" class='td_body'>${locomotive.loconumber}</td>
			<td align="left" class='td_body' id="td_${locomotive.loconumber}">
				<label for="chk_${locomotive.id}" id="lb_${locomotive.id}">${locomotive.loconumber}</label>
			</td>
			
			<td align="left" class='td_body'>${locomotive.loconumberCode}</td>
			<td align="left" class='td_body'>${locomotive.didian}</td>
		</tr>
	</c:forEach>
</table>


</form>
</body>
</html>