package com.power.home.di.module;


import com.power.home.data.ConvertModel;
import com.power.home.data.ConvertRecordModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.ConvertContract;
import com.power.home.presenter.contract.ConvertRecordContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class ConvertRecordModule {

    private ConvertRecordContract.View mView;

    public ConvertRecordModule(ConvertRecordContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public ConvertRecordContract.View provideView() {
        return mView;
    }

    @Provides
    public ConvertRecordContract.IConvertRecordModel provideModel(ApiService apiService) {

        return new ConvertRecordModel(apiService);
    }
}
