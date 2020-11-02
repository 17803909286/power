package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.InviteRecordBean;
import com.power.home.ui.widget.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface InviteRecordContract {

    interface IInviteRecordModel {
        Observable<BaseBean<List<InviteRecordBean>>> getInviteRecord(int page, int size, String queryType);
    }

    interface View extends BaseView {
        void getInviteRecordSuccess(List<InviteRecordBean> inviteRecordBeans);
    }

}
