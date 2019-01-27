package com.zy.util.exception;

/**
 * Created by zhuyue on 2017/7/30
 */

public class AnalyzeJsonException extends Exception {

    public static final String ANALYZE_JSON_TYPE_MESSAGE = "数据类型异常";

    public static final String AMALYZE_JSON_EXPTY_MESSAGE = "数据为空";

    public AnalyzeJsonException(String message) {
        super(message);
    }
}
