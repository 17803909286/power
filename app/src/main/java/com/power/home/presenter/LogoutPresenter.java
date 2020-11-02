package com.power.home.presenter;

import com.power.home.data.Optional;
import com.power.home.data.bean.EmptyBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.LogoutContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class LogoutPresenter extends BasePresenter<LogoutContract.ILogoutModel, LogoutContract.View> {

    @Inject
    public LogoutPresenter(LogoutContract.ILogoutModel iLogoutModel, LogoutContract.View view) {
        super(iLogoutModel, view);
    }

    public void logout() {
        mModel.logout().compose(RxHttpReponseCompat.<EmptyBean>compatResult2())
                .subscribe(new ProgressSubcriber<Optional<EmptyBean>>(mContext,mView) {
                    @Override
                    public void onNext(Optional<EmptyBean> emptyBeanOptional) {
                        if(hasView()){
                            mView.logoutSuccess();
                        }
                    }
                });
    }

}
