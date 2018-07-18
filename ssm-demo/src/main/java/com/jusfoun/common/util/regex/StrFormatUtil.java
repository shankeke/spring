package com.jusfoun.common.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrFormatUtil {

	private static final String reg = "\\d+\\.\\d+\\两|\\d+\\两";
	private static final String reg2 = "\\d+\\.\\d+|\\d+";
	private static final String reg3 = "\\-\\d+\\.\\d+|\\-\\d+";
	private static final String reg4 = "\\d+\\.\\d+|\\d+";

/*	public static void main(String[] args) {
		String str = "大闸蟹 洪湖大闸蟹";
		String guigeString = getGuigeString(str);
		System.out.println(guigeString);
	}*/

	/**
	 * 获得格式化后的字符串;
	 *
	 * @param str
	 * @return
	 */
	public static String getNewString(String str, String formatOne, String formatTwo) {
		String groups = null;
		Matcher m = null;
		m = Pattern.compile(formatOne).matcher(str);
		while (m.find()) {
			String group = m.group(0);
			m = Pattern.compile(formatTwo).matcher(group);
			while (m.find()) {
				groups = m.group(0);
			}
		}
		return groups;
	}

	/**
	 * 获得规格字符串
	 *
	 * @param str
	 * @return
	 */
	public static String getGuigeNum(String str) {
		boolean contains = str.contains("两");
		boolean contains2 = str.contains("-");

		if (contains && contains2) {
			String newString = getNewString(str, reg3, reg4);
			return newString;
		} else if (contains) {
			String newString = getNewString(str, reg, reg2);
			return newString;
		}
		return str;

	}

	/**
	 * 获得数据重构后的规格数据
	 *
	 * @param str
	 * @return
	 */
	public static String getGuigeString(String str) {
		String guigeNum = getGuigeNum(str);
		if (isDouble(guigeNum)) {
			float guige = Float.parseFloat(guigeNum);
			if (rangeInDefined(guige, 0.0, 2.0)) {
				return "2.0两以下";
			} else if (rangeInDefined(guige, 2.0, 2.5)) {
				return "2.0-2.5两";
			} else if (rangeInDefined(guige, 2.5, 3.0)) {
				return "2.5-3.0两";
			} else if (rangeInDefined(guige, 3.0, 3.5)) {
				return "3.0-3.5两";
			} else if (rangeInDefined(guige, 3.5, 4.0)) {
				return "3.5-4.0两";
			}
			return "4.0两以上";
		}
		return str;
	}

	/**
	 * 判断区间
	 *
	 * @param guige
	 * @param i
	 * @param j
	 * @return
	 */
	private static boolean rangeInDefined(float guige, Double i, Double j) {
		return Math.max(i, guige) == Math.min(guige, j);

	}

	/**
	 * 判断是否为float或Double
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {
		if (null == str || "".equals(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
		return pattern.matcher(str).matches();
	}
}