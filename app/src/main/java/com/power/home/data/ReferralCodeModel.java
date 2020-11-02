package com.power.home.data;


import com.power.home.common.util.ParamUtil;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.NickNameContract;
import com.power.home.presenter.contract.ReferralCodeContract;

import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class ReferralCodeModel implements ReferralCodeContract.IReferralCodeModel {

    private ApiService mApiService;

    public ReferralCodeModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<EmptyBean>> setUserInvitedCode(String code) {
        TreeMap<String, Object> map = ParamUtil.getParma();
        map.put("code", code);
        return mApiService.setUserInvitedCode(ParamUtil.getBody(map));
    }
}
