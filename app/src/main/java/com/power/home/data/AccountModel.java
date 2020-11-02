package com.power.home.data;


import com.power.home.BuildConfig;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.data.bean.BankInfoBean;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.GoShareBean;
import com.power.home.data.bean.UserAssetsBean;
import com.power.home.data.bean.WithdrawInfoBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.AccountContract;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class AccountModel implements AccountContract.IAccountModel {

    private ApiService mApiService;

    public AccountModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<UserAssetsBean>> getAppUserAssets() {
        return mApiService.getAppUserAssets(SharePreferencesUtils.getUserId());
    }

    @Override
    public Observable<BaseBean<WithdrawInfoBean>> getWithdrawInfo() {
        return mApiService.getWithdrawInfo(SharePreferencesUtils.getUserId());
    }

    @Override
    public Observable<BaseBean<GoShareBean>> goShare() {
        return mApiService.goShare();
    }

    @Override
    public Observable<BaseBean<BankInfoBean>> getBankCardInfo() {
        return mApiService.getBankCardInfo(SharePreferencesUtils.getUserId());
    }
}
