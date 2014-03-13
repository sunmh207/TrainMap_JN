<%@ page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>股道图</title>
</head>

<frameset rows="96,*,32" cols="*" framespacing="0" frameborder="no" border="0">
  <frame src="${root}/jsp/top.jsp" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" />
  <frameset cols="163,*" framespacing="0" frameborder="no" border="0">
	  <frame src="${root}/jsp/left.jsp" name="leftFrame" id="leftFrame" scrolling="no" noresize />
  	  <frame src="${root}/jsp/blank.jsp" name="mainFrame" id="mainFrame" />	
  </frameset>
  <frame src="${root}/jsp/down.jsp" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" />
</frameset>
<noframes><body>
</body>
</noframes></html>
