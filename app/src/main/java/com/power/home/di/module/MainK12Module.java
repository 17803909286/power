package com.power.home.di.module;


import com.power.home.data.MainHomeModel;
import com.power.home.data.MainK12Model;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.MainK12Contract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class MainK12Module {

    private MainK12Contract.View mView;

    public MainK12Module(MainK12Contract.View mView) {
        this.mView = mView;
    }

    @Provides
    public MainK12Contract.View provideView(){
        return mView;
    }
    @Provides
    public MainK12Contract.IMainK12Model provideModel(ApiService apiService){

        return new MainK12Model(apiService);
    }
}
