package com.corner.data.analysis.utils;
import java.text.SimpleDateFormat;  
import java.util.Calendar;  
import java.util.Date;  
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
  
/** 
 * <p> 
 * Title:时间工具类 
 * </p> 
 *  
 * <p> 
 * Description: 
 * </p> 
 *  
 * <p> 
 *  * </p> 
 *  
 *  
 *  
 * @since：2007-12-10 上午11:22:56 
 *  
 * @version 1.0 
 */  
public class DateUtil {  
    /** 
     * 将字符串转化为DATE 
     *  
     * @param dtFormat 
     *            格式yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd 
     * @param def 
     *            如果格式化失败返回null 
     * @return 
     */  
    /** 
     * 将字符串转化为DATE 
     *  
     * @param dtFormat 
     *            格式yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd或 yyyy-M-dd或 yyyy-M-d或 
     *            yyyy-MM-d或 yyyy-M-dd 
     * @param def 
     *            如果格式化失败返回null 
     * @return 
     */  
    public static Date fmtStrToDate(String dtFormat) {  
        if (StringUtils.isBlank(dtFormat)) {  
            return null;  
        }  
 
        try {  
            if (dtFormat.length() == 9 || dtFormat.length() == 8) {  
                String[] dateStr = dtFormat.split("-");  
                dtFormat = dateStr[0] + (dateStr[1].length() == 1 ? "-0" : "-")  
                        + dateStr[1] + (dateStr[2].length() == 1 ? "-0" : "-")  
                        + dateStr[2];  
            }  
            if (dtFormat.length() != 10 & dtFormat.length() != 19)  
                return null;  
            if (dtFormat.length() == 10)  
                dtFormat = dtFormat + " 00:00:00";  
            SimpleDateFormat dateFormat = new SimpleDateFormat(  
                    "yyyy-MM-dd HH:mm:ss");  
            return dateFormat.parse(dtFormat);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
  
    /** 
     *  
     * Description:格式化日期,如果格式化失败返回def 
     *  
     * @param dtFormat 
     * @param def 
     * @return 
     * @author 孙钰佳 
     * @since：2008-2-15 下午05:01:37 
     */  
    public static Date fmtStrToDate(String dtFormat, Date def) {  
        Date d = fmtStrToDate(dtFormat);  
        if (d == null)  
            return def;  
        return d;  
    }  
  
    /** 
     * 返回当日短日期型 
     *  
     * @return 
     * @author 孙钰佳 
     * @since：2008-2-15 下午05:03:13 
     */  
    public static Date getToDay() {  
        return toShortDate(new Date());  
    }  
  
    /** 
     *  
     * Description:格式化日期,String字符串转化为Date 
     *  
     * @param date 
     * @param dtFormat 
     *            例如:yyyy-MM-dd HH:mm:ss yyyyMMdd 
     * @return 
     * @author 孙钰佳 
     * @since：2007-7-10 上午11:24:00 
     */  
    public static String fmtDateToStr(Date date, String dtFormat) {  
        if (date == null)  
            return "";  
        try {  
            SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);  
            return dateFormat.format(date);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return "";  
        }  
    }  
  
    /** 
     * Description:按指定格式 格式化日期 
     *  
     * @param date 
     * @param dtFormat 
     * @return 
     * @author 孙钰佳 
     * @since：2007-12-10 上午11:25:07 
     */  
    public static Date fmtStrToDate(String date, String dtFormat) {  
        try {  
            SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);  
            return dateFormat.parse(date);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
  
    public static String fmtDateToYMDHM(Date date) {  
        return fmtDateToStr(date, "yyyy-MM-dd HH:mm");  
    }  
  
    public static String fmtDateToYMD(Date date) {  
        return fmtDateToStr(date, "yyyy-MM-dd");  
    }  
  
    public static String fmtDateToYM(Date date) {  
        return fmtDateToStr(date, "yyyy-MM");  
    }  
  
    public static String fmtDateToM(Date date) {  
        return fmtDateToStr(date, "MM");  
    }  
  
    /** 
     *  
     * Description:只保留日期中的年月日 
     *  
     * @param date 
     * @return 
     * @author 孙钰佳 
     * @since：2007-12-10 上午11:25:50 
     */  
    public static Date toShortDate(Date date) {  
        String strD = fmtDateToStr(date, "yyyy-MM-dd");  
        return fmtStrToDate(strD);  
    }  
  
    /** 
     *  
     * Description:只保留日期中的年月日 
     *  
     * @param date格式要求yyyy 
     *            -MM-dd…………………… 
     * @return 
     * @author 孙钰佳 
     * @since：2007-12-10 上午11:26:12 
     */  
    public static Date toShortDate(String date) {  
        if (date != null && date.length() >= 10) {  
            return fmtStrToDate(date.substring(0, 10));  
        } else  
            return fmtStrToDate(date);  
    }  
  
    /** 
     * 求对日 
     *  
     * @param countMonth 
     *            :月份的个数(几个月) 
     * @param flag 
     *            :true 求前countMonth个月的对日:false 求下countMonth个月的对日 
     * @return 
     */  
    public static Date getCounterglow(int countMonth, boolean before) {  
        Calendar ca = Calendar.getInstance();  
        return getCounterglow(ca.getTime(), before ? -countMonth : countMonth);  
    }  
  
    /** 
     *  
     * Description: 求对日 加月用+ 减月用- 
     *  
     * @param date 
     * @param countMonth 
     * @return 
     * @since：2007-12-13 下午03:16:47 
     */  
    public static Date getCounterglow(Date date, int num) {  
        Calendar ca = Calendar.getInstance();  
        ca.setTime(date);  
        ca.add(Calendar.MONTH, num);  
        return ca.getTime();  
    }  
  
    /** 
     *  
     * Description:加一天 
     *  
     * @param date 
     * @return 
     * @author 孙钰佳 
     * @since：2007-12-13 下午02:57:38 
     */  
    public static Date addDay(Date date) {  
        Calendar cd = Calendar.getInstance();  
        cd.setTime(date);  
        cd.add(Calendar.DAY_OF_YEAR, 1);  
        return cd.getTime();  
    }  
  
    /** 
     *  
     * Description:判断一个日期是否为工作日(非周六周日) 
     *  
     * @param date 
     * @return 
     * @author 孙钰佳 
     * @since：2007-12-13 下午03:01:35 
     */  
    public static boolean isWorkDay(Date date) {  
        Calendar cd = Calendar.getInstance();  
        cd.setTime(date);  
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);  
        if (dayOfWeek != Calendar.SUNDAY || dayOfWeek != Calendar.SATURDAY)  
            return false;  
        return true;  
    }  
  
    /** 
     *  
     * Description:取一个月的最后一天 
     *  
     * @param date1 
     * @return 
     * @author 孙钰佳 
     * @since：2007-12-13 下午03:28:21 
     */  
    public static Date getLastDayOfMonth(Date date1) {  
        Calendar date = Calendar.getInstance();  
        date.setTime(date1);  
        date.set(Calendar.DAY_OF_MONTH, 1);  
        date.add(Calendar.MONTH, 1);  
        date.add(Calendar.DAY_OF_YEAR, -1);  
        return toShortDate(date.getTime());  
    }  
  
    /** 
     * 求开始截至日期之间的天数差. 
     *  
     * @param d1 
     *            开始日期 
     * @param d2 
     *            截至日期 
     * @return 返回相差天数 
     */  
    public static int getDaysInterval(Date d1, Date d2) {  
        if (d1 == null || d2 == null)  
            return 0;  
        Date[] d = new Date[2];  
        d[0] = d1;  
        d[1] = d2;  
        Calendar[] cal = new Calendar[2];  
        for (int i = 0; i < cal.length; i++) {  
            cal[i] = Calendar.getInstance();  
            cal[i].setTime(d[i]);  
            cal[i].set(Calendar.HOUR_OF_DAY, 0);  
            cal[i].set(Calendar.MINUTE, 0);  
            cal[i].set(Calendar.SECOND, 0);  
        }  
        long m = cal[0].getTime().getTime();  
        long n = cal[1].getTime().getTime();  
        int ret = (int) Math.abs((m - n) / 1000 / 3600 / 24);  
        return ret;  
    }  
  
    public static String getDayOfWeek(Date date) {  
        Calendar cl = Calendar.getInstance();  
        cl.setTime(date);  
        return "周" + toChNumber(cl.get(Calendar.DAY_OF_WEEK) - 1);  
    }  
  
    /** 
     * 将数字转为中文。 "0123456789"->"〇一二三四五六七八九" 
     *  
     * @param num 
     *            长度为1,'0'-'9'的字符串 
     * @return 
     */  
    private static String toChNumber(int num) {  
        final String str = "〇一二三四五六七八九";  
        return str.substring(num, num + 1);  
    }  
  
    /** 
     *  
     * Description:指定日期加或减days天 
     *  
     * @param date1日期 
     * @param days天数 
     * @return 
     * @author 孙钰佳 
     * @since：2007-12-17 下午03:47:12 
     */  
    public static Date addDay(Date date1, int days) {  
        Calendar date = Calendar.getInstance();  
        date.setTime(date1);  
        date.add(Calendar.DAY_OF_YEAR, days);  
        return date.getTime();  
    }  
  
    /** 
     *  
     * Description:指定日期加或减months月 
     *  
     * @param date1 
     * @param months 
     * @return 
     * @author 孙钰佳 
     * @since：2008-3-5 下午05:17:26 
     */  
    public static Date addMonth(Date date1, int months) {  
        Calendar date = Calendar.getInstance();  
        date.setTime(date1);  
        date.add(Calendar.MONTH, months);  
        return date.getTime();  
    }  
  
    /** 
     *  
     * Description:指定日期加或减years年 
     *  
     * @param date1 
     * @param years 
     * @return 
     */  
    public static Date addYear(Date date1, int years) {  
        Calendar date = Calendar.getInstance();  
        date.setTime(date1);  
        date.add(Calendar.YEAR, years);  
        return date.getTime();  
    }  
  
    /** 
     * 指定期间的开始日期 
     *  
     * @param date 
     *            指定日期 
     * @param type 
     *            期间类型 
     * @param diff 
     *            与指定日期的范围 
     * @return 
     */  
    public static Date getPeriodStart(Calendar date, int type, int diff) {  
        date.add(type, diff * (-1));  
        return date.getTime();  
    }  
  
    /** 
     * 指定期间的开始日期 
     *  
     * @param date 
     *            指定日期 
     * @param type 
     *            期间类型 
     * @param diff 
     *            与指定日期的范围 
     * @return 
     */  
    public static Date getPeriodStart(Date date, int type, int diff) {  
        return getPeriodStart(dateToCalendar(date), type, diff);  
    }  
  
    /** 
     * 指定期间的结束日期 
     *  
     * @param date 
     *            指定日期 
     * @param type 
     *            期间类型 
     * @param diff 
     *            与指定日期的范围 
     * @return 
     */  
    public static Date getPeriodEnd(Calendar date, int type, int diff) {  
        date.add(type, diff);  
        return date.getTime();  
    }  
  
    /** 
     * 指定期间的结束日期 
     *  
     * @param date 
     *            指定日期 
     * @param type 
     *            期间类型 
     * @param diff 
     *            与指定日期的范围 
     * @return 
     */  
    public static Date getPeriodEnd(Date date, int type, int diff) {  
        return getPeriodEnd(dateToCalendar(date), type, diff);  
    }  
  
    /** 
     * 指定日期所在星期的第一天 
     *  
     * @param date 
     * @return 
     */  
    public static Date getWeekStart(Date date) {  
        Calendar cdate = dateToCalendar(date);  
        cdate.set(Calendar.DAY_OF_WEEK, 2);  
        return cdate.getTime();  
    }  
  
    /** 
     * 将java.util.Date类型转换成java.util.Calendar类型 
     *  
     * @param date 
     * @return 
     */  
    public static Calendar dateToCalendar(Date date) {  
        Calendar cdate = Calendar.getInstance();  
        cdate.setTime(date);  
        return cdate;  
    }  
  
    /** 
     * 指定日期所在月的第一天 
     *  
     * @param date 
     * @return 
     */  
    public static Date getMonthStart(Date date) {  
        Calendar cdate = dateToCalendar(date);  
        cdate.set(Calendar.DAY_OF_MONTH, 1);  
        return toShortDate(cdate.getTime());  
    }  
  
    /** 
     * 指定日期所在上月的第一天 
     *  
     * @param date 
     * @return 
     */  
    public static Date getLastMonthStart(Date date) {  
        Calendar cdate = dateToCalendar(date);  
        cdate.set(Calendar.DAY_OF_MONTH, 1);  
        cdate.add(Calendar.MONTH, -1);  
        return toShortDate(cdate.getTime());  
    }  
  
    /** 
     * 指定日期所在旬的第一天 
     *  
     * @param date 
     * @return 
     */  
    public static Date getTenDaysStart(Date date) {  
        Calendar cdate = dateToCalendar(date);  
        int day = cdate.get(Calendar.DAY_OF_MONTH) / 10 * 10 + 1;  
        if (cdate.get(Calendar.DAY_OF_MONTH) % 10 == 0 || day == 31)  
            day = day - 10;  
        cdate.set(Calendar.DAY_OF_MONTH, day);  
        return cdate.getTime();  
    }  
  
    /** 
     * 指定日期所在旬的最后一天 
     *  
     * @param date 
     * @return 
     */  
    public static Date getTenDaysEnd(Date date) {  
        Calendar cdate = dateToCalendar(date);  
        if (cdate.get(Calendar.DAY_OF_MONTH) / 10 == 2  
                && cdate.get(Calendar.DAY_OF_MONTH) != 20)  
            return getLastDayOfMonth(date);  
        else  
            return addDay(getTenDaysStart(addDay(date, 10)), -1);  
    } 
    
    /**
     * 当月第一天
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
     * 指定日期所在年的第一天 
     *  
     * @param date 
     * @return 
     */  
    public static Date getYearStart(Date date) {  
        Calendar cdate = dateToCalendar(date);  
        cdate.set(Calendar.DAY_OF_YEAR, 1);  
        return cdate.getTime();  
    }  
  
    /** 
     * 指定日期所在季度的第一天 
     *  
     * @param date 
     * @return 
     */  
    public static Date getQuarterStart(Date date) {  
        Calendar cdate = dateToCalendar(date);  
        int month = (cdate.get(Calendar.MONTH) / 3) * 3;  
        cdate.set(Calendar.MONTH, month);  
        return getMonthStart(cdate.getTime());  
    }  
  
    /** 
     * 指定日期返回带中文的字符串（目前为年月日类型，之后补充） 
     *  
     * @param date 
     * @param format 
     * @return 
     */  
    public static String dateToStringByChinese(String format, Date date) {  
        String dateString = fmtDateToStr(date, format);  
        String[] dateStringArray = dateString.split("-");  
        if ("yyyy-MM-dd".equals(format)) {  
            dateString = dateStringArray[0] + "年" + dateStringArray[1] + "月"  
                    + dateStringArray[2] + "日";  
        } else if ("yyyy-MM".equals(format)) {  
            dateString = dateStringArray[0] + "年" + dateStringArray[1] + "月";  
        }  
        return dateString;  
    }  
  
    public static Date getLastDayOfYear(Date date) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");  
        String years = dateFormat.format(date);  
        years += "-12-31";  
        Date returnDate = fmtStrToDate(years);  
        return toShortDate(returnDate);  
    }  
  
