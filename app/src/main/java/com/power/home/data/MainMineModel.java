package com.power.home.data;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.PersonCenterBean;
import com.power.home.data.bean.UserInfoBean;
import com.power.home.data.bean.UserInfoChildBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.MainMineContract;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class MainMineModel implements MainMineContract.IMainMineModel {

    private ApiService mApiService;

    public MainMineModel(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<UserInfoChildBean>> getUserInfo(String userId) {
        return mApiService.getUserInfo(userId);
    }

    @Override
    public Observable<BaseBean<PersonCenterBean>> myPage(String userId) {
        return mApiService.myPage(userId);
    }
}
