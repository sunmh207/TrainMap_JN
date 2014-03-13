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
 * 基本地图工具
 *
 * @author Tim.Wu Michael.Young
 */
function ToolsWidget(model) {

	// Inherit from BaseWidget
	BaseWidget.apply(this, new Array(model));

	//~ Field
	this.icon;

	//~ Method

	this.paint = function() {
		// go up
		var HTMLCode = '<table border="0" cellspacing="0" cellpadding="1"><tr><td align="center" colspan="3"><img style="cursor:pointer" src="' + imgBaseDir + 'icon_goup.gif" onclick="command.exec(\'goup\', ' + this.model.getId() + ');"></td></tr><tr><td><img style="cursor:pointer" src="' + imgBaseDir + 'icon_goleft.gif" onclick="command.exec(\'goleft\', ' + this.model.getId() + ');"></td><td><img style="cursor:pointer" src="' + imgBaseDir + 'icon_default.gif" onclick="command.exec(\'reset\', ' + this.model.getId() + ');"></td><td><img style="cursor:pointer" src="' + imgBaseDir + 'icon_goright.gif" onclick="command.exec(\'goright\', ' + this.model.getId() + ');"></td></tr><tr><td align="center" colspan="3"><img style="cursor:pointer" src="' + imgBaseDir + 'icon_godown.gif" onclick="command.exec(\'godown\', ' + this.model.getId() + ');"></td></tr><tr><td height="5"></td></tr><tr><td align="center" colspan="3"><table border="0" cellspacing="0" cellpadding="0"><tr><td align="center"><img style="cursor:pointer" src="' + imgBaseDir + 'icon_zoomin.gif" onclick="command.exec(\'zoomin\', ' + this.model.getId() + ');"></td></tr><tr><td style="height:2px;" id="map' + this.model.getId() + '_sliderbar" align="center" onmousedown="return false;"></td></tr><tr><td align="center"><img style="cursor:pointer" src="' + imgBaseDir + 'icon_zoomout.gif" onclick="command.exec(\'zoomout\', ' + this.model.getId() + ');"></td></tr></table></td></tr></table>';
		var toolbar = $("toolbar_" + this.model.getId());
		toolbar.innerHTML = HTMLCode;
	}
	
	this.propertyChange = function(param, newValue) {
	}
}

/*
var smoothMoveTarget;
var smoothMoveStep = 20;
var smoothMoveTimes = 0;
var smoothMoveThread;
var smoothMoveWay;
var smoothMoveMapId;

function smoothMove(way, mapId) {
	smoothMoveTarget = $("map_" + mapId);
	smoothMoveWay = way;
	smoothMoveMapId = mapId;
	smoothMoveTimes = 0;
	smoothMoveAction();
}

function smoothMoveAction() {
	if (smoothMoveTarget) {
		if (smoothMoveWay == "goup") {
			smoothMoveTarget.style.top = (getOffset(smoothMoveTarget.style.top) + smoothMoveStep) + "px";
		}
		if (smoothMoveWay == "godown") {
			smoothMoveTarget.style.top = (getOffset(smoothMoveTarget.style.top) - smoothMoveStep) + "px";
		}
		if (smoothMoveWay == "goleft") {
			smoothMoveTarget.style.left = (getOffset(smoothMoveTarget.style.left) + smoothMoveStep) + "px";
		}
		if (smoothMoveWay == "goright") {
			smoothMoveTarget.style.left = (getOffset(smoothMoveTarget.style.left) - smoothMoveStep) + "px";
		}
		if (smoothMoveTimes == 10) {
			clearTimeout(smoothMoveThread);
			command.exec(smoothMoveWay, smoothMoveMapId);
		} else {
			smoothMoveTimes++;
			setTimeout("smoothMoveAction()", 5);
		}
	}
}
*/