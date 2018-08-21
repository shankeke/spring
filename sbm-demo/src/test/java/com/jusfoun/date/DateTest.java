package com.jusfoun.date;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Test;

import com.jusfoun.common.utils.DateUtils;

public class DateTest {

	@Test
	public void test() throws ParseException {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		DateTime now = new DateTime();
		Date startTime = now.minusMinutes(120).toLocalDateTime().toDate();
		Date endTime = now.toLocalDateTime().toDate();

		System.out.println(sd.format(startTime));
		System.out.println(sd.format(endTime));
		/*
		 * String catchDate = getCatchDate("yyyy-MM-dd HH:mm:ss",2);
		 * System.out.println(catchDate);
		 */
		/*
		 * SimpleDateFormat format = new
		 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Calendar c =
		 * Calendar.getInstance();
		 *
		 * // 过去七天 c.setTime(new Date()); c.add(Calendar.DATE, -7); Date d =
		 * c.getTime(); String day = format.format(d); List<Date> betweenDates =
		 * getBetweenDates(format.parse(day), new Date()); for (Date date :
		 * betweenDates) {
		 *
		 * System.out.println(format.format(date));
		 *
		 * } // getBetweenDates(new Date(),format.parse(day));
		 */ // System.out.println("过去七天："+day);

		/*
		 * //过去一月 c.setTime(new Date()); c.add(Calendar.MONTH, -1); Date m =
		 * c.getTime(); String mon = format.format(m);
		 * System.out.println("过去一个月："+mon);
		 *
		 * //过去一年 c.setTime(new Date()); c.add(Calendar.YEAR, -1); Date y =
		 * c.getTime(); String year = format.format(y);
		 * System.out.println("过去一年："+year);
		 */
	}

	public List<Date> getBetweenDates(Date start, Date end) {

		List<Date> result = new LinkedList<Date>();
		if (start.getTime() == end.getTime()) {
			result.add(start);
			return result;
		}

		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(start);
		tempStart.add(Calendar.DAY_OF_YEAR, 1);

		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(end);
		result.add(start);
		while (tempStart.before(tempEnd)) {
			result.add(tempStart.getTime());
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
		}
		result.add(end);
		return result;
	}

