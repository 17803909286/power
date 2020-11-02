package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.ui.widget.BaseView;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface LogContract {

    interface ILogModel{
        Observable<BaseBean<EmptyBean>> addLog(String msg);
    }

    interface LogView extends BaseView {
        void logSuccess();
    }

}
