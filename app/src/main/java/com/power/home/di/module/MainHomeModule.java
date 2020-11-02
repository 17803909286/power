package com.power.home.di.module;


import com.power.home.data.MainHomeModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.MainHomeContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class MainHomeModule {

    private MainHomeContract.View mView;

    public MainHomeModule(MainHomeContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public MainHomeContract.View provideView(){
        return mView;
    }
    @Provides
    public MainHomeContract.IMainHomeModel provideModel(ApiService apiService){

        return new MainHomeModel(apiService);
    }
}
