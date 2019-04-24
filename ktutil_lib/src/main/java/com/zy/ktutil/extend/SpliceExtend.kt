package com.zy.ktutil.extend

import java.lang.StringBuilder

/**
 * <pre>
 *     author: zhuyue
 *     time  : 2019/4/10
 *     desc  :拼接拓展函数
 * </pre>
 */

/**
 * List数据用,拼接
 */
fun List<String>.commaSpliceStr(): String {
    val builder = StringBuilder()
    forEach {
        builder.append(it)
        builder.append(",")
    }
    return if (builder.toString().length > 1) builder.delete(
        builder.length - 1,
        builder.length
    ).toString() else builder.toString()
}

/**
 * String数组,拼接
 */
fun Array<String>.commaSpliceStr(): String {
    val builder = StringBuilder()
    for (value in this) {
        builder.append(value)
        builder.append(",")
    }
    return if (builder.toString().length > 1) builder.delete(
        builder.length - 1,
        builder.length
    ).toString() else builder.toString()
}