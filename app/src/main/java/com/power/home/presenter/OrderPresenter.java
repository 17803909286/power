package com.power.home.presenter;

import com.power.home.data.bean.OrderOutBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.OrderContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class OrderPresenter extends BasePresenter<OrderContract.IOrderModel, OrderContract.View> {

    @Inject
    public OrderPresenter(OrderContract.IOrderModel iOrderModel, OrderContract.View view) {
        super(iOrderModel, view);
    }

    public void getOrderMasterRecord(int page, int size) {
        mModel.getOrderMasterRecord(page, size).compose(RxHttpReponseCompat.<OrderOutBean>compatResult())
                .subscribe(new ProgressSubcriber<OrderOutBean>(mContext, mView) {
                    @Override
                    public void onNext(OrderOutBean orderOutBean) {
                        if (hasView()) {
                            mView.getOrderMasterRecord(orderOutBean.getContent());
                        }
                    }
                });
    }
}
