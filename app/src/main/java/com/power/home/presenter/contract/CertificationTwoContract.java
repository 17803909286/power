package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.ui.widget.BaseView;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface CertificationTwoContract {

    interface ICertificationTwoModel {
        Observable<BaseBean<EmptyBean>> realNameAuthSave(String authId, String mobile, String verifyCode);
    }

    interface View extends BaseView {
        void saveSuccess();
    }

}
