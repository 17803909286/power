package com.power.home.common.util;

import com.power.home.App;
import com.power.home.data.bean.PayOrderBean;
import com.power.home.di.component.DaggerPayOrderComponent;
import com.power.home.di.module.PayOrderModule;
import com.power.home.presenter.PayOrderPresenter;
import com.power.home.presenter.contract.PayOrderContract;
import com.power.home.wxapi.WxPayUtil;

import javax.inject.Inject;

/**
 * Created by XWL on 2020/3/27.
 * Description:
 */
public class OrderUitils implements PayOrderContract.View  {

    @Inject
    PayOrderPresenter payOrderPresenter;


    public OrderUitils() {
        DaggerPayOrderComponent.builder().appComponent(App.getApplication().getAppComponent())
                .payOrderModule(new PayOrderModule(this))
                .build().inject(this);
    }

    public void CreateOrder(String amount,String channelId,String payChannel,String topicId,String userId){

        payOrderPresenter.CreateOrder(amount,channelId,payChannel,topicId,userId);

    }


    @Override
    public void CreateOrderSuccess(PayOrderBean payOrderBean) {
        new WxPayUtil(payOrderBean);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {
        UIUtil.showToastSafe(msg);
    }

    @Override
    public void dismissLoading() {

    }
}
