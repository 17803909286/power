package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.TeamInfoBean;
import com.power.home.ui.widget.BaseView;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface TeamInfoContract {

    interface ITeamInfoModel {
        Observable<BaseBean<TeamInfoBean>> getTeamInfo();
    }

    interface View extends BaseView {
        void getTeamInfoSuccess(TeamInfoBean teamInfoBean);
    }

}
