package com.zy.util;

/**
 * @author zhuyue
 * @description 常量字段
 */
public class Constans {

    /**
     * KB与Byte的倍数
     */
    public static final int KB = 1024;
    /**
     * MB与Byte的倍数
     */
    public static final int MB = 1048576;
    /**
     * GB与Byte的倍数
     */
    public static final int GB = 1073741824;
    /**
     * 秒与毫秒的倍数
     */
    public static final int SEC = 1000;
    /**
     * 分与毫秒的倍数
     */
    public static final int MIN = 60000;
    /**
     * 时与毫秒的倍数
     */
    public static final int HOUR = 3600000;
    /**
     * 天与毫秒的倍数
     */
    public static final int DAY = 86400000;

    /**
     * 内存单位
     * BYTE:字节
     * KB：KB
     * MB：MB
     * GB：GB
     */
    public enum MemoryUnit {
        BYTE,
        KB,
        MB,
        GB
    }

    /**
     * 时间单位
     */
    public enum TimeUnit {
        MSEC,
        SEC,
        MIN,
        HOUR,
        DAY
    }
}