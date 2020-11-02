package com.power.home.presenter;

import com.power.home.data.bean.ChampGuideBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.TeacherContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class TeacherPresenter extends BasePresenter<TeacherContract.ITeacherModel, TeacherContract.View> {

    @Inject
    public TeacherPresenter(TeacherContract.ITeacherModel iTeacherModel, TeacherContract.View view) {
        super(iTeacherModel, view);
    }

    public void getChamp() {
        mModel.getChamp().compose(RxHttpReponseCompat.<List<ChampGuideBean>>compatResult())
                .subscribe(new ProgressSubcriber<List<ChampGuideBean>>(mContext, mView) {
                    @Override
                    public void onNext(List<ChampGuideBean> champGuideBeans) {
                        if (hasView()) {
                            mView.getChampSuccess(champGuideBeans);
                        }
                    }
                });
    }

}
