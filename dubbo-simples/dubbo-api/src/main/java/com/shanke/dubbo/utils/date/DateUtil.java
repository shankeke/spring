package com.shanke.dubbo.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * <pre>
 * Description: 
 *       日期工具类
 * </pre>
 * 
 * Copyright (c) 2015 优识云创(YSYC )
 * 
 * @author yjw
 * @date 2015-6-25 下午12:23:28
 */
public class DateUtil {
	// public static final String DATE_FORMAT_DATEPATH = "yyyy/MM/dd";
	// public static final String MONTH_FORMAT_DATEPATH = "yyyy/MM";
	// public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";
	// public static final String DATE_FORMAT_TIME_R = "yyyy-MM-dd HH:mm";
	// public static final String DATE_FORMAT_TIME_T = "yyyy-MM-dd HH:mm:ss";
	// public static final String DB_TIME_PATTERN = "yyyyMMddHHmmss";

	/*********************************** 时间格式化常量 ***************************************/
	/** 常用日期格式化 */
	public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
	public static final SimpleDateFormat DATE_SDF_FULL = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	/** 日期 */
	public static final String DATE_FORMAT_SPARSE_DATE = "yyyy-MM-dd";
	public static final SimpleDateFormat DATE_SDF_SPARSE_DATE = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static final String DATE_FORMAT_COMPACT_DATE = "yyyyMMdd";
	public static final SimpleDateFormat DATE_SDF_COMPACT_DATE = new SimpleDateFormat(
			"yyyyMMdd");
	/** 月份 */
	public static final String DATE_FORMAT_MONTH = "yyyy-MM";
	public static final SimpleDateFormat DATE_SDF_MONTH = new SimpleDateFormat(
			"yyyy-MM");
	/** 只取年份 */
	public static final String DATE_SDF_ONLY_YEAR = "yyyy";
	/** 只去月份 */
	public static final String DATE_SDF_ONLY_MONTH = "MM";
	/** 时间 */
	public static final String DATE_SDF_TIME = "HH:mm:ss";
	/** 精确到小时 */
	public static final String DATE_SDF_HOUR = "yyyy-MM-dd HH";
	/** 精确到分钟 */
	public static final String DATE_FORMAT_SECOND = "yyyy-MM-dd HH:mm";

	public static final SimpleDateFormat DATE_SDF_SECOND = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	/** 只取日期 */
	public static final String DATE_SDF_ONLY_DATE = "dd";

	private static final int INT_YEAR = 999;
	private static Map<Integer, Long> periods = null;

	/**
	 * <pre>
	 * Description: 
	 *        根据时间格式返回指定的时间字符串
	 * </pre>
	 * 
	 * @author yjw
	 * @date 2015-7-30 上午9:39:44
	 * @param format
	 *            时间格式
	 * @param date
	 *            时间
	 * @return
	 */
	public static String format(String format, Date date) {
		return date != null ? new SimpleDateFormat(format).format(date) : "";
	}

