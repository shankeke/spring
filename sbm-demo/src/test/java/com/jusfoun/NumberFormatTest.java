package com.jusfoun;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.junit.Test;

public class NumberFormatTest {

	@Test
	public void test() throws Exception {
		// 1:数字用BigDecimal表示，然后在输出string
		String aa = "-4.99183553218834E-7";
		BigDecimal db = new BigDecimal(aa);
		String ii = db.toPlainString();
		System.out.println(ii);
		// 2:对Double类型的数字进行 格式化输出
		DecimalFormat df = new DecimalFormat("###############0.00000000#");// 最多保留几位小数，就用几个#，最少位就用0来确定
		Double bb = -4.99183553218834E-7;
		String s = df.format(bb);
		System.out.println(s);
	}

	@Test
	public void test01() {
		BigDecimal a = new BigDecimal("1.22");
		System.out.println("construct with a String value: " + a);
		BigDecimal b = new BigDecimal("2.22");
		a.add(b);
		System.out.println("aplus b is : " + a);
		a = a.add(b);
		System.out.println(a);
		System.out.println(0.06 + 0.01);
	}

}
