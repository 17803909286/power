package com.power.home.di.module;


import com.power.home.BuildConfig;
import com.power.home.data.MoneyModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.MoneyContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class MoneyModule {

    private MoneyContract.View mView;
    public MoneyModule(MoneyContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public MoneyContract.View provideView() {
        return mView;
    }

    @Provides
    public MoneyContract.IMoneyModel provideModel(ApiService apiService) {

        return new MoneyModel(apiService);
    }
}
