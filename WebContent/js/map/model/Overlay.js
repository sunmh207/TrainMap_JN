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
 * 地图覆盖物抽象类
 *
 * @author Tim.Wu Michael.Young
 */
function Overlay(point, icon, shadowIcon) {
	
	// Inherit from BaseModel
	BaseModel.apply(this);
	
	/** 覆盖物的区域范围 */
	this.bound;
	
	/** 坐标 */
	this.coord = point.getCoord();
	
	/** 图形 */
	this.icon = icon;
	
	/** 阴影图形 */
	this.shadowIcon = shadowIcon;

	this.events = new Array();

	//~ Method
	this.getCoord = function() {
		return this.coord;
	}

	this.getIcon = function() {
		return this.icon;
	}
	
	this.getShadowIcon = function() {
		return this.shadowIcon;
	}

	this.getId = function() {
		if (this.id) {
			return this.id;
		}
	}
}

Overlay.zIndex = 10;

/**
 * 覆盖物图标
 *
 * @author Tim.Wu Michael.Young
 */
function Icon(width, height, src) {
	this.width = width;
	this.height = height;
	this.src = src;
}


/**
 * 标记
 *
 * @author Tim.Wu Michael.Young
 *
 * @param point 标记点
 * @param markerIcon 标记图标
 */
function Marker(point, iconSet) {
	
	// Inherit from Overlay
	Overlay.apply(this, new Array(point, iconSet[0], iconSet[1]));
	
	this.id = new Date().getTime();

	this.name;
	this.info;

	//~ Method
	this.setName = function(name) {
		this.name = name;
	}

	this.getName = function() {
		return this.name;
	}
	
	this.setInfo = function(info) {
		this.info = info;
	}

	this.getInfo = function() {
		return this.info;
	}
}

Marker.LARGE = new Array(new Icon(20, 34, imgBaseDir + "marker_large.png"), new Icon(37, 34, imgBaseDir + "marker_large_shadow.png"));
Marker.SMALL = new Array(new Icon(12, 20, imgBaseDir + "marker_small.png"), new Icon(22, 20, imgBaseDir + "marker_small_shadow.png"));

Marker.TRAIN_RED_BIG = new Array(new Icon(128, 70, imgBaseDir + "marker_train_red_big.png"), new Icon(1, 1, imgBaseDir + "1x1.png"));
Marker.TRAIN_RED_MIDDLE = new Array(new Icon(64, 35, imgBaseDir + "marker_train_red_middle.png"), new Icon(1, 1, imgBaseDir + "1x1.png"));
Marker.TRAIN_RED_SMALL = new Array(new Icon(32, 18, imgBaseDir + "marker_train_red_small.png"), new Icon(1, 1, imgBaseDir + "1x1.png"));
Marker.TRAIN_GREEN_BIG = new Array(new Icon(128,70, imgBaseDir + "marker_train_green_big.png"), new Icon(1, 1, imgBaseDir + "1x1.png"));
Marker.TRAIN_GREEN_MIDDLE = new Array(new Icon(64,35, imgBaseDir + "marker_train_green_middle.png"), new Icon(1, 1, imgBaseDir + "1x1.png"));
Marker.TRAIN_GREEN_SMALL = new Array(new Icon(32, 18, imgBaseDir + "marker_train_green_small.png"), new Icon(1, 1, imgBaseDir + "1x1.png"));
Marker.TRAIN_NOICON = new Array(new Icon(32, 1, imgBaseDir + "1x1.png"), new Icon(1, 1, imgBaseDir + "1x1.png"));
