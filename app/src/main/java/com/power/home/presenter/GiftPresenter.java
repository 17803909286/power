package com.power.home.presenter;

import com.power.home.data.Optional;
import com.power.home.data.bean.GiftBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.GiftContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class GiftPresenter extends BasePresenter<GiftContract.IGiftModel, GiftContract.View> {

    @Inject
    public GiftPresenter(GiftContract.IGiftModel iGiftModel, GiftContract.View view) {
        super(iGiftModel, view);
    }

    public void activityGift(String dailyId) {
        mModel.activityGift(dailyId).compose(RxHttpReponseCompat.<GiftBean>compatResult2())
                .subscribe(new ProgressSubcriber<Optional<GiftBean>>(mContext, mView) {
                    @Override
                    public void onNext(Optional<GiftBean> optional) {
                        if (hasView()) {
                            mView.activityGiftSuccess(optional.get());
                        }
                    }
                });
    }
}
