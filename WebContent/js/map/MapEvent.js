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
 * 触发自定义事件
 * 
 * @author Tim.Wu Michael.Young
 */
function MapEvent() {
}

MapEvent.getEventCoord = function(e, mapModel) {
	var mouseoffset = documentCoord(e);
	var mapDiv = $("map_" + mapModel.getId());
	var relativeOffsetX = mouseoffset.x - getLeft(mapDiv.parentNode) - getOffset(mapDiv.style.left);
	var relativeOffsetY = mouseoffset.y - getTop(mapDiv.parentNode) - getOffset(mapDiv.style.top);
	var eventX = MapModel.bound.getMinX() + relativeOffsetX * (MapModel.bound.getWidth() / (MapModel.tileSize * mapModel.getZoom().getBorderTilesNum()));
	var eventY = MapModel.bound.getMaxY() - relativeOffsetY * (MapModel.bound.getHeight() / (MapModel.tileSize * mapModel.getZoom().getBorderTilesNum()));
	return new Coordinate(eventX, eventY);
}

MapEvent.addListener = function(source, eventName, func) {
	if (source.events) {
		source.events[eventName] = func;
		source.firePropertyChange("event.addlistener", new Array(source, eventName));
	}
}

// constants
MapEvent.CLICK = "event.click";
MapEvent.DBLCLICK = "event.dblclick";
MapEvent.MOVESTART = "event.movestart";
MapEvent.MOVEEND = "event.moveend";