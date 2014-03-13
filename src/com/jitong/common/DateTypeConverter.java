package com.jitong.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;

public class DateTypeConverter extends StrutsTypeConverter {
	{
		System.out.println("DateTypeConverter initialized.");
	}
	SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	Object[][] acceptablePatterns = {
			{ new SimpleDateFormat("yyyy/MM/dd"),
					"[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}" },
			{ new SimpleDateFormat("yyyy-MM-dd"),
					"[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2}" },
			{ new SimpleDateFormat("yyyy.MM.dd"),
					"[0-9]{4}\\.[0-9]{1,2}\\.[0-9]{1,2}" },
			{ new SimpleDateFormat("yyyy。MM。dd"),
					"[0-9]{4}。[0-9]{1,2}\\。[0-9]{1,2}" },
			{ new SimpleDateFormat("yyyy年MM月dd日"),
					"[0-9]{4}年[0-9]{1,2}\\月[0-9]{1,2}日" },
			{ new SimpleDateFormat("yy/MM/dd"),
					"[0-9]{2}/[0-9]{1,2}/[0-9]{1,2}" },
			{ new SimpleDateFormat("yy-MM-dd"),
					"[0-9]{2}\\-[0-9]{1,2}\\-[0-9]{1,2}" },
			{ new SimpleDateFormat("yy。MM。dd"),
					"[0-9]{2}\\。[0-9]{1,2}\\。[0-9]{1,2}" },
			{ new SimpleDateFormat("yy年MM月dd日"),
					"[0-9]{2}\\年[0-9]{1,2}\\月[0-9]{1,2}日" }, };

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {

		if (values == null || values.length == 0 || values[0] == null)
			return null;
		String value = values[0].trim();
		System.out.println(value);
		for (Object[] arr : acceptablePatterns) {
			String pattern = (String) arr[1];
			SimpleDateFormat format = (SimpleDateFormat) arr[0];
			if (value.matches(pattern))
				try {
					Date date = format.parse(value);
					System.out.println("" + date);
					return date;
				} catch (ParseException e) {
					throw new TypeConversionException(value + "不是正确的日期格式。");
				}
		}
		throw new TypeConversionException(value + "不是正确的日期格式。");
	}

	@Override
	public String convertToString(Map context, Object o) {
		if (o == null)
			return "";
		Date date = (Date) o;
		return format.format(date);
	}

	public static void main(String[] args) {
		String[] dates = { "2010-1-1", "2001.12.21", "10年9月12日", "20。9。12",
				"31-9-12", "40-9-12", "50-9-12" };
		DateTypeConverter convert = new DateTypeConverter();

		for (int i = 0; i < dates.length; i++) {
			convert.convertFromString(null, new String[] { dates[i] }, null);
		}
	}
}
