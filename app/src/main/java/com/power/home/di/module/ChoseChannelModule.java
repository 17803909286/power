package com.power.home.di.module;


import com.power.home.data.AllCourseModel;
import com.power.home.data.ChoseChannelModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.AllCourseContract;
import com.power.home.presenter.contract.ChoseChannelContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class ChoseChannelModule {

    private ChoseChannelContract.View mView;

    public ChoseChannelModule(ChoseChannelContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public ChoseChannelContract.View provideView(){
        return mView;
    }
    @Provides
    public ChoseChannelContract.IChoseChannelModel provideModel(ApiService apiService){

        return new ChoseChannelModel(apiService);
    }
}
