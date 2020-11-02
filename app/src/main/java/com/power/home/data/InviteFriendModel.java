package com.power.home.data;


import com.power.home.common.util.SharePreferencesUtils;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.FriendsBean;
import com.power.home.data.bean.GiftBean;
import com.power.home.data.http.ApiService;
import com.power.home.presenter.contract.InviteFriendContract;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2017/11/6.
 */

public class InviteFriendModel implements InviteFriendContract.IInviteFriendModel {

    private ApiService mApiService;

    public InviteFriendModel(ApiService apiService) {
        this.mApiService = apiService;
    }


    @Override
    public Observable<BaseBean<FriendsBean>> inviteFriends() {
        return mApiService.inviteFriends(SharePreferencesUtils.getUserId());
    }
}
