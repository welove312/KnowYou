package com.know.you.app.parser;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xiaojunzi on 16-7-28.
 */
public class JsonParser extends Parser{


    public static final int MODEL_SINGLE = 1;

    public static final int MODEL_LIST = 2;

    private Class target;

    private int model;

    public JsonParser(Class target, int model) {
        this.target = target;
        this.model = model;
    }

    @Override
    public Object parseJSON(String jsonStr) throws JSONException {
        try {
            JSONObject json = new JSONObject(jsonStr);
            String data = json.optString("data");
            if (!TextUtils.isEmpty(data)) {
                if (MODEL_SINGLE == model) {
                    return JSON.parseObject(data, target);
                } else {
                    return JSON.parseArray(data, target);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