	/**
	 * <pre>
	 * Description: 
	 *         The description of this method
	 * </pre>
	 * 
	 * @author yjw
	 * @date 2015-7-30 上午9:45:14
	 * @param format
	 *            时间格式
	 * @param date
	 *            时间字符串
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String format, String date) throws ParseException {
		return StringUtils.isEmpty(date) ? null : new SimpleDateFormat(format)
				.parse(date);
	}

	/**
	 * 比较两个日期的大小
	 * 
	 * @param date1
	 * @param date2
	 * @param format
	 * @return
	 */
	public static int compare(Date date1, Date date2, String format) {
		if (date1 == null && date2 == null)
			return 0;
		if (date1 == null)
			return -1;
		if (date2 == null)
			return 1;
		int compareTo = format(format, date1).compareTo(format(format, date2));
		return compareTo;
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
	public static Date getDateOfDiffMonth(Date date, int diffMonths) {
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
	public static Date getDateOfDiffDay(Date date, int diffDays) {
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
		calendar.set(Calendar.DAY_OF_MONTH, -1);
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
		cDay.set(Calendar.DAY_OF_MONTH,
				cDay.getActualMinimum(Calendar.DAY_OF_MONTH));
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
		c.setTime(getDateOfDiffMonth(date, diffMonth));
		return c.get(c.MONTH) + 1;
	}

	/**
	 * 返回参数月的天数
	 * 
	 * @param argMonth
	 * @return
	 */
	public static long getMonthPeriod(int argMonth) {
		initPeriod();
		return periods.get(argMonth);
	}

	private static void initPeriod() {
		Calendar cal = Calendar.getInstance();
		if (periods != null
				&& periods.get(INT_YEAR).equals(cal.get(Calendar.YEAR))) {
			return;
		}
		periods = new HashMap<Integer, Long>();
		// now year
		periods.put(INT_YEAR, Long.valueOf(cal.get(Calendar.YEAR)));

		Calendar calNext = Calendar.getInstance();

		// JANUARY
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		// FEBRUARY
		calNext.set(Calendar.MONTH, Calendar.FEBRUARY);
		long lngDay = calNext.getTimeInMillis() - cal.getTimeInMillis();
		// MONTH: JANUARY
		periods.put(Calendar.JANUARY, lngDay);

		// MARCH
		cal.set(Calendar.MONTH, Calendar.MARCH);
		lngDay = cal.getTimeInMillis() - calNext.getTimeInMillis();
		// MONTH: FEBRUARY
		periods.put(Calendar.FEBRUARY, lngDay);

		// APRIL
		calNext.set(Calendar.MONTH, Calendar.APRIL);
		lngDay = calNext.getTimeInMillis() - cal.getTimeInMillis();
		// MONTH: MARCH
		periods.put(Calendar.MARCH, lngDay);

		// MAY
		cal.set(Calendar.MONTH, Calendar.MAY);
		lngDay = cal.getTimeInMillis() - calNext.getTimeInMillis();
		// MONTH: APRIL
		periods.put(Calendar.APRIL, lngDay);

		// JUNE
		calNext.set(Calendar.MONTH, Calendar.JUNE);
		lngDay = calNext.getTimeInMillis() - cal.getTimeInMillis();
		// MONTH: MAY
		periods.put(Calendar.MAY, lngDay);

		// JULY
		cal.set(Calendar.MONTH, Calendar.JULY);
		lngDay = cal.getTimeInMillis() - calNext.getTimeInMillis();
		// MONTH: JUNE
		periods.put(Calendar.JUNE, lngDay);

		// AUGUST
		calNext.set(Calendar.MONTH, Calendar.AUGUST);
		lngDay = calNext.getTimeInMillis() - cal.getTimeInMillis();
		// MONTH: JULY
		periods.put(Calendar.JULY, lngDay);

		// SEPTEMBER
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		lngDay = cal.getTimeInMillis() - calNext.getTimeInMillis();
		// MONTH: AUGUST
		periods.put(Calendar.AUGUST, lngDay);

		// OCTOBER
		calNext.set(Calendar.MONTH, Calendar.OCTOBER);
		lngDay = calNext.getTimeInMillis() - cal.getTimeInMillis();
		// MONTH: SEPTEMBER
		periods.put(Calendar.SEPTEMBER, lngDay);

		// NOVEMBER
		cal.set(Calendar.MONTH, Calendar.NOVEMBER);
		lngDay = cal.getTimeInMillis() - calNext.getTimeInMillis();
		// MONTH: OCTOBER
		periods.put(Calendar.OCTOBER, lngDay);

		// DECEMBER
		calNext.set(Calendar.MONTH, Calendar.DECEMBER);
		lngDay = calNext.getTimeInMillis() - cal.getTimeInMillis();
		// MONTH: NOVEMBER
		periods.put(Calendar.NOVEMBER, lngDay);

		// next year JANUARY
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.add(Calendar.YEAR, 1);
		lngDay = cal.getTimeInMillis() - calNext.getTimeInMillis();
		// MONTH: NOVEMBER
		periods.put(Calendar.NOVEMBER, lngDay);
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
		if (format(DATE_FORMAT_SPARSE_DATE, currDate).equals(
				format(DATE_FORMAT_SPARSE_DATE, date))) {
			// 当天时间小于一小时，返回相差的分钟数
			if (diff < oneHour) {
				return diffMinutes + "分钟前";
			} else {
				// 返回"小时:分钟"
				return time;
			}
		}
		// 如果是今年
		else if (format(DATE_SDF_ONLY_YEAR, currDate).equals(
				format(DATE_SDF_ONLY_YEAR, date))) {
			int days = curr.get(Calendar.DAY_OF_YEAR)
					- c.get(Calendar.DAY_OF_YEAR);
			if (days == 1) {
				return "昨天 " + time;
			} else if (days == 2) {
				return "前天 " + time;
			} else {
				return new SimpleDateFormat("MM-dd HH:mm").format(date);
			}
		}
		return DATE_SDF_SECOND.format(date);
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
				ftime = Math.max(
						(cal.getTimeInMillis() - date.getTime()) / 60000, 1)
						+ "分钟前";
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
	 * @Description 日期格式转化 YYYY-MM-DD 转 YYYYMMDD
	 * @param date
	 * @return
	 */
	public static String sparseToCompact(String date) {
		try {
			if (!StringUtils.isEmpty(date)) {
				if (date.contains("-")) {
					return DATE_SDF_COMPACT_DATE.format(DATE_SDF_SPARSE_DATE
							.parse(date));
				} else {
					return date;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * @Description 日期格式转化 YYYYMMDD 转 YYYY-MM-DD
	 * @param date
	 * @return
	 */
	public static String compactToSparse(String date) {
		try {
			if (!StringUtils.isEmpty(date)) {
				if (!date.contains("-")) {
					return DATE_SDF_SPARSE_DATE.format(DATE_SDF_COMPACT_DATE
							.parse(date));
				} else {
					return date;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * @Description 判断一个时间是上午还是下午
	 * @param date
	 *            时间
	 * @return
	 */
	public static boolean isAm(Date date) {
		// return date.getHours() <= 12;
		GregorianCalendar ca = new GregorianCalendar();
		int i = ca.get(GregorianCalendar.AM_PM);
		return i == 0;
	}

	public static void main(String[] args) throws ParseException {
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
		// DateUtil.parse(DateUtil.DATE_FORMAT_SPARSE_DATE, "2015-09-22"),
		// DateUtil.DATE_FORMAT_SPARSE_DATE));
		// System.out.println(DateUtil.isAm(new Date()));
		// System.out.println(DateUtil.getFirstDayOfMonth(new Date()));
		// System.out.println(DateUtil.getFirstDayOfYear(new Date()));
		// System.out.println(DateUtil.getLastDayOfYear(new Date()));
		// System.out.println(DateUtil.getFirstDayOfDiffYear(new Date(), -1));
		// System.out.println(DateUtil.getLastDayOfDiffYear(new Date(), -1));
		System.out.println(DateUtil.getFirstDayOfWeek(new Date()));
		System.out.println(DateUtil.getLastDayOfWeek(new Date()));
		System.out.println(DateUtil.getFirstDayOfDiffWeek(new Date(), -1));
		System.out.println(DateUtil.getLastDayOfDiffWeek(new Date(), -1));
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
	public static Date getFirstDayOfDiffYear(Date date, int diffYear) {
		Calendar c = Calendar.getInstance();
		c.setTime(getFirstDayOfYear(date));
		c.add(c.YEAR, diffYear);
		return c.getTime();
	}

	/**
	 * <pre>
	 * Description: 
	 *        查询相差几个年份的最后一天
	 * </pre>
	 * 
	 * @author yjw
	 * @date 2015-8-13 下午4:14:48
	 * @param date
	 *            参考日期
	 * @param diffYear
	 *            差的年份
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date getLastDayOfDiffYear(Date date, int diffYear) {
		Calendar c = Calendar.getInstance();
		c.setTime(getLastDayOfYear(date));
		c.add(c.YEAR, diffYear);
		return c.getTime();
	}

	/**
	 * 描述 : 查询年度第一天. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-8-23 下午6:50:55
	 * @param date
	 *            参考日期
	 * @return
	 */
	public static Date getFirstDayOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		c.clear();
		c.set(Calendar.YEAR, year);
		return c.getTime();
	}

	/**
	 * 得到年份最后一天的日期
	 * 
	 * @Methods Name getLastDayOfYear
	 * @return Date 参考日期
	 */
	public static Date getLastDayOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		c.clear();
		c.set(Calendar.YEAR, year);
		c.roll(Calendar.DAY_OF_YEAR, -1);
		return c.getTime();
	}

	/**
	 * <pre>
	 * Description: 
	 *        查询相差几周的第一天
	 * </pre>
	 * 
	 * @author yjw
	 * @date 2015-8-13 下午4:14:48
	 * @param date
	 *            参考日期
	 * @param diffWeek
	 *            相差周数
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date getFirstDayOfDiffWeek(Date date, int diffWeek) {
		Calendar c = Calendar.getInstance();
		c.setTime(getFirstDayOfWeek(date));
		c.add(c.WEEK_OF_YEAR, diffWeek);
		return c.getTime();
	}

	/**
	 * <pre>
	 * Description: 
	 *        查询相差几周的最后一天
	 * </pre>
	 * 
	 * @author yjw
	 * @date 2015-8-13 下午4:14:48
	 * @param date
	 *            参考日期
	 * @param diffWeek
	 *            差的周数
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static Date getLastDayOfDiffWeek(Date date, int diffWeek) {
		Calendar c = Calendar.getInstance();
		c.setTime(getLastDayOfWeek(date));
		c.add(c.WEEK_OF_YEAR, diffWeek);
		return c.getTime();
	}

	/**
	 * 描述 : 查询一周第一天. <br>
	 * <p>
	 * 
	 * @author Uknower-yjw
	 * @date 2016-8-23 下午6:50:55
	 * @param date
	 *            参考日期
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Sunday
		return c.getTime();
	}

	/**
	 * 得到一周最后一天的日期
	 * 
	 * @Methods Name getLastDayOfYear
	 * @return Date 参考日期
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Saturday
		return c.getTime();
	}
}