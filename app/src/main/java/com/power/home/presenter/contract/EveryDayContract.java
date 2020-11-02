package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EveryDayBean;
import com.power.home.ui.widget.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface EveryDayContract {

    interface IEveryDayModel {
        Observable<BaseBean<List<EveryDayBean>>> getEveryDayInfo();
    }

    interface View extends BaseView {
        void getEveryDayInfoSuccess(List<EveryDayBean> everyDayBeans);
    }

}
