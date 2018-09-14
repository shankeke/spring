package com.jusfoun.jasypt;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.junit.Test;

public class EncypterTest {

	/**
	 * 加解密BasicTextEncryptor时正常，但是加解密 StrongTextEncryptor 时，提示缺少jce
	 * Java中安全组件被分成了两部分: 不含加密功能的JCA（Java Cryptography Architecture
	 * ）和含加密功能的JCE（Java Cryptography Extension）。 需要重新下载相应版本的jce进行安装。
	 */
	@Test
	public void test0() {
		// 加密
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("password");
		String newPassword = textEncryptor.encrypt("123456");
		System.out.println(newPassword);
		// 解密
		BasicTextEncryptor textEncryptor2 = new BasicTextEncryptor();
		textEncryptor2.setPassword("password");
		String oldPassword = textEncryptor2.decrypt(newPassword);
		System.out.println(oldPassword);
		System.out.println("--------------------------");
	}

	@Test
	public void test1() {
		StrongTextEncryptor ste = new StrongTextEncryptor();
		// 加密
		ste.setPassword("password");
		String encyptedResult = ste.encrypt("123456");
		System.out.println("encyptedResult:" + encyptedResult);
		// 解密
		String dencyptedResult = ste.decrypt(encyptedResult);
		System.out.println(dencyptedResult);
	}

	@Test
	public void test2() {
		// 加密工具
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		// 加密配置
		EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
		config.setAlgorithm("PBEWithMD5AndDES");
		// 自己在用的时候更改此密码
		config.setPassword("apdplat");
		// 应用配置
		encryptor.setConfig(config);
		String plaintext = "root";
		// 加密
		String ciphertext = encryptor.encrypt(plaintext);
		System.out.println(plaintext + " : " + ciphertext);
	}

	@Test
	public void test3() {
		// 加密工具
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		// 加密配置
		EnvironmentStringPBEConfig config = new EnvironmentStringPBEConfig();
		config.setAlgorithm("PBEWithMD5AndDES");
		// 自己在用的时候更改此密码
		config.setPassword("apdplat");
		// 应用配置
		encryptor.setConfig(config);
		String ciphertext = "azL9Cyp9H62r3eUgZ+TESw==";
		// 解密
		String plaintext = encryptor.decrypt(ciphertext);
		System.out.println(ciphertext + " : " + plaintext);
	}
}
