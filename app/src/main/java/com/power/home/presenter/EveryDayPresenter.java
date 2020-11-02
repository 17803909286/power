package com.power.home.presenter;

import com.power.home.data.bean.EveryDayBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.EveryDayContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class EveryDayPresenter extends BasePresenter<EveryDayContract.IEveryDayModel, EveryDayContract.View> {

    @Inject
    public EveryDayPresenter(EveryDayContract.IEveryDayModel iEveryDayModel, EveryDayContract.View view) {
        super(iEveryDayModel, view);
    }

    public void getEveryDayInfo() {
        mModel.getEveryDayInfo().compose(RxHttpReponseCompat.<List<EveryDayBean>>compatResult())
                .subscribe(new ProgressSubcriber<List<EveryDayBean>>(mContext, mView) {
                    @Override
                    public void onNext(List<EveryDayBean> everyDayBeans) {
                        if (hasView()) {
                            mView.getEveryDayInfoSuccess(everyDayBeans);
                        }
                    }
                });
    }

}
