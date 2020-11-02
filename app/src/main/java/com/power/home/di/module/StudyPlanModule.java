package com.power.home.di.module;


import com.power.home.data.EMBAOnlineModel;
import com.power.home.data.StudyPlanModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.EMBAOnlineContract;
import com.power.home.presenter.contract.StudyPlanContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class StudyPlanModule {

    private StudyPlanContract.View mView;

    public StudyPlanModule(StudyPlanContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public StudyPlanContract.View provideView(){
        return mView;
    }
    @Provides
    public StudyPlanContract.IStudyPlanModel provideModel(ApiService apiService){

        return new StudyPlanModel(apiService);
    }
}
