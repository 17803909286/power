package com.power.home.data;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.MainK12Bean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.MainK12Contract;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class MainK12Model implements MainK12Contract.IMainK12Model {

    private ApiService mApiService;

    public MainK12Model(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<MainK12Bean>> getK12Data() {
        return mApiService.getK12Data();
    }

}
