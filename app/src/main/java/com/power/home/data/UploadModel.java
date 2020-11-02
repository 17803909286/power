package com.power.home.data;


import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.data.bean.AreaBean;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.UploadContract;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by ZHP on 2017/11/6.
 */

public class UploadModel implements UploadContract.IUploadModel {

    private ApiService mApiService;

    public UploadModel(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<String>> uploadPhoto(File f) {

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("application/octet-stream"), f);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", f.getName(), requestFile);
        return mApiService.uploadPhoto(SharePreferencesUtils.getUserId(), body);
    }

    @Override
    public Observable<BaseBean<List<AreaBean>>> getProvince() {
        return mApiService.getProvince();
    }
}
