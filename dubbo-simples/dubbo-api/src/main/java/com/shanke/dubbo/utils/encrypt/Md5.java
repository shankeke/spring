package com.shanke.dubbo.utils.encrypt;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 描述 : md5算法. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016年8月4日 下午10:15:17
 */
public class Md5 {
	// public static final String SALT = "www.shanke.com";
	public static final int hashIterations = 5;

	/*
	 * public final static String md5(String s) { char hexDigits[] = { '0', '1',
	 * '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	 * try { byte[] btInput = s.getBytes(); // 获得MD5摘要算法的 MessageDigest 对象
	 * MessageDigest mdInst = MessageDigest.getInstance("MD5"); // 使用指定的字节更新摘要
	 * mdInst.update(btInput); // 获得密文 byte[] md = mdInst.digest(); //
	 * 把密文转换成十六进制的字符串形式 int j = md.length; char str[] = new char[j * 2]; int k =
	 * 0; for (int i = 0; i < j; i++) { byte byte0 = md[i]; str[k++] =
	 * hexDigits[byte0 >>> 4 & 0xf]; str[k++] = hexDigits[byte0 & 0xf]; } return
	 * new String(str); } catch (Exception e) { e.printStackTrace(); return
	 * null; } }
	 */

	public static String md5(String source, String salt, int hashIterations) {
		return new Md5Hash(source, salt, hashIterations).toString();
	}

	public static String md5(String source, String salt) {
		return md5(source, salt, hashIterations);
	}

	public static String md5(String source) {
		return new Md5Hash(source.getBytes()).toString();
	}

	public static void main(String[] args) {
		System.out.println(md5("111111", "admin", hashIterations));
	}
}
