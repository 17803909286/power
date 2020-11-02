package com.power.home.presenter;

import com.power.home.data.bean.CourseFreeDailyBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.CourseDailyContract;
import com.power.home.presenter.contract.CourseFreeContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class CourseDailyPresenter extends BasePresenter<CourseDailyContract.ICourseDailyModel, CourseDailyContract.View> {

    @Inject
    public CourseDailyPresenter(CourseDailyContract.ICourseDailyModel iCourseDailyModel, CourseDailyContract.View view) {
        super(iCourseDailyModel, view);
    }



    public void getDailyCourse(int pageNumber, int pageSize) {
        mModel.getDailyCourse(pageNumber, pageSize).compose(RxHttpReponseCompat.<List<CourseFreeDailyBean>>compatResult())
                .subscribe(new ProgressSubcriber<List<CourseFreeDailyBean>>(mContext, mView) {
                    @Override
                    public void onNext(List<CourseFreeDailyBean> courseFreeDailyBeans) {
                        if (hasView()) {
                            mView.getDailyCourseSuccess(courseFreeDailyBeans);
                        }
                    }
                });
    }

}
