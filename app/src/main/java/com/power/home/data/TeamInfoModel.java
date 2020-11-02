package com.power.home.data;


import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.TeamInfoBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.TeamInfoContract;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class TeamInfoModel implements TeamInfoContract.ITeamInfoModel {

    private ApiService mApiService;

    public TeamInfoModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<TeamInfoBean>> getTeamInfo() {
        return mApiService.getTeamInfo(SharePreferencesUtils.getUserId());
    }
}
