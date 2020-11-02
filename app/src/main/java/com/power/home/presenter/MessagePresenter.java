package com.power.home.presenter;

import com.power.home.data.bean.MessageOutBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.MessageContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class MessagePresenter extends BasePresenter<MessageContract.IMessageModel, MessageContract.View> {

    @Inject
    public MessagePresenter(MessageContract.IMessageModel iMessageModel, MessageContract.View view) {
        super(iMessageModel, view);
    }

    public void getUserNotify(int page, int size, String source) {
        mModel.getUserNotify(page, size,source).compose(RxHttpReponseCompat.<MessageOutBean>compatResult())
                .subscribe(new ProgressSubcriber<MessageOutBean>(mContext, mView) {
                    @Override
                    public void onNext(MessageOutBean messageOutBean) {
                        if (hasView()) {
                            mView.getUserNotifySuccess(messageOutBean.getContent());
                        }
                    }
                });
    }
}
