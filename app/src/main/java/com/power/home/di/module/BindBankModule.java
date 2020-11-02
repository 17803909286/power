package com.power.home.di.module;


import com.power.home.data.BindBankModel;
import com.power.home.data.EveryDayModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.BindBankContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class BindBankModule {

    private BindBankContract.View mView;

    public BindBankModule(BindBankContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public BindBankContract.View provideView() {
        return mView;
    }

    @Provides
    public BindBankContract.IBindBankModel provideModel(ApiService apiService) {

        return new BindBankModel(apiService);
    }
}
