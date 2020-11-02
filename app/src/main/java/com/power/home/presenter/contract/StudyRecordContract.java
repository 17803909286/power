package com.power.home.presenter.contract;


import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.StudyRecordsBean;
import com.power.home.ui.widget.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface StudyRecordContract {

    interface IStudyRecordModel {
        Observable<BaseBean<List<StudyRecordsBean>>> getStudyProgress();

    }

    interface View extends BaseView {
        void getStudyProgressSuccess(List<StudyRecordsBean> studyRecordsBeans);
    }

}
