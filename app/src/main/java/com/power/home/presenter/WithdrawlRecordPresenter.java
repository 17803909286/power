package com.power.home.presenter;

import com.power.home.data.bean.WithdrawOutBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.WithdrawlRecordContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class WithdrawlRecordPresenter extends BasePresenter<WithdrawlRecordContract.IWithdrawlRecordModel, WithdrawlRecordContract.View> {

    @Inject
    public WithdrawlRecordPresenter(WithdrawlRecordContract.IWithdrawlRecordModel iWithdrawlRecordModel, WithdrawlRecordContract.View view) {
        super(iWithdrawlRecordModel, view);
    }

    public void getWithdrawRecord(int page, int size) {
        mModel.getWithdrawRecord(page, size).compose(RxHttpReponseCompat.<WithdrawOutBean>compatResult())
                .subscribe(new ProgressSubcriber<WithdrawOutBean>(mContext, mView) {
                    @Override
                    public void onNext(WithdrawOutBean withdrawOutBean) {
                        if (hasView()) {
                            mView.getWithdrawRecordSuccess(withdrawOutBean.getContent());
                        }
                    }
                });
    }
}
