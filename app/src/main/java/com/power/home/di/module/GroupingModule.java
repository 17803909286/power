package com.power.home.di.module;


import com.power.home.data.EMBAOnlineModel;
import com.power.home.data.GroupingModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.EMBAOnlineContract;
import com.power.home.presenter.contract.GroupingContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class GroupingModule {

    private GroupingContract.View mView;

    public GroupingModule(GroupingContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public GroupingContract.View provideView(){
        return mView;
    }
    @Provides
    public GroupingContract.IGroupingModel provideModel(ApiService apiService){

        return new GroupingModel(apiService);
    }
}
