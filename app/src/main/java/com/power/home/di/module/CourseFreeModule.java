package com.power.home.di.module;


import com.power.home.data.CourseFreeModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.CourseFreeContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class CourseFreeModule {

    private CourseFreeContract.View mView;

    public CourseFreeModule(CourseFreeContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public CourseFreeContract.View provideView() {
        return mView;
    }

    @Provides
    public CourseFreeContract.ICourseFreeModel provideModel(ApiService apiService) {

        return new CourseFreeModel(apiService);
    }
}
