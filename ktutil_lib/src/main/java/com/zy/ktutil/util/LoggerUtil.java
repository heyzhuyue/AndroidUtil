package com.zy.ktutil.util;

import com.orhanobut.logger.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * Created by win7 on 2017/1/6.
 * Log日志统一管理工具类
 */

public class LoggerUtil {

    private LoggerUtil() {
        /**
         * cannot be instantiated
         */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 初始化Log工具类
     *
     * @param debug 是否为Debug模式
     */
    public static void init(boolean debug, String tag) {
        isDebug = debug;
    }

    /**
     * 是否需要打印bug，可以在application的onCreate函数里面初始化
     * 如果为Debug模式,isDebug为true
     */
    public static boolean isDebug = true;
    private static String TAG = "AndroidUtilCode";

    /**
     * Log输出Info内容(Info)
     *
     * @param msg msg
     */
    public static void i(String msg) {
        if (isDebug & BuildConfig.DEBUG) {
            Logger.i(TAG, msg);
        }
    }

    /**
     * Log输出Debug内容(Debug)
     * <p>
     * 建议测试Bug使用此类Log
     *
     * @param msg msg
     */
    public static void d(String msg) {
        if (isDebug && BuildConfig.DEBUG)
            Logger.d(TAG, msg);
    }

    /**
     * Log输出错误信息(Error)
     *
     * @param msg msg
     */
    public static void e(String msg) {
        if (isDebug && BuildConfig.DEBUG)
            Logger.e(TAG, msg);
    }

    /**
     * Log输出啰嗦信息(verbose)
     *
     * @param msg msg
     */
    public static void v(String msg) {
        if (isDebug && BuildConfig.DEBUG)
            Logger.v(TAG, msg);
    }

    /**
     * Log输出警告信息(Warn)
     * <p>
     * 建议抛出异常使用此类Log
     *
     * @param msg msg
     */
    public static void w(String msg) {
        if (isDebug && BuildConfig.DEBUG) {
            Logger.w(TAG, msg);
        }
    }

    /**
     * 自定义TAG日志（info）
     *
     * @param tag TAG
     * @param msg msg
     */
    public static void i(String tag, String msg) {
        if (isDebug && BuildConfig.DEBUG)
            Logger.i(tag, msg);
    }

    /**
     * 自定义TAG日志（Debug）
     *
     * @param tag TAG
     * @param msg msg
     */
    public static void d(String tag, String msg) {
        if (isDebug && BuildConfig.DEBUG)
            Logger.i(tag, msg);
    }

    /**
     * 自定义TAG日志(Error)
     *
     * @param tag TAG
     * @param msg msg
     */
    public static void e(String tag, String msg) {
        if (isDebug && BuildConfig.DEBUG)
            Logger.i(tag, msg);
    }

    /**
     * 自定义TAG日志(Verbose)
     *
     * @param tag TAG
     * @param msg msg
     */
    public static void v(String tag, String msg) {
        if (isDebug && BuildConfig.DEBUG)
            Logger.i(tag, msg);
    }

    /**
     * 自定义TAG日志(Warn)
     *
     * @param tag TAG
     * @param msg msg
     */
    public static void w(String tag, String msg) {
        if (isDebug && BuildConfig.DEBUG)
            Logger.w(tag, msg);
    }

    /**
     * 打印对象Log info
     *
     * @param object Object
     * @param msg    信息
     */
    public static void i(Object object, String msg) {
        if (isDebug && BuildConfig.DEBUG) {
            String tag = object.getClass().getSimpleName();
            Logger.i(tag, msg);
        }
    }

    /**
     * 打印对象Log debug
     *
     * @param object Object
     * @param msg    信息
     */
    public static void d(Object object, String msg) {
        if (isDebug && BuildConfig.DEBUG) {
            String tag = object.getClass().getSimpleName();
            Logger.d(tag, msg);
        }
    }

    /**
     * 打印对象Log error
     *
     * @param object Object
     * @param msg    信息
     */
    public static void e(Object object, String msg) {
        if (isDebug && BuildConfig.DEBUG) {
            String tag = object.getClass().getSimpleName();
            Logger.e(tag, msg);
        }
    }

    /**
     * 打印对象Log warn
     *
     * @param object Object
     * @param msg    信息
     */
    public static void w(Object object, String msg) {
        if (isDebug && BuildConfig.DEBUG) {
            String tag = object.getClass().getSimpleName();
            Logger.w(tag, msg);
        }
    }
}
