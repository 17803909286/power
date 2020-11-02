package com.power.home.data;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.BusinessGrowthBean;
import com.power.home.data.bean.EMBAOnlineBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.EMBAOnlineContract;
import com.power.home.presenter.contract.GroupingContract;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class GroupingModel implements GroupingContract.IGroupingModel {

    private ApiService mApiService;

    public GroupingModel(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<BusinessGrowthBean>> getGroupingData() {
        return mApiService.getGroupingData();
    }
}
