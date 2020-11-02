package com.power.home.di.module;


import com.power.home.data.InviteRecordModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.InviteRecordContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class InviteRecordModule {

    private InviteRecordContract.View mView;

    public InviteRecordModule(InviteRecordContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public InviteRecordContract.View provideView() {
        return mView;
    }

    @Provides
    public InviteRecordContract.IInviteRecordModel provideModel(ApiService apiService) {

        return new InviteRecordModel(apiService);
    }
}
