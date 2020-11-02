package com.power.home.presenter;

import com.power.home.data.bean.MoneyOutRecordBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.MoneyContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class MoneyPresenter extends BasePresenter<MoneyContract.IMoneyModel, MoneyContract.View> {

    @Inject
    public MoneyPresenter(MoneyContract.IMoneyModel iMoneyModel, MoneyContract.View view) {
        super(iMoneyModel, view);
    }

    public void getMoneyIn(int page, int size, String incomeType) {
        mModel.getMoneyIn(page, size, incomeType).compose(RxHttpReponseCompat.<MoneyOutRecordBean>compatResult())
                .subscribe(new ProgressSubcriber<MoneyOutRecordBean>(mContext, mView) {
                    @Override
                    public void onNext(MoneyOutRecordBean moneyOutRecordBean) {
                        if (hasView()) {
                            mView.getMoneyInSuccess(moneyOutRecordBean.getContent());
                        }
                    }
                });
    }
}
