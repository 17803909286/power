package com.power.home.di.module;


import com.power.home.data.CourseDailyModel;
import com.power.home.data.CourseFreeModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.CourseDailyContract;
import com.power.home.presenter.contract.CourseFreeContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class CourseDailyModule {

    private CourseDailyContract.View mView;

    public CourseDailyModule(CourseDailyContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public CourseDailyContract.View provideView() {
        return mView;
    }

    @Provides
    public CourseDailyContract.ICourseDailyModel provideModel(ApiService apiService) {

        return new CourseDailyModel(apiService);
    }
}
