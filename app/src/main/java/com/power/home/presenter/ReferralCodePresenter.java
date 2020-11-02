package com.power.home.presenter;

import com.power.home.data.Optional;
import com.power.home.data.bean.EmptyBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.ReferralCodeContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class ReferralCodePresenter extends BasePresenter<ReferralCodeContract.IReferralCodeModel, ReferralCodeContract.View> {

    @Inject
    public ReferralCodePresenter(ReferralCodeContract.IReferralCodeModel iReferralCodeModel, ReferralCodeContract.View view) {
        super(iReferralCodeModel, view);
    }

    public void setUserInvitedCode(String code) {
        mModel.setUserInvitedCode(code).compose(RxHttpReponseCompat.<EmptyBean>compatResult2())
                .subscribe(new ProgressSubcriber<Optional<EmptyBean>>(mContext, mView) {
                    @Override
                    public void onNext(Optional<EmptyBean> emptyBeanOptional) {
                        if (hasView()) {
                            mView.setUserInvitedCodeSuccess();
                        }
                    }
                });
    }
}
