package com.zy.util.calculate;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by zy on 2018/1/2.
 *
 * @author win7
 * @description 金额计算工具类
 */

public class ArithUtil {

    private static final int DEF_DIV_SCALE = 10;

    private static final int DEF_SCALE = 2;

    public ArithUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 相加
     *
     * @param d1 金额1
     * @param d2 金额2
     * @return 相加后的金额
     */
    public static double add(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        String result = b1.add(b2).setScale(2, BigDecimal.ROUND_UP).toString();
        return Double.parseDouble(result);
    }

    /**
     * 相减
     *
     * @param d1 金额1
     * @param d2 金额2
     * @return 相减后的金额
     */
    public static double sub(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        String result = b1.subtract(b2).setScale(2, BigDecimal.ROUND_UP).toString();
        return Double.parseDouble(result);

    }

    /**
     * 相乘
     *
     * @param d1 金额1
     * @param d2 金额2
     * @return 相乘后的金额
     */
    public static double mul(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        String result = b1.multiply(b2).setScale(2, BigDecimal.ROUND_UP).toString();
        return Double.parseDouble(result);

    }

    /**
     * 相除
     *
     * @param d1 金额1
     * @param d2 金额2
     * @return 相除后的金额
     */
    public static double div(double d1, double d2) {
        return div(d1, d2, DEF_DIV_SCALE);
    }

    /**
     * 相除
     *
     * @param d1    金额1
     * @param d2    金额2
     * @param scale 保留位数
     * @return 相除后的金额
     */
    public static double div(double d1, double d2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(d1));
        BigDecimal b2 = new BigDecimal(Double.toString(d2));
        String result = b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_UP).toString();
        return Double.parseDouble(result);
    }

    /**
     * 保留小数点后2位数
     *
     * @param value 值
     * @return 格式化的值
     */
    public static String scaleValue(double value) {
        return scaleValue(value, DEF_SCALE);
    }

    /**
     * 保留小数点后n位数
     *
     * @param value 值
     * @param scale 保留位数
     * @return 格式化的值
     */
    public static String scaleValue(double value, int scale) {
        BigDecimal num1 = new BigDecimal(value).setScale(scale, BigDecimal.ROUND_HALF_UP);
        return num1.toString();
    }

    /**
     * 保留小数点后2位
     *
     * @param value 值
     * @return 格式化值
     */
    public static String formatDouble(double value) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(value);
    }

}
