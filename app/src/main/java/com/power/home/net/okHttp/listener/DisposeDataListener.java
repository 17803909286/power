package com.power.home.net.okHttp.listener;

import org.json.JSONException;
import org.json.JSONObject;

public interface DisposeDataListener {
    /**
     * 请求成功回调事件处理
     */
    void onSuccess(JSONObject responseObj) throws JSONException;

    void onSuccessConvetedToEntity(Object responesObj);

    /**
     * 请求失败回调事件处理
     */
    void onFailure(Object reasonObj);
}
