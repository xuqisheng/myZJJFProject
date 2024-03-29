package com.corner.core.pay.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DateUtil {
	
	
    /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong                  = "yyyyMMddHHmmss";
    
    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple                  = "yyyy-MM-dd HH:mm:ss";
    
    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtShort                 = "yyyyMMdd";
	
    
    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
	public  static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public  static String getDateFormatter(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(simple);
		return df.format(date);
	}
	
	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * @return
	 */
	public static String getDate(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtShort);
		return df.format(date);
	}
	
	/**
	 * 产生随机的三位数
	 * @return
	 */
	public static String getThree(){
		Random rad=new Random();
		int radint ;
		do{
			radint= rad.nextInt(1000);
		}while(radint<123);
		return radint+"";
	}
	
	/**
	 * 产生随机的三位数 
	 * @return
	 */
	public static String getThreeInt(){
		Random rad=new Random();
		return rad.nextInt(900)+100+"";
	}
	
	/**
	 * 获取系统当期年月日(精确到天)，格式："yyyyMMddHHmmss"
	 * @return
	 */
	public static Date StringToDateDtLong(String dateString){
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
	 * @return
	 */
	public static Date StringToDateSimple(String dateString){
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
	
	public static void main(String[] args) {
		Date d = DateUtil.StringToDateDtLong("20150320101934");
		System.out.println(d);
	}

}
