package com.power.home.presenter;

import com.power.home.data.Optional;
import com.power.home.data.bean.EmptyBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.ConvertContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class ConvertPresenter extends BasePresenter<ConvertContract.IConvertModel, ConvertContract.View> {

    @Inject
    public ConvertPresenter(ConvertContract.IConvertModel iConvertModel, ConvertContract.View view) {
        super(iConvertModel, view);
    }

    public void codeMarket(String exchangeCode) {
        mModel.codeMarket(exchangeCode).compose(RxHttpReponseCompat.<String>compatResult2())
                .subscribe(new ProgressSubcriber<Optional<String>>(mContext, mView) {
                    @Override
                    public void onNext(Optional<String> optional) {
                        if (hasView()) {
                            mView.codeMarketSuccess(optional.get());
                        }
                    }
                });
    }
}
