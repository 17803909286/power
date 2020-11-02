package com.power.home.di.module;


import com.power.home.data.ConvertModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.ConvertContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class ConvertModule {

    private ConvertContract.View mView;

    public ConvertModule(ConvertContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public ConvertContract.View provideView() {
        return mView;
    }

    @Provides
    public ConvertContract.IConvertModel provideModel(ApiService apiService) {

        return new ConvertModel(apiService);
    }
}
