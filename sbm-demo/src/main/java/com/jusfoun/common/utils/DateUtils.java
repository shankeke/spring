package com.jusfoun.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 说明： 日期工具类. <br>
 *
 * @author yjw@jusfoun.com
 * @date 2017年9月6日 上午11:33:06
 */
public class DateUtils {
	private static final Logger log = LoggerFactory.getLogger(DateUtils.class);

	/*
	 * public static final String PATTERN_YYYYMM_1 = "yyyy-MM"; public static
	 * final String PATTERN_YYYYMM_2 = "yyyy/MM";
	 * 
	 * public static final String PATTERN_MMDD_1 = "MM-dd"; public static final
	 * String PATTERN_MMDD_2 = "MM/dd";
	 * 
	 * public static final String PATTERN_YYYYMMDD_1 = "yyyy-MM-dd"; public
	 * static final String PATTERN_YYYYMMDD_2 = "yyyy/MM/dd";
	 * 
	 * public static final String PATTERN_YYYYMMDD_HHMMSS_1 =
	 * "yyyy-MM-dd HH:mm:ss"; public static final String
	 * PATTERN_YYYYMMDD_HHMMSS_2 = "yyyy/MM/dd HH:mm:ss";
	 * 
	 * public static final String PATTERN_YYYYMMDD_HHMM_1 = "yyyy-MM-dd HH:mm";
	 * public static final String PATTERN_YYYYMMDD_HHMM_2 = "yyyy/MM/dd HH:mm";
	 * 
	 * public static final String PATTERN_HHMMSS = "HH:mm:ss"; public static
	 * final String PATTERN_HHMM = "HH:mm";
	 */

	/*********************************** 时间格式化常量 ***************************************/
	/** 常用日期格式化 */
	public static final String DATE_SDF_FULL = "yyyy-MM-dd HH:mm:ss";
	/** 日期 */
	public static final String DATE_SDF_DATE = "yyyy-MM-dd";
	/** 日期 */
	public static final String DATE_SDF_DATE_PATH = "yyyy/MM/dd";
	/** 月份 */
	public static final String DATE_SDF_MONTH = "yyyy-MM";
	/** 只取年份 */
	public static final String DATE_SDF_ONLY_YEAR = "yyyy";
	/** 只去月份 */
	public static final String DATE_SDF_ONLY_MONTH = "MM";
	/** 时间 */
	public static final String DATE_SDF_TIME = "HH:mm:ss";
	/** 时间 */
	public static final String DATE_SDF_HOUR_MINUTE = "HH:mm";
	/** 精确到小时 */
	public static final String DATE_SDF_HOUR = "yyyy-MM-dd HH";
	/** 精确到分钟 */
	public static final String DATE_SDF_SECOND = "yyyy-MM-dd HH:mm";
	/** 只取日期 */
	public static final String DATE_SDF_ONLY_DATE = "dd";
	/** 时间戳 */
	public static final String timeStamp = "yyyyMMddHHmmss";

	/**
	 * 说明： 根据时间格式返回指定的时间字符串. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月9日 下午1:55:19
	 * @param format
	 *            时间格式
	 * @param date
	 *            时间
	 * @return 格式化时间字符串
	 */
	public static String format(String format, Date date) {
		return date != null ? new SimpleDateFormat(format).format(date) : "";
	}

	/**
	 * 说明： 解析日期. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月9日 下午1:54:30
	 * @param format
	 *            时间格式
	 * @param date
	 *            时间字符串
	 * @return 时间
	 * @throws ParseException
	 */
	public static Date parse(String format, String date) throws ParseException {
		return StringUtils.isEmpty(date) ? null : new SimpleDateFormat(format).parse(date);
	}

