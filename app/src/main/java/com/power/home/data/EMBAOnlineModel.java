package com.power.home.data;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EMBAOnlineBean;
import com.power.home.data.bean.MainHomeBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.EMBAOnlineContract;
import com.power.home.presenter.contract.MainHomeContract;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class EMBAOnlineModel implements EMBAOnlineContract.IEMBAOnlineModel {

    private ApiService mApiService;

    public EMBAOnlineModel(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<EMBAOnlineBean>> getEmbaData() {
        return mApiService.getEmbaData();
    }
}
