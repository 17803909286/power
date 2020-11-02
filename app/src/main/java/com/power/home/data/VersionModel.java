package com.power.home.data;


import com.power.home.R;
import com.power.home.common.Constant;
import com.power.home.common.util.DesUtils;
import com.power.home.common.util.ParamUtil;
import com.power.home.common.util.UIUtil;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.VersionBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.VersionContract;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class VersionModel implements VersionContract.IVersionModel {

    private ApiService mApiService;

    public VersionModel(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<VersionBean>> getVersion() {
        return mApiService.getVersion();
    }
}
