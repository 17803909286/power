package com.power.home.presenter.contract;

import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.CourseOfflineBean;
import com.power.home.ui.widget.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public interface CourseOfflineContract {

    interface ICourseOfflineModel {

        Observable<BaseBean<List<CourseOfflineBean>>> getOfflineCourse(int pageNumber, int pageSize);

    }

    interface View extends BaseView {

        void getOfflineCourseSucess(List<CourseOfflineBean> courseOfflineBeans);

    }
}
