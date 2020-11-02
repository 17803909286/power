package com.power.home.presenter;

import com.power.home.data.Optional;
import com.power.home.data.bean.EmptyBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.WithdrawlContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class WithdrawlPresenter extends BasePresenter<WithdrawlContract.IWithdrawlModel, WithdrawlContract.View> {

    @Inject
    public WithdrawlPresenter(WithdrawlContract.IWithdrawlModel iWithdrawlModel, WithdrawlContract.View view) {
        super(iWithdrawlModel, view);
    }

    public void withdraw(String accountBalance, String applyAmount, String bankId) {
        mModel.withdraw(accountBalance, applyAmount, bankId).compose(RxHttpReponseCompat.<EmptyBean>compatResult2())
                .subscribe(new ProgressSubcriber<Optional<EmptyBean>>(mContext, mView) {
                    @Override
                    public void onNext(Optional<EmptyBean> emptyBeanOptional) {
                        if (hasView()) {
                            mView.withdrawSuccess();
                        }
                    }
                });
    }
}
