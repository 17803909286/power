package com.power.home.presenter.contract;

import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.CoursePlayerBean;
import com.power.home.data.bean.EMBAOnlineBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.ui.widget.BaseView;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public interface CoursePlayerContract {

    interface ICoursePlayerModel {

        Observable<BaseBean<CoursePlayerBean>> getCourseDetails(String type ,String id);
        Observable<BaseBean<EmptyBean>> setStudyProgress(String courseId, boolean isFinish , String progress);

    }

    interface View extends BaseView {

        void getCourseDetailsSuces(CoursePlayerBean coursePlayerBean);

        void setStudyProgressSuccess();

    }
}
