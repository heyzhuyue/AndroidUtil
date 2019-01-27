package com.zy.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by zhuyue on 2017/7/30
 */

public class JsonUtils {


    /**
     * 解析Json数据
     *
     * @param key    更换数据key
     * @param value  更换Value
     * @param object 解析对象
     */
    public static Object analyzeJson(String key, Object value, Object object) throws Exception {
        if (object instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) object;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                analyzeJson(key, value, jsonObject);
            }
        } else if (object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            Iterator iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                String jsonKey = iterator.next().toString();
                Object obj = jsonObject.get(jsonKey);
                if (obj != null) {
                    if (obj instanceof JSONArray) {
                        analyzeJson(key, value, obj);
                    } else if (obj instanceof JSONObject) {
                        analyzeJson(key, value, obj);
                    } else {
                        if (jsonKey.equals(key)) {
                            jsonObject.put(jsonKey, value);
                        }
                    }
                }
            }
        }
        return object;
    }
}
