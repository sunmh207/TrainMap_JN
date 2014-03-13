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

/** 地理信息基本对象 */

/**
 * 坐标
 *
 * @author Tim.Wu Michael.Young
 */
function Coordinate(x, y) {

	this.x = x;

	this.y = y;
	
	/**
	 * 判断坐标是否相等
	 */
	this.same = function(coord) {
		if (this.x == coord.x && this.y == coord.y) {
			return true;
		}
		return false;
	}
	
	/**
	 * 通过中心坐标得到Bound
	 */
	this.getBound = function(boundWidth, boundHeight) {
		return new Bound(this.x - boundWidth / 2, this.x + boundWidth / 2, this.y - boundHeight / 2, this.y + boundHeight / 2);
	}

	this.getPoint = function() {
		var point = new Point();
		point.setCoord(this);
		return point;
	}

	this.toString = function() {
		return "Coordinate: " + this.x + "," + this.y;
	}

}

/**
 * 点对象
 *
 * @author Tim.Wu Michael.Young
 */
function Point(x, y) {
	
	this.coord = new Coordinate(x * 1e16, y * 1e16);

	this.getCoord = function() {
		return this.coord;
	}

	this.setCoord = function(coord) {
		this.coord = coord;
	}
	
	/**
	 * 计算两点间距离
	 */
	this.countDistance = function(point) {
		var distance = Math.sqrt(Math.pow(this.coord.x - point.getCoord().x, 2) + Math.pow(this.coord.y - point.getCoord().y, 2));
		return distance;
	}
}

/**
 * 矩形区域
 *
 * @author Tim.Wu Michael.Young
 */
function Bound(minX, maxX, minY, maxY) {
	
	//~ Field
	/** 组成区域的四个基本坐标 */
	this.minX = minX;
	this.maxX = maxX;
	this.minY = minY;
	this.maxY = maxY;

	/** 中心坐标 */
	this.centerCoord = new Coordinate((this.minX + this.maxX) / 2, (this.minY + this.maxY) / 2);

	//~ Method
	/**
	 * 获取中心点坐标
	 */
	this.getCenterCoord = function() {
		return this.centerCoord;
	}
	
	/**
	 * 得到克隆的区域对象
	 */
	this.clone = function(coord) {
		if (coord == null || coord.same(this.centerCoord)) {
			return this;
		} else {
			var minX = this.minX + coord.x - this.centerCoord.x;
			var maxX = this.maxX + coord.x - this.centerCoord.x;
			var minY = this.minY + coord.y - this.centerCoord.y;
			var maxY = this.maxY + coord.y - this.centerCoord.y;
			return new Bound(minX, maxX, minY, maxY);
		}
	}
	
	/**
	 * 计算两个区域是否有重叠部分
	 *
	 * @param bound 要与之比较的区域
	 */
	this.isCover = function(bound) {
		if (this.getMinX() > bound.getMaxX() || this.getMaxX() < bound.getMinX() || this.getMinY() > bound.getMaxY() || this.getMaxY() < bound.getMinY()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 判断某坐标是否在该区域内
	 */
	this.isWithin = function(coord) {
		if (coord.x < this.maxX && coord.x > this.minX && coord.y < this.maxY && coord.y > this.minY) {
			return true;
		}
		return false;
	}

	this.getMinX = function() {
		return this.minX;
	}

	this.getMaxX = function() {
		return this.maxX;
	}
	
	this.getMinY = function() {
		return this.minY;
	}

	this.getMaxY = function() {
		return this.maxY;
	}

	this.getHeight = function() {
		return this.maxY - this.minY;
	}

	this.getWidth = function() {
		return this.maxX - this.minX;
	}

	this.toString = function() {
		return "Bound: " + minX + "," + maxX + "," + minY + "," + maxY;
	}
}