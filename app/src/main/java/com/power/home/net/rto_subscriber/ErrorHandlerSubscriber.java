package com.power.home.net.rto_subscriber;

import android.content.Context;
import android.util.Log;

import com.power.home.net.rto_exception.BaseException;
import com.power.home.net.rto_rxbuild.RxErrorHandler;


/**
 * Created by ZHP on 2017/6/26.
 */


public abstract class ErrorHandlerSubscriber<T> extends DefualtSubscriber<T> {


    protected RxErrorHandler mErrorHandler = null;

    protected Context mContext;

    public ErrorHandlerSubscriber(Context context) {

        this.mContext = context;
        mErrorHandler = new RxErrorHandler(mContext);

    }


    @Override
    public void onError(Throwable e) {
        BaseException baseException = mErrorHandler.handleError(e);
        if (baseException == null) {
            e.printStackTrace();
            Log.d("api", e.getMessage());
        } else {
            mErrorHandler.showErrorMessage(baseException);
        }

    }

}
