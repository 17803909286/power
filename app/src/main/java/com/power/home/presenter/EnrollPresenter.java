package com.power.home.presenter;

import com.power.home.data.Optional;
import com.power.home.data.bean.CourseOfflineBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.EnrollContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class EnrollPresenter extends BasePresenter<EnrollContract.IEnrollModel, EnrollContract.View> {

    @Inject
    public EnrollPresenter(EnrollContract.IEnrollModel iEnrollModel, EnrollContract.View view) {
        super(iEnrollModel, view);
    }

    public void enroll(String offlineActivityId) {
        mModel.enroll(offlineActivityId).compose(RxHttpReponseCompat.<EmptyBean>compatResult2())
                .subscribe(new ProgressSubcriber<Optional<EmptyBean>>(mContext, mView) {
                    @Override
                    public void onNext(Optional<EmptyBean> emptyBeanOptional) {
                        if (hasView()) {
                            mView.enrollSuccess();
                        }
                    }
                });
    }

    public void getOfflineCourseDetails(String id) {
        mModel.getOfflineCourseDetails(id).compose(RxHttpReponseCompat.<CourseOfflineBean>compatResult2())
                .subscribe(new ProgressSubcriber<Optional<CourseOfflineBean>>(mContext, mView) {
                    @Override
                    public void onNext(Optional<CourseOfflineBean> courseOfflineBeanOptional) {
                        if (hasView()) {
                            mView.getOfflineCourseDetailsSuccess(courseOfflineBeanOptional.get());
                        }
                    }
                });
    }
}
