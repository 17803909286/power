package com.power.home.data;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.LogoutContract;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class LogoutModel implements LogoutContract.ILogoutModel {

    private ApiService mApiService;

    public LogoutModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<EmptyBean>> logout() {
        return mApiService.logout();
    }
}
