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
 
/** 获取某对象位置 */
function getTop(obj){
	var t = obj.offsetTop;

	while(obj = obj.offsetParent){
		t += obj.offsetTop;
	}
	return t;
}

function getLeft(obj){
	var t = obj.offsetLeft;

	while(obj = obj.offsetParent){
		t += obj.offsetLeft;
	}
	return t;
}

/** 全局标识 */
function UniqueId(){
	this.lastId=0;
	this.getId = function(){
		this.lastId++;
		return this.lastId;
	}
}
var uid = new UniqueId();

/** 对象组装器 */
function objectAssembler(target, assembleNode, type) {
	var fieldNodes = assembleNode.selectNodes("ct:field");
	for (var i = 0;i < fieldNodes.length;i++) {
		var field = fieldNodes[i].attributes.getNamedItem("name").nodeValue;
		var value = fieldNodes[i].attributes.getNamedItem("value").nodeValue;
		var type = null;
		if (fieldNodes[i].attributes.getNamedItem("type")) {
			type = fieldNodes[i].attributes.getNamedItem("type").nodeValue;
		}
		if (type == "number") {
			eval("target." + field + "=" + value + ";");
		} else {
			eval("target." + field + "='" + value + "';");
		}
	}
}

/** 取得Document根节点对象 */
function getDomNode(configUrl) {
	var configDoc = Sarissa.getDomDocument();
	configDoc.async = false; // 是否异步
	configDoc.validateOnParse = false;
	configDoc.load(configUrl);
	if (configDoc.parseError < 0){
		alert("Error loading config document: " + configUrl );
	}
	// Set namespace for xpath
	Sarissa.setXpathNamespaces(configDoc, "xmlns:ct='" + namespace +"'");
	return configDoc.documentElement;
}

/** 引用其他脚本文件 */
function include(src) {
	HTMLCode = '<script language="javascript" src="' + src + '"></script>';
	document.write(HTMLCode);
}

/** 得到数字化的Offset值 */
function getOffset(offsetStr) {
	if (offsetStr != null && offsetStr.indexOf("px")) {
		var i = offsetStr.indexOf("px");
		return Number(offsetStr.substring(0, i));
	}
}

/** 判断浏览器类型 */
function isIE() {
	if (navigator.appName!="Microsoft Internet Explorer") {
		return false;
	}
	return true;
}