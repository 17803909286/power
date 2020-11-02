package com.power.home.di.module;


import com.power.home.data.LoginModel;
import com.power.home.data.WxLoginModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.LoginContract;
import com.power.home.presenter.contract.WxLoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class WxLoginModule {

    private WxLoginContract.View mView;

    public WxLoginModule(WxLoginContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public WxLoginContract.View provideView() {
        return mView;
    }

    @Provides
    public WxLoginContract.IWxLoginModel provideModel(ApiService apiService) {

        return new WxLoginModel(apiService);
    }
}
