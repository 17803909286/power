package com.power.home.presenter;

import com.power.home.data.Optional;
import com.power.home.data.bean.FriendsBean;
import com.power.home.data.bean.GiftBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.InviteFriendContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class InviteFriendPresenter extends BasePresenter<InviteFriendContract.IInviteFriendModel, InviteFriendContract.View> {

    @Inject
    public InviteFriendPresenter(InviteFriendContract.IInviteFriendModel iInviteFriendModel, InviteFriendContract.View view) {
        super(iInviteFriendModel, view);
    }

    public void inviteFriends() {
        mModel.inviteFriends().compose(RxHttpReponseCompat.<FriendsBean>compatResult2())
                .subscribe(new ProgressSubcriber<Optional<FriendsBean>>(mContext, mView) {
                    @Override
                    public void onNext(Optional<FriendsBean> optional) {
                        if (hasView()) {
                            mView.inviteFriendsSuccess(optional.get());
                        }
                    }
                });
    }
}
