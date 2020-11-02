package com.power.home.data;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.CourseAlbumBean;
import com.power.home.data.bean.HomePagePopupData;
import com.power.home.data.bean.MainHomeBean;
import com.power.home.data.bean.UserSignatureBean;
import com.power.home.data.bean.WeekRecommendBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.MainHomeContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class MainHomeModel implements MainHomeContract.IMainHomeModel {

    private ApiService mApiService;

    public MainHomeModel(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<MainHomeBean>> getHomeData() {
        return mApiService.getHomeData();
    }

    @Override
    public Observable<BaseBean<List<HomePagePopupData>>> getHomePagePopup() {
        return mApiService.getHomePagePopup();
    }
}
