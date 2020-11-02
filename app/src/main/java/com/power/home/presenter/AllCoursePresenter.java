package com.power.home.presenter;

import com.power.home.data.bean.AllCourseBean;
import com.power.home.data.bean.MainHomeBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.AllCourseContract;
import com.power.home.presenter.contract.MainHomeContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public class AllCoursePresenter extends BasePresenter<AllCourseContract.IAllCourseModel, AllCourseContract.View> {

    @Inject
    public AllCoursePresenter(AllCourseContract.IAllCourseModel iAllCourseModel, AllCourseContract.View view) {
        super(iAllCourseModel, view);
    }
    //首页：COMMON，学霸课堂：K12
    public void findAllCourse(String type) {
        mModel.findAllCourse(type).compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressSubcriber<List<AllCourseBean>>(mContext, mView) {
                    @Override
                    public void onNext(List<AllCourseBean> allCourseBeans) {
                        if (hasView()) {
                            mView.findAllCourseSuces(allCourseBeans);
                        }
                    }
                });
    }

}
