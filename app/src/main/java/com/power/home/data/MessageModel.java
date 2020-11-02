package com.power.home.data;


import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.MessageOutBean;
import com.power.home.data.bean.WithdrawOutBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.MessageContract;
import com.power.home.presenter.contract.WithdrawlRecordContract;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class MessageModel implements MessageContract.IMessageModel {

    private ApiService mApiService;

    public MessageModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<MessageOutBean>> getUserNotify(int page, int size, String source) {
        return mApiService.getSpreadNotifies(String.valueOf(page), String.valueOf(size), SharePreferencesUtils.getUserId());
    }
}
