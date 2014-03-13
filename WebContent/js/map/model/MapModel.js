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
 * 地图模型
 * 
 * @author Tim.Wu Michael.Young
 */
function MapModel(id) {
	
	// Inherit from BaseModel
	BaseModel.apply(this);

	//~ Field
	this.id = id;

	/** 缩放比例 */
	this.zoom;
	
	/** 显示区域中心坐标 */
	this.viewerCenterCoord;
	
	/** 显示区域 */
	this.viewerBound;
	
	/** 默认显示中心坐标 */
	this.defaultCoord;
	
	/** 默认显示比例 */
	this.defaultZoom;

	/** 覆盖物集合 */
	this.overlays;
	
	/** 地图事件 */
	this.events = new Array();

	/** 地图类型 */
	this.currentMapType = 0;

	//~ Method
	this.getZoom = function() {
		return this.zoom;
	}

	this.setZoom = function(zoom) {
		this.zoom = zoom;
		this.zoom.setMap(this);
		this.firePropertyChange("zoom");
	}

	this.setViewerCenterCoord = function(coord){
		this.viewerCenterCoord = coord;
		this.firePropertyChange("move");
	}

	this.getViewerCenterCoord = function() {
		return this.viewerCenterCoord;
	}

	this.setViewerBound = function(viewerBound) {
		this.viewerBound = viewerBound;
	}

	this.getViewerBound = function() {
		return this.viewerBound;
	}

	this.setDefault = function(defaultCoord, defaultZoom) {
		this.defaultCoord = defaultCoord;
		this.defaultZoom = defaultZoom;
	}

	this.reset = function() {
		this.setViewerCenterCoord(this.defaultCoord);
		this.setZoom(this.defaultZoom);
	}
	
	/**
	 * 增加覆盖物
	 */
	this.addOverlay = function(overlay) {
		if (this.overlays == null) {
			this.overlays = new Array();
		}
		this.overlays.push(overlay);
		this.firePropertyChange("addoverlay", overlay);
		return overlay;
	}
	
	/**
	 * 移除某覆盖物
	 */
	this.removeOverlay = function(overlay) {
		if (this.overlays) {
			this.firePropertyChange("removeoverlay", overlay);//stanley	
			alert("rm"+overlays.length);
			this.overlays = this.overlays.without(overlay);
		}
	}
	
	/**
	 * 清除所有覆盖物
	 */
	this.clearOverlays = function() {
		if (this.overlays) {
			//this.overlays.clear();//stanley
			//this.firePropertyChange("clearoverlays", overlay);//stanley	
			//stanley
			//while(this.overlays.length>0){
			var i = this.overlays.length-1;
			for(;i>=0;i--){
				//alert("cls"+this.overlays.length);
				this.firePropertyChange("removeoverlay", this.overlays.pop());//stanley
			}
		}
	}

	this.getOverlays = function() {
		return this.overlays;
	}

	this.getId = function() {
		return this.id;
	}

	this.setCurrentMapType = function(arrIndex) {
		this.currentMapType = arrIndex;
		this.firePropertyChange("maptype");
	}
}

/**
 * 地图类型
 *
 * @author Tim.Wu Michael.Young
 */
function MapType() {
	
	this.enablePic;

	this.disablePic;
	
	//~ Method
	{
		this.enablePic = imgBaseDir + "maptype_0b.gif";
		this.disablePic = imgBaseDir + "maptype_0a.gif";
	}

	this.getSrc = function(level, row, column) {
		return imgBaseDir + 'zoom_' + level + '/' + level + '_' + column + '_' + row + '.jpg'; 
	}

	this.getEnablePic = function() {
		return this.enablePic;
	}

	this.getDisablePic = function() {
		return this.disablePic;
	}
}

/** 地图模型常量 */
/** 地图坐标系范围 */
//MapModel.bound = new Bound(-180e16, 180e16, -90e16, 90e16);
MapModel.bound = new Bound(-180e16, 180e16, -180e16, 180e16);
/** 第一个缩放等级的瓦片数 */
MapModel.firstZoomTileNum = 1;
//MapModel.firstZoomTileNum = 25;
/** 每层缩放之间的比例参数 */
MapModel.scalePara = 2;
/** 瓦片尺寸 */
//MapModel.tileSize = 256;
MapModel.tileSize = 837;
/** 最大缩放比例 */
MapModel.maxZoomLevel = 5;
/** 地图类型集合 */
MapModel.mapTypes = new Array();
MapModel.mapTypes.push(new MapType());

