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
 * 抽象监听者供继承，子监听者需实现自己的 propertyChange 方法
 * 
 * @author Tim.Wu Michael.Young
 */
function Listener() {
	
	/**
	 * 被监听对象属性变化时执行的方法，在具体监听者中实现该方法
	 *
	 * @param param 监听的参数
	 * @param newValue 该参数新的值
	 */
	this.propertyChange = function(param, newValue) {
	}
}