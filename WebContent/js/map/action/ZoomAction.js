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
 * 地图缩放
 *
 * @author Tim.Wu Michael.Young
 */
function ZoomAction(mapModel) {

	BaseAction.apply(this, new Array(mapModel));
	
	//~ Method
	/**
	 * 构造函数
	 */
	{
		// 注册 Command 对象的监听器
		command.addListener("zoomin", this);
		command.addListener("zoomout", this);
		command.addListener("sliderzoom", this);
	}

	this.propertyChange = function(param, newValue) {
		if (param == "zoomin") {
			if (newValue != null && newValue == this.model.getId()) {
				this.zoomin();
			}
		}
		if (param == "zoomout") {
			if (newValue != null && newValue == this.model.getId()) {
				this.zoomout();
			}
		}
		if (param == "sliderzoom") {
			if (newValue != null && newValue[0] == this.model.getId()) {
				this.sliderzoom(newValue[1]);
			}
		}
	}
	
	/**
	 * 地图放大
	 */
	this.zoomin = function() {
		var level = this.model.getZoom().getLevel();
		if (level < MapModel.maxZoomLevel) {
			this.model.setZoom(new Zoom(level + 1));
		}
	}

	/**
	 * 地图缩小
	 */
	this.zoomout = function() {
		var level = this.model.getZoom().getLevel();
		if (level > 1) {
			this.model.setZoom(new Zoom(level - 1));
		}
	}
	
	/**
	 * 滑块拖动缩放
	 */
	this.sliderzoom = function(newLevel) {
		var level = this.model.getZoom().getLevel();
		if (level != newLevel) {
			this.model.setZoom(new Zoom(newLevel));
		}
	}
}