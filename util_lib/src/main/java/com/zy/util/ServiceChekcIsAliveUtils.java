package com.zy.util;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by zhyue on 2017/1/13.
 */

public class ServiceChekcIsAliveUtils {

    /**
     * 判断某个服务是否正在运行的方法
     *
     * @param context     上下文
     * @param serviceName 是包名+服务的类名（例如：net.loonggg.testbackstage.TestService）
     * @return true代表正在运行，false代表服务没有正在运行
     */
    public static boolean isServiceAlive(Context context, String serviceName) {
        boolean isWork = false;
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> myList = activityManager.getRunningServices(40);
        if (myList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < myList.size(); i++) {
            String mName = myList.get(i).service.getClassName();
            if (mName.equals(serviceName)) {
                isWork = true;
                break;
            }
        }
        return isWork;
    }
}
