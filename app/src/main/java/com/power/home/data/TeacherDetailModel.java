package com.power.home.data;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.ChampDetailBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.TeacherDetailContract;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class TeacherDetailModel implements TeacherDetailContract.ITeacherDetailModel {

    private ApiService mApiService;

    public TeacherDetailModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<ChampDetailBean>> getChampDetails(String id, int page, int size) {
        return mApiService.getChampDetails(id, String.valueOf(page), String.valueOf(size));
    }
}
