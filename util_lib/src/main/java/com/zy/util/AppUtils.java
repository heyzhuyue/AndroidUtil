package com.zy.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhyue on 2017/2/7.
 * 检测App相关信息工具类
 */

public class AppUtils {

    public AppUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断当前界面是否是桌面
     *
     * @param context 上下文
     */
    public static boolean isHome(Context context) {
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> rti = mActivityManager.getRunningTasks(1);
        return getHomes(context).contains(rti.get(0).topActivity.getPackageName());
    }

    /**
     * 获取App包名
     *
     * @param context 上下文
     * @return
     */
    public static String getAppPackName(Context context) {
        return context.getPackageName();
    }

    /**
     * 获取应用版本名
     *
     * @param context 上下文
     * @return 版本名
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取应用版本号
     *
     * @param context 上下文
     * @return 版本号
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return packageManager.getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context 上下文
     * @param file    文件
     * @return
     */
    public static boolean isAvailable(Context context, File file) {
        return isAvailable(context, getPackageName(context, file.getAbsolutePath()));
    }

    /**
     * 获得属于桌面的应用的应用包名称
     *
     * @param context Context
     * @return 返回包含所有包名的字符串列表
     */
    private static List<String> getHomes(Context context) {
        List<String> names = new ArrayList<String>();
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        for (ResolveInfo ri : resolveInfo) {
            names.add(ri.activityInfo.packageName);
        }
        return names;
    }

    /**
     * 检查手机上是否安装了指定的软件
     */
    private static boolean isAvailable(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        List<String> packageNames = new ArrayList<>();
        if (packageInfoList != null) {
            for (int i = 0; i < packageInfoList.size(); i++) {
                String packName = packageInfoList.get(i).packageName;
                packageNames.add(packName);
            }
        }
        return packageNames.contains(packageName);// 判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
    }

    /**
     * 根据文件路径获取包名
     */
    private static String getPackageName(Context context, String filePath) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo info = packageManager.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            ApplicationInfo appInfo = info.applicationInfo;
            return appInfo.packageName;  //得到安装包名称
        }
        return null;
    }
}
