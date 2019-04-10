package com.zy.util.keybord;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.TextView;
import org.jetbrains.annotations.NotNull;

/**
 * <pre>
 *     author: zhuyue
 *     time  : 2019/4/10
 *     desc  :输入法冲突帮助类
 * </pre>
 */
public class KeyBoardHelper {

    /**
     * Activity
     */
    private Activity activity;
    /**
     * 键盘高度
     */
    private int lastKeyBoardHeight;
    /**
     * 最后显示View与输入法之间间距
     */
    private int offset;

    private KeyBoardHelper(Activity activity) {
        this.activity = activity;
    }

    /**
     * 绑定Activity
     *
     * @param activity Activity
     * @return KeyBoardHelper
     */
    public static KeyBoardHelper attachToActivity(@NotNull Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return new KeyBoardHelper(activity);
    }

    /**
     * 绑定冲入布局
     *
     * @param viewGroup       处理输入法冲突ViewGroup
     * @param lastVisibleView 屏幕底部最后显示的View
     * @return KeyBoardHelper
     */
    public KeyBoardHelper bind(ViewGroup viewGroup, View lastVisibleView) {
        if (viewGroup instanceof RecyclerView || viewGroup instanceof ScrollView) {
            bindViewGroup(viewGroup);
        } else {
            bindLayout(viewGroup, lastVisibleView);
        }
        return this;
    }

    /**
     * 绑定处理冲突ViewGroup
     *
     * @param viewGroup 处理冲突ViewGroup
     * @return
     */
    public KeyBoardHelper bind(ViewGroup viewGroup) {
        bind(viewGroup, activity.getCurrentFocus());
        return this;
    }

    /**
     * 设置最后显示View与输入法之间间距
     *
     * @param offset 距离
     * @return
     */
    public KeyBoardHelper offset(int offset) {
        this.offset = offset;
        return this;
    }

    private void bindLayout(@NotNull final ViewGroup viewGroup, final View view) {
        viewGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        View lastVisibleView = view;
                        if (lastVisibleView == null) {
                            lastVisibleView = activity.getCurrentFocus();
                        }
                        //获得屏幕高度
                        int screenHeight = viewGroup.getRootView().getHeight();
                        //r.bottom - r.top计算出输入法弹起后viewGroup的高度，屏幕高度-viewGroup高度即为键盘高度
                        Rect r = new Rect();
                        viewGroup.getWindowVisibleDisplayFrame(r);
                        int keyboardHeight = screenHeight - (r.bottom - r.top);
                        //当设置layout_keyboard设置完padding以后会重绘布局再次执行onGlobalLayout()
                        //所以判断如果键盘高度未改变就不执行下去
                        if (keyboardHeight == lastKeyBoardHeight) {
                            return;
                        }
                        lastKeyBoardHeight = keyboardHeight;
                        if (keyboardHeight < 300) {
                            //键盘关闭后恢复布局
                            viewGroup.setPadding(0, 0, 0, 0);
                        } else {
                            //计算出键盘最顶端在布局中的位置
                            int keyboardTop = screenHeight - keyboardHeight;
                            int[] location = new int[2];
                            lastVisibleView.getLocationOnScreen(location);
                            //获取登录按钮底部在屏幕中的位置
                            int lastVisibleViewBottom = location[1] + lastVisibleView.getHeight();
                            //登录按钮底部在布局中的位置-输入法顶部的位置=需要将布局弹起多少高度
                            int reSizeLayoutHeight = lastVisibleViewBottom - keyboardTop;
                            //需要多弹起一个StatusBar的高度
                            reSizeLayoutHeight -= getStatusBarHeight();
                            //设置登录按钮与输入法之间间距
                            reSizeLayoutHeight += offset;
                            if (reSizeLayoutHeight > 0) {
                                viewGroup.setPadding(0, -reSizeLayoutHeight, 0, 0);
                            }
                        }
                    }
                }, 50);
            }
        });
    }

    private void bindViewGroup(@NotNull final ViewGroup viewGroup) {
        viewGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {   //获得屏幕高度
                        int screenHeight = viewGroup.getRootView().getHeight();
                        //r.bottom - r.top计算出输入法弹起后viewGroup的高度，屏幕高度-viewGroup高度即为键盘高度
                        Rect r = new Rect();
                        viewGroup.getWindowVisibleDisplayFrame(r);
                        int keyboardHeight = screenHeight - (r.bottom - r.top);
                        //当设置layout_keyboard设置完padding以后会重绘布局再次执行onGlobalLayout()
                        //所以判断如果键盘高度未改变就不执行下去
                        if (keyboardHeight == lastKeyBoardHeight) {
                            return;
                        }
                        lastKeyBoardHeight = keyboardHeight;
                        View view = activity.getWindow().getCurrentFocus();
                        if (keyboardHeight > 300 && null != view) {
                            if (view instanceof TextView) {
                                //计算出键盘最顶端在布局中的位置
                                int keyboardTop = screenHeight - keyboardHeight;
                                int[] location = new int[2];
                                view.getLocationOnScreen(location);
                                //获取登录按钮底部在屏幕中的位置
                                int viewBottom = location[1] + view.getHeight();
                                //比较输入框与键盘的位置关系，如果输入框在键盘之上的位置就不做处理
                                if (viewBottom <= keyboardTop)
                                    return;
                                //需要滚动的距离即为输入框底部到键盘的距离
                                int reSizeLayoutHeight = viewBottom - keyboardTop;
                                reSizeLayoutHeight -= getStatusBarHeight();
                                reSizeLayoutHeight += offset;
                                if (viewGroup instanceof ScrollView) {
                                    ((ScrollView) viewGroup).smoothScrollBy(0, reSizeLayoutHeight);
                                } else if (viewGroup instanceof RecyclerView) {
                                    ((RecyclerView) viewGroup).smoothScrollBy(0, reSizeLayoutHeight);
                                }
                            }
                        }
                    }
                }, 50);
            }
        });
    }

    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度
     */
    private int getStatusBarHeight() {
        int result = 0;
        int resId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            result = activity.getResources().getDimensionPixelOffset(resId);
        }
        return result;
    }
}
