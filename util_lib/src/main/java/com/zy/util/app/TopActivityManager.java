package com.zy.util.app;

import android.app.Activity;

import java.lang.ref.WeakReference;

/**
 * Created by zhuyue on 2017/9/15.
 * 获取App当前显示Activity
 */

public class TopActivityManager {

    private static TopActivityManager sInstance;
    /**
     * 采用弱引用持有 Activity ，避免造成 内存泄露
     */
    private WeakReference<Activity> sCurrentActivityWeakRef;

    public static TopActivityManager getInstance() {
        if(sInstance==null){
            sInstance=new TopActivityManager();
        }
        return sInstance;
    }

    public Activity getCurrentActivity() {
        Activity currentActivity = null;
        if (sCurrentActivityWeakRef != null) {
            currentActivity = sCurrentActivityWeakRef.get();
        }
        return currentActivity;
    }

    public void setCurrentActivity(Activity activity) {
        sCurrentActivityWeakRef = new WeakReference<>(activity);
    }

}
