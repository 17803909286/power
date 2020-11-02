package com.power.home.di.module;


import com.power.home.data.CourseOfflineModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.CourseOfflineContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class CourseOfflineModule {

    private CourseOfflineContract.View mView;

    public CourseOfflineModule(CourseOfflineContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public CourseOfflineContract.View provideView(){
        return mView;
    }
    @Provides
    public CourseOfflineContract.ICourseOfflineModel provideModel(ApiService apiService){

        return new CourseOfflineModel(apiService);
    }
}
