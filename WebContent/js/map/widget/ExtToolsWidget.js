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
 * 扩展工具 -- 滑块
 *
 * @author Tim.Wu Michael.Young
 */
function SliderWidget(model) {

	// Inherit from BaseWidget
	BaseWidget.apply(this, new Array(model));
	
	//~ Method
	{
		this.model.addListener("zoom", this);
	}

	this.paint = function() {
		// Slider 12px/per scale
		HTMLCode = '<div style="position:relative;height:' + (MapModel.maxZoomLevel * 12 + 9) + 'px;width:18px;z-index:100;overflow:hidden;" onmousedown="return false;">';
		for (var i = 0;i <= MapModel.maxZoomLevel;i++) {
			HTMLCode += '<img src="' + imgBaseDir + 'zoom_bar.gif">'
		}
		HTMLCode += '<div id="map' + this.model.getId() + '_slider" style="position:absolute;top:' + ((MapModel.maxZoomLevel - this.model.getZoom().getLevel()) * 12 + 6) + 'px;left:0px;z-index:100;" onmousedown="sliderMDown(event, $(\'map' + this.model.getId() + '_slider\'), ' + this.model.getId() + ');"><img src="' + imgBaseDir + 'slider.gif" onmousedown="return false;"></div></div>';
		var sliderbar = $('map' + this.model.getId() + '_sliderbar');
		sliderbar.innerHTML = HTMLCode;
	}
	
	this.propertyChange = function(param, newValue) {
		if (param == "zoom") {
			var slider = $('map' + this.model.getId() + '_slider');
			slider.style.top = ((MapModel.maxZoomLevel - this.model.getZoom().getLevel()) * 12 + 6) + "px";
		}
	}
}

/**
 * 滑块拖动
 */
function sliderMDown(e, slider, modelId) {
	var orgOffsetY = getOffset(slider.style.top);
	document.body.style.cursor = "move";
	var originCoord = documentCoord(e);
	if(slider.setCapture) {
		slider.setCapture();
	} else if (window.captureEvents) {
		window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);
	}

	document.onmousemove = function(e) {
		var newCoord = documentCoord(e);
		var pixelTop = (orgOffsetY + newCoord.y - originCoord.y);
		if (pixelTop > 0 && pixelTop < MapModel.maxZoomLevel * 12) {
			slider.style.top = pixelTop + "px";
		}
	}

	document.onmouseup = function(e) {
		if(slider.releaseCapture) {
			slider.releaseCapture();
		} else if (window.captureEvents) {
			window.captureEvents(Event.MOUSEMOVE|Event.MOUSEUP);
		}
		document.onmousemove = null;
		document.onmouseup = null;
		document.body.style.cursor = "";
		var sliderLevel = Math.floor(getOffset(slider.style.top) / 12);
		var sliderTop = sliderLevel * 12 + 6;
		slider.style.top = sliderTop + "px";
		command.exec("sliderzoom", new Array(modelId, MapModel.maxZoomLevel - sliderLevel));
	}
}

/**
 * 扩展工具 -- 标记
 *
 * @author Tim.Wu Michael.Young
 */
