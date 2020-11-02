package com.power.home.di.module;


import com.power.home.data.GiftModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.GiftContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class GiftModule {

    private GiftContract.View mView;

    public GiftModule(GiftContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public GiftContract.View provideView() {
        return mView;
    }

    @Provides
    public GiftContract.IGiftModel provideModel(ApiService apiService) {

        return new GiftModel(apiService);
    }
}
