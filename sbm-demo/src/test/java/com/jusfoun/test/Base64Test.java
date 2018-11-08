package com.jusfoun.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.junit.Test;

public class Base64Test {

	@Test
	public void test() {
		Encoder encoder = Base64.getEncoder();
		System.out.println("移动端:Basic " + encoder.encodeToString("app_client:123456".getBytes()));
		System.out.println("大数据端:Basic " + encoder.encodeToString("bigdata_client:123456".getBytes()));
		System.out.println("PC端:Basic " + encoder.encodeToString("web_client:123456".getBytes()));
	}

	@Test
	public void test1() {
		InputStream ins = null;
		String[] cmd = new String[] { "cmd.exe", "/C", "ipconfig" }; // 命令
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			ins = process.getInputStream(); // 获取执行cmd命令后的信息
			BufferedReader reader = new BufferedReader(new InputStreamReader(ins));
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line); // 输出
			}
			int exitValue = process.waitFor();
			System.out.println("返回值：" + exitValue);
			process.getOutputStream().close(); // 不要忘记了一定要关
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
