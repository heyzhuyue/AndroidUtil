package com.zy.util.app;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhuyue on 2017/9/15.
 *
 * @author zhuyue
 * @description Activity管理类
 */

public class ActivityManager {

    private List<Activity> activityList = new LinkedList<>();
    private static ActivityManager instance;

    /**
     * 单例模式中获取唯一的MyApplication实例
     *
     * @return
     */
    public static ActivityManager getInstance() {
        if (null == instance) {
            instance = new ActivityManager();
        }
        return instance;
    }

    /**
     * 添加Activity到容器中
     *
     * @param activity 添加Activity
     */
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    /**
     * 关闭所有Activity
     */
    public void finishAllActivity() {
        for (Activity activity : activityList) {
            activity.finish();
        }
    }

    /**
     * 退出应用
     */
    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }

    /**
     * 删除Activity于容器中
     *
     * @param activity 删除Activity
     */
    public void delActivity(Activity activity) {
        activityList.remove(activity);
    }
}
