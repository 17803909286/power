package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.CourseOfflineBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.ui.widget.BaseView;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface EnrollContract {

    interface IEnrollModel {
        Observable<BaseBean<EmptyBean>> enroll(String offlineActivityId);

        Observable<BaseBean<CourseOfflineBean>> getOfflineCourseDetails(String id);
    }

    interface View extends BaseView {
        void enrollSuccess();

        void getOfflineCourseDetailsSuccess(CourseOfflineBean courseOfflineBean);
    }

}
