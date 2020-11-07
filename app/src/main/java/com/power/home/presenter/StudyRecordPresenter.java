package com.power.home.presenter;

import com.power.home.data.bean.StudyRecordsBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.StudyRecordContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class StudyRecordPresenter extends BasePresenter<StudyRecordContract.IStudyRecordModel, StudyRecordContract.View> {

    @Inject
    public StudyRecordPresenter(StudyRecordContract.IStudyRecordModel iStudyRecordModel, StudyRecordContract.View view) {
        super(iStudyRecordModel, view);
    }

    public void getStudyProgress() {
        mModel.getStudyProgress().compose(RxHttpReponseCompat.<List<StudyRecordsBean>>compatResult())
                .subscribe(new ProgressSubcriber<List<StudyRecordsBean>>(mContext, mView) {
                    @Override
                    public void onNext(List<StudyRecordsBean> studyRecordsBeans) {
                        if (hasView()) {
                            mView.getStudyProgressSuccess(studyRecordsBeans);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {


                    }
                });
    }
}
