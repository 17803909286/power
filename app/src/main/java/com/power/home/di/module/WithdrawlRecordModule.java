package com.power.home.di.module;


import com.power.home.data.WithdrawlRecordModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.WithdrawlRecordContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class WithdrawlRecordModule {

    private WithdrawlRecordContract.View mView;

    public WithdrawlRecordModule(WithdrawlRecordContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public WithdrawlRecordContract.View provideView() {
        return mView;
    }

    @Provides
    public WithdrawlRecordContract.IWithdrawlRecordModel provideModel(ApiService apiService) {

        return new WithdrawlRecordModel(apiService);
    }
}
