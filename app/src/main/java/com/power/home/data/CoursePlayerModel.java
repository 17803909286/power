package com.power.home.data;


import com.power.home.common.util.ParamUtil;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.CoursePlayerBean;
import com.power.home.data.bean.EMBAOnlineBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.CoursePlayerContract;
import com.power.home.presenter.contract.EMBAOnlineContract;

import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class CoursePlayerModel implements CoursePlayerContract.ICoursePlayerModel {

    private ApiService mApiService;

    public CoursePlayerModel(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<CoursePlayerBean>> getCourseDetails(String type,String id) {
        return mApiService.getCourseDetails(type , id);
    }

    @Override
    public Observable<BaseBean<EmptyBean>> setStudyProgress(String courseId, boolean isFinish, String progress) {
        TreeMap<String, Object> map = ParamUtil.getParma();
        map.put("courseId", courseId);
        map.put("isFinish", isFinish);
        map.put("progress", progress);
        return mApiService.setStudyProgress(ParamUtil.getBody(map));
    }


}
