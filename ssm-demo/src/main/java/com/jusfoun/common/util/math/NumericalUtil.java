package com.jusfoun.common.util.math;

import java.util.Stack;

/**
 * 描述 : 数字工具,进行42以下的任意进制转换. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年10月19日 下午3:30:29
 */
public class NumericalUtil {
	private static final String C_CODES_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$.`";

	/**
	 * 描述 : <方法描述>. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月19日 下午3:29:17
	 * @param src
	 *            原十进制数据
	 * @return 目标进制的结果数据
	 */
	public static String int2CodeStr(long src) {
		return int2CodeStr(src, C_CODES_STRING.length());
	}

	/**
	 * 描述 :将10进制转换为任意进制. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月19日 下午3:27:38
	 * @param src
	 *            原十进制数据
	 * @param base
	 *            目标进制 <=42
	 * @return 目标进制的结果数据
	 */
	public static String int2CodeStr(long src, int base) {
		int w_code_len = C_CODES_STRING.length();
		if (base > w_code_len) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		Stack<String> s = new Stack<String>();
		while (src != 0) {
			s.push(C_CODES_STRING.charAt((int) (src % base)) + "");
			src /= base;
		}
		while (!s.empty()) {
			sb.append(s.pop());
		}
		return sb.length() == 0 ? "0" : sb.toString();
	}

	/**
	 * 描述 : 任何进制转换. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月19日 下午3:24:56
	 * @param str
	 *            需要转换的字串
	 * @param srcBase
	 *            原进制
	 * @param destBase
	 *            目标进制
	 * @return 转化后进制的数据串
	 */
	public static String baseConvert(String str, int srcBase, int destBase) {
		if (srcBase == destBase) {
			return str;
		}
		char[] chars = str.toCharArray();
		int len = chars.length;
		if (destBase != 10) {// 目标进制不是十进制 先转化为十进制
			str = baseConvert(str, srcBase, 10);
		} else {
			long n = 0;
			for (int i = len - 1; i >= 0; i--) {
				n += C_CODES_STRING.indexOf(chars[i]) * Math.pow(srcBase, len - i - 1);
			}
			return String.valueOf(n);
		}
		return int2CodeStr(Integer.valueOf(str), destBase);
	}

	/*
	 * public static void main(String[] args) {
	 * System.out.println(int2CodeString(120L, 16));
	 * System.out.println(BaseConvert("00B3", 16, 10)); }
	 */

}
