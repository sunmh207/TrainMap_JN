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

/**
 * 地图面板
 *
 * @author Tim.Wu Michael.Young
 */
function MapPaneWidget(mapModel) {
	
	// Inherit from BaseWidget
	BaseWidget.apply(this, new Array(mapModel));

	this.container;

	//~ Method
	{
		this.model.addFirstListener("zoom", this);
		this.model.addFirstListener("move", this);
		this.model.addListener("event.addlistener", this);
		this.model.addFirstListener("maptype", this);
		this.model.addFirstListener("clearoverlays", this);//stanley
	}

	/**
	 * 绘制界面
	 */
	this.paint = function() {
		if (this.container) {
			this.container.style.border = "1px solid #666666";
			this.container.style.overflow = "hidden";
			this.container.style.position = "relative";
			// 屏蔽右键
			document.oncontextmenu = function(event) {
				self.event.returnValue = false;
				return false;
			};
			// 屏蔽全选
			this.container.onselectstart = function() {
				return false;
			};
			// 工具栏
			HTMLCode = '<div id="toolbar_' + this.model.getId() + '" style="position:absolute;left:5px;top:5px;z-index:2000;"></div>';
			// MapType栏
			HTMLCode += '<div id="maptypebar_' + this.model.getId() + '" style="position:absolute;left:100px;top:10px;z-index:2000;"></div>';
			//foot bar
			//HTMLCode += '<div id="footbar_' + this.model.getId() + '" style="position:absolute;left:5px;bottom:0px;z-index:2000;"></div>';
			//compass bar
			//HTMLCode += '<div id="compass_' + this.model.getId() + '" style="position:absolute;right:5px;top:5px;z-index:2000;"></div>';
			
			// 地图
			HTMLCode += '<div id="map_' + this.model.getId() + '" style="position:absolute;background:white;z-index:0;" onmousedown="mapMDown(event, $(\'map_' + this.model.getId() + '\'), ' + this.model.getId() + ');"></div>';
			this.container.innerHTML = HTMLCode;
			this.paintMap();
		}
	}

	this.setContainer = function(container) {
		this.container = container;
	}

	this.propertyChange = function(param, newValue) {
		//alert("MapPaneWidget.param="+param);
		if (param == "zoom") {
			this.paintMap();
		}
		if (param == "move") {
			this.paintMap();
		}
		if (param == "event.addlistener") {
			if (newValue) {
				this.customEvent(newValue[1]);
			}
		}
		if (param == "maptype") {
			this.changeMaptype();
		}
	}
	
	this.customEvent = function(eventName) {
		var mapDiv = $("map_" + this.model.getId());
		if (eventName == MapEvent.DBLCLICK) {
			mapDiv.ondblclick = this.model.events[MapEvent.DBLCLICK];
		}
	}

	/**
	 * 改变地图类型
	 */
	this.changeMaptype = function() {
		// clear map
		var mapDiv = $("map_" + this.model.getId());
		var tileNodes = mapDiv.childNodes;
		if (tileNodes) {
			for (var i = 0;i < tileNodes.length;i++) {
				mapDiv.removeChild(tileNodes[i]);
				i--;
			}
		}
		this.paintMap();
	}

	/**
	 * 绘制地图
	 */
	this.paintMap = function() {
		//alert("MapPaneWidget.paintMap()");
		var zoom = this.model.getZoom();
		var viewerBound = zoom.getViewerBound().clone(this.model.getViewerCenterCoord());
		// alert(viewerBound);
		var offsetX = (MapModel.bound.getMinX() - viewerBound.getMinX()) * (zoom.getBorderTilesNum() * MapModel.tileSize / MapModel.bound.getWidth());
		var offsetY = (viewerBound.getMaxY() - MapModel.bound.getMaxY()) * (zoom.getBorderTilesNum() * MapModel.tileSize / MapModel.bound.getHeight());
		//alert("viewerBound.getMinX()="+viewerBound.getMinX()+" viewerBound.getMaxX()="+viewerBound.getMaxX()+"viewerBound.getMinY()="+viewerBound.getMinY()+"viewerBound.getMaxY()="+viewerBound.getMaxY());
		//alert("offsetX="+offsetX+",offsetY="+offsetY);
		var mapDiv = $("map_" + this.model.getId());
		mapDiv.style.left = offsetX + "px";
		mapDiv.style.top = offsetY + "px";
		mapDiv.style.width = (MapModel.tileSize * zoom.getBorderTilesNum()) + "px";
		mapDiv.style.height = (MapModel.tileSize * zoom.getBorderTilesNum()) + "px";
		var tiles = zoom.getTiles(this.model.getViewerCenterCoord());
		// get old
		var oldTileNodes = new Array();
		var tileNodes = mapDiv.childNodes;
		if (tileNodes) {
			for (var i = 0;i < tileNodes.length;i++) {
				oldTileNodes.push(tileNodes[i]);
			}
		}
		// add new
		if (tiles) {
			for (var i = 0;i < tiles.length;i++) {
				var tileId = "map" + this.model.getId() + "_zoom_" + this.model.getZoom().getLevel() + "_tile_" + tiles[i].getRow() + "_" + tiles[i].getColumn();
				var isExist = false;
				// 检查该瓦片是否已经显示
				for (var j = 0;j < oldTileNodes.length;j++) {
					if (oldTileNodes[j] != null && oldTileNodes[j].getAttribute("id") == tileId) {
						isExist = true;
						oldTileNodes[j] = null;
						break;
					}
				}
				if (isExist == false) {
					offsetX = tiles[i].getColumn() * MapModel.tileSize;
					offsetY = tiles[i].getRow() * MapModel.tileSize;
					var tile = document.createElement("div");
					tile.id = tileId;
					tile.style.position = "absolute";
					tile.style.left = offsetX + "px";
					tile.style.top = offsetY + "px";
					tile.onmousedown = function() {
						return false;
					};
					mapDiv.appendChild(tile);
					var tileImage = document.createElement("img");
					tileImage.src = tiles[i].getSrc();
					tileImage.onmousedown = function() {
						return false;
					}
					tile.appendChild(tileImage);
				}
			}
		}
		// remove all
		for (var i = 0;i < oldTileNodes.length;i++) {
			if (oldTileNodes[i] != null) {
				mapDiv.removeChild(oldTileNodes[i]);
			}
		}
	}
}

