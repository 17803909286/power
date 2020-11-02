package com.power.home.presenter;

import com.power.home.data.bean.StudyPlanBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.StudyPlanContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public class StudyPlanPresenter extends BasePresenter<StudyPlanContract.IStudyPlanModel, StudyPlanContract.View> {

    @Inject
    public StudyPlanPresenter(StudyPlanContract.IStudyPlanModel iStudyPlanModel, StudyPlanContract.View view) {
        super(iStudyPlanModel, view);
    }

    public void getStudyPlan() {
        mModel.getStudyPlan().compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressSubcriber<StudyPlanBean>(mContext, mView, true) {
                    @Override
                    public void onNext(StudyPlanBean studyPlanBean) {
                        if (hasView()) {
                            mView.getStudyPlanSuces(studyPlanBean);
                        }
                    }
                });
    }

}
