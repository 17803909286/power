package com.power.home.data;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.CourseFreeDailyBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.CourseFreeContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class CourseFreeModel implements CourseFreeContract.ICourseFreeModel {

    private ApiService mApiService;

    public CourseFreeModel(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<List<CourseFreeDailyBean>>> getFreeCourse(int pageNumber, int pageSize, String type) {
        return mApiService.getFreeCourse(String.valueOf(pageNumber),String.valueOf(pageSize),type);
    }

}
