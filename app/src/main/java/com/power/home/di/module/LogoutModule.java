package com.power.home.di.module;


import com.power.home.data.LogoutModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.LogoutContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class LogoutModule {

    private LogoutContract.View mView;

    public LogoutModule(LogoutContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public LogoutContract.View provideView() {
        return mView;
    }

    @Provides
    public LogoutContract.ILogoutModel provideModel(ApiService apiService) {

        return new LogoutModel(apiService);
    }
}
