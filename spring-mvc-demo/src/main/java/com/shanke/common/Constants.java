package com.shanke.common;

import java.text.SimpleDateFormat;

/**
 * 描述 : 我的常量. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016年8月4日 下午10:41:22
 */
public class Constants {
	/*********************************** 字符常量 ***************************************/
	// 逗号
	public static final String MARK_COMMA = ",";
	// 单空格
	public static final String MARK_SINGLE_BLANK = " ";
	// 双空格
	public static final String MARK_DOUBLE_BLANK = "  ";
	// 制表
	public static final String MARK_TABLE = "\t";
	// 换行
	public static final String MARK_NEWLINE = "\n";

	/*********************************** 时间格式化常量 ***************************************/
	// 常用日期格式化
	public static final SimpleDateFormat DATE_SDF_FULL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// 日期
	public static final SimpleDateFormat DATE_SDF_DATE = new SimpleDateFormat("yyyy-MM-dd");
	// 时间
	public static final SimpleDateFormat DATE_SDF_TIME = new SimpleDateFormat("HH:mm:ss");
	// 精确到小时
	public static final SimpleDateFormat DATE_SDF_HOUR = new SimpleDateFormat("yyyy-MM-dd HH");
	// 精确到分钟
	public static final SimpleDateFormat DATE_SDF_SECOND = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	// 只取日期
	public static final SimpleDateFormat DATE_SDF_ONLY_DATE = new SimpleDateFormat("dd");

	/*********************************** 编码常量 ***************************************/
	// 默认编码
	public static final String ENCODE_DEFAULT = "UTF-8";
	// 中文编码
	public static final String ENCODE_CN = "GBK";
	// 英文编码s
	public static final String ENCODE_EN = "ISO-8859-1";

	/*********************************** 系统信息 ***************************************/
	/** 系统超级管理员 **/
	public static final String USER_ADMIN = "admin";
	/** 系统管理员默认密码 **/
	public static final String USER_PWD = "123456";
}