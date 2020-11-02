package com.power.home.data;


import com.power.home.common.util.ParamUtil;
import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.NickNameContract;

import java.util.TreeMap;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class NickNameModel implements NickNameContract.INickNameModel {

    private ApiService mApiService;

    public NickNameModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<String>> saveInfo(String avatar, String nickName,String sex,String birthday,String cityCode,String provinceCode) {
        TreeMap<String, Object> map = ParamUtil.getParma();
        map.put("avatar", avatar);
        map.put("birthday", birthday);
        map.put("cityCode", cityCode);
        map.put("provinceCode", provinceCode);
        map.put("userSex", sex);
        map.put("userId", SharePreferencesUtils.getUserId());
        map.put("nickName", nickName);
        return mApiService.saveInfo(ParamUtil.getBody(map));
    }
}
