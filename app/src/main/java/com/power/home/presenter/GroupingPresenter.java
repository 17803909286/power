package com.power.home.presenter;

import com.power.home.data.bean.BusinessGrowthBean;
import com.power.home.data.bean.EMBAOnlineBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.EMBAOnlineContract;
import com.power.home.presenter.contract.GroupingContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2018/12/27/0027.
 */

public class GroupingPresenter extends BasePresenter<GroupingContract.IGroupingModel, GroupingContract.View> {

    @Inject
    public GroupingPresenter(GroupingContract.IGroupingModel iGroupingModel, GroupingContract.View view) {
        super(iGroupingModel, view);
    }

    public void getGroupingData() {
        mModel.getGroupingData().compose(RxHttpReponseCompat.compatResult())
                .subscribe(new ProgressSubcriber<BusinessGrowthBean>(mContext, mView) {
                    @Override
                    public void onNext(BusinessGrowthBean businessGrowthBean) {
                        if (hasView()) {
                            mView.getGroupingSuces(businessGrowthBean);
                        }
                    }
                });
    }

}
