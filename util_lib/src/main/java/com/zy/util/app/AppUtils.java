package com.zy.util.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

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
     * 获取App包名
     *
     * @param context 上下文
     * @return 包名
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
     * @return 是否已安装
     */
    public static boolean isAvailable(Context context, File file) {
        return isAvailable(context, getPackageName(context, file.getAbsolutePath()));
    }

    /**
     * 检查手机上是否安装了指定的软件
     *
     * @param context     上下文
     * @param packageName 包名
     * @return 是否已安装
     */
    public static boolean isAvailable(Context context, String packageName) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        List<String> packageNames = new ArrayList<>();
        if (packageInfoList != null) {
            for (int i = 0; i < packageInfoList.size(); i++) {
                String packName = packageInfoList.get(i).packageName;
                packageNames.add(packName);
            }
        }
        return packageNames.contains(packageName);
    }

    /**
     * 根据文件路径获取包名
     *
     * @param context  上下文
     * @param filePath 文件路径
     * @return 包名
     */
    private static String getPackageName(Context context, String filePath) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo info = packageManager.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);
        if (info != null) {
            ApplicationInfo appInfo = info.applicationInfo;
            return appInfo.packageName;
        }
        return null;
    }
}
