package com.power.home.data;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.VipBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.VipContract;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class VipModel implements VipContract.IVipModel {

    private ApiService mApiService;

    public VipModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<VipBean>> getVipInfo() {
        return mApiService.getVipInfo();
    }
}