/**
 * 地图拖动
 */
function mapMDown(e, mapDiv, modelId) {
	var orgOffsetX = getOffset(mapDiv.style.left);
	var orgOffsetY = getOffset(mapDiv.style.top);
	document.body.style.cursor = "move";
	var originCoord = documentCoord(e);
	if(mapDiv.setCapture) {
		mapDiv.setCapture();
	} else if (window.captureEvents) {
		window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);
	}
	
	document.onmousemove = function(e) {
		var newCoord = documentCoord(e);
		mapDiv.style.left = (orgOffsetX + newCoord.x - originCoord.x) + "px";
		mapDiv.style.top = (orgOffsetY + newCoord.y - originCoord.y) + "px";
	}

	document.onmouseup = function(e) {
		if(mapDiv.releaseCapture) {
			mapDiv.releaseCapture();
		} else if (window.captureEvents) {
			window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);
		}
		document.onmousemove = null;
		document.onmouseup = null;
		document.body.style.cursor = "";
		var newCoord = documentCoord(e);
		var offset = {};
		offset.x = newCoord.x - originCoord.x;
		offset.y = newCoord.y - originCoord.y;
		// alert(offset);
		command.exec("drag", new Array(modelId, offset));
	}
}

/**
 * 得到鼠标事件位置
 */
function documentCoord(e) {
	var coord = {};
	if(!e) {
		e = window.event;
	}
	if(!e.pageX) {
		e.pageX = e.clientX;
	}
	if(!e.pageY) {
		e.pageY = e.clientY;
	}
	coord.x = e.pageX;
	coord.y = e.pageY;
	return coord;
}