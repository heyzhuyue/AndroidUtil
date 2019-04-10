package com.zy.util.keybord;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * @author zhuyue
 * 自定义输入法冲入处理RelativeLayout
 */
public class KeyBoardListenLayout extends RelativeLayout {

    private onSizeChangedListener mChangedListener;

    public KeyBoardListenLayout(Context context) {
        super(context);
    }

    public KeyBoardListenLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyBoardListenLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (null != mChangedListener && 0 != oldw && 0 != oldh) {
            boolean showKeyboard = h < oldh;
            mChangedListener.onChanged(showKeyboard, h, oldh);
        }
    }

    public void setOnSizeChangedListener(onSizeChangedListener listener) {
        mChangedListener = listener;
    }

    public interface onSizeChangedListener {
        void onChanged(boolean showKeyboard, int h, int oldh);
    }
}