package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.VipRecordBean;
import com.power.home.ui.widget.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface VipRecordContract {

    interface IVipRecordModel {
        Observable<BaseBean<List<VipRecordBean>>> getVipRecord(int page, int size);

    }

    interface View extends BaseView {
        void getVipRecordSuccess(List<VipRecordBean> vipRecordBeans);
    }

}
