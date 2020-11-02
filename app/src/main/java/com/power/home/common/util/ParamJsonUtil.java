package com.power.home.common.util;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by zhangpeng on 2017/12/8.
 */

public class ParamJsonUtil {

    public static JSONObject getjson(TreeMap<String,String> map) {

        JSONObject js = new JSONObject();
        try {
            for (Map.Entry<String,String> e : map.entrySet()) {
                js.put(e.getKey(),e.getValue());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return js;
    }

    public static String getjsonData(TreeMap<String,String> map) {

        Gson gson = new Gson();
        return gson.toJson(map);
    }
}
