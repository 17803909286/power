package com.power.home.di.module;


import com.power.home.data.InviteFriendModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.InviteFriendContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class InviteFriendlModule {

    private InviteFriendContract.View mView;

    public InviteFriendlModule(InviteFriendContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public InviteFriendContract.View provideView() {
        return mView;
    }

    @Provides
    public InviteFriendContract.IInviteFriendModel provideModel(ApiService apiService) {

        return new InviteFriendModel(apiService);
    }
}