	public static String format(String dateStr) throws ParseException {
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

	/**
	 * 说明： 时间比较. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月9日 下午1:51:44
	 * @param date1
	 *            比较时间
	 * @param date2
	 *            被比较时间
	 * @return
	 */
	public static int compare(Date date1, Date date2) {
		return date1.compareTo(date2);
	}

	/**
	 * 说明： 前面时间 等于后面时间. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月9日 上午10:09:43
	 * @param date1
	 *            比较时间
	 * @param date2
	 *            被比较时间
	 * @return
	 */
	public static boolean equalTo(Date date1, Date date2) {
		return compare(date1, date2) == 0;
	}

	/**
	 * 说明： 前面时间大于后面时间. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月9日 上午10:08:36
	 * @param date1
	 *            比较时间
	 * @param date2
	 *            被比较时间
	 * @return
	 */
	public static boolean greaterThan(Date date1, Date date2) {
		return compare(date1, date2) > 0;
	}

	/**
	 * 说明：前面时间大于等于后面时间. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月9日 上午10:09:10
	 * @param date1
	 *            比较时间
	 * @param date2
	 *            被比较时间
	 * @return
	 */
	public static boolean greaterThanOrEqualTo(Date date1, Date date2) {
		return compare(date1, date2) >= 0;
	}

	/**
	 * 说明：前面时间小于后面时间. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月9日 上午10:09:28
	 * @param date1
	 *            比较时间
	 * @param date2
	 *            被比较时间
	 * @return
	 */
	public static boolean lessThan(Date date1, Date date2) {
		return compare(date1, date2) < 0;
	}

	/**
	 * 说明： 前面时间小于等于后面时间. <br>
	 *
	 * @author yjw@jusfoun.com
	 * @date 2017年10月9日 上午10:09:43
	 * @param date1
	 *            比较时间
	 * @param date2
	 *            被比较时间
	 * @return
	 */
	public static boolean lessThanOrEqualTo(Date date1, Date date2) {
		return compare(date1, date2) <= 0;
	}

	/**
	 * <pre>
	 * Description:
	 *         根据特定的日期返回当天的星期数
	 * </pre>
	 *
	 * @author yjw
	 * @date 2015-7-30 上午9:47:49
	 * @param date
	 * @return 返回指定日期中文星期几
	 */
	public static String getDayOfWeekCn(Date date) {
		String dayOfWeek = "";
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int weekday = c.get(Calendar.DAY_OF_WEEK);
		switch (weekday) {
		case 1:
			dayOfWeek = "星期日";
			break;
		case 2:
			dayOfWeek = "星期一";
			break;
		case 3:
			dayOfWeek = "星期二";
			break;
		case 4:
			dayOfWeek = "星期三";
			break;
		case 5:
			dayOfWeek = "星期四";
			break;
		case 6:
			dayOfWeek = "星期五";
			break;
		case 7:
			dayOfWeek = "星期六";
			break;
		}
		return dayOfWeek;
	}

	/**
	 * 根据特定的日期返回当天的星期数
	 *
	 * @param date
	 * @return
	 */
	public static String getDayOfWeekEn(Date date) {
		String dayOfWeek = "";
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int weekday = c.get(Calendar.DAY_OF_WEEK);
		switch (weekday) {
		case 1:
			dayOfWeek = "SUN";
			break;
		case 2:
			dayOfWeek = "MON";
			break;
		case 3:
			dayOfWeek = "TUE";
			break;
		case 4:
			dayOfWeek = "WED";
			break;
		case 5:
			dayOfWeek = "THU";
			break;
		case 6:
			dayOfWeek = "FRI";
			break;
		case 7:
			dayOfWeek = "SAT";
			break;
		}
		return dayOfWeek;
	}

	/**
	 * <pre>
	 * Description:
	 *        获取相差某个年份的时间
	 * </pre>
	 *
	 * @author yjw
	 * @date 2015-8-13 下午1:04:42
	 * @param date
	 *            参考日期
	 * @param diffMonths
	 *            相差的年份数，正数：几个年后的日期，负数：几个年前的日期
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date getDateOfDiffYears(Date date, int diffYears) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.YEAR, diffYears);
		return calendar.getTime();
	}

	/**
	 * <pre>
	 * Description:
	 *        获取相差某个月份的日期
	 * </pre>
	 *
	 * @author yjw
	 * @date 2015-8-13 下午1:04:42
	 * @param date
	 *            参考日期
	 * @param diffMonths
	 *            相差的月份数，正数：几个月后的日期，负数：几个月前的日期
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date getDateOfDiffMonths(Date date, int diffMonths) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.MONTH, diffMonths);
		return calendar.getTime();
	}

	/**
	 * <pre>
	 * Description:
	 *        获取相差几天的日期
	 * </pre>
	 *
	 * @author yjw
	 * @date 2015-8-13 下午1:04:42
	 * @param date参考日期
	 * @param diffMonth
	 *            相差的月份数，正数：几天后的日期，负数：几天前的日期
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date getDateOfDiffDays(Date date, int diffDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.DATE, diffDays);
		return calendar.getTime();
	}

	/**
	 * <pre>
	 * Description:
	 *        获取相差几小时的日期
	 * </pre>
	 *
	 * @author yjw
	 * @date 2015-8-13 下午1:04:42
	 * @param date
	 *            参考日期
	 * @param diffMonth
	 *            相差的月份数，正数：几个小时后的日期，负数：几小时前的日期
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date getDateOfDiffHours(Date date, int diffHours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.DATE, diffHours);
		return calendar.getTime();
	}

	/**
	 * <pre>
	 * Description:
	 *        获取相差几分钟的日期
	 * </pre>
	 *
	 * @author yjw
	 * @date 2015-8-13 下午1:04:42
	 * @param date
	 *            参考日期
	 * @param diffMinutes
	 *            相差的分钟数，正数：几分钟后的日期，负数：几分钟前的日期
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date getDateOfDiffMinutes(Date date, int diffMinutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.MINUTE, diffMinutes);
		return calendar.getTime();
	}

	/**
	 * <pre>
	 * Description:
	 *        获取相差几秒钟的时间
	 * </pre>
	 *
	 * @author yjw
	 * @date 2015-8-13 下午1:04:42
	 * @param date
	 *            参考日期
	 * @param diffSeconds
	 *            相差的秒钟数，正数：几秒钟后的时间，负数：几秒钟前的时间
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date getDateOfDiffSeconds(Date date, int diffSeconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.SECOND, diffSeconds);
		return calendar.getTime();
	}

	/**
	 * <pre>
	 * Description:
	 *          获取一个日期所在月的第一天
	 * </pre>
	 *
	 * @author yjw
	 * @date 2015-8-13 下午4:06:58
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * <pre>
	 * Description:
	 *          获取一个日期所在月的最后一天
	 * </pre>
	 *
	 * @author yjw
	 * @date 2015-8-13 下午4:06:58
	 * @param date
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return calendar.getTime();
	}

	/**
	 * <pre>
	 * Description:
	 *        查询相差几个季度的第一天
	 * </pre>
	 *
	 * @author yjw
	 * @date 2015-8-13 下午4:14:48
	 * @param date
	 *            参考日期
	 * @param quarterNum
	 *            相差季度
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date getFirstDayOfDiffQuarter(Date date, int diffQuarter) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getFirstDayOfQuarter(date));
		calendar.add(calendar.MONTH, 3 * diffQuarter);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * <pre>
	 * Description:
	 *        查询相差几个季度的最后一天
	 * </pre>
	 *
	 * @author yjw
	 * @date 2015-8-13 下午4:14:48
	 * @param date
	 *            参考日期
	 * @param quarterNum
	 *            第几季度
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date getLastDayOfDiffQuarter(Date date, int diffQuarter) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getFirstDayOfQuarter(date));
		calendar.add(calendar.MONTH, 3 * (diffQuarter + 1));
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return calendar.getTime();
	}

	/**
	 * 得到本季度第一天的日期
	 *
	 * @Methods Name getFirstDayOfQuarter
	 * @return Date
	 */
	public static Date getFirstDayOfQuarter(Date date) {
		Calendar cDay = Calendar.getInstance();
		cDay.setTime(date);
		int curMonth = cDay.get(Calendar.MONTH);
		if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.MARCH) {
			cDay.set(Calendar.MONTH, Calendar.JANUARY);
		}
		if (curMonth >= Calendar.APRIL && curMonth <= Calendar.JUNE) {
			cDay.set(Calendar.MONTH, Calendar.APRIL);
		}
		if (curMonth >= Calendar.JULY && curMonth <= Calendar.AUGUST) {
			cDay.set(Calendar.MONTH, Calendar.JULY);
		}
		if (curMonth >= Calendar.OCTOBER && curMonth <= Calendar.DECEMBER) {
			cDay.set(Calendar.MONTH, Calendar.OCTOBER);
		}
		cDay.set(Calendar.DAY_OF_MONTH, cDay.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cDay.getTime();
	}

	/**
	 * 得到本季度最后一天的日期
	 *
	 * @Methods Name getLastDayOfQuarter
	 * @return Date
	 */
	public static Date getLastDayOfQuarter(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int curMonth = c.get(Calendar.MONTH);
		if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.MARCH) {
			c.set(Calendar.MONTH, Calendar.MARCH);
		}
		if (curMonth >= Calendar.APRIL && curMonth <= Calendar.JUNE) {
			c.set(Calendar.MONTH, Calendar.JUNE);
		}
		if (curMonth >= Calendar.JULY && curMonth <= Calendar.AUGUST) {
			c.set(Calendar.MONTH, Calendar.AUGUST);
		}
		if (curMonth >= Calendar.OCTOBER && curMonth <= Calendar.DECEMBER) {
			c.set(Calendar.MONTH, Calendar.DECEMBER);
		}
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * <pre>
	 * Description:
	 *        查看指定日期所在季度数
	 * </pre>
	 *
	 * @author yjw
	 * @date 2015-8-13 下午5:04:50
	 * @param date
	 * @return
	 */
	public static int getQuarterOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int curMonth = c.get(Calendar.MONTH);
		if (curMonth >= Calendar.JANUARY && curMonth <= Calendar.MARCH) {
			return 1;
		}
		if (curMonth >= Calendar.APRIL && curMonth <= Calendar.JUNE) {
			return 2;
		}
		if (curMonth >= Calendar.JULY && curMonth <= Calendar.AUGUST) {
			return 3;
		}
		if (curMonth >= Calendar.OCTOBER && curMonth <= Calendar.DECEMBER) {
			return 4;
		}
		return 1;
	}