function MarkerWidget(model) {
	
	// Inherit from BaseWidget
	BaseWidget.apply(this, new Array(model));
	
	//~ Method
	{
		this.model.addListener("addoverlay", this);
		this.model.addListener("removeoverlay", this);//stanley
		this.model.addListener("zoom", this);
		this.model.addListener("move", this);
		this.model.addListener("maptype", this);
	}

	this.propertyChange = function(param, newValue) {
		if (param == "addoverlay") {
			if (newValue instanceof Marker) {
				if (MapModel.bound.isWithin(newValue.getCoord())) {
					this.addMarker(newValue);
				}
			}
		}
		
		//stanley
		if (param == "removeoverlay") {	
			if (newValue instanceof Marker) {
				//if (MapModel.bound.isWithin(newValue.getCoord())) {
					this.removeMarker(newValue);
				//}
			}
		}
		
		if (param == "zoom" || param == "move" || param == "maptype") {
			var viewerBound = this.model.getZoom().getViewerBound();
			var overlays = this.model.getOverlays();
			if (overlays) {
				Overlay.zIndex = 10;
				for (var i = 0;i < overlays.length;i++) {
					if (overlays[i] instanceof Marker) {
						if (viewerBound.isWithin(overlays[i].getCoord())) {
							this.addMarker(overlays[i]);
						}
					}
				}
			}
		}
		
	}
	
	/**
	 * 添加标注
	 *
	 * TODO: 重构重复性代码
	 */
	this.addMarker = function(marker) {
		var mapDiv = $("map_" + this.model.getId());
		var coord = marker.getCoord();
		var offsetX = (coord.x - MapModel.bound.getMinX()) * ((this.model.getZoom().getBorderTilesNum() * MapModel.tileSize) / (MapModel.bound.getWidth())) - marker.getIcon().width / 2;
		var offsetY = (MapModel.bound.getMaxY() - coord.y) * ((this.model.getZoom().getBorderTilesNum() * MapModel.tileSize) / (MapModel.bound.getHeight())) - marker.getIcon().height;
		
		//alert("Div map_"+this.model.getId()+" offsetX:"+offsetX +",offsetY:"+offsetY);
		//alert(offsetX+" = ("+coord.x +"-"+ MapModel.bound.getMinX()+") * (("+this.model.getZoom().getBorderTilesNum()+" * "+MapModel.tileSize+") / ("+MapModel.bound.getWidth()+")) - "+marker.getIcon().width+"/2" );
		//alert(offsetY+"=("+MapModel.bound.getMaxY() +"-"+ coord.y+") * (("+this.model.getZoom().getBorderTilesNum() +"*"+ MapModel.tileSize+") / ("+MapModel.bound.getHeight()+")) - "+marker.getIcon().height);
		
		var markerDiv = document.createElement("div");
		markerDiv.id = "marker_" + marker.getId();
		markerDiv.style.position = "absolute";
		markerDiv.style.left = offsetX + "px";
		markerDiv.style.top = offsetY + "px";
		//markerDiv.style.zIndex = Math.round((MapModel.bound.getMaxY() - coord.y) / 1e10);
		markerDiv.style.zIndex = Math.round((MapModel.bound.getMaxY() - coord.y) / 1e16);
		if (isIE() && marker.getIcon().src.toLowerCase().indexOf("png") != -1) {
			markerDiv.style.width = marker.getIcon().width + "px";
			markerDiv.style.height = marker.getIcon().height + "px";
			markerDiv.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src=" + marker.getIcon().src + ", sizingmethod=scale);";
		} else {
			var icon = document.createElement("img");
			icon.style.cursor = "pointer";
			icon.src = marker.getIcon().src;
			icon.onclick = function() {
				return false; 
			};
			markerDiv.appendChild(icon);
			//add by stanley
			/*a=document.createElement("div");
			a.innerHTML = '<div align="middle" style="background:blue;padding:2px;font-size:14px; font-weight:bold; color:yellow;z-index:10000;">'+marker.getName()+'</div>';
			markerDiv.appendChild(a);
			*/
			
		}
		var mapModel = this.model;
		markerDiv.onclick = function() {
			if (marker.getInfo()) {
				showInfoWindow(mapModel, marker);
			}
		};
		mapDiv.appendChild(markerDiv);
		
		//车号,利用shadow的部分属性， stanley 
		var chDiv = document.createElement("div");
		chDiv.id = "ch_" + marker.getId();
		chDiv.style.position = "absolute";
		chDiv.style.left = offsetX+ "px";
		chDiv.style.top = (offsetY + (marker.getIcon().height)) + "px";
		//chDiv.style.background="blue";
		//chDiv.style.padding="2px";
		//chDiv.style.font.size="14px";
		chDiv.style.zIndex = 10000;
		//chDiv.innerHTML=marker.getName();
		chDiv.innerHTML = '<div align="middle" style="background:blue;border: solid 2px;border-color:yellow;font-size:14px; font-weight:bold; color:yellow;">'+marker.getName()+'</div>';
		mapDiv.appendChild(chDiv);
		
		// 阴影
		/*var markerShadowDiv = document.createElement("div");
		markerShadowDiv.id = "shadow_" + marker.getId();
		markerShadowDiv.style.position = "absolute";
		markerShadowDiv.style.left = offsetX + "px";
		markerShadowDiv.style.top = offsetY + "px";
		markerShadowDiv.style.zIndex = 1;
		if (isIE() && marker.getShadowIcon().src.toLowerCase().indexOf("png") != -1) {
			markerShadowDiv.style.width = marker.getShadowIcon().width + "px";
			markerShadowDiv.style.height = marker.getShadowIcon().height + "px";
			markerShadowDiv.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src=" + marker.getShadowIcon().src + ", sizingmethod=scale);";
		} else {
			icon = document.createElement("img");
			icon.src = marker.getShadowIcon().src;
			icon.onclick = function() {
				return false;
			};
			markerShadowDiv.appendChild(icon);
		}
		mapDiv.appendChild(markerShadowDiv);
		*/
		
	}

	//add by stanley
	this.removeMarker = function(marker) {
		//alert("removeMarker"+marker.getName());
		var mapDiv = $("map_" + this.model.getId());
		var markerDiv = document.getElementById("marker_"+marker.getId());
		//var shadowDiv = document.getElementById("shadow_"+marker.getId());
		var chDiv = document.getElementById("ch_"+marker.getId());
		mapDiv.removeChild(markerDiv);
		//mapDiv.removeChild(shadowDiv);
		mapDiv.removeChild(chDiv);
	}
	
}



