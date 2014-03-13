<%@ page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%@page import="com.jitong.JitongConstants"%><html>
<% com.stanley.pms.User user = (com.stanley.pms.User)session.getAttribute(JitongConstants.USER);%>


<head>
<title>top</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	font-size:13px;
}
.STYLE1 {
	color: #43860c;
	font-size: 12px;
}

.tablefont
{

	font-family:'新宋体';
	color:#0033FF;
}
#Layer1 {
	position:absolute;
	width:121px;
	height:16px;
	z-index:1;
}
html {
  scrollbar-face-color:#66B9D4;


}
a{
	color:#0033FF;
	text-decoration:none;
	font-size:13px;
} 
a:hover{color:#ff6600;}


#Layer2 {
	position:absolute;

	z-index:1;
	right:13px;
	top: 85px;
	font-size:15px;
	font-weight:bold;
	color:#FFFFFF;
}
.divfont
{
	font-size:15px;
	font-weight:bold;
	color:#FFFFFF;
}
    .style1
    {
        width: 88px;
    }

    .style2
    {
        width: 369px;
    }

-->
</style>
<SCRIPT language=javascript type=text/javascript> 
function showOnMain(url){
		parent.mainFrame.location.href=url; 
}
function showOnParent(url){
		parent.location.href=url; 
}



 function correctPNG()
   {
   for(var i=0; i<document.images.length; i++)
      {
     var img = document.images[i]
     var imgName = img.src.toUpperCase()
     if (imgName.substring(imgName.length-3, imgName.length) == "PNG")
        {
       var imgID = (img.id) ? "id='" + img.id + "' " : ""
       var imgClass = (img.className) ? " " : ""
       var imgTitle = (img.title) ? "title='" + img.title + "' " : "title='" + img.alt + "' "
       var imgStyle = "display:inline-block;" + img.style.cssText
       if (img.align == "left") imgStyle = "float:left;" + imgStyle
       if (img.align == "right") imgStyle = "float:right;" + imgStyle
       if (img.parentElement.href) imgStyle = "cursor:hand;" + imgStyle      
       var strNewHTML = "<span " + imgID + imgClass + imgTitle
       + " style=\"" + "width:" + img.width + "px; height:" + img.height + "px;" + imgStyle + ";"
        + "filter:progid:DXImageTransform.Microsoft.AlphaImageLoader"
       + "(src=\'" + img.src + "\', sizingMethod='scale');\"></span>"
       img.outerHTML = strNewHTML
       i = i-1
        }
      }
   }
window.attachEvent("onload", correctPNG);


if (navigator.platform == "Win32" && navigator.appName == "Microsoft Internet Explorer" && window.attachEvent) {
   window.attachEvent("onload", alphaBackgrounds);
}

function alphaBackgrounds(){
   var rslt = navigator.appVersion.match(/MSIE (d+.d+)/, '');
   var itsAllGood = (rslt != null && Number(rslt[1]) >= 5.5);
   for (i=0; i<document.all.length; i++){
      var bg = document.all[i].currentStyle.backgroundImage;
      if (itsAllGood && bg){
         if (bg.match(/.png/i) != null){
            var mypng = bg.substring(5,bg.length-2);
            document.all[i].style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+mypng+"', sizingMethod='scale')";
            document.all[i].style.backgroundImage = "url('')";
         }                                                
      }
   }
} 

alphaBackgrounds();




</script>
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;background-image:url('${root}/images/backgroud.jpg')">
<tr>
		 <td style="height:75px;">
		   <table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;height:75px" >
		   <tr>
		   <td align="right" class="style1"><img src="${root}/images/title.png"  width="66" height="60" /></td>
		   <td align="center" class="style2"><img src="${root}/images/biaoti.png" /></td>
		     <td></td>
		       <td></td>
		   </tr>
		 </table>
  </td>
		 
</tr>
<tr><td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tablefont" background="${root}/images/top2.jpg" >
			<tr>
			<td align="left">&nbsp;<img style="top:2px;left:2px;position:relative;" src="${root}/images/xiaolian.gif" height="18" width="18" />&nbsp;<span style="font-size:15px;font-weight:bold;">登陆用户：<%= user==null?"":user.getUsername()%></span></td>
			<td align="left">
			
			</td>
			<td align="right"> 
			&nbsp;
			
			</td>
			</tr>


</table>

<map name="Map" id="Map">
<area shape="rect" coords="4,2,50,23" href="../index.html" target="_parent" />
<area shape="rect" coords="52,2,95,21" href="#" /><area shape="rect" coords="102,2,144,21" href="#" /><area shape="rect" coords="150,1,197,22" href="#" /><area shape="rect" coords="210,2,304,20" href="#" /><area shape="rect" coords="314,1,361,23" href="#" /></map></body>
</html>
