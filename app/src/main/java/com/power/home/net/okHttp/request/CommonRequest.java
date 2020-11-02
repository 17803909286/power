package com.power.home.net.okHttp.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Request;

public class CommonRequest {

    /**
     * create the key-value Request
     *
     * @param url
     * @param params
     * @return
     */
    public static Request createPostRequest(String url, RequestParams params) {
        return createPostRequest(url, params, null);
    }
    /**
     * 可以带请求头的Post请求
     *
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static Request createPostRequest(String url, RequestParams params, RequestParams headers){
        FormBody.Builder mFormBuild = new FormBody.Builder();
        if(params != null){
            for(Map.Entry<String,String> entry : params.urlParams.entrySet())
            {
                mFormBuild.add(entry.getKey(),entry.getValue());
            }
        }

        Headers.Builder mHeaderBuild = new Headers.Builder();
        if(headers != null){
            for (Map.Entry<String, String> entry : headers.urlParams.entrySet()) {
                mHeaderBuild.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody mFormBody = mFormBuild.build();
        Headers mHeader = mHeaderBuild.build();
        Request request = new Request.Builder().url(url)
                .post(mFormBody)
                .headers(mHeader)
                .build();
        return request;
    }
    /**
     * ressemble the params to the url
     *
     * @param url
     * @param params
     * @return
     */
    public static Request createGetRequest(String url, RequestParams params) {

        return createGetRequest(url, params, null);
    }

    /**
     * 可以带请求头的Get请求
     *
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static Request createGetRequest(String url, RequestParams params, RequestParams headers){
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if(params != null){
            for(Map.Entry<String,String> entry : params.urlParams.entrySet())
            {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        Headers.Builder mHeaderBuild = new Headers.Builder();
        if(headers != null){
            for(Map.Entry<String,String> entry : headers.urlParams.entrySet())
            {
                mHeaderBuild.add(entry.getKey(),entry.getValue());
            }
        }
        Headers mHeader = mHeaderBuild.build();
        return new Request.Builder().
        url(urlBuilder.substring(0,urlBuilder.length()-1))
                .get()
                .headers(mHeader)
                .build();
    }

}
