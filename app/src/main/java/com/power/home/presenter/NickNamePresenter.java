package com.power.home.presenter;

import com.power.home.data.Optional;
import com.power.home.data.bean.EmptyBean;
import com.power.home.net.rto_rxbuild.RxHttpReponseCompat;
import com.power.home.net.rto_subscriber.ProgressSubcriber;
import com.power.home.presenter.contract.NickNameContract;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/19.
 */

public class NickNamePresenter extends BasePresenter<NickNameContract.INickNameModel, NickNameContract.View> {

    @Inject
    public NickNamePresenter(NickNameContract.INickNameModel iNickNameModel, NickNameContract.View view) {
        super(iNickNameModel, view);
    }

    public void saveInfo(String avatar, String nickName,String sex,String birthday,String cityCode,String provinceCode) {
        mModel.saveInfo(avatar, nickName, sex, birthday, cityCode, provinceCode).compose(RxHttpReponseCompat.<String>compatResult2())
                .subscribe(new ProgressSubcriber<Optional<String>>(mContext, mView) {
                    @Override
                    public void onNext(Optional<String> emptyBeanOptional) {
                        if (hasView()) {
                            mView.modfiySuccess();
                        }
                    }
                });
    }
}
