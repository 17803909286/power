package com.power.home.di.module;


import com.power.home.data.MainMineModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.MainMineContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class MainMineModule {

    private MainMineContract.View mView;

    public MainMineModule(MainMineContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public MainMineContract.View provideView() {
        return mView;
    }

    @Provides
    public MainMineContract.IMainMineModel provideModel(ApiService apiService) {

        return new MainMineModel(apiService);
    }
}
