package com.power.home.presenter;

import com.power.home.data.bean.AllCourseBean;
import com.power.home.data.bean.ReceiveMaterialBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.BasePresenter;
import com.power.home.presenter.contract.ReceiveMaterialContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public class ReceiverMaterialPresenter extends BasePresenter<ReceiveMaterialContract.IReceiveMaterialModel, ReceiveMaterialContract.View> {

    @Inject
    public ReceiverMaterialPresenter(ReceiveMaterialContract.IReceiveMaterialModel iReceiveMaterialModel, ReceiveMaterialContract.View view) {
        super(iReceiveMaterialModel, view);
    }

    public void receiveMaterial() {
        mModel.receiveMaterial().compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressSubcriber<ReceiveMaterialBean>(mContext, mView) {
                    @Override
                    public void onNext(ReceiveMaterialBean receiveMaterialBean) {
                        if (hasView()) {
                            mView.receiveMaterialSuces(receiveMaterialBean);
                        }
                    }
                });
    }

}
