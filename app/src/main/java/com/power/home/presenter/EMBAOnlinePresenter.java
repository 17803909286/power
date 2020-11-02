package com.power.home.presenter;

import com.power.home.data.bean.EMBAOnlineBean;
import com.power.home.data.bean.MainHomeBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.BasePresenter;
import com.power.home.presenter.contract.EMBAOnlineContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public class EMBAOnlinePresenter extends BasePresenter<EMBAOnlineContract.IEMBAOnlineModel, EMBAOnlineContract.View> {

    @Inject
    public EMBAOnlinePresenter(EMBAOnlineContract.IEMBAOnlineModel iembaOnlineModel, EMBAOnlineContract.View view) {
        super(iembaOnlineModel, view);
    }

    public void getEmbaData() {
        mModel.getEmbaData().compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressSubcriber<EMBAOnlineBean>(mContext, mView) {
                    @Override
                    public void onNext(EMBAOnlineBean embaOnlineBean) {
                        if (hasView()) {
                            mView.getEmbaDataSuces(embaOnlineBean);
                        }
                    }
                });
    }

}
