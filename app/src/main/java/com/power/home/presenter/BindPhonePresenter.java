package com.power.home.presenter;

import com.power.home.data.Optional;
import com.power.home.data.bean.EmptyBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.BindPhoneContract;
import com.power.home.presenter.contract.NickNameContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class BindPhonePresenter extends BasePresenter<BindPhoneContract.IBindPhoneModel, BindPhoneContract.View> {

    @Inject
    public BindPhonePresenter(BindPhoneContract.IBindPhoneModel iBindPhoneModel, BindPhoneContract.View view) {
        super(iBindPhoneModel, view);
    }

    public void changeMobile(String code, String newPhone, String oldPhone) {
        mModel.changeMobile(code, newPhone, oldPhone).compose(RxHttpReponseCompat.<EmptyBean>compatResult2())
                .subscribe(new ProgressSubcriber<Optional<EmptyBean>>(mContext, mView) {
                    @Override
                    public void onNext(Optional<EmptyBean> emptyBeanOptional) {
                        if (hasView()) {
                            mView.changeMobileSuccess();
                        }
                    }
                });
    }
}
