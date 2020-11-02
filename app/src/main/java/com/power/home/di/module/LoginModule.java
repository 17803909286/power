package com.power.home.di.module;


import com.power.home.data.LoginModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class LoginModule {

    private LoginContract.View mView;

    public LoginModule(LoginContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public LoginContract.View provideView() {
        return mView;
    }

    @Provides
    public LoginContract.ILoginModel provideModel(ApiService apiService) {

        return new LoginModel(apiService);
    }
}
