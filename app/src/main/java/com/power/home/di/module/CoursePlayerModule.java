package com.power.home.di.module;


import com.power.home.data.CoursePlayerModel;
import com.power.home.data.EMBAOnlineModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.CoursePlayerContract;
import com.power.home.presenter.contract.EMBAOnlineContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class CoursePlayerModule {

    private CoursePlayerContract.View mView;

    public CoursePlayerModule(CoursePlayerContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public CoursePlayerContract.View provideView(){
        return mView;
    }
    @Provides
    public CoursePlayerContract.ICoursePlayerModel provideModel(ApiService apiService){

        return new CoursePlayerModel(apiService);
    }
}
