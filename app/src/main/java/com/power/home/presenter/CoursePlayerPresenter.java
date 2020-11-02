package com.power.home.presenter;

import com.power.home.data.Optional;
import com.power.home.data.bean.CoursePlayerBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.CoursePlayerContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public class CoursePlayerPresenter extends BasePresenter<CoursePlayerContract.ICoursePlayerModel, CoursePlayerContract.View> {

    @Inject
    public CoursePlayerPresenter(CoursePlayerContract.ICoursePlayerModel iCoursePlayerModel, CoursePlayerContract.View view) {
        super(iCoursePlayerModel, view);
    }

    public void getCourseDetails(String type, String id) {
        mModel.getCourseDetails(type, id).compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressSubcriber<CoursePlayerBean>(mContext, mView) {
                    @Override
                    public void onNext(CoursePlayerBean coursePlayerBean) {
                        if (hasView()) {
                            mView.getCourseDetailsSuces(coursePlayerBean);
                        }
                    }
                });
    }

    public void setStudyProgress(String courseId, boolean isFinish, String progress) {
        mModel.setStudyProgress(courseId, isFinish, progress).compose(RxHttpReponseCompat.compatResult2())
                .subscribe(new ProgressSubcriber<Optional<EmptyBean>>(mContext, mView,true) {
                    @Override
                    public void onNext(Optional<EmptyBean> emptyBeanOptional) {
                        if (hasView()) {
                            mView.setStudyProgressSuccess();
                        }
                    }
                });
    }

}
