package com.power.home.data;


import com.power.home.common.util.ParamUtil;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.data.bean.BankInfoBean;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.BindBankContract;

import java.util.TreeMap;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * Created by ZHP on 2017/11/6.
 */

public class BindBankModel implements BindBankContract.IBindBankModel {

    private ApiService mApiService;

    public BindBankModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<BankInfoBean>> bindBankCardInfo(String accountName, String bankCardNum, String bankId, String bankName, String identityCardNum, String mobile, String verifyCode) {

        TreeMap<String, Object> map = new TreeMap<>();
        map.put("accountName", accountName);
        map.put("bankCardNum", bankCardNum);
        map.put("bankId", bankId);
        map.put("bankName", bankName);
        map.put("identityCardNum", identityCardNum);
        map.put("mobile", mobile);
        map.put("userId", SharePreferencesUtils.getUserId());
        map.put("verifyCode", verifyCode);
        return mApiService.bindBankCardInfo(ParamUtil.getBody(map));
    }
}
