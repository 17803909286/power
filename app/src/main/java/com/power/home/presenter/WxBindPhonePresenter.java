package com.power.home.presenter;

import com.power.home.data.bean.EmptyBean;
import com.power.home.data.bean.UserInfoBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.WxBindPhoneContract;
import com.power.home.presenter.contract.WxLoginContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class WxBindPhonePresenter extends BasePresenter<WxBindPhoneContract.IWxBindPhoneModel, WxBindPhoneContract.View> {

    @Inject
    public WxBindPhonePresenter(WxBindPhoneContract.IWxBindPhoneModel iWxBindPhoneModel, WxBindPhoneContract.View view) {
        super(iWxBindPhoneModel, view);
    }

    public void bindPhone(String code,String phone,String userId) {
        mModel.bindPhone(code,phone,userId).compose(RxHttpReponseCompat.<UserInfoBean>compatResult())
                .subscribe(new ProgressSubcriber<UserInfoBean>(mContext, mView) {
                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        if (hasView()) {
                            mView.bindPhoneSuccess(userInfoBean);
                        }
                    }
                });


    }


}
