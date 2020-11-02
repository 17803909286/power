package com.power.home.presenter;

import com.power.home.data.bean.PayOrderBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.PayOrderContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class PayOrderPresenter extends BasePresenter<PayOrderContract.IPayOrderModel, PayOrderContract.View> {

    @Inject
    public PayOrderPresenter(PayOrderContract.IPayOrderModel iPayOrderModel, PayOrderContract.View view) {
        super(iPayOrderModel, view);
    }

    public void CreateOrder(String amount, String channelId, String payChannel, String topicId, String userId) {
        mModel.CreateOrder(amount, channelId, payChannel, topicId, userId).compose(RxHttpReponseCompat.<PayOrderBean>compatResult())
                .subscribe(new ProgressSubcriber<PayOrderBean>(mContext, mView) {
                    @Override
                    public void onNext(PayOrderBean payOrderBean) {
                        if (hasView()) {
                            mView.CreateOrderSuccess(payOrderBean);
                        }
                    }
                });
    }
}
