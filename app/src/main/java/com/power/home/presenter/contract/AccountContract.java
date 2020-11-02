package com.power.home.presenter.contract;


import com.power.home.data.bean.BankInfoBean;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.GoShareBean;
import com.power.home.data.bean.UserAssetsBean;
import com.power.home.data.bean.WithdrawInfoBean;
import com.power.home.ui.widget.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface AccountContract {

    interface IAccountModel {
        Observable<BaseBean<UserAssetsBean>> getAppUserAssets();

        Observable<BaseBean<WithdrawInfoBean>> getWithdrawInfo();

        Observable<BaseBean<GoShareBean>> goShare();

        Observable<BaseBean<BankInfoBean>> getBankCardInfo();
    }

    interface View extends BaseView {
        void getAppUserAssetsSuccess(UserAssetsBean userAssetsBean);

        void getWithdrawInfoSuccess(WithdrawInfoBean withdrawInfoBean);

        void goShareSuccess(GoShareBean goShareBean);

        void getBankCardInfoSuccess(BankInfoBean bankInfoBean);

    }

}
