package com.power.home.di.module;


import com.power.home.data.MessageModel;
import com.power.home.data.WithdrawlRecordModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.MessageContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class MessageModule {

    private MessageContract.View mView;

    public MessageModule(MessageContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public MessageContract.View provideView() {
        return mView;
    }

    @Provides
    public MessageContract.IMessageModel provideModel(ApiService apiService) {
        return new MessageModel(apiService);
    }
}
