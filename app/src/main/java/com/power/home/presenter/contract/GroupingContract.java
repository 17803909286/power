package com.power.home.presenter.contract;

import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.BusinessGrowthBean;
import com.power.home.data.bean.EMBAOnlineBean;
import com.power.home.ui.widget.BaseView;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public interface GroupingContract {

    interface IGroupingModel {

        Observable<BaseBean<BusinessGrowthBean>> getGroupingData();

    }

    interface View extends BaseView {

        void getGroupingSuces(BusinessGrowthBean businessGrowthBean);

    }
}
