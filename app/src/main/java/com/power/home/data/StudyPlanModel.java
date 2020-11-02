package com.power.home.data;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EMBAOnlineBean;
import com.power.home.data.bean.StudyPlanBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.EMBAOnlineContract;
import com.power.home.presenter.contract.StudyPlanContract;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class StudyPlanModel implements StudyPlanContract.IStudyPlanModel {

    private ApiService mApiService;

    public StudyPlanModel(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<StudyPlanBean>> getStudyPlan() {
        return mApiService.getStudyPlan();
    }
}
