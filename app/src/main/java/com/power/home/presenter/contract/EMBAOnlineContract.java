package com.power.home.presenter.contract;

import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EMBAOnlineBean;
import com.power.home.data.bean.MainHomeBean;
import com.power.home.ui.widget.BaseView;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public interface EMBAOnlineContract {

    interface IEMBAOnlineModel {

        Observable<BaseBean<EMBAOnlineBean>> getEmbaData();

    }

    interface View extends BaseView {

        void getEmbaDataSuces(EMBAOnlineBean embaOnlineBean);

    }
}
