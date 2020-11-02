package com.power.home.data;


import com.power.home.common.util.ParamUtil;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.CertificationTwoContract;

import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class CertificationTwoModel implements CertificationTwoContract.ICertificationTwoModel {

    private ApiService mApiService;

    public CertificationTwoModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<EmptyBean>> realNameAuthSave(String authId, String mobile, String verifyCode) {
        TreeMap<String, Object> map = ParamUtil.getParma();
        map.put("authId", authId);
        map.put("mobile", mobile);
        map.put("verifyCode", verifyCode);
        return mApiService.realNameAuthSave(ParamUtil.getBody(map));
    }
}
