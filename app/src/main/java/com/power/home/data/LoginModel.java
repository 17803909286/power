package com.power.home.data;


import com.power.home.common.util.ParamUtil;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.UserInfoBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.LoginContract;

import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class LoginModel implements LoginContract.ILoginModel {

    private ApiService mApiService;

    public LoginModel(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<UserInfoBean>> login(String code, String phone) {
        TreeMap<String, Object> map = ParamUtil.getParma();
        map.put("code", code);
        map.put("phone", phone);
        return mApiService.login(ParamUtil.getBody(map));
    }
}
