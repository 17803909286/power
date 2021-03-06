package com.power.home.di.module;


import com.power.home.data.AllCourseModel;
import com.power.home.data.ReceiveMaterialModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.AllCourseContract;
import com.power.home.presenter.contract.ReceiveMaterialContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class ReceiveMaterialModule {

    private ReceiveMaterialContract.View mView;

    public ReceiveMaterialModule(ReceiveMaterialContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public ReceiveMaterialContract.View provideView(){
        return mView;
    }
    @Provides
    public ReceiveMaterialContract.IReceiveMaterialModel provideModel(ApiService apiService){

        return new ReceiveMaterialModel(apiService);
    }
}
