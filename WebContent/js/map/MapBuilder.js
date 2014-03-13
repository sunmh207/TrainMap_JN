/**
 * Copyright 2006 mapeasy.sf.net.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

var baseDir = "";	// 脚本路径
var viewerWidth;	// 可视区域宽度
var viewerHeight;	// 可视区域高度
var mapId = (new Date()).getTime();
var imgBaseDir = "images/";

// 从页面标签中取得Script文件目录baseDir
var head = document.getElementsByTagName("head")[0];
var nodes = head.childNodes;
for (var i = 0; i < nodes.length;++i) {
	var src = nodes.item(i).src;
	if(src) {
		var index = src.indexOf("/Util.js");
		if (index >= 0) {
			baseDir = src.substring(0, index);
		}
	}
}

// 载入核心脚本库
include(baseDir + "/Prototype.js");
include(baseDir + "/Listener.js");
include(baseDir + "/ListenerSupport.js");
include(baseDir + "/Command.js");
include(baseDir + "/ClientListener.js");
include(baseDir + "/BaseModel.js");
include(baseDir + "/BaseWidget.js");
include(baseDir + "/BaseAction.js");

// 载入地图相关脚本库
include(baseDir + "/map/MapEvent.js");
include(baseDir + "/map/model/GeoObject.js");
include(baseDir + "/map/model/MapModel.js");
include(baseDir + "/map/model/Overlay.js");
include(baseDir + "/map/widget/MapPaneWidget.js");
include(baseDir + "/map/widget/ToolsWidget.js");
include(baseDir + "/map/widget/ExtToolsWidget.js");
include(baseDir + "/map/widget/OverlayWidget.js");
include(baseDir + "/map/action/ZoomAction.js");
include(baseDir + "/map/action/MoveAction.js");
include(baseDir + "/map/action/DragAction.js");
include(baseDir + "/map/action/ResetAction.js");
include(baseDir + "/map/action/MapTypeAction.js");

/**
 * 地图构建器
 * 
 * @author Tim.Wu Michael.Young
 */
function MapBuilder(container) {
	
	//~ Field
	/** 地图模型对象 */
	this.mapModel;
	
	/** 定义了DIV层的网页容器 */
	this.container;

	this.mapId;
	
	//~ Method
	{
		this.mapId = mapId++;
		this.mapModel = new MapModel(this.mapId);
		this.container = container;
		viewerWidth = this.container.style.width.indexOf("px")?Number(this.container.style.width.substring(0, this.container.style.width.indexOf("px"))):Number(this.container.style.width);
		viewerHeight = this.container.style.height.indexOf("px")?Number(this.container.style.height.substring(0, this.container.style.height.indexOf("px"))):Number(this.container.style.height);
		// actions
		new ZoomAction(this.mapModel);
		new MoveAction(this.mapModel);
		new DragAction(this.mapModel);
		new ResetAction(this.mapModel);
		new MapTypeAction(this.mapModel);
		// widgets
		new MarkerWidget(this.mapModel);
	}

	/**
	 * 定位中心点和缩放比例并输出地图
	 *
	 * @param point 默认显示区域中心点
	 * @param zoomLevel 缩放比例
	 */
	this.outputMap = function(point, zoomLevel) {
		var defaultZoom = new Zoom(zoomLevel);
		var centerCoord = point.getCoord();
		this.mapModel.setZoom(defaultZoom);
		this.mapModel.setViewerCenterCoord(centerCoord);
		this.mapModel.setDefault(centerCoord, defaultZoom);
		var mapPaneWidget = new MapPaneWidget(this.mapModel);
		mapPaneWidget.setContainer(this.container);
		mapPaneWidget.paint();
		// 工具栏
		var toolsWidget = new ToolsWidget(this.mapModel);
		toolsWidget.paint();
	}
	
	/**
	 * 填加扩展工具
	 *
	 * @param para 扩展工具别名
	 */
	this.addTool = function(para) {
		if (para == MapBuilder.TOOL_SLIDERBAR) {
			new SliderWidget(this.mapModel).paint();
		}
		if (para == MapBuilder.TOOL_MAPTYPE) {
			new MapTypeWidget(this.mapModel).paint();
		}
		if (para == MapBuilder.TOOL_FOOTBAR) {
			new FootBarWidget(this.mapModel).paint();
		}
		if (para == MapBuilder.TOOL_COMPASS) {
			new CompassWidget(this.mapModel).paint();
		}
	}
	
	/**
	 * 获得地图对象
	 */
	this.getMap = function() {
		return this.mapModel;
	}
}

MapBuilder.TOOL_SLIDERBAR = "tools.sliderbar";
MapBuilder.TOOL_MAPTYPE = "tools.maptype";
MapBuilder.TOOL_FOOTBAR = "tools.footbar";
MapBuilder.TOOL_COMPASS = "tools.compass";