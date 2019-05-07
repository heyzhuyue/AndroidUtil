package com.zy.util.calculate;


import android.content.Context;

/**
 * Created by sqw on 2017/8/23.
 * px与dp,sp计算工具类
 */

public class DensityUtil {


    public DensityUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param context 上下文
     * @param dpValue dp值
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param context 上下文
     * @param pxValue px值
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 根据手机分辨率从sp单位转换为px单位
     *
     * @param context 上下文
     * @param spValue sp值
     */
    public static int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (spValue * scale + 0.5f);
    }


    /**
     * 根据手机分辨率从sp单位转换为px单位
     *
     * @param context 上下文
     * @param pxValue px值
     */
    public static int px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
