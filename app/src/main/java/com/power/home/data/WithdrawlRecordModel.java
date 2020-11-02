package com.power.home.data;


import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.WithdrawOutBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.WithdrawlRecordContract;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class WithdrawlRecordModel implements WithdrawlRecordContract.IWithdrawlRecordModel {

    private ApiService mApiService;

    public WithdrawlRecordModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<WithdrawOutBean>> getWithdrawRecord(int page, int size) {
        return mApiService.getWithdrawRecord(String.valueOf(page),String.valueOf(size),SharePreferencesUtils.getUserId());
    }
}
