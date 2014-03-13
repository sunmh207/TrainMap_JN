<%@ tag pageEncoding="utf-8" %>
<%@tag import="java.util.Set"%>
<%@tag import="com.jitong.common.util.SysCache"%>
<%@tag import="java.util.Map"%>
<%@ attribute name="nameUnit" type="java.lang.String" required="true"%>
<%@ attribute name="nameDept" type="java.lang.String" required="true"%>

<script type="text/javascript">
var unitValue = "<%=request.getParameter(nameUnit)%>";
var deptValue = "<%=request.getParameter(nameDept)%>";
<%
	String idUnit = nameUnit.replace('.','_');
	String idDept = nameDept.replace('.','_');
{
	Map<String, Set<String>>  unitDeptMap = SysCache.unitDeptMap;
	out.print("var depts = {");
	boolean fisrtUnit = true;
	for (Map.Entry<String, Set<String>> entry : unitDeptMap.entrySet()) {
		out.print(fisrtUnit?"\n":",\n");
		String unit = entry.getKey();
		out.print("\t\""+unit+"\":[");
		boolean first = true;
		for (String dept : entry.getValue()) {
			if(!first)out.print(",");
			out.print("\""+dept+"\"");
			first = false;
		}
		out.print("]");
		fisrtUnit = false;
	}
	out.print("\n};");
}%>

function changeDept(){
	var unitSelected = $('#<%=idUnit%> option:selected').val();
	var deptSelect = $("#<%=idDept%>");
	var deptsOfUnit = depts[unitSelected];
	deptSelect.html("");
	deptSelect.append(
		$("<option value=''>--部门--</option>")
	);
	for(var i=0;deptsOfUnit&&i<deptsOfUnit.length;i++){
		deptSelect.append(
				$("<option value=''></option>").val(deptsOfUnit[i]).text(deptsOfUnit[i])
		);
	}		
}
</script>
<select name="<%=nameUnit%>" id="<%=idUnit%>" onchange="changeDept();">
	<option value="">-- 单位 --</option>
	<%{
		Map<String, Set<String>>  unitDeptMap = SysCache.unitDeptMap;
		for (Map.Entry<String, Set<String>> entry : unitDeptMap.entrySet()) {
			String unit = entry.getKey();
			out.print("<option value=\""+unit+"\">"+unit+"</option>\n");
		} 
	}%>
</select> 
<select name="<%=nameDept%>" id="<%=idDept%>">
	<option value="">-- 部门 --</option>
</select> 
<script>
	if(unitValue){
		$("#<%=idUnit%> option[value='"+unitValue+"']").attr("selected", true);  
		changeDept();
		$("#<%=idDept%> option[value='"+deptValue+"']").attr("selected", true); 
	}
</script>