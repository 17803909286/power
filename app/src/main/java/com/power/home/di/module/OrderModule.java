package com.power.home.di.module;


import com.power.home.data.OrderModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.OrderContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class OrderModule {

    private OrderContract.View mView;

    public OrderModule(OrderContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public OrderContract.View provideView() {
        return mView;
    }

    @Provides
    public OrderContract.IOrderModel provideModel(ApiService apiService) {

        return new OrderModel(apiService);
    }
}
