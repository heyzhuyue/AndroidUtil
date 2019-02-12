package com.zy.ktutil.extend

import android.text.ParcelableSpan
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import com.zy.ktutil.Utils

/**
 * <pre>
 *     author: zhuyue
 *     time  : 2019/2/11
 *     desc  : String拓展函数
 * </pre>
 */


/**
 * 拼接不同颜色的字符串
 */
fun CharSequence.formatStringColor(color: Int, start: Int, end: Int): SpannableString {
    return this.setSpan(ForegroundColorSpan(Utils.getContext().resources.getColor(color)), start, end)
}

private fun CharSequence.setSpan(span: ParcelableSpan, start: Int, end: Int): SpannableString {
    val spannableString = SpannableString(this)
    spannableString.setSpan(span, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
    return spannableString
}