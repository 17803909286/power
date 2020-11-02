package com.power.home.data;


import com.power.home.common.util.ParamUtil;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.CourseOfflineBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.EnrollContract;

import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class EnrollModel implements EnrollContract.IEnrollModel {

    private ApiService mApiService;

    public EnrollModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<EmptyBean>> enroll(String offlineActivityId) {
        TreeMap<String, Object> map = ParamUtil.getParma();
        map.put("mobileNumber", SharePreferencesUtils.getPhone());
        map.put("nickName", SharePreferencesUtils.getNickName());
        map.put("offlineActivityId", offlineActivityId);
        return mApiService.enroll(ParamUtil.getBody(map));
    }

    @Override
    public Observable<BaseBean<CourseOfflineBean>> getOfflineCourseDetails(String id) {
        return mApiService.getOfflineCourseDetails(id);
    }
}
