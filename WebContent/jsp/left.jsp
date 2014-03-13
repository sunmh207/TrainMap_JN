<%@ page contentType="text/html; charset=utf-8" %> 
<%@include file="/WEB-INF/jsp/include.jsp" %>
<STYLE type=text/css> 
body {font:12px;color:#4D4D4D;margin:0px; text-align:center;} 
a{
	color:#009933;
	text-decoration:none;
	font-size:12px;	
} 
#menu {
	width:150px;
	margin:0px;
	padding:0px;
	text-align:left;
	list-style:none;
} 
#menu .item {margin:0px auto;padding:0px;list-style:none;} 
a.title:link,a.title:visited,a.title:hover {
	display:block;
	height:26px;
	line-height:26px;
	margin-top:0px;
	background-image: url(${root}/images/meau1.jpg);
	background-repeat: no-repeat;
	width: 150px;padding-left:0px; color: #e5e1e2;
	font-weight:bold;
	font-size:16px;
} 


#menu .item  a{color:#fbfcfc; display:block; height:20px;line-height:18px; margin:1px 0;border:0px solid #fff; margin-left:7px}
#menu .item  a li {margin:1;background: url(/images/meau1.jpg);} 
#menu .item  a li a{color:#fbfcfc; display:block; height:20px;line-height:18px; margin:1px 0;border:0px solid #fff; margin-left:7px}
#menu .item  a:hover{color:#ebe808;border:0px solid #9FE26A;}

html {
  scrollbar-face-color:#66B9D4;


}

.ashow
{
}
</STYLE>

<script type="text/javascript">
if(!document.getElementsByClassName)//判断浏览器是否支持这个方法
{
    document.getElementsByClassName=function(cname){
        var selected=new Array();
        var alltag=document.getElementsByTagName("*");//获取所有标签
        for(var i=0;i<alltag.length;i++)
        {
            var t=alltag[i];            
            if(t.className==cname)    //比较标签的class与所要查找的class是否相同
            {
                selected.push(t);          //将相同的存入数组
            }
        }
        return selected;
    }
}
</script>

<script type="text/javascript">
function showcolor(showli){
    var items = document.getElementsByClassName("title"); 
    for (var j=0; j<items.length-1; j++) { 
            var o = "a" + j; 
            if (showli != o){
				document.getElementById("a" + j).style.color="#fbfcfc";	
			}else{
				document.getElementById("a" + j).style.color="yellow";
			}
    } 
    return;
}

