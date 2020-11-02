package com.power.home.data;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.CodeContract;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class CodeModel implements CodeContract.ICodeModel {

    private ApiService mApiService;

    public CodeModel(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<EmptyBean>> getCode(String mobile) {
        return mApiService.getCode(mobile);
    }
}
