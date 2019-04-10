package com.zy.ktutil.keyboard

import android.app.Activity
import android.graphics.Rect
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.WindowManager
import android.widget.ScrollView
import android.widget.TextView

/**
 * <pre>
 * author: zhuyue
 * time  : 2019/4/10
 * desc  :输入法冲突帮助类
</pre>
 */
class KeyBoardHelper(val activity: Activity) {

    /**
     * 键盘高度
     */
    private var lastKeyBoardHeight: Int = 0
    /**
     * 最后显示View与输入法之间间距
     */
    private var offset: Int = 0

    companion object {

        /**
         * 绑定Activity
         *
         * @param activity Activity
         * @return KeyBoardHelper
         */
        fun attachToActivity(activity: Activity): KeyBoardHelper {
            activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            return KeyBoardHelper(activity)
        }
    }

    /**
     * 绑定冲入布局
     *
     * @param viewGroup       处理输入法冲突ViewGroup
     * @param lastVisibleView 屏幕底部最后显示的View
     * @return KeyBoardHelper
     */
    fun bind(viewGroup: ViewGroup, lastVisibleView: View?): KeyBoardHelper {
        if (viewGroup is RecyclerView || viewGroup is ScrollView) {
            bindViewGroup(viewGroup)
        } else {
            bindLayout(viewGroup, lastVisibleView)
        }
        return this
    }

    /**
     * 绑定处理冲突ViewGroup
     *
     * @param viewGroup 处理冲突ViewGroup
     * @return
     */
    fun bind(viewGroup: ViewGroup): KeyBoardHelper {
        bind(viewGroup, activity.currentFocus)
        return this
    }

    /**
     * 设置最后显示View与输入法之间间距
     *
     * @param offset 距离
     * @return
     */
    fun offset(offset: Int): KeyBoardHelper {
        this.offset = offset
        return this
    }

    private fun bindLayout(viewGroup: ViewGroup, view: View?) {
        viewGroup.viewTreeObserver.addOnGlobalLayoutListener {
            Handler().postDelayed(Runnable {
                var lastVisibleView = view
                if (lastVisibleView == null) {
                    lastVisibleView = activity.currentFocus
                }
                //获得屏幕高度
                val screenHeight = viewGroup.rootView.height
                //r.bottom - r.top计算出输入法弹起后viewGroup的高度，屏幕高度-viewGroup高度即为键盘高度
                val r = Rect()
                viewGroup.getWindowVisibleDisplayFrame(r)
                val keyboardHeight = screenHeight - (r.bottom - r.top)
                //当设置layout_keyboard设置完padding以后会重绘布局再次执行onGlobalLayout()
                //所以判断如果键盘高度未改变就不执行下去
                if (keyboardHeight == lastKeyBoardHeight) {
                    return@Runnable
                }
                lastKeyBoardHeight = keyboardHeight
                if (keyboardHeight < 300) {
                    //键盘关闭后恢复布局
                    viewGroup.setPadding(0, 0, 0, 0)
                } else {
                    //计算出键盘最顶端在布局中的位置
                    val keyboardTop = screenHeight - keyboardHeight
                    val location = IntArray(2)
                    lastVisibleView!!.getLocationOnScreen(location)
                    //获取登录按钮底部在屏幕中的位置
                    val lastVisibleViewBottom = location[1] + lastVisibleView.height
                    //登录按钮底部在布局中的位置-输入法顶部的位置=需要将布局弹起多少高度
                    var reSizeLayoutHeight = lastVisibleViewBottom - keyboardTop
                    //需要多弹起一个StatusBar的高度
                    reSizeLayoutHeight -= statusBarHeight
                    //设置登录按钮与输入法之间间距
                    reSizeLayoutHeight += offset
                    if (reSizeLayoutHeight > 0) {
                        viewGroup.setPadding(0, -reSizeLayoutHeight, 0, 0)
                    }
                }
            }, 50)
        }
    }

    private fun bindViewGroup(viewGroup: ViewGroup) {
        viewGroup.viewTreeObserver.addOnGlobalLayoutListener {
            Handler().postDelayed(Runnable {
                //获得屏幕高度
                val screenHeight = viewGroup.rootView.height
                //r.bottom - r.top计算出输入法弹起后viewGroup的高度，屏幕高度-viewGroup高度即为键盘高度
                val r = Rect()
                viewGroup.getWindowVisibleDisplayFrame(r)
                val keyboardHeight = screenHeight - (r.bottom - r.top)
                //当设置layout_keyboard设置完padding以后会重绘布局再次执行onGlobalLayout()
                //所以判断如果键盘高度未改变就不执行下去
                if (keyboardHeight == lastKeyBoardHeight) {
                    return@Runnable
                }
                lastKeyBoardHeight = keyboardHeight
                val view = activity.window.currentFocus
                if (keyboardHeight > 300 && null != view) {
                    if (view is TextView) {
                        //计算出键盘最顶端在布局中的位置
                        val keyboardTop = screenHeight - keyboardHeight
                        val location = IntArray(2)
                        view.getLocationOnScreen(location)
                        //获取登录按钮底部在屏幕中的位置
                        val viewBottom = location[1] + view.height
                        //比较输入框与键盘的位置关系，如果输入框在键盘之上的位置就不做处理
                        if (viewBottom <= keyboardTop)
                            return@Runnable
                        //需要滚动的距离即为输入框底部到键盘的距离
                        var reSizeLayoutHeight = viewBottom - keyboardTop
                        reSizeLayoutHeight -= statusBarHeight
                        reSizeLayoutHeight += offset
                        if (viewGroup is ScrollView) {
                            viewGroup.smoothScrollBy(0, reSizeLayoutHeight)
                        } else if (viewGroup is RecyclerView) {
                            viewGroup.smoothScrollBy(0, reSizeLayoutHeight)
                        }
                    }
                }
            }, 50)
        }
    }

    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度
     */
    private val statusBarHeight: Int
        get() {
            var result = 0
            val resId = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resId > 0) {
                result = activity.resources.getDimensionPixelOffset(resId)
            }
            return result
        }
}
