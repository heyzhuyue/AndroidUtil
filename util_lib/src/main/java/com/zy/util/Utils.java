package com.zy.util;

import android.content.Context;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;


/**
 * Utils初始化相关
 */
public class Utils {

    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
        Logger.init().setLogLevel(LogLevel.FULL);
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null)
            return context;
        throw new NullPointerException("u should initJumpWhenMore first");
    }
}