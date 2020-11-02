package com.power.home.data;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.CourseOfflineBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.CourseOfflineContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class CourseOfflineModel implements CourseOfflineContract.ICourseOfflineModel {

    private ApiService mApiService;

    public CourseOfflineModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<CourseOfflineBean>>> getOfflineCourse(int pageNumber, int pageSize) {
        return mApiService.getOfflineCourse(String.valueOf(pageNumber), String.valueOf(pageSize));
    }
}
