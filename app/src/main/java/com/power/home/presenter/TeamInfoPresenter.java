package com.power.home.presenter;

import com.power.home.data.bean.TeamInfoBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.TeamInfoContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class TeamInfoPresenter extends BasePresenter<TeamInfoContract.ITeamInfoModel, TeamInfoContract.View> {

    @Inject
    public TeamInfoPresenter(TeamInfoContract.ITeamInfoModel iTeamInfoModel, TeamInfoContract.View view) {
        super(iTeamInfoModel, view);
    }

    public void getTeamInfo() {
        mModel.getTeamInfo().compose(RxHttpReponseCompat.<TeamInfoBean>compatResult())
                .subscribe(new ProgressSubcriber<TeamInfoBean>(mContext, mView) {
                    @Override
                    public void onNext(TeamInfoBean teamInfoBean) {
                        if (hasView()) {
                            mView.getTeamInfoSuccess(teamInfoBean);
                        }
                    }
                });
    }
}
