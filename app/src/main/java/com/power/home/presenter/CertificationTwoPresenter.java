package com.power.home.presenter;

import com.power.home.data.Optional;
import com.power.home.data.bean.EmptyBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.CertificationTwoContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class CertificationTwoPresenter extends BasePresenter<CertificationTwoContract.ICertificationTwoModel, CertificationTwoContract.View> {

    @Inject
    public CertificationTwoPresenter(CertificationTwoContract.ICertificationTwoModel iCertificationTwoModel, CertificationTwoContract.View view) {
        super(iCertificationTwoModel, view);
    }

    public void realNameAuthSave(String authId, String mobile, String verifyCode) {
        mModel.realNameAuthSave(authId, mobile, verifyCode).compose(RxHttpReponseCompat.<EmptyBean>compatResult2())
                .subscribe(new ProgressSubcriber<Optional<EmptyBean>>(mContext, mView) {
                    @Override
                    public void onNext(Optional<EmptyBean> emptyBeanOptional) {
                        if (hasView()) {
                            mView.saveSuccess();
                        }
                    }
                });
    }
}
