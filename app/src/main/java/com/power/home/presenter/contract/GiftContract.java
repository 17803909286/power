package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.GiftBean;
import com.power.home.ui.widget.BaseView;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface GiftContract {

    interface IGiftModel {
        Observable<BaseBean<GiftBean>> activityGift(String dailyId);
    }

    interface View extends BaseView {
        void activityGiftSuccess(GiftBean giftBean);
    }

}
