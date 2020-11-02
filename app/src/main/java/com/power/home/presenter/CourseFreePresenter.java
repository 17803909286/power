package com.power.home.presenter;

import com.power.home.data.bean.CourseFreeDailyBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.CourseFreeContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class CourseFreePresenter extends BasePresenter<CourseFreeContract.ICourseFreeModel, CourseFreeContract.View> {

    @Inject
    public CourseFreePresenter(CourseFreeContract.ICourseFreeModel iCourseFreeModel, CourseFreeContract.View view) {
        super(iCourseFreeModel, view);
    }

    //首页：types：COMMON,COMMON_DAILY 学霸课堂：types：K12,K12_CHAMP
    public void getFreeCourse(int pageNumber, int pageSize, String type) {
        mModel.getFreeCourse(pageNumber, pageSize, type).compose(RxHttpReponseCompat.<List<CourseFreeDailyBean>>compatResult())
                .subscribe(new ProgressSubcriber<List<CourseFreeDailyBean>>(mContext, mView) {
                    @Override
                    public void onNext(List<CourseFreeDailyBean> courseFreeDailyBeans) {
                        if (hasView()) {
                            mView.getFreeCourseSuccess(courseFreeDailyBeans);
                        }
                    }
                });
    }


}
