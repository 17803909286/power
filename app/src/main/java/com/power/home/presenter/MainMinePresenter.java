package com.power.home.presenter;

import com.power.home.data.bean.PersonCenterBean;
import com.power.home.data.bean.UserInfoChildBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.MainMineContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class MainMinePresenter extends BasePresenter<MainMineContract.IMainMineModel, MainMineContract.View> {

    @Inject
    public MainMinePresenter(MainMineContract.IMainMineModel iMainMineModel, MainMineContract.View view) {
        super(iMainMineModel, view);
    }

    public void myPage(String userId) {
        mModel.myPage(userId).compose(RxHttpReponseCompat.<PersonCenterBean>compatResult())
                .subscribe(new ProgressSubcriber<PersonCenterBean>(mContext, mView, true) {
                    @Override
                    public void onNext(PersonCenterBean personCenterBean) {
                        if (hasView()) {
                            mView.myPageSuccess(personCenterBean);
                        }
                    }
                });
    }

    public void getUserInfo(String userId) {
        mModel.getUserInfo(userId).compose(RxHttpReponseCompat.<UserInfoChildBean>compatResult())
                .subscribe(new ProgressSubcriber<UserInfoChildBean>(mContext, mView, true) {
                    @Override
                    public void onNext(UserInfoChildBean userInfoChildBean) {
                        if (hasView()) {
                            mView.getUserInfoSuccess(userInfoChildBean);
                        }
                    }
                });
    }

}
