package com.zjjf.analysis.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {

	/** 年月日时分秒(无下划线) yyyyMMddHHmmss */
	public static final String dtLong = "yyyyMMddHHmmss";

	/** 完整时间 yyyy-MM-dd HH:mm:ss */
	public static final String simple = "yyyy-MM-dd HH:mm:ss";

	/** 年月日(无下划线) yyyyMMdd */
	public static final String dtShort = "yyyyMMdd";

	/**
	 * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
	 * 
	 * @return 以yyyyMMddHHmmss为格式的当前系统时间
	 */
	public static String getOrderNum() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtLong);
		return df.format(date);
	}

	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getDateFormatter() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(simple);
		return df.format(date);
	}

	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * 
	 * @return
	 */
	public static String getDate() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtShort);
		return df.format(date);
	}

	/**
	 * timeFormat yyyyMMdd
	 * 
	 * @param format
	 * @return
	 */
	public static String getYesToday(String format) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		return new SimpleDateFormat(format).format(cal.getTime());
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

	/**
	 * 产生随机的三位数
	 * 
	 * @return
	 */
	public static String getThree() {
		Random rad = new Random();
		int radint;
		do {
			radint = rad.nextInt(1000);
		} while (radint < 123);
		return radint + "";
	}

	/**
	 * 产生随机的三位数
	 * 
	 * @return
	 */
	public static String getThreeInt() {
		Random rad = new Random();
		return rad.nextInt(900) + 100 + "";
	}

	/**
	 * 获取系统当期年月日(精确到天)，格式："yyyyMMddHHmmss"
	 * 
	 * @return
	 */
	public static Date StringToDateDtLong(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat(dtLong);
		Date date = new Date();
		try {
			date = sdf.parse(dateString);
		} catch (Exception e) {
			// TODO: handle exception
			date = null;
		}
		return date;
	}

	/**
	 * 获取系统当期年月日(精确到天)，格式："yyyy-MM-dd HH:mm:ss";
	 * 
	 * @return
	 */
	public static Date StringToDateSimple(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat(simple);
		Date date = new Date();
		try {
			date = sdf.parse(dateString);
		} catch (Exception e) {
			// TODO: handle exception
			date = null;
		}
		return date;
	}

	/**
	 * 获取今天的开始时间
	 * 
	 * @return
	 */
	public static Timestamp getDayBegin() {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 00);
		return new Timestamp(cal.getTimeInMillis());
	}

	public static String getZjjfMonthBeginTime(String currentDay, String pattern, Integer monthIndex) {

		String zjjfMonthBeginTime = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern != null ? pattern : "yyyyMMdd");
			Date date = sdf.parse(currentDay);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int day = cal.get(Calendar.DATE);// 获取日
			if(day > 28){
				cal.add(Calendar.MONTH, -monthIndex + 1);
			}else{
				cal.add(Calendar.MONTH, -monthIndex);
			}
			zjjfMonthBeginTime = sdf.format(cal.getTime()).substring(0, 6) + "29";
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
			if(day > 28){
				cal.add(Calendar.MONTH, -monthIndex + 1);
			}else{
				cal.add(Calendar.MONTH, -monthIndex);
			}
			zjjfMonthEndTime = sdf.format(cal.getTime()).substring(0, 6) + "28";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return zjjfMonthEndTime;
	}

	public static String getLastMonth(String currentDay, String pattern, Integer index) {

		String zjjfMonthEndTime = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern != null ? pattern : "yyyyMMdd");
			Date date = sdf.parse(currentDay);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.MONTH, -index);
			zjjfMonthEndTime = sdf.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return zjjfMonthEndTime;
	}

	public static String getCurrentMonthFirstDay(String currentDay, String pattern) {
		String firstDay = "";
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			Date date = format.parse(currentDay);
			Calendar cal_1 = Calendar.getInstance();// 获取当前日期
			cal_1.setTime(date);
			// cal_1.add(Calendar.MONTH, -1);
			cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
			firstDay = format.format(cal_1.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return firstDay;
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

		
//	public static void main(String[] args) {
//		System.out.println(getCurrentMonthFirstDay("20160602", "yyyyMMdd"));
//
//	}

	public static void main(String[] args) {
		Date date = new Date();
		// add method
		// This method deprecated
		//System.out.println(DateUtils.add(date, Calendar.MONTH, 1));
		// Use addYears addWeeks addMonths addDays addHours addMinutes
		// addSeconds replace it
		System.out.println(DateUtils.addDays(date, 1));
		System.out.println(DateUtils.addHours(date, 1));
		System.out.println(DateUtils.addMinutes(date, 1));
		System.out.println(DateUtils.addSeconds(date, 1));
		// ceiling 取上限
		System.out.println(DateUtils.ceiling(date, Calendar.HOUR));
		System.out.println(DateUtils.ceiling(date, Calendar.MINUTE));
		// truncate 类似Oracle SQL语句中的truncate函数
		System.out.println(DateUtils.truncate(date, Calendar.HOUR));
		// truncatedEquals truncate之后进行比较

		// toCalendar方法将Date装换成Calendar (java.util.GregorianCalendar)
		System.out.println((DateUtils.toCalendar(date)).getClass());
		// parseDate方法
		try {
			System.out.println(DateUtils.parseDateStrictly("10-22-2010", new String[] { "yyyy-MM-dd", "MM-dd-yyyy" }));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// DateUtils 是对Calendar和SimpleDateFormat方法的补充
	}

}
