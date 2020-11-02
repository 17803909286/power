package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.ConvertRecordBean;
import com.power.home.data.bean.ConvertRecordOutBean;
import com.power.home.ui.widget.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface ConvertRecordContract {

    interface IConvertRecordModel {
        Observable<BaseBean<ConvertRecordOutBean>> getCodeMarketRecord(int page, int size);

    }

    interface View extends BaseView {
        void getCodeMarketRecordSuccess(List<ConvertRecordBean> convertRecordBeans);
    }

}
