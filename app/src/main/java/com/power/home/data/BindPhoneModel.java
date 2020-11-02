package com.power.home.data;


import com.power.home.common.util.ParamUtil;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.BindPhoneContract;

import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class BindPhoneModel implements BindPhoneContract.IBindPhoneModel {

    private ApiService mApiService;

    public BindPhoneModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<EmptyBean>> changeMobile(String code, String newPhone, String oldPhone) {
        TreeMap<String, Object> map = ParamUtil.getParma();
        map.put("code", code);
        map.put("newPhone", newPhone);
        map.put("oldPhone", oldPhone);
        return mApiService.changeMobile(ParamUtil.getBody(map));
    }
}
