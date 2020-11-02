package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.UserInfoBean;
import com.power.home.ui.widget.BaseView;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface LoginContract {

    interface ILoginModel {
        Observable<BaseBean<UserInfoBean>> login(String code, String phone);
    }

    interface View extends BaseView {
        void loginSuccess(UserInfoBean userInfoBean);
    }

}
