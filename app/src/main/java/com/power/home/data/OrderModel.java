package com.power.home.data;


import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.OrderOutBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.OrderContract;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class OrderModel implements OrderContract.IOrderModel {

    private ApiService mApiService;

    public OrderModel(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<OrderOutBean>> getOrderMasterRecord(int page, int size) {
        return mApiService.getOrderMasterRecord(String.valueOf(page),String.valueOf(size),SharePreferencesUtils.getUserId());
    }
}
