package com.power.home.di.module;


import com.power.home.data.WithdrawlModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.WithdrawlContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class WithdrawlModule {

    private WithdrawlContract.View mView;

    public WithdrawlModule(WithdrawlContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public WithdrawlContract.View provideView() {
        return mView;
    }

    @Provides
    public WithdrawlContract.IWithdrawlModel provideModel(ApiService apiService) {

        return new WithdrawlModel(apiService);
    }
}
