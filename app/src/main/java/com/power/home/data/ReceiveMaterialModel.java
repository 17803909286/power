package com.power.home.data;

import com.power.home.data.bean.AllCourseBean;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.ReceiveMaterialBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.AllCourseContract;
import com.power.home.presenter.contract.ReceiveMaterialContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by XWL on 2020/3/5.
 * Description:
 */
public class ReceiveMaterialModel implements ReceiveMaterialContract.IReceiveMaterialModel {

    private ApiService mApiService;

    public ReceiveMaterialModel(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<ReceiveMaterialBean>> receiveMaterial() {
        return mApiService.receiveMaterial();
    }
}