	/**
	 * <pre>
	 * Description:
	 *        查看相差几个季度季度数
	 *        如上一季度是第几季度 getQuarterOfDiffQuarter(当前时间, -1)
	 * </pre>
	 *
	 * @author yjw
	 * @date 2015-8-13 下午5:04:50
	 * @param date
	 * @return
	 */
	public static int getQuarterOfDiffQuarter(Date date, int diffNum) {
		return getQuarterOfYear(getFirstDayOfDiffQuarter(date, diffNum));
	}

	/**
	 * <pre>
	 * Description:
	 *         与指定日期相差指定月份在一年中的月份
	 * </pre>
	 *
	 * @author yjw
	 * @date 2015-8-13 下午5:23:16
	 * @param date
	 * @param diffMonth
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static int getMonthOfDiffMonth(Date date, int diffMonth) {
		Calendar c = Calendar.getInstance();
		c.setTime(getDateOfDiffMonths(date, diffMonth));
		return c.get(c.MONTH) + 1;
	}

	/**
	 * <pre>
	 * Description:
	 *       计算时间据当前时间的距离
	 * </pre>
	 *
	 * @author yjw
	 * @date 2015-7-3
	 * @param date
	 *            日期时间
	 * @return
	 */
	public static String calculate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		// 当前时间
		Date currDate = new Date();
		Calendar curr = Calendar.getInstance();
		curr.setTime(currDate);

