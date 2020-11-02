package com.power.home.presenter;

import com.power.home.data.bean.VipRecordBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.VipRecordContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class VipRecordPresenter extends BasePresenter<VipRecordContract.IVipRecordModel, VipRecordContract.View> {

    @Inject
    public VipRecordPresenter(VipRecordContract.IVipRecordModel iVipRecordModel, VipRecordContract.View view) {
        super(iVipRecordModel, view);
    }

    public void getVipRecord(int page, int size) {
        mModel.getVipRecord(page, size).compose(RxHttpReponseCompat.<List<VipRecordBean>>compatResult())
                .subscribe(new ProgressSubcriber<List<VipRecordBean>>(mContext, mView) {
                    @Override
                    public void onNext(List<VipRecordBean> vipRecordBeans) {
                        if (hasView()) {
                            mView.getVipRecordSuccess(vipRecordBeans);
                        }
                    }
                });
    }
}
