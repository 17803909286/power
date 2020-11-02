package com.power.home.presenter;

import com.power.home.data.bean.UserInfoBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.LoginContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel, LoginContract.View> {

    @Inject
    public LoginPresenter(LoginContract.ILoginModel iLoginModel, LoginContract.View view) {
        super(iLoginModel, view);
    }

    public void login(String code, String phone) {
        mModel.login(code, phone).compose(RxHttpReponseCompat.<UserInfoBean>compatResult())
                .subscribe(new ProgressSubcriber<UserInfoBean>(mContext, mView) {
                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        if (hasView()) {
                            mView.loginSuccess(userInfoBean);
                        }
                    }
                });
    }

}
