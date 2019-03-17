package com.zy.util.camera;

import android.os.Build;

/**
 * <pre>
 *     author: zhuyue
 *     time  : 2019/2/18
 *     desc  : 判断手机系统化工具类
 * </pre>
 */
class PhoneInfo {

    public static boolean isMIUI() {
        String manufacturer = Build.MANUFACTURER;
        //这个字符串可以自己定义,例如判断华为就填写huawei,魅族就填写meizu
        if ("xiaomi".equalsIgnoreCase(manufacturer)) {
            return true;
        }
        return false;
    }
}
