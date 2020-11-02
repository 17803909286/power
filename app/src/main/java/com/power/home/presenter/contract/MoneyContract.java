package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.MoneyInRecordBean;
import com.power.home.data.bean.MoneyOutRecordBean;
import com.power.home.ui.widget.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface MoneyContract {

    interface IMoneyModel {
        Observable<BaseBean<MoneyOutRecordBean>> getMoneyIn(int page, int size, String incomeType);
    }

    interface View extends BaseView {
        void getMoneyInSuccess(List<MoneyInRecordBean> moneyInRecordBeans);
    }

}
