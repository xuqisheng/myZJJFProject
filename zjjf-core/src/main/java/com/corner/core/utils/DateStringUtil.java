package com.corner.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateStringUtil {

	/**
	 * 日期转换(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String str = sdf.format(date);

		return str;
	}
	/**
	 * 日期转换(yyyy-MM-dd)
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString1(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String str = sdf.format(date);

		return str;
	}
	/**
	 * 日期转换(HH:mm:ss)
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString2(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		String str = sdf.format(date);

		return str;
	}
	/**
	 * 日期转换(yyyy-MM)
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString3(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

		String str = sdf.format(date);

		return str;
	}
	/**
	 * 日期转换(yyyy-MM-dd HH:mm)
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString4(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		String str = sdf.format(date);

		return str;
	}
	/**
	 * 日期转换(HH:mm)
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString5(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

		String str = sdf.format(date);

		return str;
	}
	
	/**
	 * 日期转换(MM-dd HH:mm)
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString6(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");

		String str = sdf.format(date);

		return str;
	}
	/**
	 * 字符串转成日期格式（yyyy-MM-dd）
	 * 
	 * @param str
	 * @return Date
	 */
	public static Date stringToDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 字符串转成日期格式（yyyy-MM-dd HH:mm:ss）
	 * 
	 * @param str
	 * @return Date
	 */
	public static Date stringToDate1(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 字符串转成日期格式（yyyy-MM-dd HH:mm）
	 * 
	 * @param str
	 * @return Date
	 */
	public static Date stringToDate2(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 字符串转成日期格式（HH:mm:ss）
	 * 
	 * @param str
	 * @return Date
	 */
	public static Date stringToDate3(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	// 时间戳转化为Date
	public static Date ToDate(Long time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = format.format(time);
		Date date = null;
		try {
			date = format.parse(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static Long ToLong(String time) {
		// Date或者String转化为时间戳
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("Format To times:" + date.getTime());
		Long Ltime = date.getTime();
		return Ltime;
	}

	// 时间戳转化为String
	public static String ToString(Long time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = format.format(time);
		return d;
	}
    //比较两个日期是否属于同一月
	public static int calculateMonthIn(Date date1, Date date2) {
		Calendar cal1 = new GregorianCalendar();
		cal1.setTime(date1);
		Calendar cal2 = new GregorianCalendar();
		cal2.setTime(date2);
		int c = (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12
				+ cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
		return c;
	}
	//根据当前日期获取其他日期(yyyy-MM-dd)
	public static String getTomorrow(int days){
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,days);//把日期往后增加一天.整数往后推,负数往前移动
		date=calendar.getTime(); //这个时间就是日期往后推一天的结果
		String dateString = dateToString1(date);
        return dateString;
	}
	
	//根据当前日期获取其他日期(yyyy-MM-dd HH:mm:ss)
		public static String getTomorrow1(int days){
			Date date=new Date();//取时间
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(calendar.DATE,days);//把日期往后增加一天.整数往后推,负数往前移动
			date=calendar.getTime(); //这个时间就是日期往后推一天的结果
			String dateString = dateToString(date);
	        return dateString;
		}
	
	public static void main(String[] args) {
		//DateStringUtil util = new DateStringUtil();
		Date date = DateStringUtil.stringToDate3("08:00:00");
		System.out.println(date);
	}
}
