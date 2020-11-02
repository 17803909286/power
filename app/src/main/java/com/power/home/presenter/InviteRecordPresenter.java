package com.power.home.presenter;

import com.power.home.data.bean.InviteRecordBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.InviteRecordContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class InviteRecordPresenter extends BasePresenter<InviteRecordContract.IInviteRecordModel, InviteRecordContract.View> {

    @Inject
    public InviteRecordPresenter(InviteRecordContract.IInviteRecordModel iInviteRecordModel, InviteRecordContract.View view) {
        super(iInviteRecordModel, view);
    }

    public void getInviteRecord(int page, int size, String queryType) {
        mModel.getInviteRecord(page, size, queryType).compose(RxHttpReponseCompat.<List<InviteRecordBean>>compatResult())
                .subscribe(new ProgressSubcriber<List<InviteRecordBean>>(mContext, mView) {
                    @Override
                    public void onNext(List<InviteRecordBean> inviteRecordOutBeans) {
                        if (hasView()) {
                            mView.getInviteRecordSuccess(inviteRecordOutBeans);
                        }
                    }
                });
    }
}
