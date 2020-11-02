package com.power.home.net.okHttp;

import com.power.home.net.okHttp.client.CommonOkHttpClient;
import com.power.home.net.okHttp.listener.DisposeDataHandle;
import com.power.home.net.okHttp.listener.DisposeDataListener;
import com.power.home.net.okHttp.request.CommonRequest;
import com.power.home.net.okHttp.request.RequestParams;

public class RequestCenter {

    static class HttpContants {
        private static final String VERIFY_URL = "http://apis.juhe.cn/idimage/verify";
    }
    //根据参数发送所有post请求
    public static void postRequest(String url, RequestParams params, DisposeDataListener listener,
                                   Class<?> clazz){
        CommonOkHttpClient.post(CommonRequest.
                createPostRequest(url, params), new DisposeDataHandle(listener, clazz));
    }
    public static void requestPersonVerifyinfo(DisposeDataListener listener,RequestParams params){

        RequestCenter.postRequest(HttpContants.VERIFY_URL,params,listener,null);

    }
}

