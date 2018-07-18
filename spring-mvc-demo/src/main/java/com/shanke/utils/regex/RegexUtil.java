package com.shanke.utils.regex;

import java.util.regex.Pattern;

/**
 * 描述 : 常用正则验证. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016-8-29 下午7:23:50
 */
public class RegexUtil {
	public static final String REGEX_PHONE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	public static final String REGEX_INTERNETURL = "^http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";// 或[a-zA-z]+://[^\s]*
	public static final String REGEX_USERNAME = "^[\u4e00-\u9fa5A-Za-z0-9_]{6,20}$";// 用户名为6到20个字符，可使用汉字、英文字母、数字及下划线！
	public static final String REGEX_PASSWORD = "^[0-9A-Za-z_]{6,}$";// 密码至少6个字符，可使用字母、数字及下划线！
	public static final String REGEX_IDNUMBER = "^(\\d{6})(18|19|20)?(\\d{2})([01]\\d)([0123]\\d)(\\d{3})(\\d|X)?$";// 身份证验证，比较严格的验证

	// html标签正则
	public static final String REGEX_HTML_EMBED = "<embed.*?>";// flash元素
	public static final String REGEX_HTML_IMG = "<img.*?>";// 图片
	public static final String REGEX_HTML_A = "<a.*?>";// 链接

	/**
	 * 正则验证
	 * 
	 * @param regix
	 *            正则表达式
	 * @param s
	 *            验证字符串
	 * @return
	 */
	public static boolean check(String regix, String s) {
		return Pattern.compile(regix).matcher(s).matches();
	}

	/**
	 * 判定邮箱正确性
	 * 
	 * @param email
	 *            邮箱字段
	 * @return
	 */
	public static boolean checkEmail(String email) {
		return check(REGEX_EMAIL, email);
	}

	/**
	 * 判定手机号码正确性
	 * 
	 * @param phone
	 *            用户名字段
	 * @return
	 */
	public static boolean checkPhone(String phone) {
		return check(REGEX_PHONE, phone);
	}

	/**
	 * 判定用户名正确性
	 * 
	 * @param username
	 *            用户名字段
	 * @return
	 */
	public static boolean checkUsername(String username) {
		return check(REGEX_USERNAME, username);
	}

	/**
	 * 判定用户密码正确性
	 * 
	 * @param password
	 *            用户密码字段
	 * @return
	 */
	public static boolean checkPassword(String password) {
		return check(REGEX_PASSWORD, password);
	}

	/**
	 * 身份证号码验证
	 * 
	 * @param IDNumber
	 *            身份证号码
	 * @return
	 */
	public static boolean checkIDNumber(String IDNumber) {
		return check(REGEX_IDNUMBER, IDNumber);
	}

	/**
	 * 网络路径验证
	 * 
	 * @param url
	 *            网络路径
	 * @return
	 */
	public static boolean checkInternetUrl(String internetUrl) {
		return check(REGEX_INTERNETURL, internetUrl);
	}
}