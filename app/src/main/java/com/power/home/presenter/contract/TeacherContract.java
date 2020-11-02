package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.ChampGuideBean;
import com.power.home.ui.widget.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface TeacherContract {

    interface ITeacherModel {
        Observable<BaseBean<List<ChampGuideBean>>> getChamp();
    }

    interface View extends BaseView {
        void getChampSuccess(List<ChampGuideBean> champGuideBeans);
    }

}
