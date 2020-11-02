package com.power.home.di.module;


import com.power.home.data.VipModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.VipContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class VipModule {

    private VipContract.View mView;

    public VipModule(VipContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public VipContract.View provideView() {
        return mView;
    }

    @Provides
    public VipContract.IVipModel provideModel(ApiService apiService) {

        return new VipModel(apiService);
    }
}
