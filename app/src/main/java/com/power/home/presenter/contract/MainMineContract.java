package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.PersonCenterBean;
import com.power.home.data.bean.UserInfoChildBean;
import com.power.home.ui.widget.BaseView;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface MainMineContract {

    interface IMainMineModel {
        Observable<BaseBean<UserInfoChildBean>> getUserInfo(String userId);

        Observable<BaseBean<PersonCenterBean>> myPage(String userId);
    }

    interface View extends BaseView {
        void getUserInfoSuccess(UserInfoChildBean userInfoChildBean);

        void myPageSuccess(PersonCenterBean personCenterBean);
    }

}
