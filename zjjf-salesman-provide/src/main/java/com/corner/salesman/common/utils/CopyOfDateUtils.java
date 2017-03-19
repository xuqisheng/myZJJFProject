package com.corner.salesman.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author ThinkGem
 * @version 2014-4-15
 */
public class CopyOfDateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

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
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
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
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
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
     * 判断两个时间是否相同
     * 
     * @param date1
     *            等待比较第一个时间
     * @param date2
     *            等待比较第二个时间
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 比较结果
     */
    public static boolean isSameDay(Date date1, Date date2) {
        return CopyOfDateUtils.isSameDay(date1, date2);
    }
 
    /**
     * 比较两个日历类数据是否相同
     * 
     * @param cal1
     *            比较第一个日历类
     * @param cal2
     *            比较第二个日历类
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 比较结果
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        return CopyOfDateUtils.isSameDay(cal1, cal2);
    }
 
    /**
     * 新增年份
     * 
     * @param date
     *            需要新增时间
     * @param year
     *            增加年份
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 增加后年份
     */
    public static Date addYears(Date date, int year) {
        return CopyOfDateUtils.addYears(date, year);
    }
 
    /**
     * 对时间格式进行格式化
     * 
     * @param date
     *            时间类型
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> yyyy-MM-dd
     */
    public static String format(Date date) {
        return DateFormatUtils.format(date, DateFormatUtils.ISO_DATE_FORMAT
                .getPattern());
    }
 
    /**
     * 对时间格式进行格式化
     * 
     * @param date
     *            时间类型
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> yyyy-MM-dd'T'HH:mm:ss
     */
    public static String formatDate(Date date) {
        return DateFormatUtils.format(date, DateFormatUtils.ISO_DATETIME_FORMAT
                .getPattern());
    }
 
    /**
     * 格式化时间
     * 
     * @param date
     *            时间参数
     * @param pattern
     *            格式化参数类型
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> */
    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }
 
    /**
     * 格式化时间参数
     * 
     * @param date
     *            时间参数
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> HH:mm:ss
     */
    public static String formatTime(Date date) {
        return DateFormatUtils.format(date,
                DateFormatUtils.ISO_TIME_NO_T_FORMAT.getPattern());
    }
 
    /**
     * 增加月份
     * 
     * @param date
     *            传入时间
     * @param month
     *            需要增加月份
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 增加月份
     */
    public static Date addMonths(Date date, int month) {
        return CopyOfDateUtils.addMonths(date, month);
    }
 
    /**
     * 增加周
     * 
     * @param date
     *            当前时间
     * @param amount
     *            需要增加周
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 增加后时间
     */
    public static Date addWeeks(Date date, int amount) {
        return CopyOfDateUtils.addWeeks(date, amount);
    }
 
    /**
     * 增加天
     * 
     * @param date
     *            当前时间
     * @param amount
     *            需要增加天数
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 增加后时间
     */
    public static Date addDays(Date date, int amount) {
        return CopyOfDateUtils.addDays(date, amount);
    }
 
    /**
     * 增加小时
     * 
     * @param date
     *            当前时间
     * @param amount
     *            增加小时数
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 增加后时间
     */
    public static Date addHours(Date date, int amount) {
        return CopyOfDateUtils.addHours(date, amount);
    }
 
    /**
     * 增加分钟
     * 
     * @param date
     *            当前时间
     * @param amount
     *            增加分钟数
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 增加后时间
     */
    public static Date addMinutes(Date date, int amount) {
        return CopyOfDateUtils.addHours(date, amount);
    }
 
    /**
     * 增加秒
     * 
     * @param date
     *            当前时间
     * @param amount
     *            增加秒数
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 增加后时间
     */
    public static Date addSeconds(Date date, int amount) {
        return CopyOfDateUtils.addSeconds(date, amount);
    }
 
    /**
     * 添加毫秒
     * 
     * @param date
     *            当前时间
     * @param amount
     *            增加毫秒
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 增加后时间
     */
    public static Date addMilliseconds(Date date, int amount) {
        return CopyOfDateUtils.addMilliseconds(date, amount);
    }
 
    /**
     * 设置年分 修改传入时间年份为amount
     * 
     * @param date
     *            当前时间
     * @param amount
     *            设置年份 传入Int类型 
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 设置后时间
     */
    public static Date setYears(Date date, int amount) {
        return CopyOfDateUtils.setYears(date, amount);
    }
 
    /**
     * 设置月份
     * 
     * @param date
     *            当前时间
     * @param amount
     *            设置月份 传入Int类型
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 设置月份后时间
     */
    public static Date setMonths(Date date, int amount) {
        return CopyOfDateUtils.setMonths(date, amount);
    }
 
    /**
     * 设置天数 
     * 
     * @param date
     *            当前时间
     * @param amount
     *            设置天数 传入Int类型
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 设置后时间
     */
    public static Date setDays(Date date, int amount) {
        return CopyOfDateUtils.setDays(date, amount);
    }
 
    /**
     * 设置小时数 
     * 
     * @param date
     *            当前时间
     * @param amount
     *            设置小时 传入Int类型
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 设置后时间
     */
    public static Date setHours(Date date, int amount) {
        return CopyOfDateUtils.setHours(date, amount);
    }
 
    /**
     * 设置分钟
     * 
     * @param date
     *            当前时间
     * @param amount
     *            设置分钟数
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 设置后时间
     */
    public static Date setMinutes(Date date, int amount) {
        return CopyOfDateUtils.setMinutes(date, amount);
    }
 
    /**
     * 时间日历类转换
     * 
     * @param date
     *            时间类型
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 日类类型
     */
    public static Calendar toCalendar(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }
 
    /**
     * 设置分钟数
     * 
     * @param date
     *            时间类型
     * @param amount
     *            设置分钟数
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 设置后时间
     */
    public static Date round(Date date, int amount) {
        return CopyOfDateUtils.setMinutes(date, amount);
    }
 
    /**
     * 获取传入时间的周一
     * 
     * @param date 当前时间
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 返回传入时间当周星期一
     */
    public static Date getNowWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
         
        cal.add(Calendar.DAY_OF_MONTH, -1); //解决周日会出现 并到下一周的情况
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
         
        return cal.getTime();
    }
     
    /**
     * 获取传入时间的上周一
     * 
     * @param date 当前时间
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 返回上周一时间
     */
    public static Date getLastWeekMonday(Date date) {
        Date a = CopyOfDateUtils.addDays(date, -1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(a);
        cal.add(Calendar.WEEK_OF_YEAR, -1);// 一周
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
          
        return cal.getTime();
    }
     
    /**
     * 获取传入时间下周一
     * @param date 当前时间
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 返回下周一日期
     */
    public static Date  getNextWeekMonday(Date date) {
        Date a = CopyOfDateUtils.addDays(date, -1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(a);
        cal.add(Calendar.WEEK_OF_YEAR, 1);// 一周
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
          
        return cal.getTime();
    }
     
    /**
     * 查询当周周日
     * @param date 当前时间 
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 当周周日
     */
    public static Date  getNowWeekSunday(Date date) {
        Date a = CopyOfDateUtils.addDays(date, -1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(a);
        cal.add(Calendar.WEEK_OF_YEAR, 1);// 一周
        cal.set(Calendar.DAY_OF_WEEK, 1);
          
        return cal.getTime();
    }
     
     
    /**
     * 查询下周周日
     * @param date 当前时间
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 返回下周周日
     */
    public static Date  getNextWeekSunday(Date date) {
         
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.WEEK_OF_YEAR, 1);// 一周
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
          
        return cal.getTime();
    }
     
    /**
     * 查询上周周日
     * @param date 当前时间
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 返回上周周日
     */
    public static Date  getLastWeekSunday(Date date) {
         
        Date a = CopyOfDateUtils.addDays(date, -1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(a);
        cal.set(Calendar.DAY_OF_WEEK, 1);
          
        return cal.getTime();
    }   
     
    /**
     * 解析字符串为 时间类型
     * @param date 字符串
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 时间类型
     * @throws ParseException 解析异常
     */
    public static Date format(String date) throws ParseException
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(date);
    }
     
     
    /**
     * 返回当月第一天
     * @param date 当前时间
     * <a href="http://my.oschina.net/u/556800" target="_blank" rel="nofollow">@return</a> 返回当月第一天
     */
    public static Date getNowMonthFirstDay(Date date)
    {
        return setDays(date,1);
    }
	
	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
		
		Date date = getNowWeekSunday(format("2012-9-30"));   
        System.out.println(format(date));
	}
}
