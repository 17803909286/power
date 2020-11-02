package com.power.home.data;


import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.ConvertRecordOutBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.ConvertRecordContract;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class ConvertRecordModel implements ConvertRecordContract.IConvertRecordModel {

    private ApiService mApiService;

    public ConvertRecordModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<ConvertRecordOutBean>> getCodeMarketRecord(int page, int size) {
        return mApiService.getCodeMarketRecord(String.valueOf(page),String.valueOf(size),SharePreferencesUtils.getUserId());
    }
}
