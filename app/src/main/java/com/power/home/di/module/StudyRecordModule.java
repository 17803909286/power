package com.power.home.di.module;


import com.power.home.data.StudyRecordModel;
import com.power.home.data.WithdrawlRecordModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.StudyRecordContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class StudyRecordModule {

    private StudyRecordContract.View mView;

    public StudyRecordModule(StudyRecordContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public StudyRecordContract.View provideView() {
        return mView;
    }

    @Provides
    public StudyRecordContract.IStudyRecordModel provideModel(ApiService apiService) {

        return new StudyRecordModel(apiService);
    }
}
