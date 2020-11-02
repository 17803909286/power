package com.power.home.data;


import com.power.home.common.util.ParamUtil;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.UserInfoBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.WxBindPhoneContract;
import com.power.home.presenter.contract.WxLoginContract;

import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class WxBindPhoneModel implements WxBindPhoneContract.IWxBindPhoneModel {

    private ApiService mApiService;

    public WxBindPhoneModel(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<UserInfoBean>> bindPhone(String code , String phone,String userId) {

        TreeMap<String, Object> map = ParamUtil.getParma();
        map.put("code", code);
        map.put("phone", phone);
        map.put("userId", userId);
        return mApiService.bindPhone(ParamUtil.getBody(map));
    }
}
