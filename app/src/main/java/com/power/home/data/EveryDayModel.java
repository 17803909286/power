package com.power.home.data;


import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EveryDayBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.EveryDayContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class EveryDayModel implements EveryDayContract.IEveryDayModel {

    private ApiService mApiService;

    public EveryDayModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<EveryDayBean>>> getEveryDayInfo() {
        return mApiService.getEveryDayInfo(SharePreferencesUtils.getUserId());
    }
}
