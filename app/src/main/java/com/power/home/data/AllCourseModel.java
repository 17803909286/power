package com.power.home.data;

import com.power.home.data.bean.AllCourseBean;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.AllCourseContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by XWL on 2020/3/5.
 * Description:
 */
public class AllCourseModel implements AllCourseContract.IAllCourseModel {

    private ApiService mApiService;

    public AllCourseModel(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<List<AllCourseBean>>> findAllCourse(String type) {
        return mApiService.findAllCourse(type);
    }
}
