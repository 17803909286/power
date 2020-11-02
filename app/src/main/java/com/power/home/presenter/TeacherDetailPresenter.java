package com.power.home.presenter;

import com.power.home.data.bean.ChampDetailBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.TeacherDetailContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class TeacherDetailPresenter extends BasePresenter<TeacherDetailContract.ITeacherDetailModel, TeacherDetailContract.View> {

    @Inject
    public TeacherDetailPresenter(TeacherDetailContract.ITeacherDetailModel iTeacherDetailModel, TeacherDetailContract.View view) {
        super(iTeacherDetailModel, view);
    }

    public void getChampDetails(String id, int page, int size) {
        mModel.getChampDetails(id, page, size).compose(RxHttpReponseCompat.<ChampDetailBean>compatResult())
                .subscribe(new ProgressSubcriber<ChampDetailBean>(mContext, mView) {
                    @Override
                    public void onNext(ChampDetailBean champDetailBean) {
                        if (hasView()) {
                            mView.getChampDetailsSuccess(champDetailBean);
                        }
                    }
                });
    }

}
