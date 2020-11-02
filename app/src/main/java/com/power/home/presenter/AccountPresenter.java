package com.power.home.presenter;

import com.power.home.data.bean.BankInfoBean;
import com.power.home.data.bean.GoShareBean;
import com.power.home.data.bean.UserAssetsBean;
import com.power.home.data.bean.WithdrawInfoBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.AccountContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class AccountPresenter extends BasePresenter<AccountContract.IAccountModel, AccountContract.View> {

    @Inject
    public AccountPresenter(AccountContract.IAccountModel iAccountModel, AccountContract.View view) {
        super(iAccountModel, view);
    }

    public void getAppUserAssets() {
        mModel.getAppUserAssets().compose(RxHttpReponseCompat.<UserAssetsBean>compatResult())
                .subscribe(new ProgressSubcriber<UserAssetsBean>(mContext, mView) {
                    @Override
                    public void onNext(UserAssetsBean userAssetsBean) {
                        if (hasView()) {
                            mView.getAppUserAssetsSuccess(userAssetsBean);
                        }
                    }
                });
    }

    public void getWithdrawInfo() {
        mModel.getWithdrawInfo().compose(RxHttpReponseCompat.<WithdrawInfoBean>compatResult())
                .subscribe(new ProgressSubcriber<WithdrawInfoBean>(mContext, mView) {
                    @Override
                    public void onNext(WithdrawInfoBean withdrawInfoBean) {
                        if (hasView()) {
                            mView.getWithdrawInfoSuccess(withdrawInfoBean);
                        }
                    }
                });
    }

    public void goShare() {
        mModel.goShare().compose(RxHttpReponseCompat.<GoShareBean>compatResult())
                .subscribe(new ProgressSubcriber<GoShareBean>(mContext, mView) {
                    @Override
                    public void onNext(GoShareBean goShareBean) {
                        if (hasView()) {
                            mView.goShareSuccess(goShareBean);
                        }
                    }
                });
    }

    public void getBankCardInfo() {
        mModel.getBankCardInfo().compose(RxHttpReponseCompat.<BankInfoBean>compatResult())
                .subscribe(new ProgressSubcriber<BankInfoBean>(mContext, mView) {
                    @Override
                    public void onNext(BankInfoBean bankInfoBean) {
                        if (hasView()) {
                            mView.getBankCardInfoSuccess(bankInfoBean);
                        }
                    }
                });
    }
}
