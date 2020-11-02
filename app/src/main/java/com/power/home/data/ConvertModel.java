package com.power.home.data;


import com.power.home.common.util.ParamUtil;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.ConvertContract;

import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class ConvertModel implements ConvertContract.IConvertModel {

    private ApiService mApiService;

    public ConvertModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<String>> codeMarket(String exchangeCode) {
        TreeMap<String, Object> map = ParamUtil.getParma();
        map.put("exchangeCode", exchangeCode);
        map.put("userId", SharePreferencesUtils.getUserId());
        return mApiService.codeMarket(ParamUtil.getBody(map));
    }
}
