package com.jitong.common.util;

import java.math.BigDecimal;

public class JTMath {
	public static String add(String v1, String v2) {
		v1 = filterNullToZero(v1);
		v2 = filterNullToZero(v2);

		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);

		return b1.add(b2).toString();
	}

	public static String filterNullToZero(String str) {
		String s = "";
		if (str == null || "".equals(str)) {
			s = "0";
		} else {
			s = str.trim();
		}
		return s;
	}
}
