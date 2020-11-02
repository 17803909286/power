package com.power.home.data;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.CourseFreeDailyBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.CourseDailyContract;
import com.power.home.presenter.contract.CourseFreeContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class CourseDailyModel implements CourseDailyContract.ICourseDailyModel {

    private ApiService mApiService;

    public CourseDailyModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<CourseFreeDailyBean>>> getDailyCourse(int pageNumber, int pageSize) {
        return mApiService.getDailyCourse(String.valueOf(pageNumber),String.valueOf(pageSize));
    }
}
