package com.power.home.presenter.contract;

import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.HomePagePopupData;
import com.power.home.data.bean.MainHomeBean;
import com.power.home.ui.widget.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public interface MainHomeContract {

    interface IMainHomeModel {

        Observable<BaseBean<MainHomeBean>> getHomeData();

        Observable<BaseBean<List<HomePagePopupData>>> getHomePagePopup();

    }

    interface View extends BaseView {

        void getHomeDataSuces(MainHomeBean mainHomeBean);

        void getHomePagePopupSucess(List<HomePagePopupData> homePagePopupDatas);
    }
}
