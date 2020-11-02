package com.power.home.di.module;


import com.power.home.data.LogModel;
import com.power.home.data.PayOrderModel;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.LogContract;
import com.power.home.presenter.contract.PayOrderContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
@Module
public class PayOrderModule {

    private PayOrderContract.View mView;

    public PayOrderModule(PayOrderContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public PayOrderContract.View provideView(){
        return mView;
    }
    @Provides
    public PayOrderContract.IPayOrderModel provideModel(ApiService apiService){

        return new PayOrderModel(apiService);
    }
}
