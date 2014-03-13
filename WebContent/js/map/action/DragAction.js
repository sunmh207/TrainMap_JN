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
 * 地图移动
 *
 * @author Tim.Wu Michael.Young
 */
function DragAction(mapModel) {
	
	BaseAction.apply(this, new Array(mapModel));

	//~ Method
	/**
	 * 构造函数
	 */
	{
		// 注册 Command 对象的监听器
		command.addListener("drag", this);
	}

	this.propertyChange = function(param, newValue) {
		if (newValue != null && newValue[0] == this.model.getId()) {
			if (param == "drag") {
				var offset = newValue[1];
				if (offset.x != 0 && offset.y != 0) {
					var centerCoord = this.model.getViewerCenterCoord();
					newX = centerCoord.x - offset.x * MapModel.bound.getWidth() / (this.model.getZoom().getBorderTilesNum() * MapModel.tileSize);
					newY = centerCoord.y + offset.y * MapModel.bound.getHeight() / (this.model.getZoom().getBorderTilesNum() * MapModel.tileSize);
					var newCoord = new Coordinate(newX, newY);
					if (!newCoord.same(centerCoord)) {
						this.model.setViewerCenterCoord(newCoord);
					}
				}
			}
		}
	}
}