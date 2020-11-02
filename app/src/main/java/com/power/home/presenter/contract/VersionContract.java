package com.power.home.presenter.contract;

import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.VersionBean;
import com.power.home.ui.widget.BaseView;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public interface VersionContract {

    interface IVersionModel {
        Observable<BaseBean<VersionBean>> getVersion();

    }

    interface View extends BaseView {
        void getVersionSucess(VersionBean versionBean);
    }
}
