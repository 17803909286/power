package com.power.home.presenter;

import com.power.home.data.bean.AllCourseBean;
import com.power.home.data.bean.PayChannelBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.AllCourseContract;
import com.power.home.presenter.contract.ChoseChannelContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public class ChoseChannelPresenter extends BasePresenter<ChoseChannelContract.IChoseChannelModel, ChoseChannelContract.View> {

    @Inject
    public ChoseChannelPresenter(ChoseChannelContract.IChoseChannelModel iChoseChannelModel, ChoseChannelContract.View view) {
        super(iChoseChannelModel, view);
    }

    public void getPayChannel(String payPlatform) {
        mModel.getPayChannel(payPlatform).compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressSubcriber<List<PayChannelBean>>(mContext, mView) {
                    @Override
                    public void onNext(List<PayChannelBean> payChannelBeans) {
                        if (hasView()) {
                            mView.getPayChanneleSuces(payChannelBeans);
                        }
                    }
                });
    }

}
