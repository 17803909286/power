package com.power.home.di.module;


import com.power.home.data.UploadModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.UploadContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class UploadModule {

    private UploadContract.View mView;

    public UploadModule(UploadContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public UploadContract.View provideView() {
        return mView;
    }

    @Provides
    public UploadContract.IUploadModel provideModel(ApiService apiService) {

        return new UploadModel(apiService);
    }
}
