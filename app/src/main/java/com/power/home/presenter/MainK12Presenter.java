package com.power.home.presenter;

import com.power.home.data.bean.MainK12Bean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.MainK12Contract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public class MainK12Presenter extends BasePresenter<MainK12Contract.IMainK12Model, MainK12Contract.View> {

    @Inject
    public MainK12Presenter(MainK12Contract.IMainK12Model iMainK12Model, MainK12Contract.View view) {
        super(iMainK12Model, view);
    }

    public void getK12Data() {
        mModel.getK12Data().compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressSubcriber<MainK12Bean>(mContext, mView, true) {
                    @Override
                    public void onNext(MainK12Bean mainK12Bean) {
                        if (hasView()) {
                            mView.getK12DataSuccess(mainK12Bean);
                        }
                    }
                });
    }

}
