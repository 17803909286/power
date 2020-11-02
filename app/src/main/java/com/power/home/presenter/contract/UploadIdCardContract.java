package com.power.home.presenter.contract;

import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.CertificationBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.data.bean.TokenBean;
import com.power.home.ui.widget.BaseView;

import java.io.File;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public interface UploadIdCardContract {

    interface IUploadIdCardModel {
        Observable<BaseBean<CertificationBean>> uploadIdCard(File file0, File file1, String idCardNum, String realName);
        Observable<BaseBean<TokenBean>> getUploadToken();
        Observable<BaseBean<EmptyBean>> getPersonVerifyInfoResult(String realName,String idCardNum,int userid,String frontUrl,String backUrl,String address,String begin,String end);

    }


    interface View extends BaseView {
        void uploadIdCardSuccess(CertificationBean certificationBean);
        void getUploadTokenBeanSuccess(TokenBean tokenBean);
        void getPersonVerifyInfoSuccess(EmptyBean emptyBean);
    }
}
