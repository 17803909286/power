package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.CourseFreeDailyBean;
import com.power.home.data.bean.UserInfoBean;
import com.power.home.ui.widget.BaseView;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Query;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface CourseFreeContract {

    interface ICourseFreeModel {
        Observable<BaseBean<List<CourseFreeDailyBean>>> getFreeCourse(int pageNumber, int pageSize, String type);
    }

    interface View extends BaseView {
        void getFreeCourseSuccess(List<CourseFreeDailyBean> courseFreeDailyBeans);
    }

}
