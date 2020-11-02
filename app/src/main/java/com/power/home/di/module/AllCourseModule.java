package com.power.home.di.module;


import com.power.home.data.AllCourseModel;
import com.power.home.data.MainHomeModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.AllCourseContract;
import com.power.home.presenter.contract.MainHomeContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class AllCourseModule {

    private AllCourseContract.View mView;

    public AllCourseModule(AllCourseContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public AllCourseContract.View provideView(){
        return mView;
    }
    @Provides
    public AllCourseContract.IAllCourseModel provideModel(ApiService apiService){

        return new AllCourseModel(apiService);
    }
}
