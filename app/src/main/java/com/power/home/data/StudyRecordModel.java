package com.power.home.data;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.StudyRecordsBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.StudyRecordContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class StudyRecordModel implements StudyRecordContract.IStudyRecordModel {

    private ApiService mApiService;

    public StudyRecordModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<StudyRecordsBean>>> getStudyProgress() {
        return mApiService.getStudyProgress();
    }
}
