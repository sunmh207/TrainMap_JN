<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%> 
<jsp:useBean id="JSONRPCBridge" scope="session"	class="com.metaparadigm.jsonrpc.JSONRPCBridge" />
<jsp:useBean id="manager" scope="session" class="com.stanley.TaiWeiManager" />

<%
JSONRPCBridge.registerObject("manager", manager);
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
<script type="text/javascript" src="trainmap.js"></script>
<script type="text/javascript" src="js/jsonrpc.js"></script>
<script language="JavaScript">
var jsonrpc = null;
function onLoad() {
	jsonrpc = new JSONRpcClient("JSON-RPC");
}
</script>
<STYLE type=text/css> 
#toolbar{
	border-right-style:solid;
	border-right-color:rgb(176,176,176);
	border-right-width:1px;
	border-left-style:solid;
	border-left-color:rgb(176,176,176);
	border-left-width:1px;
	font-size:12px;
	background-image: url(${root}/images/nav_bj.gif); 
	width:1280px;
}
</style>
</HEAD>
 
<BODY onLoad="onLoad()">
<div align="center" >
<br>
	<div id="toolbar" align="right">
		<a href="/TrainMap/qry.do"><img src="${root}/images/tool_query.png" ></img>机车查询</a>&nbsp;&nbsp;
		|&nbsp;&nbsp;最后更新时间 : <span id="screenTime">未更新</span>&nbsp;&nbsp;
	</div>

  <div id="map" style="width:1280px;height:512px;background-color:blue;">
  </div>
  
</div>
<script language="JavaScript">
<!--
//全局变量，存放所有台位信息
var twSet = null;

//提交一个set对象并且将其返回
function getLatestTaiWeis() {
	//set属性是一个数组对象,每个数组元素就是set里的一个元素.
	var set = {"javaClass":"java.util.HashSet", "set":{}};
	jsonrpc.manager.getLatestTaiWeis(cbSet, set);
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
//----------------------------
	if(twSet!=null){
		for (var value in twSet) {
			var attrs = value.split("|");
			var m=new Marker(new Point(attrs[1],attrs[2]), Marker.TRAIN_GREEN_SMALL);
			m.setName(attrs[3]);
			m.setInfo("台位:"+attrs[0]+"&nbsp;&nbsp;车号:"+attrs[3]+"<br>司机:"+attrs[4]+"&nbsp;&nbsp;副司机:"+attrs[5]+"<br>开点:"+attrs[6]+"&nbsp;&nbsp;开站:"+attrs[7]+"<br>到点:"+attrs[8]+"&nbsp;&nbsp;到站:"+attrs[9]+"<br>坐标:"+attrs[1]+","+attrs[2]);
			_map.addOverlay(m);
		}
	}
	updateScreenTime();
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
//--></script>

<SCRIPT LANGUAGE="JavaScript">
<!--
function AmoyMapType() {

	MapType.apply(this);

	this.getSrc = function(level, row, column) {
		return "http://localhost:9090/TrainMap/images/zoom_"+level+"/"+level+"-"+row+"-"+column+".jpeg"; 
		//return "http://222.39.14.169/TrainMap/images/zoom_"+level+"/"+level+"-"+row+"-"+column+".jpeg"; 
	}
}
MapModel.mapTypes = new Array(new AmoyMapType());
MapModel.maxZoomLevel=3;

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
/*
var myhome=new Marker(new Point(4.1363, -36.5487), Marker.LARGE);
myhome.setInfo("Hello! Here is my home.");
map.addOverlay(myhome);
*/
window.setInterval(_refreshMarker(map),5000);
//window.setTimeout(_refreshMarker(map),2000);
// 在地图上加入双击事件
MapEvent.addListener(map, MapEvent.DBLCLICK, function(e) {
	var coord = MapEvent.getEventCoord(e, map);
	//alert(coord.toString());
	var marker = new Marker(coord.getPoint(), Marker.TRAIN_GREEN_MIDDLE);
	map.addOverlay(marker);
  	//alert(coord.x/1e16+":"+coord.y/1e16);
	marker.setInfo("车号:T000 &nbsp; 车道:D000<br> 司机：张三&nbsp; 副司机:李四 <br>位置:"+coord.x/1e16+","+coord.y/1e16);
});
//-->
</SCRIPT>
</BODY>
</HTML>