package com.zy.ktutil.extend

import android.text.ParcelableSpan
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import com.zy.ktutil.Utils
import com.zy.ktutil.util.LoggerUtil
import com.zy.ktutil.util.ToastUtils
import java.util.*
import java.util.regex.Pattern

val REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$"

/**
 * 正则：手机号（精确）
 *
 *
 * 移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、
 * 184、187、188
 *
 *
 *
 * 联通：130、131、132、145、155、156、175、176、185、186
 *
 *
 *
 * 电信：133、153、173、177、180、181、189
 *
 *
 *
 * 全球星：1349
 *
 *
 *
 * 虚拟运营商：170,171
 *
 */
const val REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,1,5-8])|(18[0-9])|(147))\\d{8}$"

/**
 * 正则：电话号码
 */
const val REGEX_TEL = "^0\\d{2,3}[- ]?\\d{7,8}"

/**
 * 正则：身份证号码15位
 */
const val REGEX_ID_CARD15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$"

/**
 * 正则：身份证号码18位
 */
const val REGEX_ID_CARD18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$"

/**
 * 正则：邮箱
 */
const val REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"

/**
 * 正则：URL
 */
const val REGEX_URL = "[a-zA-z]+://[^\\s]*"

/**
 * 正则：汉字
 */
const val REGEX_ZH = "^[\\u4e00-\\u9fa5]+$"

/**
 * 正则：用户名，取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾,用户名必须是6-20位
 */
const val REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$"

/**
 * 正则：yyyy-MM-dd格式的日期校验，已考虑平闰年
 */
const val REGEX_DATE =
    "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$"

/**
 * 正则：IP地址
 */
const val REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)"

/**
 * 判断全为数字与字母
 */
const val REGEX_NUMBER_LETTER = "^[A-Za-z0-9]+$"

/**
 * 禁止含有^%&',;=?$\"等字符
 */
const val REGEX_NO_SPECIALSYMBOL = "[^%&',;=?$~\\x22]+"

/**
 * 验证账号是否合法
 */
const val REGEX_ACCOUNT = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$"

/**
 * 验证密码合法性 (以字母开头，长度在6~18之间，只能包含字母、数字和下划线)
 */
const val REGEX_PASSWORD = "^[a-zA-Z]\\w{5,17}$"

/**
 * 空白行删除处理
 */
const val REGEX_SPLACE = "\\n\\s*\\r"

/**
 * 检验中国邮政编码
 */
const val REGEX_ZH_ZIPCODE = "[1-9]\\d{5}(?!\\d)"

/***
 * String 判断空
 */
fun String._isEmptyOrNull(): Boolean {
    return TextUtils.isEmpty(this)
}

/**
 * 显示shortToast
 */
fun Any.showToast() {
    ToastUtils.showShortToast(this.toString())
}

/**
 * 显示longToast
 */
fun Any.showLongToast() {
    ToastUtils.showLongToast(this.toString())
}

/**
 *  增加Log
 */
fun Any.logTag(tag: String = "TAG") {
    LoggerUtil.i(tag, this.toString())
}

/**
 *  是否为简单的手机号
 */
fun CharSequence.isMobileSimple(): Boolean {
    return isMatch(REGEX_MOBILE_SIMPLE, this)
}

/**
 * 验证手机号（精确）
 */
fun CharSequence.isMobileExact(): Boolean {
    return isMatch(REGEX_MOBILE_EXACT, this)
}

/**
 * 验证电话号码
 */
fun CharSequence.isTel(): Boolean {
    return isMatch(REGEX_TEL, this)
}

/**
 * 验证身份证号码15位
 */
fun CharSequence.isIDCard15(): Boolean {
    return isMatch(REGEX_ID_CARD15, this)
}

/**
 * 验证身份证号码18位
 */
fun CharSequence.isIDCard18(): Boolean {
    return isMatch(REGEX_ID_CARD18, this)
}

