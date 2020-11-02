package com.power.home.di.module;


import com.power.home.data.NickNameModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.NickNameContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class NickNameModule {

    private NickNameContract.View mView;

    public NickNameModule(NickNameContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public NickNameContract.View provideView() {
        return mView;
    }

    @Provides
    public NickNameContract.INickNameModel provideModel(ApiService apiService) {

        return new NickNameModel(apiService);
    }
}
