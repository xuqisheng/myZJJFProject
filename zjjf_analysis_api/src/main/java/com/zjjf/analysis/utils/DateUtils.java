/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.zjjf.analysis.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * 
 * @author setsail
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss",
			"yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM" };

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
	 * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	/**
	 * 获取过去的小时
	 * 
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 60 * 1000);
	}

	/**
	 * 获取过去的分钟
	 * 
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * 
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
	}

	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

	/**
	 * 当月第一天
	 * 
	 * @return
	 */
	public static String getFirstDay() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();

		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
		return str.toString();

	}

	/**
	 * 当月最后一天
	 * 
	 * @return
	 */
	public static String getLastDay() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date theDate = calendar.getTime();
		String s = df.format(theDate);
		StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
		return str.toString();

	}

	/**
	 * timeFormat yyyyMMdd
	 * 
	 * @param format
	 * @return
	 */
	public static String getByTime(String format, Integer day) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -day);
		return new SimpleDateFormat(format).format(cal.getTime());
	}

	public static String getCurrentMonth(String format) {

		SimpleDateFormat sdf = new SimpleDateFormat(format != null ? format : "yyyyMM");
		Date date = new Date();
		return sdf.format(date);
	}

	public static String getMonth(String format, Integer monthIndex) {

		SimpleDateFormat sdf = new SimpleDateFormat(format != null ? format : "yyyyMM");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, monthIndex);
		return sdf.format(cal.getTime());
	}

	public static String getDay(String pattern, String currentDay, Integer day) {

		String zjjfMonthBeginTime = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern != null ? pattern : "yyyyMMdd");
			Date date = sdf.parse(currentDay);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, -day);
			zjjfMonthBeginTime = sdf.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return zjjfMonthBeginTime;
	}

	public static String getPreDay(String currentDay, String pattern) {

		String preDay = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			Date date = sdf.parse(currentDay);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.DATE, -1);
			preDay = sdf.format(DateUtils.addDays(date, -1));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preDay;
	}

	public static String getZjjfMonthBeginTime(String currentDay, String pattern, Integer monthIndex) {

		String zjjfMonthBeginTime = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern != null ? pattern : "yyyyMMdd");
			Date date = sdf.parse(currentDay);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int day = cal.get(Calendar.DATE);// 获取日
			if (day > 28) {
				cal.add(Calendar.MONTH, -monthIndex + 1);
			} else {
				cal.add(Calendar.MONTH, -monthIndex);
			}
			String temp = sdf.format(cal.getTime());
			zjjfMonthBeginTime = temp.substring(0, temp.length() - 2) + "29";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return zjjfMonthBeginTime;
	}

	public static String getZjjfMonthEndTime(String currentDay, String pattern, Integer monthIndex) {

		String zjjfMonthEndTime = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern != null ? pattern : "yyyyMMdd");
			Date date = sdf.parse(currentDay);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int day = cal.get(Calendar.DATE);// 获取日
			if (day > 28) {
				cal.add(Calendar.MONTH, -monthIndex + 1);
			} else {
				cal.add(Calendar.MONTH, -monthIndex);
			}
			String temp = sdf.format(cal.getTime());
			zjjfMonthEndTime = temp.substring(0, temp.length() - 2) + "28";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return zjjfMonthEndTime;
	}

	// 获得当前周- 周一的日期
	public static String getCurrentMonday(String currentDay) {
		String preMonday = "";
		try {
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			int mondayPlus = getMondayPlus(currentDay);
			Date date = df.parse(currentDay);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, mondayPlus);
			preMonday = df.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return preMonday;
	}
	
	public static String getCurrentSunday(String currentDay) {
		String preMonday = "";
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			int mondayPlus = getMondayPlus(currentDay);
			Date date = df.parse(currentDay);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, mondayPlus+6);
			preMonday = df.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return preMonday;
	}

	public static String getPreviousSunday(String currentDay) {
		int mondayPlus = getMondayPlus(currentDay);
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	public static int getMondayPlus(String currentDay) {
		try {
			Calendar cd = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = sdf.parse(currentDay);
			cd.setTime(date);
			// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
			int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
			if (dayOfWeek == 1) {
				return -6;
			} else {
				return 2 - dayOfWeek;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static String getCurrentWeek(String currentDay) {
		int mondayPlus = getMondayPlus(currentDay);
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preMonday = df.format(monday);
		return preMonday;
	}

	public static String timeyyyyMMddConvertyyyy_MM_dd(String dayTime) {

		SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
		String sfstr = "";
		try {
			sfstr = sf2.format(sf1.parse(dayTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sfstr;
	}

	public static String timeyyyy_MM_ddConvertyyyyMMdd(String dayTime) {

		if (dayTime == null || "".equals(dayTime) || "null".equals(dayTime.trim())) {
			return null;
		}
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
		String sfstr = "";
		try {
			sfstr = sf1.format(sf2.parse(dayTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sfstr;
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}
	
	public static String getPrex(String dayTime, int pre) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = sdf.parse(dayTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -pre);
			return sdf.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		// System.out.println(formatDate(parseDate("2010/3/6")));
		// System.out.println(getDate("yyyy年MM月dd日 E"));
		// long time = new Date().getTime()-parseDate("2012-11-19").getTime();
		// System.out.println(time/(24*60*60*1000));

		System.err.println(DateUtils.getZjjfMonthBeginTime("20160527", "yyyyMMdd", 1));
	}
}
