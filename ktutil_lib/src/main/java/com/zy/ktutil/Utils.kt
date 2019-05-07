package com.zy.ktutil

import android.content.Context
import com.orhanobut.logger.LogLevel
import com.orhanobut.logger.Logger


/**
 * Utils初始化相关
 */
class Utils private constructor() {

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    companion object {

        private var context: Context? = null

        /**
         * 初始化工具类
         *
         * @param context 上下文
         */
        fun init(context: Context) {
            Utils.context = context.applicationContext
            Logger.init().setLogLevel(LogLevel.FULL)
        }

        /**
         * 获取ApplicationContext
         *
         * @return ApplicationContext
         */
        fun getContext(): Context {
            if (context != null) {
                return context!!
            }
            throw NullPointerException("u should initJumpWhenMore first")
        }
    }
}