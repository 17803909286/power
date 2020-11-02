package com.power.home.presenter;

import com.power.home.data.bean.ConvertRecordOutBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.ConvertRecordContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class ConvertRecordPresenter extends BasePresenter<ConvertRecordContract.IConvertRecordModel, ConvertRecordContract.View> {

    @Inject
    public ConvertRecordPresenter(ConvertRecordContract.IConvertRecordModel iConvertRecordModel, ConvertRecordContract.View view) {
        super(iConvertRecordModel, view);
    }

    public void getCodeMarketRecord(int page, int size) {
        mModel.getCodeMarketRecord(page, size).compose(RxHttpReponseCompat.<ConvertRecordOutBean>compatResult())
                .subscribe(new ProgressSubcriber<ConvertRecordOutBean>(mContext, mView) {
                    @Override
                    public void onNext(ConvertRecordOutBean convertRecordOutBean) {
                        if (hasView()) {
                            mView.getCodeMarketRecordSuccess(convertRecordOutBean.getContent());
                        }
                    }
                });
    }
}
