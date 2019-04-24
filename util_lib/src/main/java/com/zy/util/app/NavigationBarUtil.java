package com.zy.util.app;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import com.zy.util.PhoneInfo;

/**
 * <pre>
 *     author: zhuyue
 *     time  : 2019/4/17
 *     desc  :虚拟按键工具类
 * </pre>
 */
public class NavigationBarUtil {

    private static final String XIAOMI_FULLSCREEN_GESTURE = "force_fsg_nav_bar";

    /**
     * 判断虚拟导航栏是否显示
     *
     * @param context 上下文对象
     * @return true(显示虚拟导航栏)，false(不显示或不支持虚拟导航栏)
     */
    public static boolean checkNavigationBarShow(@NonNull Activity context) {
        boolean show = true;
        if (PhoneInfo.isMIUI()) {
            show = isMIUINavigationGestureEnabled(context);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                Display display = context.getWindow().getWindowManager().getDefaultDisplay();
                Point point = new Point();
                display.getRealSize(point);
                View decorView = context.getWindow().getDecorView();
                Configuration conf = context.getResources().getConfiguration();
                if (Configuration.ORIENTATION_LANDSCAPE == conf.orientation) {
                    View contentView = decorView.findViewById(android.R.id.content);
                    show = (point.x != contentView.getWidth());
                } else {
                    Rect rect = new Rect();
                    decorView.getWindowVisibleDisplayFrame(rect);
                    show = (rect.bottom != point.y);
                }
            }
        }
        return show;
    }

    /**
     * 获取虚拟按键的高度
     *
     * @param context 上下文
     * @return
     */
    public static int getNavigationBarHeight(Activity context) {
        int result = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取虚拟按钮ActionBar的高度
     *
     * @param activity activity
     * @return ActionBar高度
     */
    private static int getActionBarHeight(Activity activity) {
        TypedValue tv = new TypedValue();
        if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, activity.getResources().getDisplayMetrics());
        }
        return 0;
    }

    /**
     * 判断小米是否开启手势
     *
     * @param context Activity
     * @return 是否开启手势
     */
    public static boolean isMIUINavigationGestureEnabled(Activity context) {
        int val = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val = Settings.Global.getInt(context.getContentResolver(), XIAOMI_FULLSCREEN_GESTURE, 0);
        }
        return val != 0;
    }
}
