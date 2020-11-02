package com.power.home.di.module;


import com.power.home.data.VersionModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.VersionContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class VersionModule {

    private VersionContract.View mView;

    public VersionModule(VersionContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public VersionContract.View provideView() {
        return mView;
    }

    @Provides
    public VersionContract.IVersionModel provideModel(ApiService apiService) {

        return new VersionModel(apiService);
    }
}
