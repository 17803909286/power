package com.power.home.presenter.contract;

import com.power.home.data.bean.AreaBean;
import com.power.home.data.bean.BaseBean;
import com.power.home.ui.widget.BaseView;

import java.io.File;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public interface UploadContract {

    interface IUploadModel {
        Observable<BaseBean<String>> uploadPhoto(File f);

        Observable<BaseBean<List<AreaBean>>> getProvince();

    }

    interface View extends BaseView {
        void uploadPhotoSucess(String url);

        void getProvinceSuccess(List<AreaBean> areaBeans);
    }
}
