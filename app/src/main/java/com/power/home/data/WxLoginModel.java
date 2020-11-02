package com.power.home.data;


import com.power.home.common.util.ParamUtil;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.data.bean.UserInfoBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.LoginContract;
import com.power.home.presenter.contract.WxLoginContract;

import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class WxLoginModel implements WxLoginContract.IWxLoginModel {

    private ApiService mApiService;

    public WxLoginModel(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<UserInfoBean>> weChatLogin(String code) {

        return mApiService.weChatLogin(code);
    }
}
