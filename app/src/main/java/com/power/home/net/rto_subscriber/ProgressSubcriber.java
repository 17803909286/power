package com.power.home.net.rto_subscriber;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.power.home.common.Constant;
import com.power.home.common.util.JsonUtils;
import com.power.home.common.util.LogUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.common.util.UserCacheUtil;
import com.power.home.data.bean.BaseBean;
import com.power.home.net.rto_exception.ApiException;
import com.power.home.net.rto_exception.BaseException;
import com.power.home.ui.activity.LoginActivity;
import com.power.home.ui.widget.BaseView;
import com.power.home.ui.widget.LoginPopup;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by ZHP on 2017/6/25.
 */

public abstract class ProgressSubcriber<T> extends ErrorHandlerSubscriber<T> {
    private boolean isNotShowProgress;
    private BaseView mView;
    private Context context;
    private LoginPopup loginPopup;


    public ProgressSubcriber(Context context, BaseView view) {
        super(context);
        this.mView = view;
        this.context = context;
    }

    public ProgressSubcriber(Context context, BaseView view, boolean isNotShowProgress) {
        super(context);
        this.mView = view;
        this.isNotShowProgress = isNotShowProgress;
        this.context = context;

    }


    public boolean isShowProgress() {
        return !isNotShowProgress;
    }


    @Override
    public void onSubscribe(Disposable d) {
        super.onSubscribe(d);
        if (isShowProgress() && null != mView) {
            mView.showLoading();
        }
    }

    @Override
    public void onComplete() {
        if (null != mView) {
            mView.dismissLoading();
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof ApiException) {
            ApiException a = (ApiException) e;
            LogUtil.d("api", "------" + a.getDisplayMessage() + "------" + a.getCode());
            if (a.getCode().equals(BaseException.GO_LOGIN)) {
                try {
                    showLoginPop();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else if (a.getCode().equals(BaseException.NOT_LOGIN)) {
                UserCacheUtil.loginOut();
                ActivityUtils.finishAllActivities();
                toLogin();
            } else {
                if (null != mView) {
                    mView.showError(a.getDisplayMessage());
                }
            }

        } else if (e instanceof HttpException) {
            ResponseBody body = ((HttpException) e).response().errorBody();
            try {
                if (body.string().contains("<head>")) {

                } else {
                    BaseBean baseBean = JsonUtils.fromJson(body.string(), BaseBean.class);
                    mView.showError(null != baseBean ? baseBean.getMsg() : "");
                }
            } catch (IOException IOe) {
                IOe.printStackTrace();
            }
        } else if (e instanceof SocketTimeoutException) {  //VPN open
            //errorMessage = "服务器响应超时";
            if (null != mView) {
                mView.showError("服务器响应超时");
            }
        } else if (e instanceof ConnectException) {
            //errorMessage = "网络连接异常，请检查网络";
            if (null != mView) {
                mView.showError("网络连接异常，请检查网络");
            }
        } else if (e instanceof RuntimeException) {
            //errorMessage = "运行时错误";
            if (null != mView) {
                mView.showError("运行时错误");
            }
        } else if (e instanceof UnknownHostException) {
            //errorMessage = "无法解析主机，请检查网络连接";
            if (null != mView) {
                mView.showError("无法解析主机，请检查网络连接");
            }
        } else if (e instanceof UnknownServiceException) {
            // errorMessage = "未知的服务器错误";
            if (null != mView) {
                mView.showError("未知的服务器错误");
            }
        } else if (e instanceof IOException) {  //飞行模式等
//            errorMessage = "没有网络，请检查网络连接";
            if (null != mView) {
                mView.showError("没有网络，请检查网络连接");
            }
        } else {
            ApiException exception = new ApiException("500", "");
            if (null != mView) {
                mView.showError(exception.getDisplayMessage());
            }
        }
        if (isShowProgress()) {
            if (null != mView) {
                mView.dismissLoading();
            }
        }

    }

    private void showLoginPop() {
        if (loginPopup == null) {
            loginPopup = new LoginPopup(context);
            loginPopup.showPopWindow(true);
            loginPopup.setSure(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserCacheUtil.loginOut();
                    ActivityUtils.finishAllActivities();
                    toLogin();
                }
            });
        }

    }

    private void toLogin() {
        Intent intent = new Intent(UIUtil.getContext(), LoginActivity.class);
        intent.putExtra(Constant.from, "out");
        UIUtil.startActivity(intent);
    }


}
