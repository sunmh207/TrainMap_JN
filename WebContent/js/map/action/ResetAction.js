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
function ResetAction(mapModel) {

	BaseAction.apply(this, new Array(mapModel));
	
	//~ Method
	/**
	 * 构造函数
	 */
	{
		// 注册 Command 对象的监听器
		command.addListener("reset", this);
	}

	this.propertyChange = function(param, newValue) {
		if (newValue != null && newValue == this.model.getId()) {
			if (param == "reset") {
				this.model.reset();
			}
		}
	}
}