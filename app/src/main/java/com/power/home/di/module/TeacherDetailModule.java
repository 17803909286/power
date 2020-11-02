package com.power.home.di.module;


import com.power.home.data.TeacherDetailModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.TeacherDetailContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class TeacherDetailModule {

    private TeacherDetailContract.View mView;

    public TeacherDetailModule(TeacherDetailContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public TeacherDetailContract.View provideView() {
        return mView;
    }

    @Provides
    public TeacherDetailContract.ITeacherDetailModel provideModel(ApiService apiService) {

        return new TeacherDetailModel(apiService);
    }
}
