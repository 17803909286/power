package com.power.home.di.module;


import com.power.home.data.ReferralCodeModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.ReferralCodeContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class ReferralCodeModule {

    private ReferralCodeContract.View mView;

    public ReferralCodeModule(ReferralCodeContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public ReferralCodeContract.View provideView() {
        return mView;
    }

    @Provides
    public ReferralCodeContract.IReferralCodeModel provideModel(ApiService apiService) {

        return new ReferralCodeModel(apiService);
    }
}
