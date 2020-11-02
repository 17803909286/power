package com.power.home.di.module;


import com.power.home.data.BindPhoneModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.BindPhoneContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class BindPhoneModule {

    private BindPhoneContract.View mView;

    public BindPhoneModule(BindPhoneContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public BindPhoneContract.View provideView() {
        return mView;
    }

    @Provides
    public BindPhoneContract.IBindPhoneModel provideModel(ApiService apiService) {

        return new BindPhoneModel(apiService);
    }
}