/**
 * 显示信息窗口
 */
function showInfoWindow(map, marker) {
	var infoWindowID = "map" + map.getId() + "_infowindow";
	var infoWindow = $(infoWindowID);
	var coord = marker.getCoord();
	var offsetX = (coord.x - MapModel.bound.getMinX()) * ((map.getZoom().getBorderTilesNum() * MapModel.tileSize) / (MapModel.bound.getWidth())) + marker.getIcon().width;
	var offsetY = (MapModel.bound.getMaxY() - coord.y) * ((map.getZoom().getBorderTilesNum() * MapModel.tileSize) / (MapModel.bound.getHeight())) - marker.getIcon().height * 1.5;
	if (infoWindow == null) {
		//create info window;
		var mapDiv = $("map_" + map.getId());
		infoWindow = document.createElement("div");
		infoWindow.id = infoWindowID;
		infoWindow.onselect = function() {
			return false;
		};
		infoWindow.style.position = "absolute";
		infoWindow.style.background = "#FFFFFF";
		infoWindow.style.border = "1px solid #999999";
		infoWindow.style.fontSize = "12px";
		infoWindow.style.padding = "2px";
		infoWindow.innerHTML = '<div align="right" style="background:#EEEEEE;padding:2px;"><img onclick="hideInfoWindown(\'' + infoWindowID + '\')" src="' + imgBaseDir + 'infowindow_close.gif"></div><div id="map' + map.getId() + '_infowindow_content" style="padding:2px;text-align:justify;"></div>';
		/*
		infoWindow.style.width = "250px";
		infoWindow.style.height = "165px";
		infoWindow.style.background = "url('" + imgBaseDir + "infowindow.gif')";
		*/
		mapDiv.appendChild(infoWindow);
	}
	//infoWindow.style.zIndex = Overlay.zIndex++;
	infoWindow.style.zIndex = Overlay.zIndex*1000;
	infoWindow.style.left = offsetX + "px";
	infoWindow.style.top = offsetY + "px";
	$("map" + map.getId() + "_infowindow_content").innerHTML = marker.getInfo();
	infoWindow.style.display = "";
}

/**
 * 隐藏信息窗口
 */
function hideInfoWindown(windowId) {
	var infoWindow = $(windowId);
	infoWindow.style.display = "none";
}


/**
 * 扩展工具 -- 多地图类型
 *
 * @author Tim.Wu Michael.Young
 */
function MapTypeWidget(model) {
	
	// Inherit from BaseWidget
	BaseWidget.apply(this, new Array(model));

	//~ Method
	{
		this.model.addListener("maptype", this);
	}

	this.propertyChange = function(param, newValue) {
		if (param == "maptype") {
			this.paint();
		}
	}

	this.paint = function() {
		var HTMLCode = '';
		for (var i = 0;i < MapModel.mapTypes.length;i++) {
			var mapType = MapModel.mapTypes[i];
			if (this.model.currentMapType == i) {
				HTMLCode += '<img src="' + mapType.getEnablePic() + '" style="cursor:pointer;"> ';
			} else {
				HTMLCode += '<img src="' + mapType.getDisablePic() + '" style="cursor:pointer;" onclick="command.exec(\'maptype\', new Array(' + this.model.getId() + ', ' + i + '))"> ';
			}
		}
		var maptypeBar = $("maptypebar_" + this.model.getId());
		maptypeBar.innerHTML = HTMLCode;
	}
}

function FootBarWidget(model) {
	// Inherit from BaseWidget
	BaseWidget.apply(this, new Array(model));
	
	//~ Method
	this.paint = function() {
	    HTMLCode = '<img src="images/footbar.jpg">';
		var footBar = $("footbar_" + this.model.getId());
		footBar.innerHTML = HTMLCode;
	}
}

function CompassWidget(model) {
	// Inherit from BaseWidget
	BaseWidget.apply(this, new Array(model));
	
	//~ Method
	this.paint = function() {
	    HTMLCode = '<img src="images/compass.png">';
		var footBar = $("compass_" + this.model.getId());
		footBar.innerHTML = HTMLCode;
	}
}
