package com.jusfoun.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateFormatUtil {

	private static final Logger log = LoggerFactory.getLogger(DateFormatUtil.class);

	public String FormatDate(String dateStr) throws ParseException {
		HashMap<String, String> dateRegFormat = new HashMap<String, String>();
		// 2014年3月12日 13时5分34秒，2014-03-12 12:05:34，2014/3/12 12:5:34
		dateRegFormat.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$",
				"yyyy-MM-dd-HH-mm-ss");
		// 2014-03-12 12:05
		dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd-HH-mm");
		// 2014-03-12 12
		dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd-HH");
		// 2014-03-12
		dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd");
		// 2014-03
		dateRegFormat.put("^\\d{4}\\D+\\d{2}$", "yyyy-MM");
		// 2014
		dateRegFormat.put("^\\d{4}$", "yyyy");
		// 20140312120534
		dateRegFormat.put("^\\d{14}$", "yyyyMMddHHmmss");
		// 201403121205
		dateRegFormat.put("^\\d{12}$", "yyyyMMddHHmm");
		// 2014031212
		dateRegFormat.put("^\\d{10}$", "yyyyMMddHH");
		// 20140312
		dateRegFormat.put("^\\d{8}$", "yyyyMMdd");
		// 201403
		dateRegFormat.put("^\\d{6}$", "yyyyMM");
		// 13:05:34 拼接当前日期
		dateRegFormat.put("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$", "yyyy-MM-dd-HH-mm-ss");
		// 13:05 拼接当前日期
		dateRegFormat.put("^\\d{2}\\s*:\\s*\\d{2}$", "yyyy-MM-dd-HH-mm");
		// 14.10.18(年.月.日)
		dateRegFormat.put("^\\d{2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yy-MM-dd");
		// 30.12(日.月) 拼接当前年份
		dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}$", "yyyy-dd-MM");
		// 12.21.2013(日.月.年)
		dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}\\D+\\d{4}$", "dd-MM-yyyy");

		String curDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat formatter2;
		String dateReplace;
		String strSuccess = "";

		try {
			for (String key : dateRegFormat.keySet()) {
				if (Pattern.compile(key).matcher(dateStr).matches()) {
					formatter2 = new SimpleDateFormat(dateRegFormat.get(key));
					if (key.equals("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$") || key.equals("^\\d{2}\\s*:\\s*\\d{2}$")) {
						// 13:05:34 或 13:05 拼接当前日期
						dateStr = curDate + "-" + dateStr;
					} else if (key.equals("^\\d{1,2}\\D+\\d{1,2}$")) {
						// 21.1 (日.月) 拼接当前年份
						dateStr = curDate.substring(0, 4) + "-" + dateStr;
					}
					dateReplace = dateStr.replaceAll("\\D+", "-");
					strSuccess = formatter1.format(formatter2.parse(dateReplace));
					break;
				}
			}
		} catch (ParseException e) {
			log.error("无效的日期格式： " + dateStr);
			throw e;
		}
		return strSuccess;
	}

	@Test
	public void test() throws ParseException {
		String[] dateStrArray = new String[] { //
				"2014-03-12 12:05:34", //
				"2014-03-12 12:05", //
				"2014-03-12 12", //
				"2014-03-12", //
				"2014-03", //
				"2014", //
				"20140312120534", //
				"2014/03/12 12:05:34", //
				"2014/3/12 12:5:34", //
				"2014年3月12日 13时5分34秒", "201403121205", //
				"1234567890", //
				"20140312", //
				"201403", //
				"2000 13 33 13 13 13", //
				"30.12.2013", //
				"12.21.2013", //
				"21.1", //
				"13:05:34", //
				"12:05", //
				"14.1.8", //
				"14.10.18", //
				"sdjdsdsjhj"//
		};
		for (int i = 0; i < dateStrArray.length; i++) {
			System.out.println(
					dateStrArray[i] + "------------------------------".substring(1, 30 - dateStrArray[i].length())
							+ FormatDate(dateStrArray[i]));
		}
	}

}
