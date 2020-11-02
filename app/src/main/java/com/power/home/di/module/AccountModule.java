package com.power.home.di.module;


import com.power.home.data.AccountModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.AccountContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class AccountModule {

    private AccountContract.View mView;

    public AccountModule(AccountContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public AccountContract.View provideView() {
        return mView;
    }

    @Provides
    public AccountContract.IAccountModel provideModel(ApiService apiService) {

        return new AccountModel(apiService);
    }
}
