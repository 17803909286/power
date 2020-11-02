package com.power.home.data;


import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.VipRecordBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.VipRecordContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class VipRecordModel implements VipRecordContract.IVipRecordModel {

    private ApiService mApiService;

    public VipRecordModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<VipRecordBean>>> getVipRecord(int page, int size) {
        return mApiService.getVipRecord(SharePreferencesUtils.getUserId(),String.valueOf(page), String.valueOf(size));
    }
}
