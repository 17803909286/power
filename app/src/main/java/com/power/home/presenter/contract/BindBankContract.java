package com.power.home.presenter.contract;


import com.power.home.data.bean.BankInfoBean;
import com.power.home.data.bean.BaseBean;
import com.power.home.data.bean.EmptyBean;
import com.power.home.ui.widget.BaseView;

import io.reactivex.Observable;

/**
 * Created by ZHP on 2018/12/27/0027.
 */
public interface BindBankContract {

    interface IBindBankModel {
        Observable<BaseBean<BankInfoBean>> bindBankCardInfo(String accountName, String bankCardNum, String bankId, String bankName, String identityCardNum, String mobile, String verifyCode);
    }

    interface View extends BaseView {
        void bindBankCardInfoSuccess(BankInfoBean bankInfoBean);
    }

}
