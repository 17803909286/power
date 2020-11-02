package com.power.home.presenter;

import com.power.home.data.bean.AreaBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.UploadContract;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public class UploadPresenter extends BasePresenter<UploadContract.IUploadModel, UploadContract.View> {

    @Inject
    public UploadPresenter(UploadContract.IUploadModel iUploadModel, UploadContract.View view) {
        super(iUploadModel, view);
    }

    public void uploadPhoto(File f) {
        mModel.uploadPhoto(f).compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressSubcriber<String>(mContext, mView) {
                    @Override
                    public void onNext(String url) {
                        if (hasView()) {
                            mView.uploadPhotoSucess(url);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (hasView()) {
                            mView.uploadPhotoSucess(null);
                        }
                    }
                });
    }

    public void getProvince() {
        mModel.getProvince().compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressSubcriber<List<AreaBean>>(mContext, mView, true) {
                    @Override
                    public void onNext(List<AreaBean> areaBeans) {
                        if (hasView()) {
                            mView.getProvinceSuccess(areaBeans);
                        }
                    }
                });
    }

}
