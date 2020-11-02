package com.power.home.presenter;

import com.power.home.data.bean.HomePagePopupData;
import com.power.home.data.bean.MainHomeBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.MainHomeContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public class MainHomePresenter extends BasePresenter<MainHomeContract.IMainHomeModel, MainHomeContract.View> {

    @Inject
    public MainHomePresenter(MainHomeContract.IMainHomeModel iMainHomeModel, MainHomeContract.View view) {
        super(iMainHomeModel, view);
    }

    public void getHomeData() {
        mModel.getHomeData().compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressSubcriber<MainHomeBean>(mContext, mView, true) {
                    @Override
                    public void onNext(MainHomeBean mainHomeBean) {
                        if (hasView()) {
                            mView.getHomeDataSuces(mainHomeBean);
                        }
                    }
                });
    }

    public void getHomePagePopup() {
        mModel.getHomePagePopup().compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressSubcriber<List<HomePagePopupData>>(mContext, mView, true) {
                    @Override
                    public void onNext(List<HomePagePopupData> homePagePopupDatas) {
                        if (hasView()) {
                            mView.getHomePagePopupSucess(homePagePopupDatas);
                        }
                    }
                });
    }

}
