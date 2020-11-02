package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.MessageBean;
import com.power.home.data.bean.MessageOutBean;
import com.power.home.ui.widget.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface MessageContract {

    interface IMessageModel {
        Observable<BaseBean<MessageOutBean>> getUserNotify(int page, int size, String source);

    }

    interface View extends BaseView {
        void getUserNotifySuccess(List<MessageBean> messageBeans);
    }

}
