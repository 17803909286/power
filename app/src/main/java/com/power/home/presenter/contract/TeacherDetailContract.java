package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.ChampDetailBean;
import com.power.home.ui.widget.BaseView;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface TeacherDetailContract {

    interface ITeacherDetailModel {
        Observable<BaseBean<ChampDetailBean>> getChampDetails(String id, int page, int size);
    }

    interface View extends BaseView {
        void getChampDetailsSuccess(ChampDetailBean champDetailBean);
    }

}