/**other links*/
//MapModel.links = new Array();

/**
 * 某比例模型
 * 
 * @author Tim.Wu Michael.Young
 */
function Zoom(level) {
	
	//~ Field
	/** 比例等级 */
	this.level = level;
	
	/** 总瓦片数 */
	this.tilesNum;
	
	/** 边缘瓦片数 */
	this.borderTilesNum;
	
	/** 可视区域 */
	this.viewerBound;

	this.map;

	//~ Method
	{	
		this.tilesNum = Math.pow(Math.pow(MapModel.scalePara, (this.level - 1)), 2) * MapModel.firstZoomTileNum;
		this.borderTilesNum = Math.sqrt(this.tilesNum);
		// 以坐标系原点为中心的可视区域
		this.viewerBound = MapModel.bound.getCenterCoord().getBound(MapModel.bound.getWidth() * viewerWidth / (this.borderTilesNum * MapModel.tileSize), MapModel.bound.getHeight() * viewerHeight / (this.borderTilesNum * MapModel.tileSize));
		//alert("viewerBount: "+MapModel.bound.getWidth()+" * "+viewerWidth +"/ ("+this.borderTilesNum+" * "+MapModel.tileSize+"), "+MapModel.bound.getHeight()+" * "+viewerHeight+" / ("+this.borderTilesNum+" * "+MapModel.tileSize+")");
	}

	this.getLevel = function() {
		return this.level;
	}
	
	this.setMap = function(map) {
		this.map = map;
	}

	/**
	 * 取得以某坐标为中心点的可视区域的瓦片集合
	 *
	 * @param coord 可视区域中心点
	 */
	this.getTiles = function(coord) {
		// 重新定位可视区域
		if (this.viewerBound.getCenterCoord() != coord) {
			this.viewerBound = this.viewerBound.clone(coord);
		}
		if (!MapModel.bound.isCover(this.viewerBound)) {
			// 可视区域在地图之外
			return null;
		} else {
			// 可视区域有地图
			var tiles = new Array();
			var rowFrom = Math.floor((MapModel.bound.getMaxY() - this.viewerBound.getMaxY()) / (MapModel.bound.getHeight() / this.borderTilesNum));
			rowFrom = rowFrom<0?0:rowFrom;
			var rowTo = Math.floor((this.viewerBound.getMinY() - MapModel.bound.getMinY()) / (MapModel.bound.getHeight() / this.borderTilesNum));
			rowTo = rowTo<0?this.borderTilesNum:(this.borderTilesNum - rowTo);
			var columnFrom = Math.floor((this.viewerBound.getMinX() - MapModel.bound.getMinX()) / (MapModel.bound.getWidth() / this.borderTilesNum));
			columnFrom = columnFrom<0?0:columnFrom;
			var columnTo = Math.floor((MapModel.bound.getMaxX() - this.viewerBound.getMaxX()) / (MapModel.bound.getWidth() / this.borderTilesNum));
			columnTo = columnTo<0?this.borderTilesNum:(this.borderTilesNum - columnTo);
			for (var i = rowFrom;i < rowTo;i++) {
				for (var j = columnFrom;j < columnTo;j++) {
					var tile = new Tile(i, j, this.level);
					tile.setMap(this.map);
					tiles.push(tile);
				}
			}
			return tiles;
		}
	}

	this.getBorderTilesNum = function() {
		return this.borderTilesNum;
	}
	
	this.getViewerBound = function() {
		return this.viewerBound;
	}

	this.setViewerBound = function(viewerBound) {
		this.viewerBound = viewerBound;
	}

	this.getLevel = function() {
		return this.level;
	}
}

/**
 * 瓦片
 *
 * @author Tim.Wu Michael.Young
 */
function Tile(row, column, level) {
	
	//~ Field
	this.row = row;
	
	this.column = column;
	
	this.level = level;

	this.map;

	//~ Method
	/**
	 * 得到瓦片图片
	 */
	this.getSrc = function() {
		return MapModel.mapTypes[this.map.currentMapType].getSrc(this.level, this.row, this.column);
	}

	this.getRow = function() {
		return this.row;
	}

	this.getColumn = function() {
		return this.column;
	}

	this.setMap = function(map) {
		this.map = map;
	}
}

