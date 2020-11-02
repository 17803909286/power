package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.WithDrawalRecordBean;
import com.power.home.data.bean.WithdrawOutBean;
import com.power.home.ui.widget.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface WithdrawlRecordContract {

    interface IWithdrawlRecordModel {
        Observable<BaseBean<WithdrawOutBean>> getWithdrawRecord(int page, int size);

    }

    interface View extends BaseView {
        void getWithdrawRecordSuccess(List<WithDrawalRecordBean> withDrawalRecordBeans);
    }

}
