package com.power.home.data;


import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.InviteRecordBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.InviteRecordContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class InviteRecordModel implements InviteRecordContract.IInviteRecordModel {

    private ApiService mApiService;

    public InviteRecordModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<List<InviteRecordBean>>> getInviteRecord(int page, int size, String queryType) {
        return mApiService.getInviteRecord(SharePreferencesUtils.getUserId(), String.valueOf(page), String.valueOf(size), queryType);
    }
}
