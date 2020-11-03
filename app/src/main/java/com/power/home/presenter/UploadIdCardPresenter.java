package com.power.home.presenter;

import com.power.home.data.bean.CertificationBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.data.bean.TokenBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.UploadIdCardContract;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public class UploadIdCardPresenter extends BasePresenter<UploadIdCardContract.IUploadIdCardModel, UploadIdCardContract.View> {

    @Inject
    public UploadIdCardPresenter(UploadIdCardContract.IUploadIdCardModel iUploadIdCardModel, UploadIdCardContract.View view) {
        super(iUploadIdCardModel, view);
    }

    public void uploadIdCard(File file0, File file1, String idCardNum, String realName) {
        mModel.uploadIdCard(file0, file1, idCardNum, realName).compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressSubcriber<CertificationBean>(mContext, mView) {
                    @Override
                    public void onNext(CertificationBean certificationBean) {
                        if (hasView()) {
                            mView.uploadIdCardSuccess(certificationBean);
                        }
                    }
                });
    }

    public void getUploadToken(){
        mModel.getUploadToken().compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressSubcriber<TokenBean>(mContext,mView,true) {
                    @Override
                    public void onNext(@NonNull TokenBean tokenBean) {
                        if(hasView()){
                            mView.getUploadTokenBeanSuccess(tokenBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(hasView()){
                            mView.getUploadTokenBeanSuccess(null);
                        }
                    }
                });
    }

    public  void  personVerify(String realName,String idCardNum,int userid,String frontUrl,String backUrl,String address,String begin,String end){
        mModel.getPersonVerifyInfoResult(realName,idCardNum,userid,frontUrl,backUrl,address,begin,end).compose(RxHttpReponseCompat.compatResult())
        .subscribe(new ProgressSubcriber<CertificationBean>(mContext,mView,true) {
            @Override
            public void onNext(@NonNull CertificationBean certificationBean) {
                if(hasView()){
                    mView.getPersonVerifyInfoSuccess(certificationBean);
                }
            }

            @Override
            public void onError(Throwable e) {
                if(hasView()){
                    mView.getPersonVerifyInfoSuccess(null);
                }
            }
        });
    }


}
