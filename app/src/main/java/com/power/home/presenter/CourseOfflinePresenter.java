package com.power.home.presenter;

import com.power.home.data.bean.CourseOfflineBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.CourseOfflineContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public class CourseOfflinePresenter extends BasePresenter<CourseOfflineContract.ICourseOfflineModel, CourseOfflineContract.View> {

    @Inject
    public CourseOfflinePresenter(CourseOfflineContract.ICourseOfflineModel iCourseOfflineModel, CourseOfflineContract.View view) {
        super(iCourseOfflineModel, view);
    }

    public void getOfflineCourse(int pageNumber, int pageSize) {
        mModel.getOfflineCourse(pageNumber, pageSize).compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressSubcriber<List<CourseOfflineBean>>(mContext, mView) {
                    @Override
                    public void onNext(List<CourseOfflineBean> courseOfflineBeans) {
                        if (hasView()) {
                            mView.getOfflineCourseSucess(courseOfflineBeans);
                        }
                    }
                });
    }

}