	/**
	 * @title: getCatchDate
	 * @description: 测试当前时间到24小时前的时间 一周以前的时间 一个月以前的时间 0 ==24小时 1==一周 2==一个月
	 * @author: 王顺义
	 * @date:2017年10月24日 上午11:22:15
	 * @param dataFormat
	 * @param timeFlag
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getCatchDate(String dataFormat, Integer timeFlag) {
		SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);
		Date date = new Date();
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		if (timeFlag == 0) {// 近24小时
			cl.set(Calendar.HOUR_OF_DAY, cl.get(Calendar.HOUR_OF_DAY) - 24);
		}
		if (timeFlag == 1) { // 近一周
			cl.add(Calendar.DATE, -7);
		}
		if (timeFlag == 2) {// 近一月
			cl.add(Calendar.MONTH, -1);
		}
		Date catchDate = cl.getTime();
		String catchDateStr = sdf.format(catchDate);
		return catchDateStr;
	}

	@Test
	public void timeStamp() {
		String format = DateUtils.format(DateUtils.timeStamp, new Date());
		for (int i = 0; i < 10; i++) {
			System.out.println("=========================");
			System.out.println(format);
			System.out.println("=========================");

		}

	}

	@Test
	public void testFolat() {
		float f = 34.235323f;
		BigDecimal b = new BigDecimal(f);
		float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		// b.setScale(2, BigDecimal.ROUND_HALF_UP)
		System.out.println(f1);

	}

	@SuppressWarnings({ "unused", "static-access" })
	@Test
	public void testGetYesterdayDate() {
		Date todayDate = null;
		Date yesterdayDate = null;

			String time = "2017-11-17";
			//todayDate = DateUtil.parse(DateUtil.DATE_SDF_DATE, time);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(calendar.DATE, -1);
			yesterdayDate = calendar.getTime();
			String yesterdayDateStr = DateUtils.format(DateUtils.DATE_SDF_DATE, yesterdayDate);
			System.out.println(yesterdayDateStr);



	}

	@Test
	public void testMap() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("productName", "河蟹");
		paramMap.put("sex", "公");
		paramMap.put("time", "2017-11-17");
		Set<String> keySet = paramMap.keySet();
		System.out.println("======================");
		for (String string : keySet) {
			System.out.println(string);

		}
		for (Object value : paramMap.values()) {
			System.out.println(value);

		}
		System.out.println("========================");
		paramMap.put("time", "2017-11-16");
		System.out.println("======================");
		for (String string : keySet) {
			System.out.println(string);

		}
		for (Object value : paramMap.values()) {
			System.out.println(value);

		}
		System.out.println("======================");
	}

	@Test
	public void testSumList() {

		List<Map<String, Object>> currentData = new ArrayList<Map<String, Object>>();

		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("name", "北京市");
		map1.put("todayAvgPrice", 23);

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("name", "天津市");
		map2.put("todayAvgPrice", 24);

		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("name", "河北省");
		map3.put("todayAvgPrice", 25);

		currentData.add(map1);
		currentData.add(map2);
		currentData.add(map3);

		List<Map<String, Object>> yestdayData = new ArrayList<Map<String, Object>>();

		Map<String, Object> map4 = new HashMap<String, Object>();
		map4.put("name", "北京市");
		map4.put("yestedayAvgPrice", 23);

		Map<String, Object> map5 = new HashMap<String, Object>();
		map5.put("name", "天津市");
		map5.put("yestedayAvgPrice", 24);

		Map<String, Object> map6 = new HashMap<String, Object>();
		map6.put("name", "河北省");
		map6.put("yestedayAvgPrice", 25);

		yestdayData.add(map4);
		yestdayData.add(map5);
		yestdayData.add(map6);

		currentData.removeAll(yestdayData);
		currentData.addAll(yestdayData);
		for (Map<String, Object> m : currentData) {
			for (String k : m.keySet()) {
				System.out.println(k + " : " + m.get(k));
			}
		}
	}

	@Test
	public void testFloat() {
		float price = (float) 1;
		DecimalFormat decimalFormat = new DecimalFormat(".00");
		String format = decimalFormat.format(price);
		System.out.println(format);

	}
	@Test
	public void testMap1(){

		Map<String,Object> resultMap=new HashMap<String,Object>();
		resultMap.put("aa", 50);
		resultMap.put("bb", 5);
		resultMap.put("cc", 51);
		resultMap.put("dd", 56);
		resultMap.put("ee", 51);
		resultMap.put("ff", 53);
		Iterator<String> iterator = resultMap.keySet().iterator();
		while(iterator.hasNext()){
			String next = iterator.next();
			System.out.println(resultMap.get(next));


		}


	}
	@Test
	public void testData(){

		//int result=5/5;
		int ch=4;
		for(int i=0;i<40;i++){
			int temp=i/ch+1;

			System.out.println("值="+temp);

		}



	}


	public static String getSpecifiedDayAfter(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = DateUtils.parse(DateUtils.DATE_SDF_DATE, specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = DateUtils.format(DateUtils.DATE_SDF_DATE, c.getTime());

		return dayAfter;
	}

	@Test
	public void testGetSpecifiedDayAfter(){
		String specifiedDayAfter = getSpecifiedDayAfter("2017-12-12");

		System.out.println(specifiedDayAfter);

	}

	public String getYesterdayDate(String today) {
		Date todayDate = null;
		Date yesterdayDate = null;
			try {
				todayDate = DateUtils.parse(DateUtils.DATE_SDF_DATE, today);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(todayDate);
			calendar.add(Calendar.DATE, -1);
			yesterdayDate = calendar.getTime();
			String yesterdayDateStr = DateUtils.format(DateUtils.DATE_SDF_DATE, yesterdayDate);
			return yesterdayDateStr;

	}

	@Test
	public void testGet(){
		String yesterdayDate = getYesterdayDate("2017-12-11");
		System.out.println(yesterdayDate);


	}

	public String getBeforeHundredDate(String today) {
		Date todayDate = null;
		Date yesterdayDate = null;
			try {
				todayDate = DateUtils.parse(DateUtils.DATE_SDF_DATE, today);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(todayDate);
			calendar.add(Calendar.YEAR, -100);
			yesterdayDate = calendar.getTime();
			String yesterdayDateStr = DateUtils.format(DateUtils.DATE_SDF_DATE, yesterdayDate);
			return yesterdayDateStr;

	}

	public String gethundredAfterDate(String today) {
		Date todayDate = null;
		Date yesterdayDate = null;
			try {
				todayDate = DateUtils.parse(DateUtils.DATE_SDF_DATE, today);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(todayDate);
			calendar.add(Calendar.YEAR, 100);
			yesterdayDate = calendar.getTime();
			String yesterdayDateStr = DateUtils.format(DateUtils.DATE_SDF_DATE, yesterdayDate);
			return yesterdayDateStr;

	}

	@Test
	public void getBrforeDate(){
		String timeStr="2017-12-11";
		String brforeDate = getBeforeHundredDate(timeStr);
		String afterDate = gethundredAfterDate(timeStr);
		System.out.println(brforeDate);
		System.out.println(afterDate);


	}
}
