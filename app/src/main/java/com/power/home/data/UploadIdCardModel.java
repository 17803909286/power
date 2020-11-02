package com.power.home.data;


import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.CertificationBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.data.bean.TokenBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.UploadIdCardContract;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by ZHP on 2017/11/6.
 */

public class UploadIdCardModel implements UploadIdCardContract.IUploadIdCardModel {

    private ApiService mApiService;

    public UploadIdCardModel(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Observable<BaseBean<CertificationBean>> uploadIdCard(File file0, File file1, String idCardNum, String realName) {

        Map<String, RequestBody> map = new HashMap<>();
        map.put(String.format("front\"; filename=\"" + file0.getName()), RequestBody.create(MediaType.parse("image/jpg"), file0));
        map.put(String.format("back\"; filename=\"" + file1.getName()), RequestBody.create(MediaType.parse("image/jpg"), file1));
        map.put("idCardNum", toRequestBody(idCardNum));
        map.put("realName", toRequestBody(realName));
        map.put("userId", toRequestBody(SharePreferencesUtils.getUserId()));
        return mApiService.uploadIdCard(map);

    }

    @Override
    public Observable<BaseBean<TokenBean>> getUploadToken() {
        return mApiService.getUploadToken();
    }

    @Override
    public Observable<BaseBean<EmptyBean>> getPersonVerifyInfoResult(String realName, String idCardNum, int userid, String frontUrl, String backUrl, String address, String begin, String end) {
        return mApiService.getPersonVerifyResult(realName,idCardNum,userid,frontUrl,backUrl,address,begin,end);
    }

    private RequestBody toRequestBody(String value) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);
        return requestBody;
    }
}
