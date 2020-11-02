package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.PayOrderBean;
import com.power.home.ui.widget.BaseView;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface PayOrderContract {

    interface IPayOrderModel{
        Observable<BaseBean<PayOrderBean>> CreateOrder(String amount,String channelId,String payChannel,String topicId,String userId);
    }

    interface View extends BaseView {
        void CreateOrderSuccess(PayOrderBean payOrderBean);
    }

}
