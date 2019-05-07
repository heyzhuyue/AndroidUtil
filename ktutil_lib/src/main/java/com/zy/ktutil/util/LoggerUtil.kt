package com.zy.ktutil.util

import com.orhanobut.logger.BuildConfig
import com.orhanobut.logger.Logger

/**
 * Created by win7 on 2017/1/6.
 * Log日志统一管理工具类
 */

class LoggerUtil {

    init {
        /**
         * cannot be instantiated
         */
        throw UnsupportedOperationException("cannot be instantiated")
    }

    companion object {

        /**
         * 初始化Log工具类
         *
         * @param debug 是否为Debug模式
         */
        fun init(debug: Boolean, tag: String) {
            isDebug = debug
        }

        /**
         * 是否需要打印bug，可以在application的onCreate函数里面初始化
         * 如果为Debug模式,isDebug为true
         */
        var isDebug = true
        private val TAG = "AndroidUtilCode"

        /**
         * Log输出Info内容(Info)
         *
         * @param msg msg
         */
        fun i(msg: String) {
            if (isDebug and BuildConfig.DEBUG) {
                Logger.i(TAG, msg)
            }
        }

        /**
         * Log输出Debug内容(Debug)
         *
         *
         * 建议测试Bug使用此类Log
         *
         * @param msg msg
         */
        fun d(msg: String) {
            if (isDebug && BuildConfig.DEBUG)
                Logger.d(TAG, msg)
        }

        /**
         * Log输出错误信息(Error)
         *
         * @param msg msg
         */
        fun e(msg: String) {
            if (isDebug && BuildConfig.DEBUG)
                Logger.e(TAG, msg)
        }

        /**
         * Log输出啰嗦信息(verbose)
         *
         * @param msg msg
         */
        fun v(msg: String) {
            if (isDebug && BuildConfig.DEBUG)
                Logger.v(TAG, msg)
        }

        /**
         * Log输出警告信息(Warn)
         *
         *
         * 建议抛出异常使用此类Log
         *
         * @param msg msg
         */
        fun w(msg: String) {
            if (isDebug && BuildConfig.DEBUG) {
                Logger.w(TAG, msg)
            }
        }

        /**
         * 自定义TAG日志（info）
         *
         * @param tag TAG
         * @param msg msg
         */
        fun i(tag: String, msg: String) {
            if (isDebug && BuildConfig.DEBUG)
                Logger.i(tag, msg)
        }

        /**
         * 自定义TAG日志（Debug）
         *
         * @param tag TAG
         * @param msg msg
         */
        fun d(tag: String, msg: String) {
            if (isDebug && BuildConfig.DEBUG)
                Logger.i(tag, msg)
        }

        /**
         * 自定义TAG日志(Error)
         *
         * @param tag TAG
         * @param msg msg
         */
        fun e(tag: String, msg: String) {
            if (isDebug && BuildConfig.DEBUG)
                Logger.i(tag, msg)
        }

        /**
         * 自定义TAG日志(Verbose)
         *
         * @param tag TAG
         * @param msg msg
         */
        fun v(tag: String, msg: String) {
            if (isDebug && BuildConfig.DEBUG)
                Logger.i(tag, msg)
        }

        /**
         * 自定义TAG日志(Warn)
         *
         * @param tag TAG
         * @param msg msg
         */
        fun w(tag: String, msg: String) {
            if (isDebug && BuildConfig.DEBUG)
                Logger.w(tag, msg)
        }

        /**
         * 打印对象Log info
         *
         * @param object Object
         * @param msg    信息
         */
        fun i(`object`: Any, msg: String) {
            if (isDebug && BuildConfig.DEBUG) {
                val tag = `object`.javaClass.simpleName
                Logger.i(tag, msg)
            }
        }

        /**
         * 打印对象Log debug
         *
         * @param object Object
         * @param msg    信息
         */
        fun d(`object`: Any, msg: String) {
            if (isDebug && BuildConfig.DEBUG) {
                val tag = `object`.javaClass.simpleName
                Logger.d(tag, msg)
            }
        }

        /**
         * 打印对象Log error
         *
         * @param object Object
         * @param msg    信息
         */
        fun e(`object`: Any, msg: String) {
            if (isDebug && BuildConfig.DEBUG) {
                val tag = `object`.javaClass.simpleName
                Logger.e(tag, msg)
            }
        }

        /**
         * 打印对象Log warn
         *
         * @param object Object
         * @param msg    信息
         */
        fun w(`object`: Any, msg: String) {
            if (isDebug && BuildConfig.DEBUG) {
                val tag = `object`.javaClass.simpleName
                Logger.w(tag, msg)
            }
        }
    }
}
