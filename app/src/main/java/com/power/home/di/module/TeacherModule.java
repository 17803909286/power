package com.power.home.di.module;


import com.power.home.data.TeacherModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.TeacherContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class TeacherModule {

    private TeacherContract.View mView;

    public TeacherModule(TeacherContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public TeacherContract.View provideView() {
        return mView;
    }

    @Provides
    public TeacherContract.ITeacherModel provideModel(ApiService apiService) {

        return new TeacherModel(apiService);
    }
}
