package com.power.home.presenter;

import com.power.home.data.bean.EmptyBean;
import com.power.home.data.bean.UserInfoBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.LoginContract;
import com.power.home.presenter.contract.WxLoginContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class WxLoginPresenter extends BasePresenter<WxLoginContract.IWxLoginModel, WxLoginContract.View> {

    @Inject
    public WxLoginPresenter(WxLoginContract.IWxLoginModel iWxLoginModel, WxLoginContract.View view) {
        super(iWxLoginModel, view);
    }

    public void weChatLogin(String code) {
        mModel.weChatLogin(code).compose(RxHttpReponseCompat.<UserInfoBean>compatResult())
                .subscribe(new ProgressSubcriber<UserInfoBean>(mContext, mView) {
                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        if (hasView()) {
                            mView.weChatLoginSuccess(userInfoBean);
                        }
                    }
                });
    }

}
