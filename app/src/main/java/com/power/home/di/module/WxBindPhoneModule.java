package com.power.home.di.module;


import com.power.home.data.WxBindPhoneModel;
import com.power.home.data.WxLoginModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.WxBindPhoneContract;
import com.power.home.presenter.contract.WxLoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class WxBindPhoneModule {

    private WxBindPhoneContract.View mView;

    public WxBindPhoneModule(WxBindPhoneContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public WxBindPhoneContract.View provideView() {
        return mView;
    }

    @Provides
    public WxBindPhoneContract.IWxBindPhoneModel provideModel(ApiService apiService) {

        return new WxBindPhoneModel(apiService);
    }
}
