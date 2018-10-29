package com.jusfoun;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
	
	@Test
	public void test02() {
		double initLots = 1, LotExponent = 2, initPrice = 1.200, pipStep = 0.03;
		int pipTimes = 5;
		System.out.println(avg(initLots, LotExponent, initPrice, pipStep, pipTimes, true));
		System.out.println(avg(initLots, LotExponent, initPrice, pipStep, pipTimes, false));
	}

	/**
	 * 说明：计算平均价格. <br>
	 * 
	 * @author yjw@jusfoun.com
	 * @date 2018年10月26日 下午3:39:57
	 * @param initLots
	 *            初始仓位
	 * @param LotExponent
	 *            初始系数
	 * @param initPrice
	 *            初始价格
	 * @param pipStep
	 *            价格步长
	 * @param pipTimes
	 *            加仓次数
	 * @param isL
	 *            是否多头，多头为true，空头为false
	 * @return 平均价格
	 */
	public double avg(double initLots, double LotExponent, double initPrice, double pipStep, int pipTimes, boolean isL) {
		double total = initLots * initPrice, count = initLots;
		for (int i = 1; i <= pipTimes; i++) {
			if (isL) {
				initPrice += pipStep * 1;// 当前价位
			} else {
				initPrice -= pipStep * 1;// 当前价位
			}
			initLots *= LotExponent;// 当前仓位
			count += initLots;// 总的仓位
			total += initPrice * initLots;// 总资金
			System.out.println(String.format("当前价格：%.2f，当前持仓总数：%.2f，当前占用总资金：%.2f。", initPrice, initLots, total));
		}
		return new BigDecimal(total / count).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

}
