package com.zy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by win7 on 2017/1/10.
 * 时间转换工具类
 */

public class TimeUtils {

    /**
     * 默认时间转换格式
     */
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将时间戳转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param millis 毫秒时间戳
     * @return 时间字符串
     */
    public static String millis2String(long millis) {
        return new SimpleDateFormat(DEFAULT_PATTERN, Locale.getDefault()).format(new Date(millis));
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为pattern</p>
     *
     * @param millis  毫秒时间戳
     * @param pattern 时间格式
     * @return 时间字符串
     */
    public static String millis2String(long millis, String pattern) {
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(new Date(millis));
    }

    /**
     * 将时间字符串转为时间戳
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 毫秒时间戳
     */
    public static long string2Millis(String time) {
        return string2Millis(time, DEFAULT_PATTERN);
    }

    /**
     * 将时间字符串转为时间戳
     * <p>time格式为pattern</p>
     *
     * @param time    时间字符串
     * @param pattern 时间格式
     * @return 毫秒时间戳
     */
    public static long string2Millis(String time, String pattern) {
        try {
            return new SimpleDateFormat(pattern, Locale.getDefault()).parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 将时间字符串转为Date类型
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return Date类型
     */
    public static Date string2Date(String time) {
        return string2Date(time, DEFAULT_PATTERN);
    }

    /**
     * 将时间字符串转为Date类型
     * <p>time格式为pattern</p>
     *
     * @param time    时间字符串
     * @param pattern 时间格式
     * @return Date类型
     */
    public static Date string2Date(String time, String pattern) {
        return new Date(string2Millis(time, pattern));
    }

    /**
     * 将Date类型转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param date Date类型时间
     * @return 时间字符串
     */
    public static String date2String(Date date) {
        return date2String(date, DEFAULT_PATTERN);
    }

    /**
     * 将Date类型转为时间字符串
     * <p>格式为pattern</p>
     *
     * @param date    Date类型时间
     * @param pattern 时间格式
     * @return 时间字符串
     */
    public static String date2String(Date date, String pattern) {
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(date);
    }

    /**
     * 将Date类型转为时间戳
     *
     * @param date Date类型时间
     * @return 毫秒时间戳
     */
    public static long date2Millis(Date date) {
        return date.getTime();
    }

    /**
     * 将时间戳转为Date类型
     *
     * @param millis 毫秒时间戳
     * @return Date类型时间
     */
    public static Date millis2Date(long millis) {
        return new Date(millis);
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>time0和time1格式都为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time0 时间字符串0
     * @param time1 时间字符串1
     * @param unit  单位类型
     *              <ul>
     *              <li>{@link Constans.TimeUnit#MSEC}: 毫秒</li>
     *              <li>{@link Constans.TimeUnit#SEC }: 秒</li>
     *              <li>{@link Constans.TimeUnit#MIN }: 分</li>
     *              <li>{@link Constans.TimeUnit#HOUR}: 小时</li>
     *              <li>{@link Constans.TimeUnit#DAY }: 天</li>
     *              </ul>
     * @return unit时间戳
     */
    public static long getTimeSpan(String time0, String time1, Constans.TimeUnit unit) {
        return getTimeSpan(time0, time1, unit, DEFAULT_PATTERN);
    }

    /**
     * 获取两个时间差（单位：unit）
     * <p>time0和time1格式都为format</p>
     *
     * @param time0   时间字符串0
     * @param time1   时间字符串1
     * @param unit    单位类型
     *                <ul>
     *                <li>{@link Constans.TimeUnit#MSEC}: 毫秒</li>
     *                <li>{@link Constans.TimeUnit#SEC }: 秒</li>
     *                <li>{@link Constans.TimeUnit#MIN }: 分</li>
     *                <li>{@link Constans.TimeUnit#HOUR}: 小时</li>
     *                <li>{@link Constans.TimeUnit#DAY }: 天</li>
     *                </ul>
     * @param pattern 时间格式
     * @return unit时间戳
     */
    public static long getTimeSpan(String time0, String time1, Constans.TimeUnit unit, String pattern) {
        return ConvertUtils.millis2TimeSpan(Math.abs(string2Millis(time0, pattern) - string2Millis(time1, pattern)), unit);
    }

    /**
     * 获取两个时间差（单位：unit）
     *
     * @param date0 Date类型时间0
     * @param date1 Date类型时间1
     * @param unit  单位类型
     *              <ul>
     *              <li>{@link Constans.TimeUnit#MSEC}: 毫秒</li>
     *              <li>{@link Constans.TimeUnit#SEC }: 秒</li>
     *              <li>{@link Constans.TimeUnit#MIN }: 分</li>
     *              <li>{@link Constans.TimeUnit#HOUR}: 小时</li>
     *              <li>{@link Constans.TimeUnit#DAY }: 天</li>
     *              </ul>
     * @return unit时间戳
     */
    public static long getTimeSpan(Date date0, Date date1, Constans.TimeUnit unit) {
        return ConvertUtils.millis2TimeSpan(Math.abs(date2Millis(date0) - date2Millis(date1)), unit);
    }

    /**
     * 获取两个时间差（单位：unit）
     *
     * @param millis0 毫秒时间戳0
     * @param millis1 毫秒时间戳1
     * @param unit    单位类型
     *                <ul>
     *                <li>{@link Constans.TimeUnit#MSEC}: 毫秒</li>
     *                <li>{@link Constans.TimeUnit#SEC }: 秒</li>
     *                <li>{@link Constans.TimeUnit#MIN }: 分</li>
     *                <li>{@link Constans.TimeUnit#HOUR}: 小时</li>
     *                <li>{@link Constans.TimeUnit#DAY }: 天</li>
     *                </ul>
     * @return unit时间戳
     */
    public static long getTimeSpan(long millis0, long millis1, Constans.TimeUnit unit) {
        return ConvertUtils.millis2TimeSpan(Math.abs(millis0 - millis1), unit);
    }

    /**
     * 获取合适型两个时间差
     * <p>time0和time1格式都为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time0     时间字符串0
     * @param time1     时间字符串1
     * @param precision 精度
     *                  <p>precision = 0，返回null</p>
     *                  <p>precision = 1，返回天</p>
     *                  <p>precision = 2，返回天和小时</p>
     *                  <p>precision = 3，返回天、小时和分钟</p>
     *                  <p>precision = 4，返回天、小时、分钟和秒</p>
     *                  <p>precision &gt;= 5，返回天、小时、分钟、秒和毫秒</p>
     * @return 合适型两个时间差
     */
    public static String getFitTimeSpan(String time0, String time1, int precision) {
        return ConvertUtils.millis2FitTimeSpan(Math.abs(string2Millis(time0, DEFAULT_PATTERN) - string2Millis(time1, DEFAULT_PATTERN)), precision);
    }

    /**
     * 获取合适型两个时间差
     * <p>time0和time1格式都为pattern</p>
     *
     * @param time0     时间字符串0
     * @param time1     时间字符串1
     * @param precision 精度
     *                  <p>precision = 0，返回null</p>
     *                  <p>precision = 1，返回天</p>
     *                  <p>precision = 2，返回天和小时</p>
     *                  <p>precision = 3，返回天、小时和分钟</p>
     *                  <p>precision = 4，返回天、小时、分钟和秒</p>
     *                  <p>precision &gt;= 5，返回天、小时、分钟、秒和毫秒</p>
     * @param pattern   时间格式
     * @return 合适型两个时间差
     */
    public static String getFitTimeSpan(String time0, String time1, int precision, String pattern) {
        return ConvertUtils.millis2FitTimeSpan(Math.abs(string2Millis(time0, pattern) - string2Millis(time1, pattern)), precision);
    }

    /**
     * 获取合适型两个时间差
     *
     * @param date0     Date类型时间0
     * @param date1     Date类型时间1
     * @param precision 精度
     *                  <p>precision = 0，返回null</p>
     *                  <p>precision = 1，返回天</p>
     *                  <p>precision = 2，返回天和小时</p>
     *                  <p>precision = 3，返回天、小时和分钟</p>
     *                  <p>precision = 4，返回天、小时、分钟和秒</p>
     *                  <p>precision &gt;= 5，返回天、小时、分钟、秒和毫秒</p>
     * @return 合适型两个时间差
     */
    public static String getFitTimeSpan(Date date0, Date date1, int precision) {
        return ConvertUtils.millis2FitTimeSpan(Math.abs(date2Millis(date0) - date2Millis(date1)), precision);
    }

    /**
     * 获取合适型两个时间差
     *
     * @param millis0   毫秒时间戳1
     * @param millis1   毫秒时间戳2
     * @param precision 精度
     *                  <p>precision = 0，返回null</p>
     *                  <p>precision = 1，返回天</p>
     *                  <p>precision = 2，返回天和小时</p>
     *                  <p>precision = 3，返回天、小时和分钟</p>
     *                  <p>precision = 4，返回天、小时、分钟和秒</p>
     *                  <p>precision &gt;= 5，返回天、小时、分钟、秒和毫秒</p>
     * @return 合适型两个时间差
     */
    public static String getFitTimeSpan(long millis0, long millis1, int precision) {
        return ConvertUtils.millis2FitTimeSpan(Math.abs(millis0 - millis1), precision);
    }

    /**
     * 获取当前毫秒时间戳
     *
     * @return 毫秒时间戳
     */
    public static long getParsentTimeMills() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间字符串
     *
     * @return 当前时间字符串
     */
    public static String getParsentTimeMillsString() {
        return millis2String(System.currentTimeMillis(), DEFAULT_PATTERN);
    }

    /**
     * 获取当前Date
     *
     * @return 当前Date
     */
    public static Date getParsentTimeDate() {
        return new Date();
    }


    /**
     * 获取与当前时间的差（单位：unit）
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @param unit 单位类型
     *             <ul>
     *             <li>{@link Constans.TimeUnit#MSEC}:毫秒</li>
     *             <li>{@link Constans.TimeUnit#SEC }:秒</li>
     *             <li>{@link Constans.TimeUnit#MIN }:分</li>
     *             <li>{@link Constans.TimeUnit#HOUR}:小时</li>
     *             <li>{@link Constans.TimeUnit#DAY }:天</li>
     *             </ul>
     * @return unit时间戳
     */
    public static long getTimeSpanByNow(String time, Constans.TimeUnit unit) {
        return getTimeSpan(getParsentTimeMillsString(), time, unit, DEFAULT_PATTERN);
    }

    /**
     * 获取与当前时间的差（单位：unit）
     * <p>time格式为pattern</p>
     *
     * @param time    时间字符串
     * @param unit    单位类型
     *                <ul>
     *                <li>{@link Constans.TimeUnit#MSEC}: 毫秒</li>
     *                <li>{@link Constans.TimeUnit#SEC }: 秒</li>
     *                <li>{@link Constans.TimeUnit#MIN }: 分</li>
     *                <li>{@link Constans.TimeUnit#HOUR}: 小时</li>
     *                <li>{@link Constans.TimeUnit#DAY }: 天</li>
     *                </ul>
     * @param pattern 时间格式
     * @return unit时间戳
     */
    public static long getTimeSpanByNow(String time, Constans.TimeUnit unit, String pattern) {
        return getTimeSpan(getParsentTimeMillsString(), time, unit, pattern);
    }

    /**
     * 获取与当前时间的差（单位：unit）
     *
     * @param date Date类型时间
     * @param unit 单位类型
     *             <ul>
     *             <li>{@link Constans.TimeUnit#MSEC}: 毫秒</li>
     *             <li>{@link Constans.TimeUnit#SEC }: 秒</li>
     *             <li>{@link Constans.TimeUnit#MIN }: 分</li>
     *             <li>{@link Constans.TimeUnit#HOUR}: 小时</li>
     *             <li>{@link Constans.TimeUnit#DAY }: 天</li>
     *             </ul>
     * @return unit时间戳
     */
    public static long getTimeSpanByNow(Date date, Constans.TimeUnit unit) {
        return getTimeSpan(getParsentTimeDate(), date, unit);
    }

    /**
     * 获取与当前时间的差（单位：unit）
     *
     * @param millis 毫秒时间戳
     * @param unit   单位类型
     *               <ul>
     *               <li>{@link Constans.TimeUnit#MSEC}: 毫秒</li>
     *               <li>{@link Constans.TimeUnit#SEC }: 秒</li>
     *               <li>{@link Constans.TimeUnit#MIN }: 分</li>
     *               <li>{@link Constans.TimeUnit#HOUR}: 小时</li>
     *               <li>{@link Constans.TimeUnit#DAY }: 天</li>
     *               </ul>
     * @return unit时间戳
     */
    public static long getTimeSpanByNow(long millis, Constans.TimeUnit unit) {
        return getTimeSpan(getParsentTimeMills(), millis, unit);
    }

    /**
     * 获取合适型与当前时间的差
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time      时间字符串
     * @param precision 精度
     *                  <ul>
     *                  <li>precision = 0，返回null</li>
     *                  <li>precision = 1，返回天</li>
     *                  <li>precision = 2，返回天和小时</li>
     *                  <li>precision = 3，返回天、小时和分钟</li>
     *                  <li>precision = 4，返回天、小时、分钟和秒</li>
     *                  <li>precision &gt;= 5，返回天、小时、分钟、秒和毫秒</li>
     *                  </ul>
     * @return 合适型与当前时间的差
     */
    public static String getFitTimeSpanByNow(String time, int precision) {
        return getFitTimeSpan(getParsentTimeMillsString(), time, precision, DEFAULT_PATTERN);
    }

    /**
     * 获取合适型与当前时间的差
     * <p>time格式为pattern</p>
     *
     * @param time      时间字符串
     * @param precision 精度
     * @param pattern   时间格式
     *                  <ul>
     *                  <li>precision = 0，返回null</li>
     *                  <li>precision = 1，返回天</li>
     *                  <li>precision = 2，返回天和小时</li>
     *                  <li>precision = 3，返回天、小时和分钟</li>
     *                  <li>precision = 4，返回天、小时、分钟和秒</li>
     *                  <li>precision &gt;= 5，返回天、小时、分钟、秒和毫秒</li>
     *                  </ul>
     * @return 合适型与当前时间的差
     */
    public static String getFitTimeSpanByNow(String time, int precision, String pattern) {
        return getFitTimeSpan(getParsentTimeMillsString(), time, precision, pattern);
    }

    /**
     * 获取合适型与当前时间的差
     *
     * @param date      Date类型时间
     * @param precision 精度
     *                  <ul>
     *                  <li>precision = 0，返回null</li>
     *                  <li>precision = 1，返回天</li>
     *                  <li>precision = 2，返回天和小时</li>
     *                  <li>precision = 3，返回天、小时和分钟</li>
     *                  <li>precision = 4，返回天、小时、分钟和秒</li>
     *                  <li>precision &gt;= 5，返回天、小时、分钟、秒和毫秒</li>
     *                  </ul>
     * @return 合适型与当前时间的差
     */
    public static String getFitTimeSpanByNow(Date date, int precision) {
        return getFitTimeSpan(getParsentTimeDate(), date, precision);
    }

    /**
     * 获取合适型与当前时间的差
     *
     * @param millis    毫秒时间戳
     * @param precision 精度
     *                  <ul>
     *                  <li>precision = 0，返回null</li>
     *                  <li>precision = 1，返回天</li>
     *                  <li>precision = 2，返回天和小时</li>
     *                  <li>precision = 3，返回天、小时和分钟</li>
     *                  <li>precision = 4，返回天、小时、分钟和秒</li>
     *                  <li>precision &gt;= 5，返回天、小时、分钟、秒和毫秒</li>
     *                  </ul>
     * @return 合适型与当前时间的差
     */
    public static String getFitTimeSpanByNow(long millis, int precision) {
        return getFitTimeSpan(getParsentTimeMills(), millis, precision);
    }


    /**
     * 判断是否同一天
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isSameDay(String time) {
        return isSameDay(string2Millis(time, DEFAULT_PATTERN));
    }

    /**
     * 判断是否同一天
     * <p>time格式为pattern</p>
     *
     * @param time    时间字符串
     * @param pattern 时间格式
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isSameDay(String time, String pattern) {
        return isSameDay(string2Millis(time, pattern));
    }

    /**
     * 判断是否同一天
     *
     * @param date Date类型时间
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isSameDay(Date date) {
        return isSameDay(date.getTime());
    }

    /**
     * 判断是否同一天
     *
     * @param millis 毫秒时间戳
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isSameDay(long millis) {
        long wee = (System.currentTimeMillis() / Constans.DAY) * Constans.DAY;
        return millis >= wee && millis < wee + Constans.DAY;
    }

    /**
     * 判断是否闰年
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return {@code true}: 闰年<br>{@code false}: 平年
     */
    public static boolean isLeapYear(String time) {
        return isLeapYear(string2Date(time, DEFAULT_PATTERN));
    }

    /**
     * 判断是否闰年
     * <p>time格式为pattern</p>
     *
     * @param time    时间字符串
     * @param pattern 时间格式
     * @return {@code true}: 闰年<br>{@code false}: 平年
     */
    public static boolean isLeapYear(String time, String pattern) {
        return isLeapYear(string2Date(time, pattern));
    }

    /**
     * 判断是否闰年
     *
     * @param date Date类型时间
     * @return {@code true}: 闰年<br>{@code false}: 平年
     */
    public static boolean isLeapYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        return isLeapYear(year);
    }

    /**
     * 判断是否闰年
     *
     * @param millis 毫秒时间戳
     * @return {@code true}: 闰年<br>{@code false}: 平年
     */
    public static boolean isLeapYear(long millis) {
        return isLeapYear(millis2Date(millis));
    }

    /**
     * 判断是否闰年
     *
     * @param year 年份
     * @return {@code true}: 闰年<br>{@code false}: 平年
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * 获取星期
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 星期
     */
    public static String getWeek(String time) {
        return getWeek(string2Date(time, DEFAULT_PATTERN));
    }

    /**
     * 获取星期
     * <p>time格式为pattern</p>
     *
     * @param time    时间字符串
     * @param pattern 时间格式
     * @return 星期
     */
    public static String getWeek(String time, String pattern) {
        return getWeek(string2Date(time, pattern));
    }

    /**
     * 获取星期
     *
     * @param date Date类型时间
     * @return 星期
     */
    public static String getWeek(Date date) {
        return new SimpleDateFormat("EEEE", Locale.getDefault()).format(date);
    }

    /**
     * 获取星期
     *
     * @param millis 毫秒时间戳
     * @return 星期
     */
    public static String getWeek(long millis) {
        return getWeek(new Date(millis));
    }

    /**
     * 获取星期
     * <p>注意：周日的Index才是1，周六为7</p>
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 1...5
     */
    public static int getWeekIndex(String time) {
        return getWeekIndex(string2Date(time, DEFAULT_PATTERN));
    }

    /**
     * 获取星期
     * <p>注意：周日的Index才是1，周六为7</p>
     * <p>time格式为pattern</p>
     *
     * @param time    时间字符串
     * @param pattern 时间格式
     * @return 1...7
     */
    public static int getWeekIndex(String time, String pattern) {
        return getWeekIndex(string2Date(time, pattern));
    }

    /**
     * 获取星期
     * <p>注意：周日的Index才是1，周六为7</p>
     *
     * @param date Date类型时间
     * @return 1...7
     */
    public static int getWeekIndex(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取星期
     * <p>注意：周日的Index才是1，周六为7</p>
     *
     * @param millis 毫秒时间戳
     * @return 1...7
     */
    public static int getWeekIndex(long millis) {
        return getWeekIndex(millis2Date(millis));
    }

    /**
     * 获取月份中的第几周
     * <p>注意：国外周日才是新的一周的开始</p>
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 1...5
     */
    public static int getWeekOfMonth(String time) {
        return getWeekOfMonth(string2Date(time, DEFAULT_PATTERN));
    }

    /**
     * 获取月份中的第几周
     * <p>注意：国外周日才是新的一周的开始</p>
     * <p>time格式为pattern</p>
     *
     * @param time    时间字符串
     * @param pattern 时间格式
     * @return 1...5
     */
    public static int getWeekOfMonth(String time, String pattern) {
        return getWeekOfMonth(string2Date(time, pattern));
    }

    /**
     * 获取月份中的第几周
     * <p>注意：国外周日才是新的一周的开始</p>
     *
     * @param date Date类型时间
     * @return 1...5
     */
    public static int getWeekOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 获取月份中的第几周
     * <p>注意：国外周日才是新的一周的开始</p>
     *
     * @param millis 毫秒时间戳
     * @return 1...5
     */
    public static int getWeekOfMonth(long millis) {
        return getWeekOfMonth(millis2Date(millis));
    }

    /**
     * 获取年份中的第几周
     * <p>注意：国外周日才是新的一周的开始</p>
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 1...54
     */
    public static int getWeekOfYear(String time) {
        return getWeekOfYear(string2Date(time, DEFAULT_PATTERN));
    }

    /**
     * 获取年份中的第几周
     * <p>注意：国外周日才是新的一周的开始</p>
     * <p>time格式为pattern</p>
     *
     * @param time    时间字符串
     * @param pattern 时间格式
     * @return 1...54
     */
    public static int getWeekOfYear(String time, String pattern) {
        return getWeekOfYear(string2Date(time, pattern));
    }

    /**
     * 获取年份中的第几周
     * <p>注意：国外周日才是新的一周的开始</p>
     *
     * @param date Date类型时间
     * @return 1...54
     */
    public static int getWeekOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取年份中的第几周
     * <p>注意：国外周日才是新的一周的开始</p>
     *
     * @param millis 毫秒时间戳
     * @return 1...54
     */
    public static int getWeekOfYear(long millis) {
        return getWeekOfYear(millis2Date(millis));
    }

    private static final String[] CHINESE_ZODIAC = {"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊"};

    /**
     * 获取生肖
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 生肖
     */
    public static String getChineseZodiac(String time) {
        return getChineseZodiac(string2Date(time, DEFAULT_PATTERN));
    }

    /**
     * 获取生肖
     * <p>time格式为pattern</p>
     *
     * @param time    时间字符串
     * @param pattern 时间格式
     * @return 生肖
     */
    public static String getChineseZodiac(String time, String pattern) {
        return getChineseZodiac(string2Date(time, pattern));
    }

    /**
     * 获取生肖
     *
     * @param date Date类型时间
     * @return 生肖
     */
    public static String getChineseZodiac(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return CHINESE_ZODIAC[cal.get(Calendar.YEAR) % 12];
    }

    /**
     * 获取生肖
     *
     * @param millis 毫秒时间戳
     * @return 生肖
     */
    public static String getChineseZodiac(long millis) {
        return getChineseZodiac(millis2Date(millis));
    }

    /**
     * 获取生肖
     *
     * @param year 年
     * @return 生肖
     */
    public static String getChineseZodiac(int year) {
        return CHINESE_ZODIAC[year % 12];
    }

    /**
     * 判断是否为今天(效率比较高)
     *
     * @param date 传入的 时间
     * @return true今天 false不是
     */
    public static boolean IsToday(Date date) {
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);
            return diffDay == 0;
        }
        return false;
    }

    /**
     * 判断是否大于当前时间(效率比较高)
     *
     * @param date 传入的 时间
     * @return true:大于当前时间 false:不大于当前时间
     */
    public static boolean IsOverData(Date date) {
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);
            int diffHour = cal.get(Calendar.HOUR_OF_DAY)
                    - pre.get(Calendar.HOUR_OF_DAY);
            if (diffDay >= 0) { //日相差>=0
                if (diffDay == 0) {
                    return diffHour >= 0;
                }
                return true;
            }
        }
        return false;
    }

}
