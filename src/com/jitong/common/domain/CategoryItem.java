package com.jitong.common.domain;

public class CategoryItem {
	private String key;
	private String display;
	private String topKey;
	private String subKey;
	private int level;

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDisplay() {
		return this.display;
	}

	public void setDisplay(String dispay) {
		this.display = dispay;
	}

	public String getTopKey() {
		return this.topKey;
	}

	public void setTopKey(String keyLv1) {
		this.topKey = keyLv1;
	}

	public String getSubKey() {
		return this.subKey;
	}

	public void setSubKey(String keyLv2) {
		this.subKey = keyLv2;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "CategoryItem [dispay=" + this.display + ", key=" + this.key
				+ ", keyLv1=" + this.topKey + ", keyLv2=" + this.subKey
				+ ", level=" + this.level + "]";
	}

	@Override
	public int hashCode() {
		return key.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof CategoryItem)) {
			return false;
		}
		CategoryItem ci = (CategoryItem) obj;
		return key.equals(ci.getKey());
	}
}
