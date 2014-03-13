<%@ page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
<title>底部文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	font-weight:bold;
	font-size:13px;
	font-family:'新宋体';
	color:#CCCCCC;
}
a{
	color:#CCCCCC;
	text-decoration:none;
	font-size:13px;
} 
a:hover{color:#ff6600;}

-->
</style></head>
<SCRIPT language=javascript type=text/javascript> 
function showOnMain(url){
		parent.mainFrame.location.href=url; 
}
</script>
</head>

<body style="background-image:url('${root}/images/backgroud.jpg')">
<table width="100%"  cellspacing="0" cellpadding="0" border="0" style="table-layout:fixed;">
<tr>
    <td height="31"  align=center class="buttomline">
			<a>济南机务段段内机车股道状态图 | 版本: 1.0 |  </a>
			<a href="#" onclick="showOnMain('${root}/jsp/home/law.jsp')">法律声明</a> |
			<a href="#" onclick="showOnMain('${root}/jsp/home/service.jsp')">售后服务</a>  |
			<a href="#" onclick="showOnMain('${root}/jsp/home/privacy.jsp')">隐私声明</a>  |
			<a href="#" onclick="showOnMain('${root}/jsp/home/contact.jsp')">联系我们</a>  |
			<a href="#" onclick="showOnMain('${root}/jsp/home/contact.jsp')">产品信息</a>  |
			<a>生产日期:2014年3月 </a>
	</td>
</tr>
</table>
</body>
</html>
