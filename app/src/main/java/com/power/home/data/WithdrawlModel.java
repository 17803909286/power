package com.power.home.data;


import com.power.home.common.util.ParamUtil;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.WithdrawlContract;

import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class WithdrawlModel implements WithdrawlContract.IWithdrawlModel {

    private ApiService mApiService;

    public WithdrawlModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<EmptyBean>> withdraw(String accountBalance,String applyAmount,String bankId) {
        TreeMap<String, Object> map = ParamUtil.getParma();
        map.put("accountBalance", accountBalance);
        map.put("applyAmount", applyAmount);
        map.put("bankId", bankId);
        map.put("userId", SharePreferencesUtils.getUserId());
        return mApiService.withdraw(ParamUtil.getBody(map));
    }
}
