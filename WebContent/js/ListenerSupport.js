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
 * 加入监听器支持，所有需要被监听的模型对象要继承它
 * 或者拷贝它的属性(ListenerSupport.apply(this))
 * 
 * @author Tim.Wu Michael.Young
 */
function ListenerSupport() {

	//~ Field
	/** 监听者集合 An array [params] of listener */
	this.listeners = new Array();
	
	/** 参数值集合 An array [params] of values.*/
	this.values = new Array();
	
	//~ Method
	/**
	 * 增加一个监听者
	 *
	 * @param param 监听者关注参数
	 * @param listener 监听者
	 */
	this.addListener = function(param, listener) {
		if(!this.listeners[param]) {
			this.listeners[param] = new Array();
		}
		// 移除旧的
		this.removeListener(param, listener);
		// 加入新的
		this.listeners[param].push(listener);
	}
	
	/**
	 * 增加一个监听者到监听队列首位
	 */
	this.addFirstListener = function(param, listener) {
		if(!this.listeners[param]){
			this.listeners[param] = new Array();
		}
		this.removeListener(param, listener);
		this.listeners[param].unshift(listener);
	}

	/**
	 * 移除一个监听器
	 *
	 * @param param
	 * @param listener
	 */
	this.removeListener = function(param, listener) {
		if(this.listeners[param]) {
			for(var i = 0;i < this.listeners[param].length;i++){
				if(this.listeners[param][i] == listener) {
					for(var j = i;i < this.listeners[param].length - 1;i++){
						this.listeners[param][j] = this.listeners[param][j+1];
					}
					this.listeners[param].pop();
					return;
				}
			}
		}
	}
	
	/**
	 * 通知某参数的所有监听者参数改变
	 *
	 * @param param 监听者关注参数
	 * @param value 参数新的值
	 */
	this.notifyListeners = function(param, newValue) {
		if(this.listeners[param]) {
			var count = this.listeners[param].length;
			for(var i = 0;i < count;i++){
				if(this.listeners[param][i]) {
					// 执行监听者的方法
					this.listeners[param][i].propertyChange(param, newValue);
				}
			}
		}
	}
	
	/**
	 * 改变某关注参数的值
	 *
	 * @param param 要改变的参数
	 * @parma value 参数新的值
	 */
	this.firePropertyChange = function(param, newValue) {
		this.values[param] = newValue;
		this.notifyListeners(param, newValue);
	}
	
	/**
	 * 获得某关注参数的值
	 */
	this.getParamValue = function(param) {
		return this.values[param];
	}
}