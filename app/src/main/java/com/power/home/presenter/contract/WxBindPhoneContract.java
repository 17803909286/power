package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.UserInfoBean;
import com.power.home.ui.widget.BaseView;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface WxBindPhoneContract {

    interface IWxBindPhoneModel {
        Observable<BaseBean<UserInfoBean>> bindPhone(String code , String phone,String userId);
    }

    interface View extends BaseView {
        void bindPhoneSuccess(UserInfoBean userInfoBean);
    }

}