		long diff = System.currentTimeMillis() - c.getTimeInMillis();
		long oneSecond = 1000L;
		long oneMinute = 60 * oneSecond;
		long oneHour = 60 * oneMinute;
		// long oneDay = 24 * oneHour;

		// 判断是否是同一天
		// long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		// long diffHours = diff / (60 * 60 * 1000) % 24;
		// long diffDays = diff / (24 * 60 * 60 * 1000);

		// 如果是当天
		String time = new SimpleDateFormat("HH:mm").format(date);
		if (format(DATE_SDF_DATE, currDate).equals(format(DATE_SDF_DATE, date))) {
			// 当天时间小于一小时，返回相差的分钟数
			if (diff < oneHour) {
				return diffMinutes + "分钟前";
			} else {
				// 返回"小时:分钟"
				return time;
			}
		}
		// 如果是今年
		else if (format(DATE_SDF_ONLY_YEAR, currDate).equals(format(DATE_SDF_ONLY_YEAR, date))) {
			int days = curr.get(Calendar.DAY_OF_YEAR) - c.get(Calendar.DAY_OF_YEAR);
			if (days == 1) {
				return "昨天 " + time;
			} else if (days == 2) {
				return "前天 " + time;
			} else {
				return new SimpleDateFormat("MM-dd HH:mm").format(date);
			}
		}
		return new SimpleDateFormat(DATE_SDF_SECOND).format(date);
	}

	/**
	 * 以友好的方式显示时间
	 *
	 * @param sdate
	 * @return
	 */
	public static String friendlyTime(Date date) {
		String ftime = "";
		Calendar cal = Calendar.getInstance();
		// 判断是否是同一天
		// String curDate = format(DATE_SDF_DATE, cal.getTime());
		// String paramDate = format(DATE_SDF_DATE, date);
		long lt = date.getTime();
		long ct = cal.getTimeInMillis();
		int days = (int) ((ct - lt) / 86400000);
		if (days == 0) {
			int hour = (int) ((cal.getTimeInMillis() - date.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math.max((cal.getTimeInMillis() - date.getTime()) / 60000, 1) + "分钟前";
			else
				ftime = hour + "小时前";
			return ftime;
		} else if (days == 1) {
			ftime = "昨天";
		} else if (days >= 2 && days <= 30) {
			ftime = days + "天前";
		} else {
			ftime = days / 30 + "月前";
		}
		return ftime;
	}

	/**
	 * 说明：计算两个日期之间的间隔. <br>
	 * <p>
	 *
	 * @author Uknower-yjw
	 * @date 2017年7月19日 上午11:20:20
	 * @param early
	 *            较早时间
	 * @param late
	 *            较晚时间
	 * @return 间隔天数
	 */
	public static int daysBetween(Date early, Date late) {
		java.util.Calendar calst = java.util.Calendar.getInstance();
		java.util.Calendar caled = java.util.Calendar.getInstance();
		calst.setTime(early);
		caled.setTime(late);
		// 设置时间为0时
		calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
		calst.set(java.util.Calendar.MINUTE, 0);
		calst.set(java.util.Calendar.SECOND, 0);
		caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
		caled.set(java.util.Calendar.MINUTE, 0);
		caled.set(java.util.Calendar.SECOND, 0);
		// 得到两个日期相差的天数
		int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;

		return days;
	}

	public static int daysBetween(String date1, String date2) throws ParseException {
		return daysBetween(parse(DATE_SDF_DATE, date1), parse(DATE_SDF_DATE, date2));
	}

	/**
	 * @title: currDayZero
	 * @description: 根据日期计算当天零点零时零分
	 * @author: 王顺义
	 * @date:2017年10月11日 下午7:43:31
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static String currDayZero(String endTime) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		Date endDate = parse(DATE_SDF_FULL, endTime);
		calendar.setTime(endDate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date zero = calendar.getTime();
		return format(DATE_SDF_FULL, zero);
	}

	/**
	 * @title: getWeekTimeStr
	 * @description: 获取当前时间以前一个周时间
	 * @author: 王顺义
	 * @date:2017年10月19日 下午4:51:46
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static String getWeekTimeStr(String endTime) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		Date endDate = parse(DATE_SDF_FULL, endTime);
		calendar.setTime(endDate);
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -7);
		Date d = calendar.getTime();
		return format(DATE_SDF_FULL, d);
	}

	/**
	 * @title: getMonthTimeStr
	 * @description: 获取当前时间前一个月时间
	 * @author: 王顺义
	 * @date:2017年10月19日 下午4:52:07
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static String getMonthTimeStr(String endTime) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		Date endDate = parse(DATE_SDF_FULL, endTime);
		calendar.setTime(endDate);
		calendar.add(Calendar.MONTH, -1);
		Date zero = calendar.getTime();
		return format(DATE_SDF_FULL, zero);
	}

	/**
	 * @title: getYearTimeStr
	 * @description: 获取当前时间前一年时间 年月表示
	 * @author: 王顺义
	 * @date:2017年10月19日 下午4:51:52
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static String getYearTimeStr(String endTime) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		Date endDate = parse(DATE_SDF_FULL, endTime);
		calendar.setTime(endDate);
		calendar.add(Calendar.YEAR, -1);
		Date zero = calendar.getTime();
		return format(DATE_SDF_FULL, zero);
	}

	/**
	 * @title: getYearTimeStr1
	 * @description: 获取当前时间前一年时间 年月日时分秒表示
	 * @author: 王顺义
	 * @date:2017年10月20日 下午5:45:24
	 * @param endTime
	 * @return
	 * @throws ParseException
	 */
	public static String getYearTimeStr1(String endTime) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		Date endDate = parse(DATE_SDF_FULL, endTime);
		calendar.setTime(endDate);
		calendar.add(Calendar.YEAR, -1);
		Date zero = calendar.getTime();
		return format(DATE_SDF_FULL, zero);
	}

	/**
	 * @title: getBetweenDates
	 * @description: 获取两个日期之间的日期
	 * @author: 王顺义
	 * @date:2017年10月19日 下午7:49:02
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<Date> getBetweenDates(Date start, Date end) {

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
	 * @title: getSpecifiedDayAfter
	 * @description: 获取制定日期的后一天时间
	 * @author: 王顺义
	 * @date:2017年12月7日 下午4:25:30
	 * @param specifiedDay
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = parse(DATE_SDF_DATE, specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = format(DATE_SDF_DATE, c.getTime());

		return dayAfter;
	}

	/**
	 * @title: getBeforeHundredDate
	 * @description:获取100年以前的数据
	 * @author: 王顺义
	 * @date:2017年12月11日 上午11:21:46
	 * @param today
	 * @return
	 */
	public static String getHundredBeforeDate(String today) {
		Date todayDate = null;
		Date hundredBeforeDate = null;
		try {
			todayDate = DateUtils.parse(DateUtils.DATE_SDF_DATE, today);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(todayDate);
		calendar.add(Calendar.YEAR, -100);
		hundredBeforeDate = calendar.getTime();
		String hundredBeforeDateStr = DateUtils.format(DateUtils.DATE_SDF_DATE, hundredBeforeDate);
		return hundredBeforeDateStr;

	}

	/**
	 * @title: gethundredAfterDate
	 * @description: 获取100年以后的数据
	 * @author: 王顺义
	 * @date:2017年12月11日 上午11:21:51
	 * @param today
	 * @return
	 */
	public static String getHundredAfterDate(String today) {
		Date todayDate = null;
		Date HundredAfterDate = null;
		try {
			todayDate = DateUtils.parse(DateUtils.DATE_SDF_DATE, today);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(todayDate);
		calendar.add(Calendar.YEAR, 100);
		HundredAfterDate = calendar.getTime();
		String HundredAfterDateStr = DateUtils.format(DateUtils.DATE_SDF_DATE, HundredAfterDate);
		return HundredAfterDateStr;

	}

	// public static void main(String[] args) throws ParseException {
	// Calendar c = Calendar.getInstance();
	// System.out.println(c.getTimeInMillis() + " " +
	// System.currentTimeMillis());

	// String dateStart = "2013-02-19 09:29:58";
	// String dateStop = "2013-02-20 11:31:48";
	//
	// SimpleDateFormat format = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//
	// Date d1 = null;
	// Date d2 = null;
	//
	// try {
	// d1 = format.parse(dateStart);
	// d2 = format.parse(dateStop);
	//
	// // 毫秒ms
	// long diff = d2.getTime() - d1.getTime();
	//
	// long diffSeconds = diff / 1000 % 60;
	// long diffMinutes = diff / (60 * 1000) % 60;
	// long diffHours = diff / (60 * 60 * 1000) % 24;
	// long diffDays = diff / (24 * 60 * 60 * 1000);
	//
	// System.out.print("两个时间相差：");
	// System.out.print(diffDays + " 天, ");
	// System.out.print(diffHours + " 小时, ");
	// System.out.print(diffMinutes + " 分钟, ");
	// System.out.print(diffSeconds + " 秒.");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }

	// System.out.println(calculate(IConstants.DATE_SDF_FULL
	// .parse("2015-06-21 12:12:12")));
	// System.out.println(calculate(IConstants.DATE_SDF_FULL
	// .parse("2015-07-01 12:12:12")));
	// System.out.println(calculate(IConstants.DATE_SDF_FULL
	// .parse("2015-07-02 12:12:12")));
	// System.out.println(calculate(IConstants.DATE_SDF_FULL
	// .parse("2015-07-03 12:12:12")));
	// System.out.println(calculate(IConstants.DATE_SDF_FULL
	// .parse("2015-07-04 12:12:12")));
	// System.out.println(calculate(IConstants.DATE_SDF_FULL
	// .parse("2015-07-05 12:12:12")));
	// System.out.println(calculate(IConstants.DATE_SDF_FULL
	// .parse("2015-07-06 08:12:12")));
	// System.out.println(calculate(IConstants.DATE_SDF_FULL
	// .parse("2015-07-06 09:12:12")));
	// System.out.println(compare(new Date(),
	// parse(DATE_SDF_DATE, "2015-07-06 09:12:12"), DATE_SDF_DATE));
	// System.out.println(format(DATE_SDF_DATE,
	// getDateOfDiffDay(new Date(), -1000)));
	// System.out.println(format(DATE_SDF_DATE, getDateOfDiffMonth(-10)));
	// System.out
	// .println(format(DATE_SDF_DATE, getFirstDayOfMonth(new Date())));
	// System.out
	// .println(format(DATE_SDF_DATE, getLastDayOfMonth(new Date())));
	// System.out.println(format(DATE_SDF_DATE,
	// getFirstDayOfDiffQuarter(new Date(), -1)));
	// System.out.println(format(DATE_SDF_DATE,
	// getLastDayOfDiffQuarter(new Date(), -1)));
	// System.out.println(getQuarterOfDiffQuarter(new Date(), -3));
	// System.out.println(getMonthOfDiffMonth(new Date(), -1));
	// System.out
	// .println(getLastDayOfMonth(getDateOfDiffMonth(new Date(), -3)));

	// System.out.println(DateUtil.compare(new Date(),
	// DateUtil.parse(DateUtil.DATE_SDF_DATE, "2016-06-22"),
	// DateUtil.DATE_SDF_DATE) > 0);
	// System.out.println(DateUtil.getLastDayOfDiffQuarter(new Date(),
	// Integer.MAX_VALUE));
	// }
}
