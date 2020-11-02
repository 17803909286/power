package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.CourseFreeDailyBean;
import com.power.home.ui.widget.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface CourseDailyContract {

    interface ICourseDailyModel {
        Observable<BaseBean<List<CourseFreeDailyBean>>> getDailyCourse(int pageNumber, int pageSize);
    }

    interface View extends BaseView {
        void getDailyCourseSuccess(List<CourseFreeDailyBean> courseFreeDailyBeans);
    }

}
