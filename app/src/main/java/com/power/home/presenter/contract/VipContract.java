package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.VipBean;
import com.power.home.ui.widget.BaseView;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface VipContract {

    interface IVipModel {
        Observable<BaseBean<VipBean>> getVipInfo();
    }

    interface View extends BaseView {
        void getVipInfoSuccess(VipBean vipBean);
    }

}
