package com.power.home.common.util;

import com.google.gson.Gson;

import java.util.TreeMap;

import okhttp3.RequestBody;

/**
 * Created by ZHP on 2017/7/18.
 */

public class ParamUtil {

    public static TreeMap<String, Object> getParma() {

        TreeMap<String, Object> map = new TreeMap<>();
        return map;
    }

    public static StringBuilder getHeaders() {

        StringBuilder headers = new StringBuilder();
        headers.append("Bearer " + SharePreferencesUtils.getToken());

        return headers;
    }

    public static RequestBody getBody(TreeMap<String, Object> map) {

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), new Gson().toJson(map));
        return body;
    }

}
