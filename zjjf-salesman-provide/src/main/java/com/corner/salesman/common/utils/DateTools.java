package com.corner.salesman.common.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
 
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
 
/**
 * 时间工具测试类 该类基于commons-lang 包 编写使用例子 方便之后调用
 * 
 * @Title: DateTools.java
 * @Package com.excel.jxl.util
 * @Description: TODO(时间工具类 )
 * <a href="http://my.oschina.net/arthor" target="_blank" rel="nofollow">@author</a> yangy * @date 2012-9-27 下午02:52:05
 */
public class DateTools {
 
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
        return DateUtils.isSameDay(date1, date2);
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
        return DateUtils.isSameDay(cal1, cal2);
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
        return DateUtils.addYears(date, year);
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
        return DateUtils.addMonths(date, month);
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
        return DateUtils.addWeeks(date, amount);
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
        return DateUtils.addDays(date, amount);
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
        return DateUtils.addHours(date, amount);
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
        return DateUtils.addHours(date, amount);
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
        return DateUtils.addSeconds(date, amount);
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
        return DateUtils.addMilliseconds(date, amount);
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
        return DateUtils.setYears(date, amount);
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
        return DateUtils.setMonths(date, amount);
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
        return DateUtils.setDays(date, amount);
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
        return DateUtils.setHours(date, amount);
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
        return DateUtils.setMinutes(date, amount);
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
        return DateUtils.setMinutes(date, amount);
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
        Date a = DateUtils.addDays(date, -1);
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
        Date a = DateUtils.addDays(date, -1);
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
        Date a = DateUtils.addDays(date, -1);
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
         
        Date a = DateUtils.addDays(date, -1);
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
     * 测试方法
     * 
     * @param args
     * @throws ParseException 
     */
    public static void main(String[] args) throws ParseException {
//       Date date = getMonday(format("2012-9-09"));
         
//      Date date = getLastSunday(new Date());
        Date date = getLastWeekSunday(new Date());   
         System.out.println(format(date));
    }
}