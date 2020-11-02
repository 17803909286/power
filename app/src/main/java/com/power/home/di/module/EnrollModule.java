package com.power.home.di.module;


import com.power.home.data.EnrollModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.EnrollContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class EnrollModule {

    private EnrollContract.View mView;

    public EnrollModule(EnrollContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public EnrollContract.View provideView() {
        return mView;
    }

    @Provides
    public EnrollContract.IEnrollModel provideModel(ApiService apiService) {

        return new EnrollModel(apiService);
    }
}
