package com.power.home.di.module;


import com.power.home.data.UploadIdCardModel;
import com.power.home.data.UploadModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.UploadIdCardContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class UploadIdCardModule {

    private UploadIdCardContract.View mView;

    public UploadIdCardModule(UploadIdCardContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public UploadIdCardContract.View provideView() {
        return mView;
    }

    @Provides
    public UploadIdCardContract.IUploadIdCardModel provideModel(ApiService apiService) {

        return new UploadIdCardModel(apiService);
    }
}