function showOnMain(showli,url){
	showcolor(showli);
	parent.mainFrame.location.href=url; 
}
</script>
<body  bgcolor="#D60121" >
<table  border="0" cellpadding="0" cellspacing="0"  >
  <tr>
  <!--左边边部分 start-->
	<td valign="top" align="left" >
	<UL id="menu">
			<LI class="item">
			  <A class=title  href="javascript:void(0)">&nbsp;&nbsp; </A>              
			 </LI> 		  
			
			  <LI class="item">			 
			 	<A href="#" onclick="showOnMain('a0','${root}/map_qd.jsp')" id="a0" class="title"  >
				<img style="top:5px;position:relative;" src="${root}/images/menuitem.png" height="20"  border="0" />青岛股道状态图</A> 
			  </LI>
			  <LI class="item">			 
			 	<A href="#" onclick="showOnMain('a1','${root}/taiweialldata_qd.do')" id="a1" class="title"  >
				<img style="top:5px;position:relative;" src="${root}/images/menuitem.png" height="20"  border="0" />青岛股道数据</A> 
			  </LI>
			  
			  <LI class="item">			 
			 	<A href="#" onclick="showOnMain('a2','${root}/map_lc.jsp')" id="a2" class="title"  >
				<img style="top:5px;position:relative;" src="${root}/images/menuitem.png" height="20"  border="0" />聊城股道状态图</A> 
			  </LI>
			  <LI class="item">			 
			 	<A href="#" onclick="showOnMain('a3','${root}/taiweialldata_lc.do')" id="a3" class="title"  >
				<img style="top:5px;position:relative;" src="${root}/images/menuitem.png" height="20"  border="0" />聊城股道数据</A> 
			  </LI>
			  <LI class="item">			 
			 	<A href="#" onclick="showOnMain('a4','${root}/youhuoyubao.do')"  id="a4" class="title"  >
				<img style="top:5px;position:relative;" src="${root}/images/menuitem.png" height="20" border="0" />机车有活预报单</A> 
			  </LI>
			  <LI class="item">			 
			 	<A href="#" onclick="showOnMain('a5','${root}/fanghuyongpin.do')" id="a5" class="title"  >
				<img style="top:5px;position:relative;" src="${root}/images/menuitem.png" height="20"  border="0" />机车防护用品表</A> 
			  </LI>
			  
			   <LI class="item">			 
			 	<A href="#" onclick="showOnMain('a6','${root}/locomotivedict.do')" id="a6" class="title"  >
				<img style="top:5px;position:relative;" src="${root}/images/menuitem.png" height="20"  border="0" />机车库字典</A> 
			  </LI>
			  <LI class="item">			 
			 	<A href="#" onclick="showOnMain('a7','${root}/trialitem.do')" id="a7" class="title"  >
				<img style="top:5px;position:relative;" src="${root}/images/menuitem.png" height="20"  border="0" />试验项目字典</A> 
			  </LI>
			  <LI class="item">			 
			 	<A href="#" onclick="showOnMain('a8','${root}/puchadetailv.do')" id="a8" class="title"  >
				<img style="top:5px;position:relative;" src="${root}/images/menuitem.png" height="20"  border="0" />普查详情单</A> 
			  </LI>
			   <LI class="item">			 
			 	<A href="#" onclick="showOnMain('a9','${root}/puchadan.do')" id="a9" class="title"  >
				<img style="top:5px;position:relative;" src="${root}/images/menuitem.png"  height="20" border="0" />机车状态普查单</A> 
			  </LI>
			  
			  <%-- <LI class="item">			 
			 	<A href="#"  id="a3" class="title"  >
				<img style="top:5px;position:relative;" src="${root}/images/menuitem.png"  height="20" border="0" />机车小配件管理</A> 
			  </LI>
			  <LI class="item">			 
			 	<A href="#"  id="a4" class="title"  >
				<img style="top:5px;position:relative;" src="${root}/images/menuitem.png"  height="20" border="0" />机车整备合格证</A> 
			  </LI>
			  <LI class="item">			 
			 	<A href="#"id="a5" class="title"  >
				<img style="top:5px;position:relative;" src="${root}/images/menuitem.png" height="20"  border="0" />机车整备试验单</A> 
			  </LI>
			  <LI class="item">			 
			 	<A href="#"  id="a6" class="title"  >
				<img style="top:5px;position:relative;" src="${root}/images/menuitem.png" height="20"  border="0" />活件故障统计单</A> 
			  </LI> --%>
			 
			  <%-- <LI class="item">			 
			 	<A href="#"  id="a8" class="title"  >
				<img style="top:5px;position:relative;" src="${root}/images/menuitem.png"  height="20" border="0" />质检员工作日志</A> 
			  </LI> --%>
			 <%--  <LI class="item">			 
			 	<A href="#" onclick="showOnMain('a4','${root}/qry.do')" id="a4" class="title"  >
				<img style="top:5px;position:relative;" src="${root}/images/menuitem.png"  height="20" border="0" />机车状态查询表</A> 
			  </LI> --%>
			  <!-- 
			  <LI class="item">			 
			 	<A href="#" onclick="showOnMain('a10','${root}/jichezhuangtai.do')" id="a10" class="title"  >
				<img style="top:5px;position:relative;" src="${root}/images/menuitem.png" height="20"  border="0" />机车整备状态表</A> 
			  </LI>
			   -->
			 <%--  <LI class="item">			 
			 	<A href="#" onclick="showOnMain('a5','${root}/runningstatus.do')" id="a5" class="title"  >
				<img style="top:5px;position:relative;" src="${root}/images/menuitem.png" height="20"  border="0" />司机出入段记录</A> 
			  </LI> --%>
			 
	</UL>
	</td>
	 <!--左边边部分 end-->	 
</tr>
</table>
</body>
</html>