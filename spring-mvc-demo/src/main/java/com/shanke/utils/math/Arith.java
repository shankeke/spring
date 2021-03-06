package com.shanke.utils.math;

import java.math.BigDecimal;

/**
 * 描述 : 进行BigDecimal对象的加减乘除，四舍五入等运算的工具类. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016-5-20 下午3:23:28
 */
public class Arith {

	/**
	 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精 确的浮点数运算，包括加减乘除和四舍五入。
	 */
	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;

	// 这个类不能实例化
	private Arith() {
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @param scale
	 *            小数点后保留几位
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2, int scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return add(b1, b2, scale).doubleValue();
	}

	public static BigDecimal add(BigDecimal v1, BigDecimal v2, int scale) {
		return v1.add(v2).setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 描述 :提供多个数据精确的加法运算。. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-5-20 下午3:51:12
	 * @param vs
	 *            数据数组
	 * @return 计算结果
	 */
	public static double add(double... vs) {
		return add(DEF_DIV_SCALE, vs);
	}

	/**
	 * 提供精确的加法运算。
	 * 
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @param scale
	 *            小数点后保留几位
	 * @return 两个参数的和
	 */
	public static double add(int scale, double... vs) {
		BigDecimal zero = new BigDecimal("0");
		BigDecimal b = null;
		for (double d : vs) {
			b = new BigDecimal(Double.toString(d));
			zero = zero.add(b);
		}
		return zero.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static double add(BigDecimal... vs) {
		return add(DEF_DIV_SCALE, vs);
	}

	/**
	 * 描述 : 精确加法运算. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-5-27 下午12:04:11
	 * @param scale
	 *            精度
	 * @param vs
	 *            加数数组
	 * @return
	 */
	public static double add(int scale, BigDecimal... vs) {
		BigDecimal zero = new BigDecimal("0");
		for (BigDecimal b : vs) {
			if (b != null) {
				zero = zero.add(b);
			}
		}
		return zero.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static double add(int scale, double v1, BigDecimal v2) {
		return add(scale, new BigDecimal(Double.toString(v1)), v2);
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 提供精确的减法运算。
	 * 
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @param scale
	 *            小数点后保留几位
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2, int scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).setScale(scale, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 提供精确的乘法运算。
	 * 
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @param scale
	 *            小数点后保留几位
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2, int scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}

	/**
	 * 描述 : 提供多个数据精确的乘法运算. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-5-20 下午3:57:35
	 * @param vs
	 *            数据数组
	 * @return
	 */
	public static double mul(double... vs) {
		return mul(DEF_DIV_SCALE, vs);
	}

	/**
	 * 描述 : 提供多个数据精确的乘法运算. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-5-20 下午3:55:45
	 * @param scale
	 *            精度
	 * @param vs
	 *            数据数组
	 * @return
	 */
	public static double mul(int scale, double... vs) {
		BigDecimal one = new BigDecimal("1");
		BigDecimal b1 = null;
		for (double d : vs) {
			b1 = new BigDecimal(Double.toString(d));
			one = one.multiply(b1);
		}
		return one.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	 * 
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 返回两个数中大的一个值
	 * 
	 * @param v1
	 *            需要被对比的第一个数
	 * @param v2
	 *            需要被对比的第二个数
	 * @return 返回两个数中大的一个值
	 */
	public static double max(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.max(b2).doubleValue();
	}

	/**
	 * 返回两个数中小的一个值
	 * 
	 * @param v1
	 *            需要被对比的第一个数
	 * @param v2
	 *            需要被对比的第二个数
	 * @return 返回两个数中小的一个值
	 */
	public static double min(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.min(b2).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的类型转换(Float)
	 * 
	 * @param v
	 *            需要被转换的数字
	 * @return 返回转换结果
	 */
	public static float convertsToFloat(double v) {
		BigDecimal b = new BigDecimal(v);
		return b.floatValue();
	}

	/**
	 * 提供精确的类型转换(Int)不进行四舍五入
	 * 
	 * @param v
	 *            需要被转换的数字
	 * @return 返回转换结果
	 */
	public static int convertsToInt(double v) {
		BigDecimal b = new BigDecimal(v);
		return b.intValue();
	}

	/**
	 * 提供精确的类型转换(Long)
	 * 
	 * @param v
	 *            需要被转换的数字
	 * @return 返回转换结果
	 */
	public static long convertsToLong(double v) {
		BigDecimal b = new BigDecimal(v);
		return b.longValue();
	}

	/**
	 * 精确对比两个数字
	 * 
	 * @param v1
	 *            需要被对比的第一个数
	 * @param v2
	 *            需要被对比的第二个数
	 * @return 如果两个数一样则返回0，如果第一个数比第二个数大则返回1，反之返回-1
	 */
	public static int compareTo(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.compareTo(b2);
	}

	public static void main(String[] args) {
		// System.out.println(add(1223.23, 0.03));
		// System.out
		// .println(add(2, 1223.23, 0.03, 23.12, 1212.1212, 1212.455455));
		// System.out.println(sub(1223.23, 0.03, 2));
		// System.out.println(mul(1223.23, 0.03));
		System.out.println(mul(2, 1223.23, 1.03, 2.323, 2323.2323));
		// System.out.println(div(1223.23, 1.03, 2));
	}
}