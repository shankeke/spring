package com.shanke.dubbo.utils.encrypt;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 描述 : DES加解密. <br>
 * <p>
 * 
 * Copyright (c) 2016 优识云创(YSYC)
 * 
 * @author Uknower-yjw
 * @date 2016-7-3 下午6:18:08
 */
public class Des {
	private static final String Algorithm = "DES"; // 定义 加密算法,可用
													// DES,DESede,Blowfish

	// src为被加密的数据缓冲区（源）
	public static byte[] encryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// keybyte为加密密钥，长度为24字节
	// src为加密后的缓冲区
	public static byte[] decryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// 转换成十六进制字符串
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + "";
		}
		return hs.toUpperCase();
	}

	// 16 进制 转 2 进制
	public static byte[] hex2byte(String hex) throws IllegalArgumentException {
		if (hex.length() % 2 != 0) {
			throw new IllegalArgumentException();
		}
		char[] arr = hex.toCharArray();
		byte[] b = new byte[hex.length() / 2];
		for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
			String swap = "" + arr[i++] + arr[i];
			int byteint = Integer.parseInt(swap, 16) & 0xFF;
			b[j] = new Integer(byteint).byteValue();
		}
		return b;
	}

	@SuppressWarnings("unused")
	private static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	// 加密
	@SuppressWarnings("restriction")
	public static String Encrypt(String str, byte[] key) {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		byte[] encrypt = encryptMode(key, str.getBytes());
		return byte2hex(encrypt);
	}

	// 加密
	@SuppressWarnings("restriction")
	public static byte[] EncryptRetByte(byte[] src, byte[] key) {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		byte[] encrypt = encryptMode(key, src);
		return encrypt;
	}

	// 解密
	@SuppressWarnings("restriction")
	public static String Decrypt(String str, byte[] key) {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		byte[] decrypt = decryptMode(key, hex2byte(str));
		return new String(decrypt);
	}

	/**
	 * 描述 : 加解密测试. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-7-4 下午2:48:32
	 */
	private static void test() {
		String key = "0001000200030004";

		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String jdbc_username = "JXTEST";
		String jdbc_password = "JXTEST";

		// 1.加密
		String encrypt = Encrypt(jdbc_driver, hex2byte(key));
		System.out.println(encrypt);
		String encrypt2 = Encrypt(jdbc_url, hex2byte(key));
		System.out.println(encrypt2);
		String encrypt3 = Encrypt(jdbc_username, hex2byte(key));
		System.out.println(encrypt3);
		String encrypt4 = Encrypt(jdbc_password, hex2byte(key));
		System.out.println(encrypt4);
		System.out.println();
		// 2.解密
		System.out.println(Decrypt(encrypt, hex2byte(key)));
		System.out.println(Decrypt(encrypt2, hex2byte(key)));
		System.out.println(Decrypt(encrypt3, hex2byte(key)));
		System.out.println(Decrypt(encrypt4, hex2byte(key)));
	}

	public static void main(String arg[]) {
		// String str = "qwertyuiopasdfghjklzxcvbnm";
		// String strKey = "0002000200020002";
		// String s3 = Encrypt(str, hex2byte(strKey));
		// String s4 = Decrypt(s3, hex2byte(strKey));
		// System.out.println(s3);
		// System.out.println(s4);

		test();

		String key = "0001000200030004";
		System.out.println(Encrypt("itax", hex2byte(key)));
		System.out.println(Encrypt("itaxjx!@#$", hex2byte(key)));

		System.out.println(Encrypt("root", hex2byte(key)));
		System.out.println(Encrypt("root", hex2byte(key)));
	}
}
