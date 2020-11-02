package com.power.home.data;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.ChampGuideBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.TeacherContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class TeacherModel implements TeacherContract.ITeacherModel {

    private ApiService mApiService;

    public TeacherModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<ChampGuideBean>>> getChamp() {
        return mApiService.getChamp();
    }
}
