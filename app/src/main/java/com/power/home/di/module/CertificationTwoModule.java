package com.power.home.di.module;


import com.power.home.data.BindPhoneModel;
import com.power.home.data.CertificationTwoModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.CertificationTwoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class CertificationTwoModule {

    private CertificationTwoContract.View mView;

    public CertificationTwoModule(CertificationTwoContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public CertificationTwoContract.View provideView() {
        return mView;
    }

    @Provides
    public CertificationTwoContract.ICertificationTwoModel provideModel(ApiService apiService) {

        return new CertificationTwoModel(apiService);
    }
}