/**
 * 验证邮箱
 */
fun CharSequence.isEmail(): Boolean {
    return isMatch(REGEX_EMAIL, this)
}

/**
 * 验证邮箱
 */
fun CharSequence.isURL(): Boolean {
    return isMatch(REGEX_URL, this)
}

/**
 * 验证邮箱
 */
fun CharSequence.isZh(): Boolean {
    return isMatch(REGEX_ZH, this)
}

/**
 * 验证用户名
 * <p>
 * 取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾,用户名必须是6-20位
 * </p>
 */
fun CharSequence.isUserName(): Boolean {
    return isMatch(REGEX_USERNAME, this)
}

/**
 * 验证yyyy-MM-dd格式的日期校验，已考虑平闰年
 */
fun CharSequence.isDate(): Boolean {
    return isMatch(REGEX_DATE, this)
}

/**
 * 验证IP地址
 */
fun CharSequence.isIP(): Boolean {
    return isMatch(REGEX_IP, this)
}

/**
 * 判断是否只为数字和字母
 */
fun CharSequence.isOnlyNumberWithLetter(): Boolean {
    return isMatch(REGEX_NUMBER_LETTER, this)
}

/**
 * 判断是否只为数字和字母
 */
fun CharSequence.isNotHaveSpecialSymbol(): Boolean {
    return isMatch(REGEX_NO_SPECIALSYMBOL, this)
}

/**
 * 验证账号合法性(字母开头，允许5-16字节，允许字母数字下划线)
 */
fun CharSequence.isLegalAccount(): Boolean {
    return isMatch(REGEX_ACCOUNT, this)
}

/**
 * 验证中国邮政编码
 */
fun CharSequence.isZhZipCode(): Boolean {
    return isMatch(REGEX_ZH_ZIPCODE, this)
}

/**
 * 验证密码合法性
 */
fun CharSequence.isLegalPassword(): Boolean {
    return isMatch(REGEX_PASSWORD, this)
}

/**
 * 获取正则匹配的部分
 */
fun CharSequence.getMatches(regex: String): List<String> {
    val matches = ArrayList<String>()
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(this.toString())
    while (matcher.find()) {
        matches.add(matcher.group())
    }
    return matches
}

/**
 * 替换正则匹配的第一部分
 */
fun CharSequence.getReplaceFirst(regex: String, replacement: String): String {
    return Pattern.compile(regex).matcher(this.toString()).replaceFirst(replacement)
}

/**
 * 替换所有空白行
 */
fun CharSequence.getReplaceSpace(regex: String, replacement: String): String {
    return Pattern.compile(REGEX_SPLACE).matcher(this.toString()).replaceFirst(replacement)
}

/**
 * 替换所有正则匹配的部分
 */
fun CharSequence.getReplaceAll(regex: String, replacement: String): String {
    return Pattern.compile(regex).matcher(this.toString()).replaceAll(replacement)
}

/**
 * 拼接不同颜色的字符串
 * @param color 颜色
 * @param start 开始位置
 * @param end 结束位置
 */
fun CharSequence.formatStringColor(color: Int, start: Int, end: Int): SpannableString {
    return this.setSpan(ForegroundColorSpan(Utils.getContext().resources.getColor(color)), start, end)
}

/**
 * 拼接不同颜色的字符串
 */
private fun CharSequence.setSpan(span: ParcelableSpan, start: Int, end: Int): SpannableString {
    val spannableString = SpannableString(this)
    spannableString.setSpan(span, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
    return spannableString
}

/**
 * 判断是否匹配正则
 *
 * @param regex 正则表达式
 * @param input 要匹配的字符串
 * @return `true`: 匹配<br></br>
 * `false`: 不匹配
 */
fun isMatch(regex: String, input: CharSequence?): Boolean {
    return input != null && input.isNotEmpty() && Pattern.matches(regex, input)
}
