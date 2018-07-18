package com.jusfoun.client;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.jusfoun.common.util.date.DateUtil;

public class Test {

	@org.junit.Test
	public void testUUID() {

		String string = UUID.randomUUID().toString();
		System.out.println(string);

	}

	public void btweenDate(int minutes) {
		DateTime now = new DateTime();
		Date startTime = now.minusMinutes(minutes).toLocalDateTime().toDate();
		Date endTime = now.toLocalDateTime().toDate();
		String startTimeStr = DateUtil.format(DateUtil.DATE_SDF_FULL, startTime);
		String endTimeStr = DateUtil.format(DateUtil.DATE_SDF_FULL, endTime);
		System.out.println("startTime=" + startTimeStr);
		System.out.println("endTime=" + endTimeStr);

	}

	@org.junit.Test
	public void BtesttweenDate() {
		btweenDate(120);
	}

	@org.junit.Test
	public void testRandom() {

		for (int i = 0; i <= 100; i++) {
			String create15 = create15();

			System.out.println(create15);

		}

	}

	public static String createNewCode() {
		AtomicInteger ATOMIC = new AtomicInteger(0);
		long currentTimeMillis = System.currentTimeMillis();
		int intValue = ATOMIC.incrementAndGet();
		if (intValue > 9999) {
			intValue = 0;
			ATOMIC.set(intValue);
		}
		return currentTimeMillis + StringUtils.leftPad(intValue + "", 4, "0");
	}

	public String create15() {
		String str62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int pixLen = 36;
		int pixOne = 0;
		int pixTwo = 0;
		int pixThree = 0;
		int pixFour = 0;
		StringBuilder sb = new StringBuilder(15);// 创建一个StringBuilder
		sb.append(Long.toHexString(System.currentTimeMillis()));// 先添加当前时间的毫秒值的16进制
		pixFour++;
		if (pixFour == pixLen) {
			pixFour = 0;
			pixThree++;
			if (pixThree == pixLen) {
				pixThree = 0;
				pixTwo++;
				if (pixTwo == pixLen) {
					pixTwo = 0;
					pixOne++;
					if (pixOne == pixLen) {
						pixOne = 0;
					}
				}
			}
		}
		return sb.append(str62.charAt(pixOne)).append(str62.charAt(pixTwo)).append(str62.charAt(pixThree))
				.append(str62.charAt(pixFour)).toString();
	}

	@org.junit.Test
	public void testNow() {
		DateTime now = DateTime.now();
		String string = now.toString(DateUtil.DATE_SDF_DATE);
		System.out.println(string);
		String string2 = now.minusDays(1).toString(DateUtil.DATE_SDF_DATE);
		System.out.println(string2);

	}

	@org.junit.Test
	public void testSplit() {

		String picturePath = "/aaa/123ajsjf.jpg;/fdhdgdjgjdfg.jpg";
		String[] split = picturePath.split(";");
		for (String string : split) {
			System.out.println(string);

		}
	}

}
