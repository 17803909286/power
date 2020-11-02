package com.power.home.data;


import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.MoneyOutRecordBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.MoneyContract;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class MoneyModel implements MoneyContract.IMoneyModel {

    private ApiService mApiService;

    public MoneyModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<MoneyOutRecordBean>> getMoneyIn(int page, int size, String incomeType) {
        return mApiService.getMoneyIn(SharePreferencesUtils.getUserId(), String.valueOf(page), String.valueOf(size), incomeType);
    }
}
