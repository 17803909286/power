package com.power.home.net.okHttp.response;




import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.google.gson.Gson;
import com.power.home.net.okHttp.exception.OkHttpException;
import com.power.home.net.okHttp.listener.DisposeDataHandle;
import com.power.home.net.okHttp.listener.DisposeDataListener;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CommonJsonCallback implements Callback {
    /**
      * the logic layer exception, may alter in different app
     */
    protected final String RESULT_CODE = "ecode"; // 有返回则对于http请求来说是成功的，但还有可能是业务逻辑上的错误
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";

    /**
     * the java layer exception, do not same to the logic error
     */
    protected final int NETWORK_ERROR = -1; // the network relative error
    protected final int JSON_ERROR = -2; // the JSON relative error
    protected final int OTHER_ERROR = -3; // the unknow error
    /**
     * 将其它线程的数据转发到UI线程
     */
    private Handler mDeliveryHandler;
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandle handle){
        this.mListener = handle.mListener;
        this.mClass = handle.mClass;
        this.mDeliveryHandler = new Handler((Looper.getMainLooper()));

    }


    @Override
    public void onFailure(Call call, IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(new OkHttpException(NETWORK_ERROR,e));
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
              handleResponse(result);
            }
        });
    }

    private void handleResponse(Object responseObj){
        if(responseObj == null || responseObj.toString().trim().equals("")){
            mListener.onFailure(new OkHttpException(JSON_ERROR,EMPTY_MSG));
            return;
        }

        try{
            JSONObject result = new JSONObject(responseObj.toString());

            if(mClass == null){
                mListener.onSuccess(result);
            }else{
                Object obj = new Gson().fromJson(responseObj.toString(),mClass);
                if(obj != null){
                    mListener.onSuccessConvetedToEntity(obj);
                }else{
                    mListener.onFailure(new OkHttpException(JSON_ERROR,EMPTY_MSG));
                }
            }
        }catch (Exception e){
            mListener.onFailure(new OkHttpException(OTHER_ERROR,e.getMessage()));
        }

    }
}
