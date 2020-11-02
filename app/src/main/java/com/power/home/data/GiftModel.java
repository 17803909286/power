package com.power.home.data;


import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.GiftBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.GiftContract;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class GiftModel implements GiftContract.IGiftModel {

    private ApiService mApiService;

    public GiftModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<GiftBean>> activityGift(String dailyId) {
        return mApiService.activityGift(dailyId, SharePreferencesUtils.getUserId());
    }
}
