package com.power.home.di.module;


import com.power.home.data.EMBAOnlineModel;
import com.power.home.data.MainHomeModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.EMBAOnlineContract;
import com.power.home.presenter.contract.MainHomeContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class EMBAOnlineModule {

    private EMBAOnlineContract.View mView;

    public EMBAOnlineModule(EMBAOnlineContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public EMBAOnlineContract.View provideView(){
        return mView;
    }
    @Provides
    public EMBAOnlineContract.IEMBAOnlineModel provideModel(ApiService apiService){

        return new EMBAOnlineModel(apiService);
    }
}
