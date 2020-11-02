package com.power.home.data;

import com.power.home.data.bean.AllCourseBean;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.PayChannelBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.AllCourseContract;
import com.power.home.presenter.contract.ChoseChannelContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by XWL on 2020/3/5.
 * Description:
 */
public class ChoseChannelModel implements ChoseChannelContract.IChoseChannelModel {

    private ApiService mApiService;

    public ChoseChannelModel(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<List<PayChannelBean>>> getPayChannel(String payPlatform) {
        return mApiService.getPayChannel(payPlatform);
    }
}
