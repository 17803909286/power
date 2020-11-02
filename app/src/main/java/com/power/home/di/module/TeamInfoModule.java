package com.power.home.di.module;


import com.power.home.data.TeamInfoModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.TeamInfoContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class TeamInfoModule {

    private TeamInfoContract.View mView;

    public TeamInfoModule(TeamInfoContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public TeamInfoContract.View provideView() {
        return mView;
    }

    @Provides
    public TeamInfoContract.ITeamInfoModel provideModel(ApiService apiService) {

        return new TeamInfoModel(apiService);
    }
}
