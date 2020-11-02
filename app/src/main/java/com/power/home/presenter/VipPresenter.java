package com.power.home.presenter;

import com.power.home.data.bean.VipBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.VipContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class VipPresenter extends BasePresenter<VipContract.IVipModel, VipContract.View> {

    @Inject
    public VipPresenter(VipContract.IVipModel iVipModel, VipContract.View view) {
        super(iVipModel, view);
    }

    public void getVipInfo() {
        mModel.getVipInfo().compose(RxHttpReponseCompat.<VipBean>compatResult())
                .subscribe(new ProgressSubcriber<VipBean>(mContext, mView) {
                    @Override
                    public void onNext(VipBean vipBean) {
                        if (hasView()) {
                            mView.getVipInfoSuccess(vipBean);
                        }
                    }
                });
    }

}
