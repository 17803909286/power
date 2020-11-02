package com.power.home.di.module;


import com.power.home.data.VipRecordModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.VipRecordContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class VipRecordModule {

    private VipRecordContract.View mView;

    public VipRecordModule(VipRecordContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public VipRecordContract.View provideView() {
        return mView;
    }

    @Provides
    public VipRecordContract.IVipRecordModel provideModel(ApiService apiService) {

        return new VipRecordModel(apiService);
    }
}
