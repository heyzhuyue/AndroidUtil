package com.zy.ktutil.extend

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * View的点击事件
 */
fun View._setOnClickListener(block: ((v: View?) -> Unit)) {
    setOnClickListener(object : View.OnClickListener {
        var last = 0L
        override fun onClick(v: View?) {
            if (System.currentTimeMillis() - last > 300) {
                block(v)
                last = System.currentTimeMillis()
            }
        }
    })
}

/***
 * 显示软键盘
 */
fun EditText.openKeyBoard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.RESULT_SHOWN)
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

/***
 * 隐藏软键盘
 */
fun EditText.hideKeyBoard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