    /** 
     * 计算两个日期之间相差的月数 
     *  
     * @param date1 
     * @param date2 
     * @return 
     */  
    public static int getMonths(Date date1, Date date2) {  
        int iMonth = 0;  
        int flag = 0;  
        try {  
            Calendar objCalendarDate1 = Calendar.getInstance();  
            objCalendarDate1.setTime(date1);  
  
            Calendar objCalendarDate2 = Calendar.getInstance();  
            objCalendarDate2.setTime(date2);  
  
            if (objCalendarDate2.equals(objCalendarDate1))  
                return 0;  
            if (objCalendarDate1.after(objCalendarDate2)) {  
                Calendar temp = objCalendarDate1;  
                objCalendarDate1 = objCalendarDate2;  
                objCalendarDate2 = temp;  
            }  
            if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1  
                    .get(Calendar.DAY_OF_MONTH))  
                flag = 1;  
  
            if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1  
                    .get(Calendar.YEAR))  
                iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1  
                        .get(Calendar.YEAR))  
                        * 12 + objCalendarDate2.get(Calendar.MONTH) - flag)  
                        - objCalendarDate1.get(Calendar.MONTH);  
            else  
                iMonth = objCalendarDate2.get(Calendar.MONTH)  
                        - objCalendarDate1.get(Calendar.MONTH) - flag;  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return iMonth;  
    }  
  
    /** 
     * 指定日期上一个旬的第一天 
     */  
    public static Date getLastTenStartDate(Date date) {  
        Date returnDate = DateUtil.toShortDate(date);  
        returnDate = DateUtil.getTenDaysStart(date);  
        returnDate = DateUtil.addDay(returnDate, -1);  
        returnDate = DateUtil.getTenDaysStart(returnDate);  
        return DateUtil.toShortDate(returnDate);  
    }  
  
    /** 
     * 指定日期上一个旬的最后一天 
     */  
    public static Date getLastTenEndDate(Date date) {  
        Date returnDate = DateUtil.toShortDate(date);  
        returnDate = DateUtil.getTenDaysStart(date);  
        returnDate = DateUtil.addDay(returnDate, -1);  
        return DateUtil.toShortDate(returnDate);  
    }  
  
    /** 
     * 指定日期上个月第一天 
     */  
    public static Date getLastMonthStartDate(Date date) {  
        Date returnDate = DateUtil.toShortDate(date);  
        returnDate = DateUtil.getLastMonthStart(date);  
        return DateUtil.toShortDate(returnDate);  
    }  
  
    /** 
     * 指定日期上个月最后一天 
     */  
    public static Date getLastMonthEndDate(Date date) {  
        Date returnDate = DateUtil.toShortDate(date);  
        returnDate = DateUtil.getMonthStart(date);  
        returnDate = DateUtil.addDay(returnDate, -1);  
        return DateUtil.toShortDate(returnDate);  
    } 
    
    /** 
     * 指定日期上个月最后一天 
     */  
    public static Date getLastMonthEndDate2(Date date,int ii) {  
        Date returnDate = DateUtil.toShortDate(date);  
        returnDate = DateUtil.getMonthStart(date);  
        returnDate = DateUtil.addDay(returnDate, ii);  
        return DateUtil.toShortDate(returnDate);  
    }  
    
    /** 
     * 指定日期所在上月的第一天 
     *  
     * @param date 
     * @return 
     */  
    public static Date getLastMonthStart(Date date,int mothVal,int dateVal) {  
        Calendar cdate = dateToCalendar(date);  
        cdate.set(Calendar.DAY_OF_MONTH, dateVal);  
        cdate.add(Calendar.MONTH, mothVal);  
        return toShortDate(cdate.getTime());  
    }  
    
    public static void main(String[] args) {
    	/*Date d =DateUtil.getMonthStart(new Date());
    	System.err.println(DateUtil.fmtDateToStr(d, "yyy-MM-dd"));
    	System.err.println(DateUtil.fmtDateToStr(new Date(), "yyy-MM-dd"));*/
//        System.err.println(fmtDateToYM(new Date()));
        String strDate = "2016-01-29";
        //小于查询时间
        Date date = fmtStrToDate(strDate, "yyy-MM-dd");//查询时间
        System.err.println(fmtDateToYM(date));
        String tempDate = fmtDateToYM(date)+"-28";
        Date compareDate = fmtStrToDate(tempDate,"yyy-MM-dd");
        
        //小于本月28
        int i = date.compareTo(compareDate);
        System.err.println(strDate);
        System.err.println(i);
        if(i>0){
        	//如果i大于本月28号则取，本月29号开始到下一个月的28号
        	System.err.println("如果i大于本月28号则取，本月29号开始到下一个月的28号");
            String preTime = fmtDateToStr(getLastMonthStart(date,0,29), "yyy-MM-dd");//查询时间
            System.err.println(preTime);
            String nextTime = fmtDateToStr(getLastMonthStart(date,1,28), "yyy-MM-dd");//查询时间
            System.err.println(nextTime);
        	
        }else if(i<=0){
        	//如果i小于等于28号，则取上月29号 到本月28号；
        	System.err.println("如果i小于等于28号，则取上月29号 到本月28号");
            String preTime = fmtDateToStr(getLastMonthStart(date,-1,29), "yyy-MM-dd");//查询时间
            System.err.println(preTime);
            String nextTime = fmtDateToStr(getLastMonthStart(date,0,28), "yyy-MM-dd");//查询时间
            System.err.println(nextTime);
        }
        
        
        
	}
  
}  
