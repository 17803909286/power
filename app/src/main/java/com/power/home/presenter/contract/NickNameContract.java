package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.ui.widget.BaseView;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface NickNameContract {

    interface INickNameModel {
        Observable<BaseBean<String>> saveInfo(String avatar, String nickName,String sex,String birthday,String cityCode,String provinceCode);
    }

    interface View extends BaseView {
        void modfiySuccess();
    }

}
