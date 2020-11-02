package com.power.home.presenter.contract;

import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.MainHomeBean;
import com.power.home.data.bean.MainK12Bean;
import com.power.home.ui.widget.BaseView;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public interface MainK12Contract {

    interface IMainK12Model {

        Observable<BaseBean<MainK12Bean>> getK12Data();
    }

    interface View extends BaseView {

        void getK12DataSuccess(MainK12Bean mainK12Bean);
    }
}
