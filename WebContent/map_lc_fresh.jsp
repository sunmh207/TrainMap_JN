<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%> 
<jsp:useBean id="JSONRPCBridge" scope="session"	class="com.metaparadigm.jsonrpc.JSONRPCBridge" />
<jsp:useBean id="manager_lc" scope="session" class="com.stanley.TaiWeiManager_LC" />

<%
JSONRPCBridge.registerObject("manager_lc", manager_lc);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> 股道图 </TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
<SCRIPT LANGUAGE="JavaScript" src="js/Util.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" src="js/map/MapBuilder.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" src="js/map/model/MapModel.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" src="js/map/model/GeoObject.js"></SCRIPT>
<script type="text/javascript" src="trainmap.js"></script>
<script type="text/javascript" src="js/jsonrpc.js"></script>
<script language="JavaScript">
var jsonrpc = new JSONRpcClient("JSON-RPC");
</script>

</HEAD>
 
<BODY topmargin="0" leftmargin="0" style="background-color:022161;">
<div align="center" >
	<div id="map" style="width:1024px;height:512px;background-color:022161;border-style:none;"></div>
	<div id="alert" style="display:none;position:fixed;TEXT-ALIGN:center;left:0;top:150;height:200;width:100%;z-index:1000000;font-size: 50px; border:1 solid red;background-color:red;">后台数据没有更新！</div>
</div>
<script language="JavaScript">
<!--
var refreshTimes=0;
//全局变量，存放所有台位信息
var twSet = null;

//提交一个set对象并且将其返回
function getLatestTaiWeis() {
	//set属性是一个数组对象,每个数组元素就是set里的一个元素.
	var set = {"javaClass":"java.util.HashSet", "set":{}};
	jsonrpc.manager_lc.getLatestTaiWeis(cbSet, set);
}
function cbSet(result, exception) {
	if (exception == null) {
		twSet = result.set;
	} else {
		alert(exception.message);
	}
}

function refreshMarker(_map){ 	
	getLatestTaiWeis();
	_map.clearOverlays();

	if(twSet!=null){
		var i=0;				
			for (var value in twSet) {
				var attrs = value.split("|");
				
				var m;
				if(attrs[11]=="1"){
					m=new Marker(new Point(attrs[1],attrs[2]),Marker.TRAIN_RED_SMALL);
					m.setInfo("台位:"+attrs[0]+"&nbsp;&nbsp;车号:"+attrs[3]+"<br>司机:"+attrs[4]+"&nbsp;&nbsp;副司机:"+attrs[5]+"<br>开点:"+attrs[6]+"&nbsp;&nbsp;开站:"+attrs[7]+"<br>到点:"+attrs[8]+"&nbsp;&nbsp;到站:"+attrs[9]+"<br>坐标:"+attrs[1]+","+attrs[2]+"<br>"+attrs[12]);
				}else{
					m=new Marker(new Point(attrs[1],attrs[2]),Marker.TRAIN_GREEN_SMALL);
					m.setInfo("台位:"+attrs[0]+"&nbsp;&nbsp;车号:"+attrs[3]+"<br>司机:"+attrs[4]+"&nbsp;&nbsp;副司机:"+attrs[5]+"<br>开点:"+attrs[6]+"&nbsp;&nbsp;开站:"+attrs[7]+"<br>到点:"+attrs[8]+"&nbsp;&nbsp;到站:"+attrs[9]+"<br>坐标:"+attrs[1]+","+attrs[2]);
				}
				m.setName(attrs[3]);
				
				_map.addOverlay(m);
				i++;
			}
			if(i==0){
				showAlert();
			}else{
				hideAlert();
			}
	}
} 

function _refreshMarker(_map){ 
	return function(){ 
		refreshMarker(_map); 
	} 
} 
function updateScreenTime(){
	var scrTime= document.getElementById('screenTime');
	scrTime.innerHTML=GetDateT();
}
function GetDateT(){
 var d,s;
 d = new Date();
 s = (d.getMonth() + 1) + "月";//取月份
 s += d.getDate() + "日 ";         //取日期
 s += d.getHours() + ":";       //取小时
 s += d.getMinutes() + ":";    //取分
 s += d.getSeconds();         //取秒
 return(s);  
} 

function showAlert(){
	document.getElementById('alert').style.display="inline";
}
function hideAlert(){
	document.getElementById('alert').style.display="none";
}
//--></script>

<SCRIPT LANGUAGE="JavaScript">
<!--
function AmoyMapType() {
	MapType.apply(this);
	this.getSrc = function(level, row, column) {
		return "${root}/images/lc/zoom_"+level+"/"+level+"-"+row+"-"+column+".jpeg"; 
	}
}
MapModel.mapTypes = new Array(new AmoyMapType());
//MapModel.maxZoomLevel=3;
MapModel.maxZoomLevel=2;

var mapbuilder = new MapBuilder($("map"));
//mapbuilder.outputMap(new Point(0, 0), 2);
mapbuilder.outputMap(new Point(0, 0), 1);
// 滑块工具
mapbuilder.addTool(MapBuilder.TOOL_SLIDERBAR); 
// 地图类型工具
//mapbuilder.addTool(MapBuilder.TOOL_MAPTYPE);
//foot 信息
//mapbuilder.addTool(MapBuilder.TOOL_FOOTBAR);
//compass 信息
//mapbuilder.addTool(MapBuilder.TOOL_COMPASS);
// 得到地图对象
var map = mapbuilder.getMap();

window.setInterval(_refreshMarker(map),5000);
//refreshMarker(map);
//-->
</SCRIPT>
</BODY>
</HTML>