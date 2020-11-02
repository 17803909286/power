package com.power.home.presenter;

import com.power.home.data.bean.BankInfoBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.BindBankContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class BindBankPresenter extends BasePresenter<BindBankContract.IBindBankModel, BindBankContract.View> {

    @Inject
    public BindBankPresenter(BindBankContract.IBindBankModel iBindBankModel, BindBankContract.View view) {
        super(iBindBankModel, view);
    }

    public void bindBankCardInfo(String accountName, String bankCardNum, String bankId, String bankName, String identityCardNum, String mobile, String verifyCode) {
        mModel.bindBankCardInfo(accountName, bankCardNum, bankId, bankName, identityCardNum, mobile, verifyCode).compose(RxHttpReponseCompat.<BankInfoBean>compatResult())
                .subscribe(new ProgressSubcriber<BankInfoBean>(mContext, mView) {
                    @Override
                    public void onNext(BankInfoBean bankInfoBean) {
                        if (hasView()) {
                            mView.bindBankCardInfoSuccess(bankInfoBean);
                        }
                    }
                });
    }

}
