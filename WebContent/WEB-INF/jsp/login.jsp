<%@ page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<html:pageHead title="用户登录">
<style type="text/css">
input {
	border: 1px solid #cadcb2;
	font-family:'Verdana';
}
.loginmenu {
	background-image: url(images/button.jpg);
	background-repeat: no-repeat;
	border: none;
	width: 92px;
	height: 26px;
}

.loginmenu2 {
	background-image: url(images/button.jpg);
	background-repeat: no-repeat;
	border: none;
	width: 92px;
	height: 26px;
}
</style>
<script type="text/javascript">
function forlogin() {
	var frm = document.forms["form1"];
	frm.action="${root}/Login!login.do";  
 	frm.submit();
}
function forKeyDown(){
	if (event.keyCode == 13){
		event.returnValue=false;
		event.cancel = true;
		var bt = document.getElementById("login");
		bt.click();
	}
}
</script>
</html:pageHead>
<body>
<form action="${root}/Login.do" method="post" name="form1">
<table width="100%" border="0" cellpadding="2" cellspacing="1" 	height="100%">
	<tr>
		<td valign="top"></td>
		<td width="780px" height="435px" background="images/login5.jpg" style="background-repeat: no-repeat;">
		<table width="100%" border="0" cellpadding="2" cellspacing="1" height="100%">
			<tr>
				<td height="240" colspan="3"></td>
			</tr>
			<tr>
				<td width="57%" height="180"></td>
				<td width="20%">
				<s:actionerror/>
				<table width="100%" border="0" cellpadding="2" cellspacing="1"	height="23%"	style="font-size: 14px; font-weight: bold; color:#FFFFFF;">
					<tr>
						<td width="5%" height="28">&nbsp;</td>
						<td width="30%"  style="color: #FFFFFF;" >用&nbsp;&nbsp;户&nbsp;&nbsp;名</td>
						<td width="35%"> <s:textfield name="username" size="17"/></td>
						<td width="30%"></td>
					</tr>
					<tr>
						<td height="28">&nbsp;</td>
						<td style="color: #FFFFFF;" >密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 码</td>
						<td> <s:password name="password" size="17" onkeydown="forKeyDown()"/></td>
						<td>
				      		<input name="login" type="button" style="font-size: 14px; font-weight:bold; color: #FFFFFF;" 	class="loginmenu" id="login" value="用户登录" onclick="forlogin()"/>
						</td>
					</tr>
				</table>
				
				</td>
				<td width="50%"></td>
			</tr>
			<tr>
				<td height="20%" colspan="3"></td>
			</tr>
		</table>

		</td>
		<td></td>
	</tr>
</table>
</form>
</body>
</html>