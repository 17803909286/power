package com.power.home.di.module;


import com.power.home.data.EveryDayModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.EveryDayContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class EveryDayModule {

    private EveryDayContract.View mView;

    public EveryDayModule(EveryDayContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public EveryDayContract.View provideView() {
        return mView;
    }

    @Provides
    public EveryDayContract.IEveryDayModel provideModel(ApiService apiService) {

        return new EveryDayModel(apiService);
    }
}
